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
        try (Connection connection = Database.getConnection()) {

            String sql = "INSERT INTO `post` (`description`, `user_id`, `track_id`) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getDescription());
            statement.setInt(2, post.getUser().getId());
            statement.setInt(3, post.getTrack().getId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

            Database.closeConnection(connection);
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }

        return -1;

    }

    public static List<Post> selectAllPosts() {
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
                    "AND post.user_id = user.user_id";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
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

                Post post = new Post(postId, description, user, track);
                posts.add(post);
            }

            Database.closeConnection(connection);

        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }

        return posts;


    }

}
