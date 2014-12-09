package server.RDBMSPlugin;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import server.persistence.CommandDAOInterface;
import server.persistence.PersistenceInterface;
import server.persistence.UserDAOInterface;

/**
 * Created by christopherbelyeu on 12/8/14.
 */
public class RDBMSPluginTest {

    @Test
    public void UserDAOTest()
    {
        PersistenceInterface persistence = new RDBMSPersistence();

        //reset db test
        persistence.startTransaction();
        persistence.resetAllPersistence();
        persistence.endTransaction();

        //create
        persistence.startTransaction();
        UserDAOInterface userDAO = persistence.createUserDAO();
        assert(userDAO.saveUser("data1", "username1"));
        persistence.endTransaction();

        //read
        persistence.startTransaction();
        assert(persistence.createUserDAO().getAllUsers().size() == 1);
        persistence.endTransaction();

        //try to re-add the user
        persistence.startTransaction();
        assert(!persistence.createUserDAO().saveUser("data1", "username1"));
        persistence.endTransaction();

        //update user
        persistence.startTransaction();
        assert(persistence.createUserDAO().updateUser("username1", "data2"));
        assert(persistence.createUserDAO().getUser("username1").equals("data2"));
        persistence.endTransaction();

        //delete user
        persistence.startTransaction();
        assert(persistence.createUserDAO().deleteUser("username1"));
        assert(persistence.createUserDAO().getUser("username1") == null);
        persistence.endTransaction();
    }

    @Test
    public void CommandDAOTest()
    {
        PersistenceInterface persistence = new RDBMSPersistence();

        //reset db test
        persistence.startTransaction();
        persistence.resetAllPersistence();
        persistence.endTransaction();

        //create
        persistence.startTransaction();
        assert(persistence.createCommandDAO().saveCommand("data1", 1));
        persistence.endTransaction();

        //read
        persistence.startTransaction();
        assert(persistence.createCommandDAO().getUnappliedCommands(1).size() == 1);
        persistence.endTransaction();

        //try to add another command
        persistence.startTransaction();
        assert(persistence.createCommandDAO().saveCommand("data2", 1));
        persistence.endTransaction();

        //read
        persistence.startTransaction();
        assert(persistence.createCommandDAO().getUnappliedCommands(1).size() == 2);
        persistence.endTransaction();

        //delete commands
        persistence.startTransaction();
        assert(persistence.createCommandDAO().deleteGameCommands(1));
        assert(persistence.createCommandDAO().getUnappliedCommands(1).size() == 0);
        persistence.endTransaction();
    }

    @Test
    public void GameDAOTest()
    {
        PersistenceInterface persistence = new RDBMSPersistence();

        //reset db test
        persistence.startTransaction();
        persistence.resetAllPersistence();
        persistence.endTransaction();

        persistence.startTransaction();
        assert(persistence.createGameDAO().getAllGames().size() == 0);
        persistence.endTransaction();

        //create
        persistence.startTransaction();
        assert(persistence.createGameDAO().saveGame("data1", 1));
        persistence.endTransaction();

        //read
        persistence.startTransaction();
        assert(persistence.createGameDAO().getAllGames().size() == 1);
        persistence.endTransaction();

        //try to re-add the user
        persistence.startTransaction();
        assert(!persistence.createGameDAO().saveGame("data1", 1));
        persistence.endTransaction();

        //update user
        persistence.startTransaction();
        assert(persistence.createGameDAO().updateGame("data2", 1));
        assert(!persistence.createGameDAO().updateGame("data1", 2));
        assert(persistence.createGameDAO().getGame(1).equals("data2"));
        persistence.endTransaction();

        //delete user
        persistence.startTransaction();
        assert(persistence.createGameDAO().deleteGame(1));
        assert(persistence.createGameDAO().getGame(1) == null);
        persistence.endTransaction();
    }

}
