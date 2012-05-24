package se.exjob.daoImplementations;


import se.exjob.exceptions.ServerException;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionGenerator {
    public static Connection getConnection() throws ServerException {
        URI dbUri;

        try {
            dbUri = new URI(System.getenv("AMAZON_DB"));
        } catch (URISyntaxException e) {
            throw new ServerException(e);
        }
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);

            return connection;
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }}
