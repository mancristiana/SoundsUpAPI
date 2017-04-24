package dk.kea.soundsup;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import dk.kea.soundsup.model.GoogleToken;
import dk.kea.soundsup.model.User;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mancr on 3/6/2017.
 */
@Path("/users")
public class UserService
{
    HttpTransport httpTransport = new NetHttpTransport();

    JsonFactory jsonFactory = new JacksonFactory();

    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
            .setAudience(Collections.singletonList("176022414732-multinga95b6se0j9024vogb8t24rvge.apps.googleusercontent.com"))
            .build();

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

    //This is a test method to see if I can fucking write a POST method
    @POST
    @Path("/getsomething")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getMessage(GoogleToken googleToken)
    {
        System.out.println(googleToken.getId_token());
    }

    @POST
    @Path("/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN})
    public Response getGoogleId(@PathParam("param") String idTokenString) throws GeneralSecurityException, IOException
    {
        System.out.println("inside post method . .");
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            // Use or store profile information
            System.out.println("The id is good :D");

        } else {
            System.out.println("Invalid ID token.");
        }

        return Response.status(201).entity(idTokenString).build();
    }
}
