package dk.kea.soundsup.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dk.kea.soundsup.entities.User;

@Path("/users")
public class UserResource {

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
    public List<User> getAllUsers() {
        return getAllUsersDB();
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

    public List<User> getAllUsersDB() {
        List<User> users = new ArrayList<User>();

        users.add(new User(0, "Cristiana Man", "cma@mail.com"));
        users.add(new User(1, "Adriel Premer", "adriel@site.com"));
        users.add(new User(2, "Batman Man", "batman@super.org"));
        users.add(new User(3, "Superman Man", "superman@super.org"));


        return users;
    }


}