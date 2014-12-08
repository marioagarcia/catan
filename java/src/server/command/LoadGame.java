package server.command;

import server.facade.ServerModelFacade;
import shared.model.manager.GameData;
import shared.serialization.parameters.LoadGameRequestParameters;

/**
 * A Command object that captures the current state of a game and saves it locally
 * @author Kevin
 */
public class LoadGame extends CatanCommand {

	private GameData gameData = null;
	/**
	 * Initializes the LoadGame object with the data necessary to load one of the games hosted on the server
	 * @param parameters An object containing the ID and name of the game to be loaded
	 */
	public LoadGame(LoadGameRequestParameters parameters){
		super(-1, -1);
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#loadGames() loadGame}
	 */
	@Override
	public void execute() {
		ServerModelFacade.getInstance().loadGames();
	}
	
	public GameData getGameData(){
		return gameData;
	}
}

