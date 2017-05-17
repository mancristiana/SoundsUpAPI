package dk.kea.soundsup.db;

import dk.kea.soundsup.model.Post;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mancr on 17-May-17.
 */
public class PostDAO {

    /**
     * This method inserts a new post into the database
     *
     * @param post Post to be inserted in the database
     * @return int representing the generated ID of the post. If an error occurs during the database insertion, the method returns -1
     */
    public static int insertPost(Post post) {
        try (Connection connection = Database.getConnection()) {

            String sql = "INSERT INTO `post` (`description`, `user_id`, `track_id`) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getDescription());
            statement.setInt(2, post.getUserId());
            statement.setString(3, post.getTrackId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }

        return -1;

    }

    public static List<Post> selectAllPosts() {
        List<Post> posts = new ArrayList<>();

        try (Connection connection = Database.getConnection()) {

            String sql = "SELECT * FROM `post`";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String description = resultSet.getString("description");
                int postId = resultSet.getInt("post_id");
                int userId = resultSet.getInt("user_id");
                String trackId = resultSet.getString("track_id");

                Post post = new Post(postId, userId, trackId, description);
                posts.add(post);
            }

        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }

        return posts;


    }

}
