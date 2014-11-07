package server.handler.facade.mock;

import shared.model.GameInfo;
import shared.model.manager.GameList;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.JoinGameRequestParameters;
import shared.serialization.parameters.LoadGameRequestParameters;
import shared.serialization.parameters.SaveGameRequestParameters;

public interface MockGamesHandlerFacadeInterface {

	/**
	 * Retrieves a hard coded list of GameInfo objects
	 * 
	 * @return An object containing a hard coded array of GameInfo objects
	 */
	public GameList getGamesList();
	
	/**
	 * Retrieves a hard coded GameInfo object
	 * 
	 * @param params An object containing the name of the new game (string), and 3 Booleans representing whether
	 * the tiles should be randomly placed, numbers should be randomly assigned to the tiles, and ports should
	 * be randomly placed 
	 * @return A hard coded GameInfo object (name of game, id of game, list of PlayerInfo objects)
	 */
	public GameInfo createGame(CreateGameRequestParameters params);
	
	/**
	 * Randomly picks between true and false
	 * 
	 * @param params An object containing the id and color of the player joining the game
	 * @return True or false, whichever was randomly selected
	 */
	public Boolean joinGame(JoinGameRequestParameters params);
	
	/**
	 * Randomly picks between true and false
	 * 
	 * @param params An object containing the id of the game to save and the name of the file the game is to be
	 * saved to
	 * @return True or false, whichever was randomly selected
	 */
	public Boolean saveGame(SaveGameRequestParameters params);
	
	/**
	 * Randomly picks between true and false
	 * 
	 * @param params An object containing the name of the saved game being loaded
	 * @return True or false, whichever was randomly selected
	 */
	public Boolean loadGame(LoadGameRequestParameters params);
}
