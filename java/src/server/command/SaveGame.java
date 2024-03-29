package server.command;

import server.facade.ServerModelFacade;
import shared.serialization.parameters.SaveGameParameters;


/**
 * A Command object that captures the current state of a game and saves it locally
 * @author Kevin
 */
public class SaveGame extends CatanCommand {

	/**
	 * Initializes the SaveGame object with the data necessary to save one of the games hosted on the server
	 * @param parameters An object containing the ID and name of the game to be saved
	 */
	public SaveGame(SaveGameParameters parameters){
		
		super(-1, parameters.getId());
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#saveGame(int) saveGame} 
	 */
	@Override
	public void execute() {
		
		success = ServerModelFacade.getInstance().saveGame(gameId);
		
	}
}
