package se.exjob.daoImplementations;

import se.exjob.databaseAccess.LoadDAO;
import se.exjob.exceptions.LoadNotFoundException;
import se.exjob.exceptions.ServerException;
import se.exjob.model.Load;
import se.exjob.model.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LoadDAOPostgres implements LoadDAO {

    @Override
    public Load insertLoad(String content, String harbor, String destination) throws ServerException {
        Load tempLoad = new Load(getValidId(), content, harbor, destination);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("INSERT INTO loads (id, content, harbor, destination) VALUES (?,?,?,?)");
            stmt.setInt(1, tempLoad.getId());
            stmt.setString(2, tempLoad.getContent());
            stmt.setString(3, tempLoad.getHarbor());
            stmt.setString(4, tempLoad.getDestination());
            stmt.execute();
        } catch (SQLException sql) {
           throw new ServerException(sql);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
        }
        return tempLoad;
    }

    private int getValidId() throws ServerException {
        int actualId;
        Random rnd = new Random();
        int primaryKeysSpan = 10000;
        actualId = rnd.nextInt(primaryKeysSpan);
        while (!testIfIdAvailable(actualId)) {
            actualId = rnd.nextInt(primaryKeysSpan);
        }

        return actualId;
    }

    private boolean testIfIdAvailable(int id) throws ServerException {
        boolean isOk = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT id FROM loads WHERE id=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            isOk = !rs.next();
        } catch (SQLException sql) {
            // todo check with Thomas
            //noinspection ThrowFromFinallyBlock
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
        }
        return isOk;
    }

    @Override
    public Load updateLoad(Load load) throws ServerException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("UPDATE loads SET content=?, harbor=?, destination=?, reserved=?,reservedBy=? WHERE id=?;");

            int content = 1;
            int harbor = 2;
            int destination = 3;
            int reserved = 4;
            int reservedBy = 5;
            int id = 6;

            ps.setString(content, load.getContent());
            ps.setString(harbor, load.getHarbor());
            ps.setString(destination, load.getDestination());
            ps.setBoolean(reserved, load.getReserved());
            ps.setString(reservedBy, load.getReservedBy().toString());
            ps.setInt(id, load.getId());
            ps.execute();
        } catch (SQLException sql) {
                                   // todo rethrow
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
        }
        return load;
    }

    @Override
    public Load getLoad(int loadID) throws LoadNotFoundException, ServerException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Load tempLoad = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT * FROM loads WHERE id = ?;");
            int id = 1;
            ps.setInt(id, loadID);
            rs = ps.executeQuery();
            tempLoad = internalGetLoad(rs);
        } catch (SQLException sql) {
            throw new ServerException(sql);
        } finally {
            // todo check with Thomas
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
        }
        return tempLoad;
    }

    private Load internalGetLoad(ResultSet rs) throws SQLException, LoadNotFoundException {
        Load tempLoad;
        if (rs.next()) {
            int id = Integer.parseInt(rs.getString("id"));
            String content = rs.getString("content");
            String harbor = rs.getString("harbor");
            String destination = rs.getString("destination");
            boolean reserved = rs.getBoolean("reserved");
            tempLoad = new Load(id, content, harbor, destination);
            tempLoad.setReserved(reserved);
        } else {
            throw new LoadNotFoundException();
        }
        return tempLoad;
    }

    @Override
    public List<Load> getReservedLoads(User user) throws LoadNotFoundException, ServerException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Load> tempLoads = new ArrayList<Load>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT id,reservedBy,userName FROM loads,loadUsers WHERE loads.reservedBy=loadUsers.userName AND reserved=TRUE AND reservedBy = ?;");
            ps.setString(1, user.getUserName());
            rs = ps.executeQuery();
            while (rs.next()) {
                tempLoads.add(getLoad(Integer.parseInt(rs.getString("id"))));
            }
        } catch (SQLException sql) {
            throw new ServerException(sql);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
        }
        return tempLoads;
    }

    @Override
    public List<Load> getNotReservedLoadsFilteredByHarbor(String s) throws ServerException, LoadNotFoundException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Load> tempLoads = new ArrayList<Load>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT id FROM loads WHERE reserved=FALSE;");
            rs = ps.executeQuery();
            while (rs.next()) {
                tempLoads.add(getLoad(Integer.parseInt(rs.getString("id"))));
            }
        } catch (SQLException sql) {
            throw new ServerException(sql);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // todo check with Thomas
                //noinspection ThrowFromFinallyBlock
                throw new ServerException(e);
            }
        }
        return tempLoads;
    }

    @Override
    public List<Load> getAllLoads() throws ServerException {
        return null;
    }

    private static Connection getConnection() throws ServerException {
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
            e.printStackTrace();
            throw new ServerException(e);
        }
    }
}
