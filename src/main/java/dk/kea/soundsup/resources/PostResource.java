package dk.kea.soundsup.resources;

import dk.kea.soundsup.database.PostDAO;
import dk.kea.soundsup.database.TrackDAO;
import dk.kea.soundsup.entities.Post;
import dk.kea.soundsup.entities.ResponseMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

/**
 * Created by mancr on 17-May-17.
 */

@Path("/posts")
public class PostResource {

    /**
     * @api {get} /posts Get all posts
     * @apiName GetAllPosts
     * @apiGroup Posts
     * @apiVersion 1.0.0
     * @apiDescription This request returns a list of all posts in the database
     * @apiSuccess {Number} id Unique post identifier
     * @apiSuccess {String} description Content of the post
     * @apiSuccess {User} user object representing the post author. Please refer to the user endpoint for the structure of the user.
     * @apiSuccess {Track} track object shared within the post
     * @apiSuccessExample {json} SuccessResponse
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
     * @apiGroup Posts
     * @apiVersion 1.0.0
     * @apiDescription This request returns the post with the specified id
     * @apiSuccess {Number} id Unique post identifier
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

    /**
     * @api {post} /posts Create Post
     * @apiName CreatePost
     * @apiGroup Posts
     * @apiVersion 1.0.0
     *
     * @apiDescription This request creates a new post by using the json body provided. For consistency the json should include the parameters specified below. A postId field is generated automatically and returned in the response once the post has been saved.
     *
     * @apiParam {String} description Content of the post
     * @apiParam {User} user object representing the post author. Please refer to the user endpoint for the structure of the user.
     * @apiParam {Track} track object shared within the post
     *
     * @apiParamExample {json} Post Example
     * {
     *     "description": "This is a description example!",
     *     "user": {
     *         "id": 3
     *     },
     *     "track": {
     *         "spotifyId": "5anCkDvJ17aznvK5TED5uo",
     *         "name": "Hail to the King",
     *         "previewUrl": "https://p.scdn.co/mp3-preview/7a8932458d8ea00a425b629f43c4d44af0c9a029?cid=null",
     *         "album": {
     *             "id": "0ks45m1bsP2JsZpM5D2FFA",
     *             "name": "Hail to the King",
     *             "imageUrl": "https://i.scdn.co/image/d6fef16190f1516d0efe91c0d1bc6f28d8aa8865"
     *         },
     *         "artist": {
     *             "id": "0ks45m1bsP2JsZpM5D2FFA",
     *             "name": "Avenged Sevenfold"
     *         },
     *         "externalUrls": {
     *             "spotify": "https://open.spotify.com/album/0ks45m1bsP2JsZpM5D2FFA"
     *         }
     *     }
     * }
     *
     * @apiSuccess (Success 2xx) 201 Post Created
     *
     * @apiSuccessExample {json} Success Response
     *     HTTP/1.1 201 Created
     *     {
     *       "status": 201,
     *       "message": "Post with id = {id} was sucessfully inserted."
     *     }
     *
     * @apiError (Error 4xx) 400 Bad Request
     * @apiError (Error 5xx) 500 Internal Server Error
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createPost(Post post) {
        ResponseMessage responseMessage;

        int trackId = TrackDAO.insertTrack(post.getTrack());
        post.getTrack().setId(trackId);
        int postId = PostDAO.insertPost(post);
        if (trackId == -1 || postId == -1) {
            responseMessage = new ResponseMessage(400, "An error occured while trying to process your request");
        } else {
            responseMessage = new ResponseMessage(201, "Post with id = " + postId + " was sucessfully inserted.");
        }

        return Response
                .status(responseMessage.getStatus())
                .entity(responseMessage)
                .build();
    }

    /**
     * @api {put} /posts/{id} Update Post
     * @apiName UpdatePost
     * @apiGroup Posts
     * @apiVersion 1.0.0
     *
     * @apiDescription This request updates an existing post using the json body provided.
     * @apiParam {string} id The id of the Post
     * @apiParamExample {json} Edit Post Description Example:
     * {
     *      "description" : "Edited description"
     * }
     *
     * @apiSuccess (Success 2xx) 201 Post Edited
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 201 Edited
     * {
     *      "status" : 201,
     *      "message" : "Post was successfully edited"
     * }
     *
     * @apiError 404 Post Not Found
     * @apiError 400 Bad Request
     * @apiError (Error 5xx) 500 Internal Server Error
     *
     */
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response editPost(@PathParam("id") int id, Post post) {
        ResponseMessage responseMessage;

        post.setId(id);
        if(PostDAO.updatePost(post)) {
            responseMessage = new ResponseMessage(201, "Post was successfully edited");
        } else {
            responseMessage = new ResponseMessage(404, "Post not found");

        }
        return Response
                .status(responseMessage.getStatus())
                .entity(responseMessage)
                .build();
    }

    /**
     * @api {delete} /post/{id} Delete Post
     * @apiName DeletePost
     * @apiGroup Posts
     * @apiVersion 1.0.0
     *
     * @apiDescription This request deletes an existing post with the id specified in the request URL.
     * @apiParam {string} id The id of the Post
     *
     * @apiSuccess (Success 2xx) 202 Accepted
     *
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 202 Accepted
     * {
     *      "status" : 202,
     *      "message" : "Post was successfully deleted"
     * }
     *
     * @apiError 404 Post Not Found
     * @apiError 400 Bad Request
     * @apiError (Error 5xx) 500 Internal Server Error
     *
     */
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response editPost(@PathParam("id") int id) {
        ResponseMessage responseMessage;
        if(PostDAO.deletePost(id)) {
            responseMessage = new ResponseMessage(202, "Post was successfully deleted");
        } else {
            responseMessage = new ResponseMessage(404, "Post not found");

        }
        return Response
                .status(responseMessage.getStatus())
                .entity(responseMessage)
                .build();
    }
}
