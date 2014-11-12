package server.command;

import shared.locations.EdgeLocation;
import shared.serialization.parameters.MaritimeTradeParameters;

/**
 * A Command object that updates a game to reflect a change in a player's resources from a maritime trade
 * @author Kevin
 */
public class MaritimeTrade extends CatanCommand {

	private EdgeLocation location = null;
	/**
	 * Initializes the MaritimeTrade object with data necessary for updating the game model
	 * @param parameters An object containing the player index of the person trading, the ratio of the trade, and the resources to be given/received
	 * @param game_id The integer ID of a game this action will be applied to
	 */
	public MaritimeTrade(MaritimeTradeParameters parameters, int game_id){
		
		this.gameId = game_id;
		this.playerIndex = parameters.getPlayerIndex();
		
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canMaritimeTrade(int, int, shared.locations.EdgeLocation, shared.model.card.MaritimeTrade) canMaritimeTrade} with 
	 * the parameters provided to the constructor to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#maritimeTrade(int, int, shared.locations.EdgeLocation, shared.model.card.MaritimeTrade) maritimeTrade}
	 */
	@Override
	public void execute() {
		
		if (facadeInstance.canMaritimeTrade(gameId, playerIndex, location, null)){
			
			success = facadeInstance.maritimeTrade(gameId, playerIndex, location, null);
		}
		
	}
}
