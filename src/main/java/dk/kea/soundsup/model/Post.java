package dk.kea.soundsup.model;

/**
 * Created by mancr on 17-May-17.
 */
public class Post {
    private int postId;
    private int userId;
    private String trackId;
    private String description;

    public Post(int postId, int userId, String trackId, String description) {
        this.postId = postId;
        this.userId = userId;
        this.trackId = trackId;
        this.description = description;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
