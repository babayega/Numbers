package Events.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by babayega on 24/08/17.
 */
public class NumberDAO extends AbstractDAO<Numbers> {
    public NumberDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public Numbers findById(int id){
        return get(id);
    }

    public List<Numbers> findAllNumbers(){
        return list(namedQuery("Find all numbers"));
    }

    public List<Numbers> findByNumber(long number){
        return list(
                namedQuery("Find by number")
                        .setParameter("number", number)
        );
    }

    public void giveNumber(long number){

        Numbers numbers = new Numbers(number);
        persist(numbers);
    }
}
