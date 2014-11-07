package shared.model.player;

import shared.definitions.CatanColor;
import shared.model.card.MaritimeTrade;
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
	 * @return the CatanColor representing this Player
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
	
	/**
	 * determines whether it is possible for the Player to make the given Maritime trade
	 * @param trade the offered MaritimeTrade
	 * @return boolean whether or not the Player may make the given MaritimeTrade
	 */
	public boolean canMaritimeTrade(MaritimeTrade trade);
	
	/**
	 * determines whether or not the Player can make the given trade
	 * @param trade the TradeInterface object representing the offered trade
	 * @return boolean whether or not the Player may offer the given trade
	 */
	public boolean canOfferTrade(TradeInterface trade);
	
	/**
	 * determines whether or not the Player may buy a dev card
	 * @return boolean, whether or not the Player may purchase a dev card
	 */
	public boolean canBuyDevCard();
	
	/**
	 * determines whether or not the Player can buy a year of plenty card
	 * @return boolean whether or not the Player can play a year of plenty card
	 */
	public boolean canPlayYearOfPlenty();
	
	/**
	 * determines whether or not the Player can play a road building card
	 * @return boolean whether or not the Player can play a road building card
	 */
	public boolean canPlayRoadBuilding();
	
	/**
	 * determines whether or not the Player can Play a Soldier card
	 * @return boolean whether or not the Player can play a Soldier card
	 */
	public boolean canPlaySoldier();
	
	/**
	 * determines whether or not the Player can play a Monument card
	 * @return boolean whether or not the Player can play a Monument card
	 */
	public boolean canPlayMonument();
	
	/**
	 * determines whether or not the Player can play a Monopoly card
	 * @return boolean whether or not the Player can play a Monopoly card
	 */
	public boolean canPlayMonopoly();
	
	
	
	
}
