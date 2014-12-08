package server.command;

import server.facade.ServerModelFacade;
import shared.definitions.ResourceType;
import shared.serialization.parameters.MaritimeTradeParameters;

/**
 * A Command object that updates a game to reflect a change in a player's resources from a maritime trade
 * @author Kevin
 */
public class MaritimeTrade extends CatanCommand {

	private MaritimeTradeParameters params = null;
	
	ResourceType in = null;
	ResourceType out = null;
	
	/**
	 * Initializes the MaritimeTrade object with data necessary for updating the game model
	 * @param parameters An object containing the player index of the person trading, the ratio of the trade, and the resources to be given/received
	 * @param game_id The integer ID of a game this action will be applied to
	 */
	public MaritimeTrade(MaritimeTradeParameters parameters, int game_id){
		
		this.gameId = game_id;
		this.playerIndex = parameters.getPlayerIndex();
		this.params = parameters;
		
		this.in = ResourceType.valueOf(parameters.getInputResource().toUpperCase());
		this.out = ResourceType.valueOf(parameters.getOutputResource().toUpperCase());
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canMaritimeTrade(int, int, int, shared.definitions.ResourceType, shared.definitions.ResourceType)  canMaritimeTrade} with
	 * the parameters provided to the constructor to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#maritimeTrade(int, int, int, shared.definitions.ResourceType, shared.definitions.ResourceType)}
	 */
	@Override
	public void execute() {

		if (ServerModelFacade.getInstance().canMaritimeTrade(gameId, playerIndex, params.getRatio(), in, out)){
			
			success = ServerModelFacade.getInstance().maritimeTrade(gameId, playerIndex, params.getRatio(), in, out);
			
			if (success){
				
			//	ServerModelFacade.getInstance().persistCommand(this, "MaritimeTrade", gameId);
			}
		}
		
	}
}
