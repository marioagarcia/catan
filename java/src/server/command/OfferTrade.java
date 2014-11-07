package server.command;

import shared.serialization.parameters.OfferTradeParameters;

/**
 * A Command object that, when the execute method is called, will update the state of the game to reflect a new trade being offered
 * @author Kevin
 *
 */
public class OfferTrade extends CatanCommand {

	/**
	 * Initializes the OfferTrade object with the data necessary to update the game model to offer a trade
	 * @param parameters An object the index of the player offering and receiving the trade, and the resources that will be exchanged
	 * @param game_id The integer ID of the game this action will applied to
	 */
	public OfferTrade(OfferTradeParameters parameters, int game_id){
		
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canOfferTrade(int, int, shared.model.card.TradeInterface) canOfferTrade} with the parameters provided to the constructor
	 * to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#offerTrade(int, int, shared.model.manager.interfaces.GMDomesticTradeInterface, int) offerTrade}
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
