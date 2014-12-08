package server.command;

import server.facade.ServerModelFacade;
import shared.serialization.parameters.MonumentParameters;

/**
 * A Command object that updates a game to reflect that a player has played the monument dev card
 * @author Kevin
 */
public class PlayMonument extends CatanCommand {

	/**
	 * Initializes the PlayMonument object with the data necessary to update a given game
	 * @param parameters An object containing the index of the player using this card
	 * @param game_id The ID of the game this action will be applied to
	 */
	public PlayMonument(MonumentParameters parameters, int game_id){
		
		this.gameId = game_id;
		this.playerIndex = parameters.getPlayerIndex();
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canPlayMonument(int, int) canPlayMonument} with the parameters provided to the constructor
	 * to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#playMonument(int, int) playMonument}
	 */
	@Override
	public void execute() {

		if (ServerModelFacade.getInstance().canPlayMonument(gameId, playerIndex)){
			
			success = ServerModelFacade.getInstance().playMonument(gameId, playerIndex);
			
			if (success){
				
			//	ServerModelFacade.getInstance().persistCommand(this, "PlayMonument", gameId);
			}
		}
		
	}
}
