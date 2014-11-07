package server.command;

import shared.serialization.parameters.CreateGameRequestParameters;

/**
 * A Command object that adds a new, empty game to the server
 * @author Kevin
 */
public class CreateGame extends CatanCommand {

	/**
	 * 
	 * @param parameters An object containing details about how the new game should be setup: random tiles, random numbers, random ports, and
	 * the name of the new game
	 */
	public CreateGame(CreateGameRequestParameters parameters){
		
	}
	
	/**
	 * Calls {@link server.manager.ServerModelFacade#createNewGame(String, boolean, boolean, boolean) createNewGame} on the facade
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
