package server.command;

import shared.serialization.parameters.CreateGameRequestParameters;

/**
 * A Command object that adds a new, empty game to the server
 * @author Kevin
 */
public class CreateGame extends CatanCommand {

	private String name = null;
	private boolean randomTiles = false;
	private boolean randomNumbers = false;
	private boolean randomPorts = false;
	
	/**
	 * 
	 * @param parameters An object containing details about how the new game should be setup: random tiles, random numbers, random ports, and
	 * the name of the new game
	 */
	public CreateGame(CreateGameRequestParameters parameters){
		
		name = parameters.getName();
		randomTiles = parameters.isRandomTiles();
		randomNumbers = parameters.isRandomNumbers();
		randomPorts = parameters.isRandomPorts();
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#createNewGame(String, boolean, boolean, boolean) createNewGame} on the facade
	 */
	@Override
	public void execute() {
	
		success = facadeInstance.createNewGame(name, randomTiles, randomNumbers, randomPorts);
		
	}
}
