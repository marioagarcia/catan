package mongo;

import com.mongodb.*;
import persistence.CommandDAOInterface;

import java.util.ArrayList;

/**
 * Created by mario on 12/6/14.
 */
public class MDBCommandDAO implements CommandDAOInterface {

    DB db;

    public MDBCommandDAO(DB db) {
        this.db = db;
    }

    @Override
    public boolean saveCommand(String command_blob, int game_id) {

        DBCollection commands =  db.getCollection("commands");

        BasicDBObject command = new BasicDBObject("gameId", game_id).append("blob", command_blob);

        WriteResult result = commands.insert(command);

        return true;
    }

    @Override
    public ArrayList<String> getUnappliedCommands(int game_id) {

        ArrayList<String> command_list = new ArrayList<>();

        DBCollection commands =  db.getCollection("commands");

        BasicDBObject query = new BasicDBObject("gameId", game_id);

        DBCursor command_cursor = commands.find(query);

        while (command_cursor.hasNext()) {

            command_list.add((String)command_cursor.next().get("blob"));
        }

        return command_list;
    }

    @Override
    public boolean deleteGameCommands(int game_id) {

        DBCollection commands =  db.getCollection("commands");

        BasicDBObject query = new BasicDBObject("gameId", game_id);

        commands.remove(query);

        return true;
    }
}
