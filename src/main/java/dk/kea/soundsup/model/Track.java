package dk.kea.soundsup.model;

/**
 * Created by mancr on 17-May-17.
 */
public class Track {
    private int id;
    private String spotifyId;
    private String name;

    private String previewUrl;

    private Album album;
    private Artist artist;

    private ExternalUrls externalUrls;

    public Track() {

    }

    public Track(int id, String spotifyId, String name, String previewUrl, Album album, Artist artist, ExternalUrls externalUrls) {
        this.id = id;
        this.spotifyId = spotifyId;
        this.name = name;
        this.previewUrl = previewUrl;
        this.album = album;
        this.artist = artist;
        this.externalUrls = externalUrls;
    }

    public Track(String spotifyId, String name, String previewUrl, Album album, Artist artist, ExternalUrls externalUrls) {
        this.spotifyId = spotifyId;
        this.name = name;
        this.previewUrl = previewUrl;
        this.album = album;
        this.artist = artist;
        this.externalUrls = externalUrls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
    }
}
