package server.command;

import shared.serialization.parameters.AcceptTradeParameters;

/**
 * A Command object that updates a game to reflect that a user has accepted or rejected a trade offer
 * @author Kevin
 */
public class AcceptTrade extends CatanCommand {

	/**
	 * Initializes the AcceptTrade object with all the information it needs to apply changes to the model
	 * to reflect the results of a trade offer
	 * @param parameters An object containing information about whether the trade will be accepted, and the index of the player accepting the trade
	 * @param game_id The integer ID of the game the operation is to be performed on. Must be an ID for an existing game
	 */
	public AcceptTrade(AcceptTradeParameters parameters, int game_id){
		
	}
	
	/**
	 * Ensures that the move, given the current parameters, is valid. After calling this method, the players involved in the trade will
	 * have their resources updated to reflect the trade.
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub		
	}
}
