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
