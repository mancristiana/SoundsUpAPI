package dk.kea.soundsup;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by mancr on 3/6/2017.
 */
@Path("/hello")
public class HelloWorld {
    @GET

    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage(@Context HttpServletRequest request) {
        String addr = request.getRemoteAddr();
        String host = request.getRemoteHost();
        return "Hello world!" + addr + " " + host;
    }
}