package dk.kea.soundsup.db;

import java.net.URISyntaxException;
import java.sql.*;

/**
 * Created by Andrei Atanasiu on 5/18/2017.
 */
public class UserDAO
{
     /**
     * This method inserts a new user into the database
     *
     * @param newEmail New email for the user to be inserted in the database
     * @param newName New name for the user to be inserted in the database
     * @return int representing the generated ID of the user. If an error occurs during the database insertion, the method returns -1
     */
    public static int insertUser(String newEmail, String newName)
    {
        try (Connection connection = Database.getConnection()) {

            String sql = "INSERT INTO `user` (`email`, `name`) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, newEmail);
            statement.setString(2, newName);

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException | URISyntaxException e)
        {
            if(e instanceof SQLException)
            {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " +
                        ((SQLException)e).getSQLState());

                System.err.println("Error Code: " +
                        ((SQLException)e).getErrorCode());

                System.err.println("Message: " + e.getMessage());
            }
            e.printStackTrace();
        }
        return -1;
    }
}
