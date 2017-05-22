package dk.kea.soundsup.db;

import dk.kea.soundsup.model.*;

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
        int postId = -1;
        try (Connection connection = Database.getConnection()) {

            String sql = "INSERT INTO `post` (`description`, `user_id`, `track_id`) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getDescription());
            statement.setInt(2, post.getUser().getId());
            statement.setInt(3, post.getTrack().getId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                postId = rs.getInt(1);
            }

            Database.closeConnection(connection);
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }

        return postId;

    }

    /**
     * This method selects all posts from the database
     *
     * @return List of posts inside the database or and empty list if there are no existing entries.
     */
    public static List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();

        try (Connection connection = Database.getConnection()) {

            String sql = "SELECT post_id, description, " +
                    "user.user_id AS user_id, user.name AS user_name, email, " +
                    "track.track_id AS track_id, track_id_spotify, track.name AS track_name, " +
                    "preview_url, " +
                    "album_id, album_image_url, album_name, " +
                    "artist_id, artist_name, " +
                    "external_url_spotify " +
                    "FROM post, user, track " +
                    "WHERE post.track_id = track.track_id " +
                    "AND post.user_id = user.user_id " +
                    "ORDER BY date DESC";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Post post = PostDAO.getPostFromResultSet(resultSet);
                posts.add(post);
            }

            Database.closeConnection(connection);

        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }

        return posts;


    }

    /**
     * This method selects a post from the database corresponding to the given parameter
     *
     * @param postId the unique identifier of the post
     * @return a post object
     */
    public static Post getPostById(int postId) {
        Post post = null;
        try (Connection connection = Database.getConnection()) {

            String sql = "SELECT post_id, description, " +
                    "user.user_id AS user_id, user.name AS user_name, email, " +
                    "track.track_id AS track_id, track_id_spotify, track.name AS track_name, " +
                    "preview_url, " +
                    "album_id, album_image_url, album_name, " +
                    "artist_id, artist_name, " +
                    "external_url_spotify " +
                    "FROM post, user, track " +
                    "WHERE post.track_id = track.track_id " +
                    "AND `post_id` = ? " +
                    "AND post.user_id = user.user_id";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, postId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                post = PostDAO.getPostFromResultSet(resultSet);
            }

            Database.closeConnection(connection);

        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }

        return post;
    }

    /**
     * This method updated the description of a post using the post id passed through the post object
     * @param post Post object containing id and description of the post to be updated
     * @return boolean representing whether the entry was updated successfully or not
     */
    public static boolean updatePost(Post post) {
        boolean isUpdated = false;
        try (Connection connection = Database.getConnection()) {

            String sql = " UPDATE `post` SET `description`= ? WHERE `post_id`= ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getDescription());
            statement.setInt(2, post.getId());

            isUpdated = statement.executeUpdate() > 0;

            Database.closeConnection(connection);
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }

        return isUpdated;

    }

    /**
     * This method deletes a post with id passed through the post object
     * @param id int representing the unique identifier of the post to be updated
     * @return boolean representing whether the entry was deleted successfully or not
     */
    public static boolean deletePost(int id) {
        boolean isDeleted = false;
        try (Connection connection = Database.getConnection()) {

            String sql = "DELETE FROM `post` WHERE `post_id`= ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            isDeleted = statement.executeUpdate() > 0;

            Database.closeConnection(connection);
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }

        return isDeleted;

    }

    private static Post getPostFromResultSet(ResultSet resultSet) throws SQLException {
        // Author details
        int userId = resultSet.getInt("user_id");
        String userName = resultSet.getString("user_name");
        String userEmail = resultSet.getString("email");
        User user = new User(userId, userName, userEmail);

        // Album
        String albumId = resultSet.getString("album_id");
        String albumName = resultSet.getString("album_name");
        String albumImageUrl = resultSet.getString("album_image_url");
        Album album = new Album(albumId, albumName, albumImageUrl);

        // Artist
        String artistId = resultSet.getString("artist_id");
        String artistName = resultSet.getString("artist_name");
        Artist artist = new Artist(artistId, artistName);

        // Track details
        int trackId = resultSet.getInt("track_id");
        String trackIdSpotify = resultSet.getString("track_id_spotify");
        String trackName = resultSet.getString("track_name");
        String previewUrl = resultSet.getString("preview_url");
        ExternalUrls externalUrlSpotify = new ExternalUrls(resultSet.getString("external_url_spotify"));

        Track track = new Track(trackId, trackIdSpotify, trackName, previewUrl, album, artist, externalUrlSpotify);

        // Post details
        int postId = resultSet.getInt("post_id");
        String description = resultSet.getString("description");

        return new Post(postId, description, user, track);
    }
}
