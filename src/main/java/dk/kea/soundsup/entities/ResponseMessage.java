package dk.kea.soundsup.entities;

/**
 * Created by mancr on 21-May-17.
 */
public class ResponseMessage {
    private int status;
    private String message;

    public ResponseMessage() {

    }

    public ResponseMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
