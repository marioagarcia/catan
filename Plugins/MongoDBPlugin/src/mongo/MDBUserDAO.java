package mongo;

import com.mongodb.*;
import persistence.UserDAOInterface;

import java.util.ArrayList;

/**
 * Created by mario on 12/6/14.
 */
public class MDBUserDAO implements UserDAOInterface {

    DB db;

    public MDBUserDAO(DB db) {
        this.db = db;
    }

    @Override
    public boolean saveUser(String user_blob, String username) {

        DBCollection users =  db.getCollection("users");

        BasicDBObject user = new BasicDBObject("username", username).append("blob", user_blob);

        WriteResult result = users.insert(user);

        return true;
    }

    @Override
    public ArrayList<String> getAllUsers() {

        ArrayList<String> user_list = new ArrayList<>();

        DBCollection users = db.getCollection("users");

        DBCursor user_cursor = users.find();

        while (user_cursor.hasNext()) {

            user_list.add((String) user_cursor.next().get("blob"));
        }

        return user_list;
    }

    @Override
    public String getUser(String username) {

        DBCollection users = db.getCollection("users");

        BasicDBObject query = new BasicDBObject("username", username);

        DBCursor user_cursor = users.find(query);

        String user_blob = null;

        if(user_cursor.hasNext()) {
            user_blob = (String)user_cursor.next().get("blob");
        }

        user_cursor.close();

        return user_blob;
    }

    @Override
    public boolean updateUser(String username, String data) {

        deleteUser(username);

        return saveUser(data, username);
    }

    @Override
    public boolean deleteUser(String username) {

        DBCollection users =  db.getCollection("users");

        users.remove(new BasicDBObject("username", username));

        return true;
    }
}
