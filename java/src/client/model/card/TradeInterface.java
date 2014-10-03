package client.model.card;

import client.model.card.ResourceCardInterface;

public interface TradeInterface {
	
	/**
	 * sets the number of a card type that we want to get
	 * @param card type
	 * @param count the number of the card that we will give/get
	 * a positive number means that we will give them the cards
	 * a negative number means that we expect cards in return
	 */
	public void setTradeCard(ResourceCardInterface.ResourceCardType type, int count);
	
	/**
	 * gets the number of a give card type that we will be trading
	 * @param the card type that we are interested in
	 * @return the number of that card that will be traded
	 * positive means that you will be giving cards
	 * negative means that you expect cards in return
	 */
	public int getTradeCard(ResourceCardInterface.ResourceCardType type);
	
}
