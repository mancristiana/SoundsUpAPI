package dk.kea.soundsup.model;

/**
 * Created by mancr on 17-May-17.
 */
public class Post {
    private int id;
    private String description;
    private User user;
    private Track track;

    public Post(int id, String description, User user, Track track) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.track = track;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
