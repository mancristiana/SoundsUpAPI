package dk.kea.soundsup.providers;

import dk.kea.soundsup.entities.ResponseMessage;

import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by mancr on 21-May-17.
 */
@Provider
@Produces({MediaType.APPLICATION_JSON})
public class NotFoundFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        Object entity = response.getEntity();
        if (entity == null) {
            response.setStatus(404);
            response.setEntity(new ResponseMessage(404, "Item not found"));
        }
    }
}

