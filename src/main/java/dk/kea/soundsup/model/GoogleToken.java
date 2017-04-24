package dk.kea.soundsup.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GoogleToken
{
    @XmlElement String id_token;

    public String getId_token() {
        return id_token;
    }

    public void setId(String id_token) {
        this.id_token = id_token;
    }
}
