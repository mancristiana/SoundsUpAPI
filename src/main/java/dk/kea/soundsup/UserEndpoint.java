package dk.kea.soundsup;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import dk.kea.soundsup.model.GoogleToken;
import dk.kea.soundsup.model.User;

@Path("/users")
public class UserEndpoint
{
    HttpTransport httpTransport = new NetHttpTransport();

    JsonFactory jsonFactory = new JacksonFactory();

    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
            .setAudience(Collections.singletonList("176022414732-multinga95b6se0j9024vogb8t24rvge.apps.googleusercontent.com"))
            .build();

    /**
     * @api {get} /users Get all users
     * @apiName GetAllUsers
     * @apiGroup User
     * @apiVersion 1.0.0
     * @apiDescription This request returns a list of all users in the database
     * @apiSuccess {Number} id Unique user id generated on sign up.
     * @apiSuccess {String} name First and last name of the user.
     * @apiSuccess {String} email The users email used for sign up.
     * @apiSuccessExample Success-Response:
     * {
     *      "id": 0,
     *      "name": "Cristiana Man",
     *      "email": "cma@mail.com"
     * },
     * {
     *      "id": 1,
     *      "name": "Andrei Atanasiu",
     *      "email": "aa@mail.dk"
     * }
     * @apiErrorExample Error-Response:
     * HTTP/1.1 400 Bad Request
     * {
     *      "error": "User table not found"
     * }
     */

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getAllUsersDB() {
        List<User> users = new ArrayList<User>();

        users.add(new User(0, "Cristiana Man", "cma@mail.com"));
        users.add(new User(1, "Adriel Premer", "adriel@site.com"));
        users.add(new User(2, "Batman Man", "batman@super.org"));
        users.add(new User(3, "Superman Man", "superman@super.org"));


        return users;
    }

    /**
     * @api {get} /users/{id} Get user
     * @apiName getUserById
     * @apiGroup User
     * @apiVersion 1.0.0
     * @apiParam {Number} id The unique ID of the user.
     * @apiSuccess {Number} id Unique user id generated on sign up.
     * @apiSuccess {String} name First and last name of the user.
     * @apiSuccess {String} email The users email used for sign up.
     * @apiSuccessExample Success-Response:
     * {
     *      "id": 3,
     *      "name": "Bat Man",
     *      "email": "batman@super.org";
     * }
     * @apiErrorExample Error-Response:
     * HTTP/1.1 400 Bad Request
     * {
     *      "error": "User not found"
     * }
     */

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User getUserById(@PathParam("id") int id) {
        List<User> users = getAllUsersDB();

        return users.get(id);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGoogleId(GoogleToken googleToken) throws GeneralSecurityException, IOException
    {
        String message = "";
        int status;
        System.out.println("inside post method . .");
        GoogleIdToken idToken = verifier.verify(googleToken.getIdToken());
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

            status = 201;
            message = "Username" + name;

        } else {
            System.out.println("Invalid ID token.");
            status = 406;
            message = "Invalid ID token.";
        }

        return Response
                .status(status)
                .entity(googleToken)
                .build();
    }
}