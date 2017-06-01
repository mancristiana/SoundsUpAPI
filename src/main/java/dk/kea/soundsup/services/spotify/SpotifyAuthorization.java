package dk.kea.soundsup.services.spotify;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;

import java.io.IOException;

/**
 * Created by mancr on 01-Jun-17.
 */
public class SpotifyAuthorization {
    final private static String clientId = "4bc6ac23937346d0b6f0324e2e1e4af4";
    final private static String clientSecret = "f9f05ad8f2744a68973a12e8d4580111";

    public static String getToken() throws IOException, WebApiException {
        Api api = Api.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
        ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();
        ClientCredentials response = request.get();
        return response.getAccessToken();
    }
}
