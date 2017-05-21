package dk.kea.soundsup;

import dk.kea.soundsup.db.PostDAO;
import dk.kea.soundsup.db.TrackDAO;
import dk.kea.soundsup.model.Post;
import dk.kea.soundsup.model.ResponseMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

/**
 * Created by mancr on 17-May-17.
 */

@Path("/posts")
public class PostEndpoint {

    /**
     * @api {get} /posts Get all posts
     * @apiName GetAllPosts
     * @apiGroup Post
     * @apiVersion 0.0.1
     * @apiDescription This request returns a list of all posts in the database
     * @apiSuccess {Number} post_id Unique post identifier
     * @apiSuccess {Number} user_id Unique user identifier generated on sign up.
     * @apiSuccess {String} track_id Unique identifier for the track corresponding to Spotify API Database.
     * @apiSuccess {String} description Content of the post
     * @apiSuccessExample Success Response
     * HTTP/1.1 200 OK
     * [
     * {
     * "postId": 0,
     * "userId": 23,
     * "trackId": "2eOn7OyFJ8ygzBrWGirpsB",
     * "description": "Some song"
     * },
     * {
     * "postId": 1,
     * "userId": 32,
     * "trackId": "1e2GBOtPyLu7iuNm4EvFKG",
     * "description": "Another description"
     * },
     * {
     * "postId": 2,
     * "userId": 11,
     * "trackId": "1zRFuPnqjBckEoOFliJyaI",
     * "description": "Such wow"
     * },
     * {
     * "postId": 3,
     * "userId": 11,
     * "trackId": "27HNh1cyB39ERqdpSjM2i1",
     * "description": "Amazing"
     * }
     * ]
     * @apiError (Error 4xx) 400 Bad request
     * @apiError (Error 5xx) 500 Server has encountered a problem
     */


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Post> getAllPosts() {

        return PostDAO.selectAllPosts();
    }

    /**
     * @api {get} /posts/{id} Get post
     * @apiName GetPost
     * @apiGroup Post
     * @apiVersion 0.0.1
     * @apiParam {Number} id Unique post identifier
     * @apiSuccess {Number} post_id Unique post identifier
     * @apiSuccess {Number} user_id Unique user identifier generated on sign up.
     * @apiSuccess {String} track_id Unique identifier for the track corresponding to Spotify API Database.
     * @apiSuccess {String} description Content of the post
     * @apiSuccessExample Success-Response:
     * {
     * "post_id": 3,
     * "user_id": 1,
     * "track_id": "someIdb436427463mnj4n6k42l",
     * "description": "OMG This song is amazing!"
     * }
     * @apiError (Error 4xx) 400 Bad request
     * @apiError (Error 4xx) 404 Post not found
     * @apiError (Error 5xx) 500 Server has encountered a problem
     */

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Post getPostById(@PathParam("id") int id) {
        List<Post> posts = getAllPosts();
        return posts.get(id); // TODO CORRECT IMPLEMENTATION
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createPost(Post post) {
        ResponseMessage responseMessage = new ResponseMessage();

        int trackId = TrackDAO.insertTrack(post.getTrack());
        post.getTrack().setId(trackId);
        int postId = PostDAO.insertPost(post);
        if (trackId == -1 || postId == -1) {
            responseMessage.setStatus(400);
            responseMessage.setMessage("An error occured while trying to process your request");
        } else {
            responseMessage.setStatus(200);
            responseMessage.setMessage("Post with id = " + postId + " was sucessfully inserted.");
        }


        return Response
                .status(responseMessage.getStatus())
                .entity(responseMessage)
                .build();
    }

}
