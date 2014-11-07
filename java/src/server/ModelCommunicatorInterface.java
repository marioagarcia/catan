package server;

import shared.model.manager.GameData;
import shared.model.manager.GameList;

/**
 * The ModelCommunicator provides a way for the Handler classes to get physical data for each Catan game
 * currently running on the server
 * @author Kevin
 */
public interface ModelCommunicatorInterface {

	/**
	 * @param game_id The integer representing the game you wish to get the model for
	 * @return A {@link shared.model.manager.GameData} object with all the current information about the state of a particular game
	 */
	public GameData getGameModel(int game_id);
	
	/**
	 * @param name The name of a game currently saved on the server
	 * @return A {@link shared.model.manager.GameData} object with all the current information about the state of the saved game
	 */
	public GameData loadGameModel(String name);
	
	/**
	 * @return A {@link shared.model.manager.GameList} object containing information about the games running on the server
	 */
	public GameList getGameList();
	
	/**
	 * @return An array of strings representing the types of AI available to put in a game
	 */
	public String[] getAIList();
}
