package se.exjob.daoImplementations;

import se.exjob.exceptions.NoSuchUserNameException;
import se.exjob.exceptions.PasswordException;
import se.exjob.databaseAccess.UserDAO;
import se.exjob.exceptions.ServerException;
import se.exjob.model.User;
import se.exjob.model.UserImpl;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOPostgres implements UserDAO{
    private Logger logger = Logger.getLogger("se.exjob.LoadDAOPostgres");

    @Override
    public UserImpl authenticate(String userName, String password) throws NoSuchUserNameException, PasswordException, ServerException {

        UserImpl tempUser =  getUser(userName);

        if(!tempUser.getPassword().equals(password))  {
            throw new PasswordException();
        }
        else{
            return tempUser;
        }
    }

    public UserImpl getUser(String userName) throws ServerException, NoSuchUserNameException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserImpl tempUser;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT userName, password FROM loadusers WHERE userName = ?;");
            int userNameIdentifier = 1;
            ps.setString(userNameIdentifier,userName);
            rs = ps.executeQuery();
            if(rs.next()){
                tempUser = new UserImpl(rs.getString("username"),rs.getString("password"));
            }
            else{
                throw new NoSuchUserNameException();
            }
        }
        catch (SQLException sql){
            throw new ServerException(sql);
        } finally {
            try { if (rs != null){ rs.close();} } catch (SQLException e) {logger.log(Level.SEVERE, e.getMessage());}
            try { if (ps != null) {ps.close();} } catch (SQLException e) {logger.log(Level.SEVERE, e.getMessage());}
            try { if (conn != null) {conn.close();} } catch (SQLException e) {logger.log(Level.SEVERE, e.getMessage());}
        }
        return tempUser;
    }

    @Override
    public void registerUser(User user) throws ServerException {
            registerUser(user.getUserName(),user.getPassword());
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
            try { if (ps != null) {ps.close();} } catch (SQLException e) {logger.log(Level.SEVERE, e.getMessage());}
            try { if (conn != null) {conn.close();} } catch (SQLException e) {logger.log(Level.SEVERE, e.getMessage());}
        }
    }

    private static Connection getConnection() throws ServerException {
        return ConnectionGenerator.getConnection();
    }
}
