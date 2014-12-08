package server.persistence;

import java.util.ArrayList;

public interface GameDAOInterface {

	/**
	 * Receives a serialized game and stores it in the database
	 * 
	 * @param game_blob An serialized representation of a game
	 * @param game_id The id that should be associated with the game blob
	 * @param title The name of the game being saved
	 * @return True if the save was successful, false otherwise
	 */
	public abstract boolean saveGame(String game_blob, int game_id, String title);
	
	/**
	 * Retrieves a list of all serialized games in the database
	 * 
	 * @return An arraylist of all of the games
	 */
	public abstract ArrayList<String> getAllGames();
	
	/**
	 * Retrieves a game based on the given game id
	 * 
	 * @param game_id The id of the game being retrieved
	 * @return An serialized representation of a game
	 */
	public abstract String getGame(int game_id);
	
	/**
	 * Updates the game associated with the given game id
	 * 
	 * @param game_blob A serialized representation of the game being updated
	 * @param game_id The id of the game being updated
	 * @return True if the update was successful, false otherwise
	 */
	public abstract boolean updateGame(String game_blob, int game_id);
	
	/**
	 * Deletes the game associated with the given id
	 * 
	 * @param game_id The id of the game being deleted
	 * @return True if the deletion was successful, false otherwise
	 */
	public abstract boolean deleteGame(int game_id);
}
