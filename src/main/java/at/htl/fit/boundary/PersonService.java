package at.htl.fit.boundary;

import at.htl.fit.control.PersonRepository;
import at.htl.fit.entity.Person;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.Instant;

@Path("api/person")
public class PersonService {

    @Inject
    PersonRepository personRepository;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("phone") String telephoneNo,
            @Context UriInfo info
            ) {
        var timeStamp = Instant.now();
        var person = new Person(firstName, lastName, email, telephoneNo, timeStamp);

        person = this.personRepository.save(person);

        UriBuilder builder = info
                .getAbsolutePathBuilder()
                .path(Long.toString(person.getId()));
        return Response
                .created(builder.build())
                .build();
    }
}
