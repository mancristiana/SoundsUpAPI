package dk.kea.soundsup;

import dk.kea.soundsup.model.User;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mancr on 3/6/2017.
 */
@Path("/users")
public class UserService {
    public List<User> getAllUsersDB() {
        List<User> users = new ArrayList<User>();

        users.add(new User(0, "Cristiana", "Man", "cma@mail.com"));
        users.add(new User(1, "Adriel", "Premer", "adriel@site.com"));
        users.add(new User(2, "Batman", "Man", "batman@super.org"));
        users.add(new User(3, "Superman", "Man", "superman@super.org"));


        return users;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getAllUsers(@Context HttpHeaders header, @Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");

        List<User> users = getAllUsersDB();

        return users;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User getUserById(@PathParam("id") int id) {
        List<User> users = getAllUsersDB();

        return users.get(id);
    }
}
