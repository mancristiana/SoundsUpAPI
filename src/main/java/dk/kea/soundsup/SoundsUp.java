package dk.kea.soundsup;


import org.glassfish.embeddable.*;

import java.io.File;

public class SoundsUp
{
    public static void main(String[] args)
    {
        String port = System.getenv("PORT");
        GlassFishProperties gfProps = new GlassFishProperties();
        gfProps.setPort("http-listener", Integer.parseInt(port));
        try
        {
            GlassFish glassFish = GlassFishRuntime.bootstrap().newGlassFish(gfProps);
            glassFish.start();
            Deployer deployer = glassFish.getDeployer();
            File file = new File("SoundsUpApi.war");
            deployer.deploy(file);
        }catch(Exception exc)
        {
            exc.printStackTrace();
        }

    }
}
