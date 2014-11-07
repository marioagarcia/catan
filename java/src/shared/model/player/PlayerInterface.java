package shared.model.player;

import shared.definitions.CatanColor;
import shared.model.card.ResourceList;
import shared.model.card.TradeInterface;

/**
 * Represents a Player in the game
 */
public interface PlayerInterface
{
	/**
	 * Gets the full name of this Player
	 * @return the name of this Player
	 */
	public String getName();
	
	/**
	 * Gets the Color representing this Player in the current game
	 * @return the Color representing this Player
	 */
	public CatanColor getColor();
	
	/**
	 * Gets the id of this Player
	 * @return the id number of this Player
	 */
	public int getId();
	
	/**
	 * normal to String metod
	 * @return a string of the player
	 */
	public String toString();
	
	/**
	 * Gets the total number of victory points earned by this Player
	 * @return the number of victory points this Player has earned
	 */
	public int getPoints();
	
	/**
	 * determines if the player has the resources to accept the trade
	 * @param trade the list of cards offered and desired
	 * @return true if the player has the resources to make the trade
	 */
	public boolean canAcceptTrade(TradeInterface trade);
	
	/**
	 * verifies that the player has the correct cards to be able to discard
	 * @param list the list of cards the player wants to discard
	 * @return true if the player has the cards in the list
	 */
	public boolean canDiscardCards(ResourceList list);
	
	/**
	 * The number of cards in the player's resource deck
	 * @return the number of cards currently in the players deck
	 */
	public int getNumberOfCards();
}
