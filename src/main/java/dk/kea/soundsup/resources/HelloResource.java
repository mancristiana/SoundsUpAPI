package dk.kea.soundsup.resources;

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
public class HelloResource {

    /**
     * @api {get} /hello Get Hello World
     * @apiName GetHelloWorld
     * @apiGroup Hello
     * @apiVersion 1.0.0
     * @apiDescription This request returns a plain text hello message. This endpoint is useful for verifying that the API works.
     * @apiSuccessExample {text/plain} SuccessResponse
     * HTTP/1.1 200 OK
     * "Hello world!"
     *
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage(@Context HttpServletRequest request) {
        return "Hello world!";
    }

    /**
     * @api {get} /hello/country Get Geo IP
     * @apiName GetGeoIp
     * @apiGroup Hello
     * @apiVersion 1.0.0
     * @apiDescription This endpoint returns a GeoIp object containing the country name and code determined from the IP Address of the client.
     * @apiSuccessExample {json} SuccessResponse
     * HTTP/1.1 200 OK
     * {
     *      "returnCode": 1,
     *      "ip": "83.93.37.233",
     *      "returnCodeDetails": "Success",
     *      "countryName": "Denmark",
     *      "countryCode": "DNK"
     * }
     */
    @GET
    @Path("/country")
    @Produces(MediaType.APPLICATION_JSON)
    public GeoIP getCountry(@Context HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
//        String ipAddress = "83.93.37.233";

        GeoIPService geoIPService = new GeoIPService();
        GeoIPServiceSoap geoIPServiceSoap = geoIPService.getGeoIPServiceSoap();
        GeoIP geoIP = geoIPServiceSoap.getGeoIP(ipAddress);

        String countryName = geoIP.getCountryName();
        String countryCode = geoIP.getCountryCode();

        return geoIP;
    }
}