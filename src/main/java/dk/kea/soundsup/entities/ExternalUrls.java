package dk.kea.soundsup.entities;

/**
 * Created by mancr on 17-May-17.
 */
public class ExternalUrls {
    private String spotify;

    public ExternalUrls() {

    }

    public ExternalUrls(String spotify) {
        this.spotify = spotify;
    }

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }
}
