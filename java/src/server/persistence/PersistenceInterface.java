package server.persistence;

public interface PersistenceInterface {
	
	/**
	 * Starts a transaction with the database
	 */
	public void startTransaction();
	
	/**
	 * Ends a transaction with the database
	 */
	public void endTransaction();
	
	/**
	 * Creates a user DAO and returns that DAO
	 * 
	 * @return The User DAO that was created
	 */
	public UserDAOInterface createUserDAO();
	
	/**
	 * Creates a game DAO and returns that DAO
	 * 
	 * @return The Game DAO that was created
	 */
	public GameDAOInterface createGameDAO();
	
	/**
	 * Creates a command DAO and returns that DAO 
	 * 
	 * @return The Command DAO that was created
	 */
	public CommandDAOInterface createCommandDAO();

	/**
	 * Resets all persistence
	 */
	public void resetAllPersistence();
	
}
