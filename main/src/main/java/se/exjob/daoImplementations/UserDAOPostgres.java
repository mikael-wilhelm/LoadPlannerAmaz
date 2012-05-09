package se.exjob.daoImplementations;

import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.databaseAccess.UserDAO;
import se.exjob.exceptions.ServerException;
import se.exjob.model.Load;
import se.exjob.model.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOPostgres implements UserDAO{
    @Override
    public User authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException, ServerException {

        User tempUser =  getUser(userName);

        if(!tempUser.getPassword().equals(password))  {
            throw new PasswordException();
        }
        else{
            return tempUser;
        }
    }

    public User getUser(String userName) throws ServerException, NoSuchUserNameException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User tempUser;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT userName, password FROM loadusers WHERE userName = ?;");
            ps.setString(1,userName);
            rs = ps.executeQuery();
            if(rs.next()){
                tempUser = new User(rs.getString("username"),rs.getString("password"));
            }
            else{
                throw new NoSuchUserNameException();
            }
        }
        catch (SQLException sql){
            throw new ServerException(sql);
        } finally {
            try { if (rs != null){ rs.close();} } catch (SQLException e) {throw new ServerException(e);};
            try { if (ps != null) {ps.close();} } catch (SQLException e) {throw new ServerException(e);};
            try { if (conn != null) {conn.close();} } catch (SQLException e) {throw new ServerException(e);};
        }
        return tempUser;
    }

    @Override
    public void registerUser(String userName, String password) throws ServerException {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("INSERT INTO loadUsers VALUES(?,?)");
            ps.setString(1,userName);
            ps.setString(2,password);
            ps.execute();
        }
        catch (SQLException sql){
            throw new ServerException(sql);
        } finally {
            try { if (ps != null) {ps.close();} } catch (SQLException e) {throw new ServerException(e);};
            try { if (conn != null) {conn.close();} } catch (SQLException e) {throw new ServerException(e);};
        }
    }

    private static Connection getConnection() throws ServerException {
        URI dbUri;
        try {
            dbUri = new URI("postgres://postgres:Dataparm1@localhost/loadPlanner");
        } catch (URISyntaxException e) {
            throw new ServerException(e);
        }
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            return connection;
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
