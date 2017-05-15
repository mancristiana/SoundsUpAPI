package dk.kea.soundsup.db;

import javafx.scene.chart.PieChart;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mancr on 15-May-17.
 */
public class Database {
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI jdbUri = new URI(System.getenv("JAWSDB_URL"));

        String username = jdbUri.getUserInfo().split(":")[0];
        String password = jdbUri.getUserInfo().split(":")[1];
        String port = String.valueOf(jdbUri.getPort());
        String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

        return DriverManager.getConnection(jdbUrl, username, password);
    }

    public static void main(String[] args) {
        try {
            Database.getConnection();
            System.out.println("Connected sucessfully");
        } catch (URISyntaxException exception) {
            System.out.println("URISyntaxException caught");
            exception.printStackTrace();
        } catch (SQLException exception) {
            System.out.println("SQLException caught");
            exception.printStackTrace();
        }
    }
}