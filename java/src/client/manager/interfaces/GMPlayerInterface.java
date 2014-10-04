package client.manager.interfaces;

import client.model.card.TradeInterface;

public interface GMPlayerInterface {
	
	/**
	 * Determines if the player has enough resources to offer the trade 
	 * @param trade
	 * @return true if the player can make the trade
	 */
	public boolean canOfferTrade(TradeInterface trade);
}
