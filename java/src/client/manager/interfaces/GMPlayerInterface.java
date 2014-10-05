package client.manager.interfaces;

import client.model.card.ResourceList;
import client.model.card.TradeInterface;

public interface GMPlayerInterface {
	
	/**
	 * Determines if the player has enough resources to offer the trade 
	 * @param trade
	 * @return true if the player can make the trade
	 */
	public boolean canOfferTrade(TradeInterface trade);
	
	/**
	 * Determines whether the player has enough resources to buy a dev card (1 ore, 1 wheat, 1 sheep)
	 * @return true if the player can buy a dev card
	 */
	public boolean canBuyDevCard();
	
	/**
	 * Determines that the player has the year of plenty, it hasn't played a dev card this turn, and the resources in the list.
	 * @param resourceList
	 * @return true if player has the year of plenty card and the resources passed in
	 */
	public boolean canPlayYearOfPlenty(ResourceList resourceList);
	
	/**
	 * Determines that the player has the road building, player has two roads and it hasn't played a dev card this turn.
	 * @return true if the player has the road building card and it hasn't played a dev card this turn yet
	 */
	public boolean canPlayRoadBuilding();

	/**
	 * Determines that the player has the soldier card and it hasn't played a dev card this turn
	 * @return true if the player has the soldier card and it hasn't played a dev card this turn
	 */
	public boolean canPlaySoldier();
}
