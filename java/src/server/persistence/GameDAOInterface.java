package server.persistence;

import java.util.ArrayList;

public interface GameDAOInterface {

	/**
	 * Serializes a game and stores it in the database
	 * 
	 * @param data An object representation of a game
	 * @return True if the save was successful, false otherwise
	 */
	public abstract boolean saveGame(GameDTO data);
	
	/**
	 * Retrieves a list of all of the games in the database
	 * 
	 * @return An arraylist of all of the games
	 */
	public abstract ArrayList<GameDTO> getAllGames();
	
	/**
	 * Retrieves a game based on the given game id
	 * 
	 * @param game_id The id of the game being retrieved
	 * @return An object representation of a game
	 */
	public abstract GameDTO getGame(int game_id);
	
	/**
	 * Updates the game associated with the given game id
	 * 
	 * @param data An object representation of the game being updated
	 * @param game_id The id of the game being updated
	 * @return True if the update was successful, false otherwise
	 */
	public abstract boolean updateGame(GameDTO data, int game_id);
	
	/**
	 * Deletes the game associated with the given id
	 * 
	 * @param game_id The id of the game being deleted
	 * @return True if the deletion was successful, false otherwise
	 */
	public abstract boolean deleteGame(int game_id);
}
