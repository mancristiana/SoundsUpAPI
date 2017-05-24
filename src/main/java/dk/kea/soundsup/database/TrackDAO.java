package dk.kea.soundsup.database;

import dk.kea.soundsup.entities.*;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mancr on 20-May-17.
 */
public class TrackDAO {
    /**
     * This method inserts a new track into the database
     *
     * @param track Post to be inserted in the database
     * @return int representing the generated ID of the post. If an error occurs during the database insertion, the method returns -1
     */
    public static int insertTrack(Track track) {
        try (Connection connection = Database.getConnection()) {

            String sql = "INSERT INTO `track` (" +
                    "`track_id_spotify`" +
                    ", `name`" +
                    ", `preview_url`" +
                    ", `album_id`, `album_name`, `album_image_url`" +
                    ", `artist_id`, `artist_name`, `external_url_spotify`" +
                    ") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, track.getSpotifyId());
            statement.setString(2, track.getName());
            statement.setString(3, track.getPreviewUrl());

            statement.setString(4, track.getAlbum().getId());
            statement.setString(5, track.getAlbum().getName());
            statement.setString(6, track.getAlbum().getImageUrl());

            statement.setString(7, track.getArtist().getId());
            statement.setString(8, track.getArtist().getName());

            statement.setString(9, track.getExternalUrls().getSpotify());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e){
            // 1062 Duplicate, then retrieve existing track
            if (e.getErrorCode() == 1062) {
                Track existingTrack = TrackDAO.getTrackBySpotifyId(track.getSpotifyId());
                if(existingTrack != null) {
                    return existingTrack.getId();
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return -1;

    }


    public static Track getTrackBySpotifyId(String trackIdSpotify) {
        try (Connection connection = Database.getConnection()) {

            String sql = "SELECT * FROM `track` WHERE `track_id_spotify` = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, trackIdSpotify);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                // Album
                Album album = new Album(
                        resultSet.getString("album_id"),
                        resultSet.getString("album_name"),
                        resultSet.getString("album_image_url"));

                // Artist
                Artist artist = new Artist(
                        resultSet.getString("artist_id"),
                        resultSet.getString("artist_name"));

                // Track details
                ExternalUrls externalUrlSpotify = new ExternalUrls(resultSet.getString("external_url_spotify"));

                return new Track(
                        resultSet.getInt("track_id"),
                        trackIdSpotify,
                        resultSet.getString("name"),
                        resultSet.getString("preview_url"),
                        album,
                        artist,
                        externalUrlSpotify);
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }

        return null;

    }
}
