package dk.kea.soundsup.resources;

import dk.kea.soundsup.entities.Post;

import org.glassfish.jersey.test.JerseyTest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * Created by mancr on 24-May-17.
 */

public class PostResourceTest extends JerseyTest {

    @Ignore
    @Test
    public void postsPathParamTest() {
        String response = target("posts/453").request().get(String.class);
//        Assert.assertTrue(453 == .equals(response));
    }

    @Ignore
    @Test
    public void createPostTest() {
        Post post = new Post();


//
//        Entity<Post> postEntity = Entity.entity(post, MediaType.APPLICATION_JSON_TYPE);
//        target("posts").request().post(postEntity); //Here we send POST request
//        Response response = target("users/find").queryParam("email", "user2@mail.com").request().get(); //Here we send GET request for retrieving results
//        Assert.assertEquals("user2@mail.com", response.readEntity(User.class).getEmail());
    }
}