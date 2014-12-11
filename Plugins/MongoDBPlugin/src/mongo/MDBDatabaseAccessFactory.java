package mongo;

import persistence.CommandDAOInterface;
import persistence.DatabaseAccessFactoryInterface;
import persistence.GameDAOInterface;
import persistence.UserDAOInterface;

import com.mongodb.MongoClient;
import com.mongodb.DB;

/**
 * Created by mario on 12/6/14.
 */
public class MDBDatabaseAccessFactory implements DatabaseAccessFactoryInterface {

    DB db;

    public MDBDatabaseAccessFactory(DB db) {
        this.db = db;
    }

    @Override
    public CommandDAOInterface createCommandDAO() {
        return new MDBCommandDAO(this.db);
    }

    @Override
    public UserDAOInterface createUserDAO() {
        return new MDBUserDAO(this.db);
    }

    @Override
    public GameDAOInterface createGameDAO() {
        return new MDBGameDAO(this.db);
    }
}
