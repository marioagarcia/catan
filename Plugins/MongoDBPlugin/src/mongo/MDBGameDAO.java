package mongo;

import com.mongodb.*;
import persistence.GameDAOInterface;

import java.util.ArrayList;

/**
 * Created by mario on 12/6/14.
 */
public class MDBGameDAO implements GameDAOInterface {

    DB db;

    public MDBGameDAO(DB db) {
        this.db = db;
    }

    @Override
    public boolean saveGame(String game_blob, int game_id) {

        DBCollection games =  db.getCollection("games");

        BasicDBObject game = new BasicDBObject("gameId", game_id).append("blob", game_blob);

        WriteResult result = games.insert(game);

        return true;
    }

    @Override
    public ArrayList<String> getAllGames() {

        ArrayList<String> game_list = new ArrayList<>();

        DBCollection games = db.getCollection("games");

        DBCursor game_cursor = games.find();

        while (game_cursor.hasNext()) {

            game_list.add((String) game_cursor.next().get("blob"));
        }

        return game_list;
    }

    @Override
    public String getGame(int game_id) {

        DBCollection games = db.getCollection("games");

        BasicDBObject query = new BasicDBObject("gameId", game_id);

        DBCursor game_cursor = games.find(query);

        String game_blob = null;

        if(game_cursor.hasNext()) {
            game_blob = (String)game_cursor.next().get("blob");
        }

        game_cursor.close();

        return game_blob;
    }

    @Override
    public boolean updateGame(String game_blob, int game_id) {

        deleteGame(game_id);

        return saveGame(game_blob, game_id);
    }

    @Override
    public boolean deleteGame(int game_id) {

        DBCollection games =  db.getCollection("games");

        games.remove(new BasicDBObject("gameId", game_id));

        return true;
    }
}
