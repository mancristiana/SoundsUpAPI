package dk.kea.soundsup.entities;

/**
 * Created by mancr on 17-May-17.
 */
public class Artist {
    private String id;
    private String name;

    public Artist() {

    }

    public Artist(String id, String name) {
        this.id = id;
        this.name = name;
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
}
