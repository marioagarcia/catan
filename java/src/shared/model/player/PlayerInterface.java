package shared.model.player;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
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
	 * accept the given trade object, adding to/deducting from Player's resources as needed
	 * @param trade the Trade object to accept
	 */
	public void acceptTrade(TradeInterface trade);
	
	/**
	 * verifies that the player has the correct cards to be able to discard
	 * @param list the list of cards the player wants to discard
	 * @return true if the player has the cards in the list
	 */
	public boolean canDiscardCards(ResourceList list);
	
	
	/**
	 * discard the given cards
	 * @param list ResourceList the cards to discard
	 */
	public void discardCards(ResourceList list);
	
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
	 * make the given MaritimeTrade
	 * @param trade MaritimeTrade to make
	 */
	public void makeMaritimeTrade(MaritimeTrade  trade);
	
	/**
	 * determines whether or not the Player can make the given trade
	 * @param trade the TradeInterface object representing the offered trade
	 * @return boolean whether or not the Player may offer the given trade
	 */
	public boolean canOfferTrade(TradeInterface trade);
	
	/**
	 * Performs the trade: adds and removes the resources specified in the trade
	 * 
	 * @param trade The object that contains the index of the player offering the trade, the index of the player
	 * receiving the trade, and the resources that will be exchanged
	 */
	public void makeDomesticTrade(TradeInterface trade);
	
	/**
	 * determines whether or not the Player may buy a dev card
	 * @return boolean, whether or not the Player may purchase a dev card
	 */
	public boolean canBuyDevCard();
	
	/**
	 * buy a dev card
	 */
	public void buyDevCard(DevCardType type);
	
	/**
	 * determines whether or not the Player can buy a year of plenty card
	 * @return boolean whether or not the Player can play a year of plenty card
	 */
	public boolean canPlayYearOfPlenty();

	/**
	 * play a year of plenty card
	 * @param type1 ResourceType the first resource that we should receive
	 * @param type2 ResourceType the second resource that we should receive
	 */
	public void playYearOfPlenty(ResourceType type1, ResourceType type2);
	/**
	 * determines whether or not the Player can play a road building card
	 * @return boolean whether or not the Player can play a road building card
	 */
	public boolean canPlayRoadBuilding();
	
	/**
	 * play a roadbuilding card
	 */
	public void playRoadBuilding();
	
	/**
	 * determines whether or not the Player can Play a Soldier card
	 * @return boolean whether or not the Player can play a Soldier card
	 */
	public boolean canPlaySoldier();
	
	/**
	 * play a soldier card
	 * @param stolen_resource ResourceType the type of resource (if any) which was stolen). Null if nothing was stolen
	 */
	public void playSoldier(ResourceType stolen_resource);
	
	/**
	 * determines whether or not the Player can play a Monument card
	 * @return boolean whether or not the Player can play a Monument card
	 */
	public boolean canPlayMonument();
	
	/**
	 * play a monument card
	 */
	public void playMonument();
	
	/**
	 * determines whether or not the Player can play a Monopoly card
	 * @return boolean whether or not the Player can play a Monopoly card
	 */
	public boolean canPlayMonopoly();
	
	/**
	 * play a monopoly card
	 * @param type ResourceType the type of resource taken
	 * @param number_of_resource_taken int the number of that resource taken
	 */
	public void playMonopoly(ResourceType type, int number_of_resource_taken);
	
	/**
	 * determines whether or not the Player can build a road
	 * @return boolean whether or not the Player can build a road
	 */
	public boolean canBuildRoad();
	
	/**
	 * build a Road
	 * @param free boolean whether or not the Road is free
	 */
	public void buildRoad(boolean free);
	
	/**
	 * determines whether or not the Player can build a Settlement
	 * @return boolean whether or not the Player can build a Settlement
	 */
	public boolean canBuildSettlement();
	
	/**
	 * build a Settlement
	 * @param free boolean whether or not the Settlement is free
	 */
	public void buildSettlement(boolean free);
	
	/**
	 * determines whether or not the Player can build a City
	 * @return boolean whether or not the Player can build a City
	 */
	public boolean canBuildCity();
	
	/**
	 * build a City
	 */
	public void buildCity();
	
	/**
	 * rob the player
	 * @return ResourceType the type of resource stolen
	 */
	public ResourceType rob();

	/**
	 * incorporates the resources gained from a roll
	 * @param resource_list the resources gained from the roll
	 */
	public void addRollResources(ResourceList resource_list);

	/**
	 * gets called when the monopoly card is played by another player
	 * @param resource_type the type of resource cards to be given up
	 * @return the number of cards that were given up
	 */
	int giveUpCards(ResourceType resource_type);
}
