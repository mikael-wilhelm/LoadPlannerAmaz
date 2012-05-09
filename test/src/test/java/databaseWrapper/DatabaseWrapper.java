package databaseWrapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWrapper {
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("SHARED_DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static void clearAllLoads() throws SQLException, URISyntaxException {
        Connection connection = null;
        connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM loads;");
        stmt.execute();
        connection.close();

    }
}
