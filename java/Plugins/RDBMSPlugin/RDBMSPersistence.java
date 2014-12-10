package Plugins.RDBMSPlugin;

import Plugins.RDBMSPlugin.DAO.CommandDAO;
import Plugins.RDBMSPlugin.DAO.GameDAO;
import Plugins.RDBMSPlugin.DAO.UserDAO;
import Plugins.Interfaces.CommandDAOInterface;
import Plugins.Interfaces.GameDAOInterface;
import Plugins.Interfaces.PersistenceInterface;
import Plugins.Interfaces.UserDAOInterface;

import java.sql.*;

/**
 * Created by christopherbelyeu on 12/5/14.
 */
public class RDBMSPersistence implements PersistenceInterface {

    private String dburl = "sqlite.db";
    private Connection connection;

    //you should verify tht the db has not been set up before calling this
    private void setupDB()
    {
        try{
            Statement statement = connection.createStatement();

            statement.execute("DROP TABLE IF EXISTS users");
            statement.execute("DROP TABLE IF EXISTS deltas");
            statement.execute("DROP TABLE IF EXISTS games");

            statement.execute("CREATE TABLE users ( id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, value BLOB)");
            statement.execute("CREATE TABLE deltas ( id INTEGER PRIMARY KEY AUTOINCREMENT, game_id INTEGER, value BLOB)");
            statement.execute("CREATE TABLE games ( id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB)");

        } catch(Exception e) { e.printStackTrace(); System.exit(0); }
    }

    /**
     * Starts a transaction with the database
     */
    public void startTransaction()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + this.dburl);

            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM sqlite_master");

            int size = 0;

            while(result.next())
                size++;

            if(size == 0)
                this.setupDB();

        }catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Ends a transaction with the database
     */
    public void endTransaction()
    {
        try { connection.close(); } catch(Exception e) { }
    }

    /**
     * Creates a user DAO and returns that DAO
     *
     * @return The User DAO that was created
     */
    public UserDAOInterface createUserDAO()
    {
        return new UserDAO(this.connection);
    }

    /**
     * Creates a game DAO and returns that DAO
     *
     * @return The Game DAO that was created
     */
    public GameDAOInterface createGameDAO()
    {
        return new GameDAO(this.connection);
    }

    /**
     * Creates a command DAO and returns that DAO
     *
     * @return The Command DAO that was created
     */
    public CommandDAOInterface createCommandDAO()
    {
        return new CommandDAO(this.connection);
    }

    public void resetAllPersistence()
    {
        this.setupDB();
    }

}
