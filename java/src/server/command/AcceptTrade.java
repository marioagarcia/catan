package server.command;

import server.facade.ServerModelFacade;
import shared.serialization.parameters.AcceptTradeParameters;

/**
 * A Command object that updates a game to reflect that a user has accepted or rejected a trade offer
 * @author Kevin
 */
public class AcceptTrade extends CatanCommand {

	private boolean accept = false;
	/**
	 * Initializes the AcceptTrade object with all the information it needs to apply changes to the model
	 * to reflect the results of a trade offer
	 * @param parameters An object containing information about whether the trade will be accepted, and the index of the player accepting the trade
	 * @param game_id The integer ID of the game the operation is to be performed on. Must be an ID for an existing game
	 */
	public AcceptTrade(AcceptTradeParameters parameters, int game_id){
		
		super(parameters.getPlayerIndex(), game_id);
		this.accept = parameters.getWillAccept();
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#acceptTrade(int, int, boolean)} on the model facade to ensure this move is valid.
	 * If the move is valid, a call is then made to {@link server.facade.ServerModelFacade#acceptTrade(int, int, boolean)}
	 */
	@Override
	public void execute() {	
			
		ServerModelFacade.getInstance().acceptTrade(gameId, playerIndex, accept);
		ServerModelFacade.getInstance().persistCommand(this, "AcceptTrade", gameId);
		success = true;
	}
}
