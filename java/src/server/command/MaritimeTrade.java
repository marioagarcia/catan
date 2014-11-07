package server.command;

import shared.serialization.parameters.MaritimeTradeParameters;

public class MaritimeTrade extends CatanCommand {

	/**
	 * 
	 * @param parameters
	 * @param game_id
	 */
	public MaritimeTrade(MaritimeTradeParameters parameters, int game_id){
		
	}
	
	/**
	 * Calls {@link server.manager.ServerModelFacade#canMaritimeTrade(shared.locations.EdgeLocation, shared.model.card.MaritimeTrade) canMaritimeTrade} with 
	 * the parameters provided to the constructor to ensure this move is valid
	 * If valid, a call is then made to {@link server.manager.ServerModelFacade#maritimeTrade(shared.locations.EdgeLocation, shared.model.card.MaritimeTrade) maritimeTrade}
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
