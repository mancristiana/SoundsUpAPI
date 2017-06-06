package dk.kea.soundsup.resources;

import com.wrapper.spotify.exceptions.WebApiException;
import dk.kea.soundsup.entities.ResponseMessage;
import dk.kea.soundsup.entities.Token;
import dk.kea.soundsup.services.spotify.SpotifyAuthorization;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/spotify")
public class SpotifyResource {

    /**
     * @api {get} /spotify/token Get Spotify access token
     * @apiName GetSpotifyToken
     * @apiGroup Spotify
     * @apiVersion 1.0.0
     * @apiDescription This request returns a token needed during the client credentials flow to access resources from the Spotify Web API.
     * @apiSuccess {String} idToken token allowing you to make requests to the Spotify Web API endpoints that do not require user authorization. For example, Get a track endpoint.
     * @apiSuccessExample {json} SuccessResponse
     * HTTP/1.1 200 OK
     * {
     *      "idToken": "BQB4eNFgjk2oxbYdNz0gMd58eMRw7k5wddbn5dA2oXt570uzVf3oK1ozrCYyB_kVkZynJxqoB-VHIdLbqkIz_w"
     * }
     *
     * @apiError (Error 4xx) 400 Bad request
     * @apiError (Error 5xx) 500 Server has encountered a problem
     */

    @GET
    @Path("/token")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getToken() {
        Response response;

        try {
            String accessToken = SpotifyAuthorization.getToken();
            Token token = new Token();
            token.setIdToken(accessToken);
            response = Response
                    .status(200)
                    .entity(token)
                    .build();
        } catch (WebApiException | IOException e) {
            e.printStackTrace();
            ResponseMessage responseMessage = new ResponseMessage(403, "Something went wrong");
            response = Response
                    .status(responseMessage.getStatus())
                    .entity(responseMessage)
                    .build();
        }

        return response;
    }
}
