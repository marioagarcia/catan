package server.RDBMSPlugin.DAO;

import server.persistence.CommandDAOInterface;

import java.util.ArrayList;
import java.sql.*;

/**
 * Created by christopherbelyeu on 12/8/14.
 */
public class CommandDAO implements CommandDAOInterface {

    Connection connection;
    GenericDAO genericDAO;


    public CommandDAO(Connection connection)
    {
        this.connection = connection;
        this.genericDAO = new GenericDAO(connection, "deltas", "id");
    }

    /**
     * Serializes a command and saves it in the database
     *
     * @param command_blob A serialized representation of a command object
     * @param game_id The id of the game this command belongs to
     * @return True if the save was successful, false otherwise
     */
    public boolean saveCommand(String command_blob, int game_id)
    {
        return this.genericDAO.executeSQL("INSERT INTO deltas ('game_id', 'value') VALUES('" + game_id + "', '" + command_blob + "');" );
    }

    /**
     * Retrieves a list of serialized commands associated with the given game id
     *
     * @param game_id The id of the game whose commands are being retrieved
     * @return An arraylist of the serialized commands associated with the give game id
     */
    public ArrayList<String> getUnappliedCommands(int game_id)
    {
        return this.genericDAO.executeBatchQuery("SELECT * from deltas WHERE game_id='" + game_id + "'", 3);
    }

    /**
     * Deletes the commands associated with a specific game, i.e. resets the list of commands for a game.
     *
     * @param game_id The id of the game whose commands are being deleted
     * @return True if the deletion was successful, false otherwise.
     */
    public boolean deleteGameCommands(int game_id)
    {
        return this.genericDAO.executeSQL("DELETE FROM deltas WHERE game_id='" + game_id + "'");
    }
}
