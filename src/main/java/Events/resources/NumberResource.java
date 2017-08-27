package Events.resources;

import Events.db.NumberDAO;
import Events.db.Numbers;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.List;

/**
 * Created by babayega on 25/08/17.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class NumberResource {

  private NumberDAO numberDAO;

  public NumberResource(NumberDAO numberDAO){
      this.numberDAO = numberDAO;
  }

  @GET
  @Path("/numbers")
  @UnitOfWork
    public List<Numbers> findAllNumbers(){
      return numberDAO.findAllNumbers();
  }

  @GET
    @Path("/id/{id}")
    @UnitOfWork
    public Numbers findById(@PathParam("id")int id){
        return numberDAO.findById(id);
  }

  @GET
    @Path("/number/{number}")
    @UnitOfWork
    public List<Numbers> findByNumber(@PathParam("number") long number){
        return numberDAO.findByNumber(number);
  }

  @GET
  @Timed
    @Path("/normal")
    @UnitOfWork
    public long giveNumber(){
      long number = (long)Math.floor(Math.random()*9000000000L) + 1000000000L;
      while(numberDAO.findByNumber(number).size()>0){
          number = (long)Math.floor(Math.random()*9000000000L) + 1000000000L;
      }
      numberDAO.giveNumber(number);
      return number;
  }

  @GET
  @UnitOfWork
    @Path("/fancy/number/{number}")
    public long fancyNumber(@PathParam("number") long number){
        if(numberDAO.findByNumber(number).size()>0){
            number = (long)Math.floor(Math.random()*9000000000L) + 1000000000L;
        }
        numberDAO.giveNumber(number);
        return number;
  }

}
