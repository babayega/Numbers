package Events.resources;

import Events.api.*;
import Events.api.Event;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.List;

/**
 * Created by babayega on 23/08/17.
 */

@Path("events")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    @GET
    public List<Event> allEvents(){
        long number = (long)Math.floor(Math.random()*9000000000L) + 1000000000L;
        Event e = new Event();
        e.setNumber(number);

        return Collections.singletonList(e);
    }
}
