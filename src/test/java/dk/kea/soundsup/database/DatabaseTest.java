package dk.kea.soundsup.database;

/**
 * Created by mancr on 17-May-17.
 */

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URISyntaxException;
import java.sql.SQLException;

public class DatabaseTest {
    private static Database database;

    @BeforeClass
    public static void initDatabase() {
        database = new Database();
    }

    @Before
    public void beforeEachTest() {
        System.out.println("Testing database connection");
    }

    @After
    public void afterEachTest() {
        System.out.println("Finished testing database connection");
    }

    @Test
    public void testConnection() {
        String result;

        try {
            Database.getConnection();
            result = "Connected sucessfully";
        } catch (URISyntaxException exception) {
            result = "URISyntaxException caught";
            exception.printStackTrace();
        } catch (SQLException exception) {
            result = "SQLException caught";
            exception.printStackTrace();
        }

        assertEquals("Connected sucessfully", result);
    }
}