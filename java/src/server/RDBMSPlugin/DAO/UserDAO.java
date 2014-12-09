package server.RDBMSPlugin.DAO;

import server.persistence.UserDAOInterface;
import server.persistence.UserDTO;

import java.util.ArrayList;
import java.sql.*;


public class UserDAO implements UserDAOInterface {

    private Connection connection;
    private GenericDAO genericDAO;

    public UserDAO(Connection connection) {
        this.connection = connection;
        this.genericDAO = new GenericDAO(this.connection, "users", "username");
    }

    public boolean saveUser(String user_blob, String username) {
        return this.genericDAO.save(user_blob, username);
    }

    public ArrayList<String> getAllUsers() {
        return this.genericDAO.getAll(3);
    }

    public String getUser(String username) {
        return this.genericDAO.getByPrimaryKey(username, 3);
    }

    public boolean updateUser(String username, String data) {
        return this.genericDAO.update(data, username);
    }

    public boolean deleteUser(String username) {
        return this.genericDAO.delete(username);
    }
}
