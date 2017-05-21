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
     * @apiSuccess {String} description Content of the post
     * @apiSuccess {User} user object representing the post author. Please refer to the user enpoint for the structure of the user.
     * @apiSuccess {Track} track object shared within the post
     * @apiSuccessExample Success Response
     * HTTP/1.1 200 OK
     * [
     *      {
     *          "id": 0,
     *          "description": "Wow amazing song",
     *          "user": {
     *              "id": 3,
     *              "name": "Andrei",
     *              "email": "and@man.com"
     *          },
     *          "track": {
     *              "id": 1,
     *              "spotifyId": "5anCkDvJ17aznvK5TED5uo",
     *              "name": "Hail to the King",
     *              "previewUrl": "https://p.scdn.co/mp3-preview/7a8932458d8ea00a425b629f43c4d44af0c9a029?cid=null",
     *              "album": {
     *                  "id": "0ks45m1bsP2JsZpM5D2FFA",
     *                  "name": "Hail to the King",
     *                  "imageUrl": "https://i.scdn.co/image/d6fef16190f1516d0efe91c0d1bc6f28d8aa8865"
     *              },
     *              "artist": {
     *                  "id": "0ks45m1bsP2JsZpM5D2FFA",
     *                  "name": "Avenged Sevenfold"
     *              },
     *              "externalUrls": {
     *                  "spotify": "https://open.spotify.com/album/0ks45m1bsP2JsZpM5D2FFA"
     *              }
     *          }
     *     },
     *     ...
     * ]
     *
     * @apiError (Error 4xx) 400 Bad request
     * @apiError (Error 5xx) 500 Server has encountered a problem
     */

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Post> getAllPosts() {

        return PostDAO.getAllPosts();
    }

    /**
     * @api {get} /posts/{id} Get post
     * @apiName GetPost
     * @apiGroup Post
     * @apiVersion 0.0.1
     * @apiDescription This request returns the post with the specified id
     * @apiSuccess {Number} post_id Unique post identifier
     * @apiSuccess {String} description Content of the post
     * @apiSuccess {User} user object representing the post author. Please refer to the user enpoint for the structure of the user.
     * @apiSuccess {Track} track object shared within the post
     * @apiError (Error 4xx) 400 Bad request
     * @apiError (Error 4xx) 404 Post not found
     * @apiError (Error 5xx) 500 Server has encountered a problem
     */

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Post getPostById(@PathParam("id") int id) {
        Post post = PostDAO.getPostById(id);
        return post;
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
