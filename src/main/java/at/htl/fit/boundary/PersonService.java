package at.htl.fit.boundary;

import at.htl.fit.control.PersonRepository;
import at.htl.fit.entity.Person;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.Instant;

@Path("/person")
public class PersonService {

    @Inject
    PersonRepository personRepository;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/add")
    public Response addPerson(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("phone") String telephoneNo) throws Exception {
        System.out.println("Thank you for using quarkus");
        var timeStamp = Instant.now();
        var person = new Person(firstName, lastName, email, telephoneNo, timeStamp);

        this.personRepository.save(person);

        return Response.noContent().build();
    }
}
