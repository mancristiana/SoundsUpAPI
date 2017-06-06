package dk.kea.soundsup.resources;

import dk.kea.soundsup.database.PostDAO;
import dk.kea.soundsup.entities.Post;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;


/**
 * Created by mancr on 24-May-17.
 */

public class PostResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        return new ResourceConfig(PostResource.class);
    }

    @Ignore
    @Test
    public void postsPathParamTest() {
        String response = target("posts/453").request().get(String.class);
//        Assert.assertTrue(453 == .equals(response));
    }

    @Test
    public void getAllPostsTest()
    {
        PostResource mockPostResource = mock(PostResource.class);
        assertNotNull(mockPostResource.getAllPosts());
        //verify(mockPostResource, times(1)).getAllPosts();
    }

    @Test
    public void getPostByIdTest()
    {
        PostResource mockPostResource = mock(PostResource.class);
        assertNull(mockPostResource.getPostById(1));
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