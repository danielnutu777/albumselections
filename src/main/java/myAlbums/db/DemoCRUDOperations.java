package myAlbums.db;
import myAlbums.Album;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemoCRUDOperations {
    // 1. define connection params to db
    final static String URL = "jdbc:mysql://localhost:3306/Auto2_DanielNutu";
    final static String USERNAME = "root";
    final static String PASSWORD = "";

    public static void writeAlbums(Album album) throws SQLException, ClassNotFoundException {
        // 1. load the driver
        Class.forName("com.mysql.jdbc.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO albumstable (album, band, years, rating) VALUES (?,?,?,?)");
        pSt.setString(1, album.getName());
        pSt.setString(2, album.getBand());
        pSt.setInt(3, album.getYear());
        pSt.setInt(4, album.getRating());

        // 4. execute a prepared statement
        pSt.executeUpdate();

        // 5. close the objects
        pSt.close();
        conn.close();
    }

    public static List<Album> readAlbums() throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("com.mysql.jdbc.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        Statement st = conn.createStatement();

        // 4. execute a query
        ResultSet rs = st.executeQuery("SELECT album, band, years, rating, id FROM albumstable");

        // 5. iterate the result set and print the values
        List<Album> albums = new ArrayList<>();
        while (rs.next()) {
            String album = rs.getString("album");
            String band  = rs.getString("band");
            int year = rs.getInt("years");
            int rating = rs.getInt("rating");
            int id = rs.getInt("id");
            Album item = new Album(album, band, year, rating, id);
            albums.add(item);
        }
        // 6. close the objects
        rs.close();
        st.close();
        conn.close();

        return albums;
    }

    public static void deleteAlbum(Album album) throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("com.mysql.jdbc.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("DELETE FROM albumstable WHERE id=?");
        pSt.setInt(1, album.getId());

        // 4. execute a prepared statement
        pSt.executeUpdate();

        // 5. close the objects
        pSt.close();
        conn.close();
    }
}
