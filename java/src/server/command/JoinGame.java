package server.command;

import shared.serialization.parameters.JoinGameRequestParameters;

/**
 * A Command object that attempts to place a player into game 
 * @author Kevin
 *
 */
public class JoinGame extends CatanCommand {

	/**
	 * Initializes the JoinGame object with the data needed to put a player into a given game
	 * @param parameters an object containing the ID of the game to be joined, and the color the player will use
	 */
	public JoinGame(JoinGameRequestParameters parameters){
		
	}
	
	/**
	 * Calls {@link server.manager.ServerModelFacade#canJoinGame(shared.definitions.CatanColor, shared.model.GameInfo) canJoinGame} with the parameters provided to the constructor
	 * to ensure this move is valid
	 * If valid, a call is then made to {@link server.manager.ServerModelFacade#joinGame(shared.definitions.CatanColor, shared.model.GameInfo) joinGame}
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
