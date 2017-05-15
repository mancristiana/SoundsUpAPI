package dk.kea.soundsup;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SoundsUp extends Application
{
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();

        // register resources and features
        classes.add(UserService.class);
        classes.add(HelloWorld.class);

        return classes;
    }
}
