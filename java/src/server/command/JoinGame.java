package server.command;

import shared.definitions.CatanColor;
import shared.serialization.parameters.JoinGameParameters;

/**
 * A Command object that attempts to place a player into game 
 * @author Kevin
 *
 */
public class JoinGame extends CatanCommand {

	private int playerId = -1;
	private CatanColor color = null;
	/**
	 * Initializes the JoinGame object with the data needed to put a player into a given game
	 * @param parameters an object containing the ID of the game to be joined, and the color the player will use
	 */
	public JoinGame(JoinGameParameters parameters, int player_id){	
		this.gameId = parameters.getId();
		this.playerId = player_id;
		this.color = CatanColor.valueOf(parameters.getColor().toUpperCase());
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canJoinGame(int, int, shared.definitions.CatanColor) canJoinGame} with the parameters provided to the constructor
	 * to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#joinGame(int, int, shared.definitions.CatanColor) joinGame}
	 */
	@Override
	public void execute() {
					
		if (facadeInstance.canJoinGame(gameId, playerId, color)){
			success = facadeInstance.joinGame(gameId, playerId, color);
		}
		
	}
}
