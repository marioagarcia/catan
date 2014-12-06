package server.RDBMSPlugin;

import server.persistence.CommandDAOInterface;
import server.persistence.GameDAOInterface;
import server.persistence.PersistenceInterface;
import server.persistence.UserDAOInterface;

/**
 * Created by christopherbelyeu on 12/5/14.
 */
public class RDBMSPersistence implements PersistenceInterface {

    /**
     * Starts a transaction with the database
     */
    public void startTransaction()
    {

    }


    /**
     * Ends a transaction with the database
     */
    public void endTransaction()
    {

    }

    /**
     * Creates a user DAO and returns that DAO
     *
     * @return The User DAO that was created
     */
    public UserDAOInterface createUserDAO()
    {
        return null;
    }

    /**
     * Creates a game DAO and returns that DAO
     *
     * @return The Game DAO that was created
     */
    public GameDAOInterface createGameDAO()
    {
        return null;
    }

    /**
     * Creates a command DAO and returns that DAO
     *
     * @return The Command DAO that was created
     */
    public CommandDAOInterface createCommandDAO()
    {
        return null;
    }
}
