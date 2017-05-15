package dk.kea.soundsup;

import com.sun.jersey.api.core.ResourceConfig;
import dk.kea.soundsup.utility.CORSResponseFilter;

import javax.ws.rs.core.Application;
import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SoundsUp extends org.glassfish.jersey.server.ResourceConfig
{
    /**
     * Register JAX-RS application components.
     */
    public SoundsUp()
    {
        packages("dk.kea.soundsup");
//        //register application resources
//        register(UserService.class);
//        register(HelloWorld.class);
//
//        //register filters
//        register(CORSResponseFilter.class);
    }
}
