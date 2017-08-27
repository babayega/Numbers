package Events;

import Events.db.NumberDAO;
import Events.db.Numbers;
import Events.resources.EventResource;
import Events.resources.NumberResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EventsApplication extends Application<EventsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new EventsApplication().run(args);
    }

    private final HibernateBundle<EventsConfiguration> hibernateBundle =
            new HibernateBundle<EventsConfiguration>(Numbers.class) {
                @Override
                public PooledDataSourceFactory getDataSourceFactory(EventsConfiguration eventsConfiguration) {
                    return eventsConfiguration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "Events";
    }

    @Override
    public void initialize(final Bootstrap<EventsConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final EventsConfiguration configuration,
                    final Environment environment) throws UnknownHostException {
        // TODO: implement application

        DateFormat eventDateFormat = new SimpleDateFormat(configuration.getDateFormat());
        environment.getObjectMapper().setDateFormat(eventDateFormat);

        EventResource eventResource = new EventResource();
        environment.jersey().register(eventResource);

        final NumberDAO numberDAO = new NumberDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new NumberResource(numberDAO));
    }

}
