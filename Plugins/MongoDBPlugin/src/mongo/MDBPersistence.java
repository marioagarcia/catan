package mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import persistence.CommandDAOInterface;
import persistence.GameDAOInterface;
import persistence.PersistenceInterface;
import persistence.UserDAOInterface;

import java.net.UnknownHostException;

/**
 * Created by mario on 12/6/14.
 */
public class MDBPersistence implements PersistenceInterface {

    MDBDatabaseAccessFactory factory;
    MongoClient mongoClient;
    DB db;

    public MDBPersistence() {

        try {
            mongoClient = new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        db = mongoClient.getDB("catanDB");

        factory = new MDBDatabaseAccessFactory(this.db);

        db.createCollection("users", null);
        db.createCollection("commands", null);
        db.createCollection("games", null);

    }

    @Override
    public void startTransaction() {
        //not needed with MongoDB
    }

    @Override
    public void endTransaction() {
        //not needed with MongoDB
    }

    @Override
    public UserDAOInterface createUserDAO() {
        return factory.createUserDAO();
    }

    @Override
    public GameDAOInterface createGameDAO() {
        return factory.createGameDAO();
    }

    @Override
    public CommandDAOInterface createCommandDAO() {
        return factory.createCommandDAO();
    }

    @Override
    public void resetAllPersistence() {

        mongoClient.dropDatabase("catanDB");

    }

    public static void main(String[] args) {
        return;
    }
//    public static void main(String[] args) {
//
//        MDBPersistence p = new MDBPersistence();
//        p.createUserDAO().saveUser("description : this is mario", "mario");
//        System.out.println(p.createUserDAO().getUser("mario"));
//        p.createUserDAO().updateUser("mario", "description: this is the real blob");
//        System.out.println(p.createUserDAO().getUser("mario"));
//        p.createUserDAO().deleteUser("mario");
//        System.out.println(p.createUserDAO().getUser("mario"));
//
//        p.createUserDAO().saveUser("description : this is mario", "mario");
//        p.createUserDAO().saveUser("description : this is casey", "casey");
//        p.createUserDAO().saveUser("description : this is kevin", "kevin");
//        p.createUserDAO().saveUser("description : this is chris", "chris");
//
//        for (String blob : p.createUserDAO().getAllUsers()) {
//
//        System.out.println(blob);
//        }
//
//        p.createGameDAO().saveGame("description : this is a game", 0);
//        System.out.println(p.createGameDAO().getGame(0));
//        p.createGameDAO().updateGame("description: this is the real game", 0);
//        System.out.println(p.createGameDAO().getGame(0));
//        p.createGameDAO().deleteGame(0);
//        System.out.println(p.createGameDAO().getGame(0));
//
//        p.createCommandDAO().saveCommand("description : this is mario", 0);
//        p.createCommandDAO().saveCommand("description : this is mario", 0);
//        p.createCommandDAO().saveCommand("description : this is mario", 0);
//        p.createCommandDAO().saveCommand("description : this is mario", 1);
//        p.createCommandDAO().saveCommand("description : this is mario", 1);
//        p.createCommandDAO().saveCommand("description : this is mario", 1);
//
//        for (String blob : p.createCommandDAO().getUnappliedCommands(0)) {
//
//            System.out.println(blob);
//        }
//
//        p.createCommandDAO().deleteGameCommands(0);
//
//        for (String blob : p.createCommandDAO().getUnappliedCommands(0)) {
//
//            System.out.println(blob);
//        }
//
//        p.resetAllPersistence();
//
//
//    }

}
