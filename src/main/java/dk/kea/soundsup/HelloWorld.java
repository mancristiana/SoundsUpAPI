package dk.kea.soundsup;

import dk.kea.soundsup.services.geoipservice.GeoIP;
import dk.kea.soundsup.services.geoipservice.GeoIPService;
import dk.kea.soundsup.services.geoipservice.GeoIPServiceSoap;

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
//        String ipAddress = request.getRemoteAddr();
        String ipAddress = "83.93.37.233";

        GeoIPService geoIPService = new GeoIPService();
        GeoIPServiceSoap geoIPServiceSoap = geoIPService.getGeoIPServiceSoap();
        GeoIP geoIP = geoIPServiceSoap.getGeoIP(ipAddress);

        String countryName = geoIP.getCountryName();
        String countryCode = geoIP.getCountryCode();

        return "Hello world!" + countryName + " " + countryCode;
    }
}