package server.persistence;

import java.util.ArrayList;

public interface PersistenceInterface {

	/**
	 * Sets the database access objects by calling the createDAO methods in the factory that is passed in
	 * 
	 * @param factory The plugin being used to persist; creates the database access objects
	 */
	public abstract void configure(DatabaseAccessFactoryInterface factory);

	/**
	 * Calls saveCommand() on the CommandDAO and passes the DAO the given CommandDTO
	 * 
	 * @param data An object containing the information needed to save a command: a parameter object, a type (string), and an id (int)
	 * @return True if the save was successful, false otherwise
	 */
	public abstract boolean saveCommand(CommandDTO data);
	
	/**
	 * Calls getUnappliedCommands() on the CommandDAO and passes it the given game id
	 * 
	 * @param game_id The id of the game whose commands are being retrieved
	 * @return An arraylist of the commands associated with the give game id
	 */
	public abstract ArrayList<CommandDTO> getUnappliedCommands(int game_id);
	
	/**
	 * Calls deleteGameCommands() on the CommandDAO and passes it the given game id
	 * Deletes the commands associated with a specific game, i.e. resets the list of commands for a game.
	 * 
	 * @param game_id The id of the game whose commands are being deleted
	 * @return True if the deletion was successful, false otherwise.
	 */
	public abstract boolean deleteGameCommands(int game_id);
	
	/**
	 * Calls saveGame() on the GameDAO and passes it the given GameDTO
	 * Saves the current state of a game
	 * 
	 * @param data An object representation of a game
	 * @return True if the save was successful, false otherwise
	 */
	public abstract boolean saveGame(GameDTO data);
	
	/**
	 * Calls getAllGames() on the GameDAO
	 * Retrieves a list of all of the games in the database
	 * 
	 * @return An arraylist of all of the games
	 */
	public abstract ArrayList<GameDTO> getAllGames();
	
	/**
	 * Calls getGame() on the GameDAO and passes it the given game id
	 * Retrieves a game based on the given game id
	 * 
	 * @param game_id The id of the game being retrieved
	 * @return An object representation of a game
	 */
	public abstract GameDTO getGame(int game_id);
	
	/**
	 * Calls updateGame() on the GameDAO and passes it the given GameDTO and game id 
	 * Updates the game associated with the given game id
	 * 
	 * @param data An object representation of the game being updated
	 * @param game_id The id of the game being updated
	 * @return True if the update was successful, false otherwise
	 */
	public abstract boolean updateGame(GameDTO data, int game_id);
	
	/**
	 * Calls deleteGame() on the GameDAO and passes it the given game id
	 * Deletes the game associated with the given id
	 * 
	 * @param game_id The id of the game being deleted
	 * @return True if the deletion was successful, false otherwise
	 */
	public abstract boolean deleteGame(int game_id);
	
	/**
	 * Calls saveUser() on the UserDAO and passes it the given UserDTO
	 * Saves a user, i.e. adds a user to the database
	 * 
	 * @param data An object representation of the user being saved/added
	 * @return True if the save was successful, false otherwise
	 */
	public abstract boolean saveUser(UserDTO data);
	
	/**
	 * Calls getAllUsers() on the UserDAO
	 * Retrieves a list of all of the users in the database
	 * 
	 * @return An arraylist containing all of the users in the database
	 */
	public abstract ArrayList<UserDTO> getAllUsers();
	
	/**
	 * Calls getUser() on the UserDAO and passes it the given username
	 * Retrieves a user based on the given username
	 * 
	 * @param username The username of the user being retrieved
	 * @return UserDTO an object representation of the user
	 */
	public abstract UserDTO getUser(String username);
	
	/**
	 * Calls updateUser() on the UserDAO and passes it the given UserDTO
	 * Updates a user
	 * 
	 * @param data An object representation of the user being updated
	 * @return True if the update was successful, false otherwise
	 */
	public abstract boolean updateUser(UserDTO data);
	
	/**
	 * Calls deleteUser() on the UserDAO and passes it the given username
	 * Deletes a user from the database
	 * 
	 * @param username The username of the user being deleted
	 * @return True if the deletion was successful, false otherwise
	 */
	public abstract boolean deleteUser(String username);
}
