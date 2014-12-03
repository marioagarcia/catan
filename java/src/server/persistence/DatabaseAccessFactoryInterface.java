package server.persistence;

/**
 * An implementation of this interface will be used to create objects that will persist Catan data into a database.
 * The implementation of the factory will be given to a persistence class which use internally the provided DAOs 
 */
public interface DatabaseAccessFactoryInterface {

	/**
	 * 
	 * @return An object that will be used to save CatanCommands to the database 
	 */
	public abstract CommandDAOInterface createCommandDAO();
	
	/**
	 *
	 * @return An object that will be used to save Users to the database
	 */
	public abstract UserDAOInterface createUserDAO();
	
	/**
	 * 
	 * @return An object that will be used to save games to the database
	 */
	public abstract GameDAOInterface createGameDAO();
}
