package client.manager;
import client.manager.interfaces.GMDomesticTradeInterface;
import client.model.GameInfo;
import client.model.card.*;
import client.model.map.*;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.*;
/**
 * This class ensures that the preconditions for the actions the different model classes
 * attempt are met.
 */
public interface GameManagerInterface 
{
	/**
	 * Checks that the player has a valid catan.user cookie set, the player
	 * is already in the game or there is an available spot in the game,
	 * and the color submitted is a valid color
	 * 
	 * @param player The player who wants to join the game
	 * @param game The game the player wants to join
	 * @param color The color the player submitted
	 * @return true if the player has a valid user cookie set, is already in
	 * the game or there is an available spot in the game, and the color
	 * is valid, false otherwise
	 */
	public boolean canJoinGame(CatanColor color, GameInfo game);
	public boolean joinGame(CatanColor color, GameInfo game);
	
	/**
	 * Checks that the player has a valid user and a valid game id
	 * 
	 * @param player The player trying to get the game model
	 * @param game The game the player is participating in
	 * @return true if the player has a valid user id and a valid game id, 
	 * false otherwise 
	 */
	public boolean resetGame();
	
	/**
	 * Checks that the player has a valid user id and a valid game id
	 * 
	 * @param player The player trying to get the game model
	 * @param game The game the player is participating in
	 * @return true if the player has a valid user id and a valid game id, 
	 * false otherwise  
	 */
	public boolean getGameCommands();
	
	/**
	 * Checks that the player has a valid user id and a valid game id
	 * 
	 * @param player The player trying to get the game model
	 * @param game The game the player is participating in
	 * @return true if the player has a valid user id and a valid game id, 
	 * false otherwise  
	 */
	public boolean postGameCommands();
	
//	/**
//	 * Checks that the poster specifies a valid logging level. Valid values
//	 * include: SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST
//	 * 
//	 * @param level The logging level the poster is specifying
//	 * @return true if the logging level is valid, false otherwise
//	 */
//	public boolean canChangeLogLevel(String level);
	
	/**
	 * Checks that the player being offered the trade has the resources that the person whose
	 * turn it is wants
	 *
	 * @param player The player who has been offered a trade
	 * @param object representing the conditions of a trade. Resources, etc.
	 * @return true if the player has the resources for a trade, false otherwise
	 */
	public boolean canAcceptTrade(TradeInterface trade);
	public boolean acceptTrade(TradeInterface trade, boolean accept);
	/**
	 * Checks that a player has over 7 cards and that the player has the cards
	 * being discarded
	 * 
	 * @param player The player attempting to discard cards
	 * @return true if the player has over 7 cards and the player has the cards
	 * being discarded, false otherwise
	 */
	public boolean canDiscardCards(ResourceList list);
	public boolean discardCards(ResourceList list);
	/**
	 * Checks that it is the player's turn and that the model status is "rolling"
	 * 
	 * @param player The player trying to roll
	 * @return true if it is the player's turn and the model status is "rolling",
	 * false otherwise
	 */
	public boolean canRoll();
	public boolean roll();
	
	/**
	 * Checks that the road location is open, the road location is connected to 
	 * another road, the road location is not on water, the player 1 brick and 1 wood
	 * 
	 * 
	 * @param player The player wanting to build a road
	 * @param location The location the player wants to build a road on
	 * @return true if the location is open, connected to another road, is not on water, 
	 * and if the player has the necessary resources, false otherwise
	 */
	public boolean canBuildRoad(EdgeLocation location);
	public boolean buildRoad(EdgeLocation location);
	
	/**
	 * Checks that the settlement location is open, not on water, connected to 
	 * one of the player's roads, and that the player has 1 wheat, 1 sheep, 1 brick, 1 wood
	 *  
	 * 
	 * @param player The player trying to build a settlement
	 * @param location The location the player wants to build a settlement on
	 * @return true if the location is open, not on water, connected to one of the
	 * player's roads, and the player has the resources to build a settlement,
	 * false otherwise
	 */
	public boolean canBuildSettlement(VertexLocation location);
	public boolean buildSettlement(VertexLocation location);
	/**
	 * Checks that the player has a settlement on the location where the player wants
	 * to build a city and that the player has 3 ore and 2 wheat (possibly vice versa)
	 * 
	 * @param player The player wanting to build a city
	 * @param location The location where the player wants to build a city
	 * @return true if it was successful
	 */
	public boolean canBuildCity(VertexLocation location);
	public boolean buildCity(VertexLocation location);
	
	/**
	 * Checks that the player has the resources that he is offering in the trade
	 * 
	 * @param player The player offering the trade
	 * @return true if the player has the resources to offer a trade, false otherwise
	 */
	public boolean canOfferTrade(TradeInterface trade);
	public boolean offerTrade(GMDomesticTradeInterface trade, int otherPlayerIndex);
	
	/**
	 * Checks that the player has a city or a settlement at the location and has either 
	 * 2 resources corresponding to the type of harbor or 3 resources corresponding to
	 * the type of harbor
	 * 
	 * @param player The player wanting to make the maritime trade
	 * @return true if the player has the resources to make a maritime trade, false
	 * otherwise
	 */
	public boolean canMaritimeTrade(VertexLocation location, MaritimeTrade trade);
	public boolean maritimeTrade(VertexLocation location, MaritimeTrade trade);
	
	/**
	 * Checks that the client model status is "playing"
	 * 
	 * @param model The client model
	 * @return true if the client model status is "playing", false otherwise
	 */
	public boolean canFinishTurn(); //do we decide this locally or do we ask the server?
	public boolean finishTurn();
	
	/**
	 * Checks that the player has 1 sheep, 1 wheat, and 1 ore and that 
	 * the bank has dev cards left
	 * 
	 * @param player The player wanting to buy a dev card
	 * @param CardBank The bank holds the dev cards
	 * @return true if the player has the resources to buy a dev card and 
	 * the bank has dev cards left, false otherwise
	 */
	public boolean canBuyDevCard();
	public boolean buyDevCard();
	
	//dev cards
	
	/**
	 * Checks that the two resources the player specifies are in the bank
	 * 
	 * @param player The player wanting to play the year of plenty card
	 * @param CardBank The bank holds the dev cards
	 * @return true if the resources the player specifies are in the bank, false
	 * otherwise
	 */
	public boolean canPlayYearOfPlenty(ResourceType type1, ResourceType type2);
	public boolean playYearOfPlenty(ResourceType type1, ResourceType type2);
	/**
	 * Checks that the first road location is connected to one of the player's
	 * roads, the second road location is connected to one of the player's roads
	 * or the previous location, neither location is on water, the player has
	 * two roads
	 * 
	 * @param player The player trying to place a road building
	 * @param location1 The first road location
	 * @param location2 The second road location
	 * @return true if the first road location is connected to one of the player's
	 * roads, the second location is connected to one of the player's roads or the
	 * previous location, neither location is on water, and the player has two
	 * roads, false otherwise
	 */
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2);
	public boolean playRoadBuilding(EdgeLocation location1, EdgeLocation location2);
	
	/**
	 * Checks that the robber isn't being kept in the same place and that the 
	 * player to rob has cards
	 * 
	 * @param player The player being robbed
	 * @param oldLocation The location the robber is coming from
	 * @param newLocation The location the robber is going to
	 * @return true if successful 
	 */
	public boolean canPlaySoldier(HexLocation oldLocation, HexLocation newLocation, int victimIndex);
	public boolean playSoldier(HexLocation newLocation, int victimIndex);
	/**
	 * Checks are completed in canPlayDevCard() so always returns true
	 * @return true
	 */
	public boolean canPlayMonopoly();
	public boolean playMonopoly(ResourceType resourceType);
	
	/**
	 * Checks are completed in canPlayDevCar() so always returns true
	 * @return true
	 */
	public boolean canPlayMonument();
	public boolean playMonument();
	
	public boolean populateGameList();
	
	public boolean registerPlayer(String username, String password);
	
	public boolean loginPlayer(String username, String password);
	
	public boolean createNewGame(String gameName, boolean randTiles, boolean randNumbers, boolean randPorts);
	
	public boolean saveGame();
	
	public boolean sendChat(String chatMessage);
	
	public boolean robPlayer(int victimPlayerIndex, HexLocation location);
	
}
