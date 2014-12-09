package server.RDBMSPlugin.DAO;


import server.persistence.GameDAOInterface;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

/**
 * Created by christopherbelyeu on 12/7/14.
 */
public class GameDAO implements GameDAOInterface {

    Connection connection;
    GenericDAO genericDAO;

    public GameDAO(Connection connection)
    {
        this.connection = connection;
        this.genericDAO = new GenericDAO(connection, "games", "id");
    }

    public boolean saveGame(String game_blob, int game_id)
    {
        return this.genericDAO.save(game_blob, String.valueOf(game_id));
    }

    public ArrayList<String> getAllGames()
    {
        return this.genericDAO.getAll(2);
    }

    public String getGame(int game_id)
    {
        return this.genericDAO.getByPrimaryKey(String.valueOf(game_id), 2);
    }

    public boolean updateGame(String game_blob, int game_id)
    {
        return this.genericDAO.update(game_blob, String.valueOf(game_id));
    }

    public boolean deleteGame(int game_id)
    {
        return this.genericDAO.delete(String.valueOf(game_id));
    }

}
