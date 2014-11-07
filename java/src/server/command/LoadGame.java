package server.command;

import shared.serialization.parameters.LoadGameRequestParameters;

/**
 * 
 * @author Kevin
 *
 */
public class LoadGame extends CatanCommand {

	/**
	 * Initializes the LoadGame object with the data necessary to find and load a game from the server
	 * @param parameters An object containing the name of the game to be loaded
	 */
	public LoadGame(LoadGameRequestParameters parameters){
		
	}
	
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#loadGame(int) loadGame} 
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
