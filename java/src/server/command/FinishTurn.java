package server.command;

import server.facade.ServerModelFacade;
import shared.serialization.parameters.FinishTurnParameters;

/**
 * A Command object that finishes a player's turn and advances the state of the game
 * @author Kevin
 */
public class FinishTurn extends CatanCommand {

	/**
	 * Initializes the FinishTurn object with the data needed to update the model
	 * @param parameters An object containing the index of the player ending their turn
	 * @param game_id The integer ID of the game this action is to be performed on. Must be a valid game.
	 */
	public FinishTurn(FinishTurnParameters parameters, int game_id){
		
		this.gameId = game_id;
		this.playerIndex = parameters.getPlayerIndex();
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canFinishTurn(int, int) canFinishTurn} with the parameters provided to the constructor
	 * to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#finishTurn(int, int) finishTurn}
	 */
	@Override
	public void execute() {
		
		if (ServerModelFacade.getInstance().canFinishTurn(gameId, playerIndex)){
			
			success = ServerModelFacade.getInstance().finishTurn(gameId, playerIndex);
			
			if (success){
				
				ServerModelFacade.getInstance().persistCommand(this, "FinishTurn", gameId);
			}
			
		}
		
	}
}
