package server.command.facade;

import shared.model.GameInfo;
import shared.model.manager.GameData;
import shared.model.manager.GameList;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.JoinGameParameters;
import shared.serialization.parameters.LoadGameRequestParameters;
import shared.serialization.parameters.SaveGameParameters;

public interface GamesCommandFacadeInterface {
	
	/**
	 * Creates a ModelCommunicator object and calls getGameList() on it
	 * 
	 * @return An object containing an array of GameInfo objects
	 */
	public GameList getGamesList();
	
	/**
	 * Creates a CreateGame command object and calls execute on it
	 * 
	 * @param params An object containing the name of the new game (string), and 3 Booleans representing whether
	 * the tiles should be randomly placed, numbers should be randomly assigned to the tiles, and ports should
	 * be randomly placed 
	 * @return A GameInfo object (name of game, id of game, list of PlayerInfo objects)
	 */
	public GameInfo createGame(CreateGameRequestParameters params);
	
	/**
	 * Creates a JoinGame command object and calls execute on it
	 * 
	 * @param params An object containing the id and color of the player joining the game
	 * @return Whether or not the join was successful; true if successful, false otherwise
	 */
	public Boolean joinGame(JoinGameParameters params, int playerId);
	
	/**
	 * Creates a SaveGame command object and calls execute on it
	 * 
	 * @param params An object containing the id of the game to save and the name of the file the game is to be
	 * saved to
	 * @return Whether or not the game was successfully saved; true if successful, false otherwise
	 */
	public Boolean saveGame(SaveGameParameters params);
	
	/**
	 * Creates a LoadGame command object and calls execute on it
	 * 
	 * @param params An object containing the name of the saved game being loaded
	 * @return Whether or not loading the game was successful; true if successful, false otherwise
	 */
	public GameData loadGame(LoadGameRequestParameters params);
	
}
