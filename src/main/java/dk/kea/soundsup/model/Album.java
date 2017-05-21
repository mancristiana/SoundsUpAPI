package dk.kea.soundsup.model;

import java.util.List;

/**
 * Created by mancr on 17-May-17.
 */
public class Album {
    private String id;
    private String name;
    private String imageUrl;

    public Album() {

    }

    public Album(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
