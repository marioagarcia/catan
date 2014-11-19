package server.manager;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.card.ResourceList;
import shared.model.manager.GameData;

public interface ServerGameManagerInterface {

	/**
	 * Game Data is the model classes that are needed in the game
	 * @return the game data
	 */
	public GameData getGameData();

	/**
	 * Adds an AI to the game
	 * @return true if it was correctly added to the current game
	 */
	public boolean addAIPlayer(String ai_type);

	/**
	 * Lists the available AI types that may be added to a game
	 * @return
	 */
	public String[] listAIPlayers();

	/**
	 * There are no preconditions so just checks for a valid player and game cookie
	 * @return true if the user is in the game
	 */
	public boolean canSendChat(int player_index);
	public boolean sendChat(int player_index, String chatMessage);

	/**
	 * Checks that the player being offered the trade has the resources that the person whose
	 * turn it is wants
	 *
	 * @param player_index representing the player who the trade is offered to
	 * @return true if the player has the resources for a trade, false otherwise
	 */
	public boolean canAcceptTrade(int player_index);
	public boolean acceptTrade(int player_index, boolean accept);

	/**
	 * Checks that a player has over 7 cards and that the player has the cards
	 * being discarded
	 * 
	 * @param list the list of cards to be discarded
	 * @return true if the player has over 7 cards and the player has the cards
	 * being discarded, false otherwise
	 */
	public boolean canDiscardCards(int player_index, ResourceList list);
	public boolean discardCards(int player_index, ResourceList list);

	/**
	 * Checks that it is the player's turn and that the model status is "rolling"
	 * 
	 * @return true if it is the player's turn and the model status is "rolling",
	 * false otherwise
	 */
	public boolean canRoll(int player_index);
	public boolean roll(int player_index, int number_rolled);

	/**
	 * Checks that the road location is open, the road location is connected to 
	 * another road, the road location is not on water, the player 1 brick and 1 wood
	 * 
	 * @param location The location the player wants to build a road on
	 * @return true if the location is open, connected to another road, is not on water, 
	 * and if the player has the necessary resources, false otherwise
	 */
	public boolean canBuildRoad(int player_index, EdgeLocation location);
	public boolean buildRoad(int player_index, EdgeLocation location);

	/**
	 * Checks that the settlement location is open, not on water, connected to 
	 * one of the player's roads, and that the player has 1 wheat, 1 sheep, 1 brick, 1 wood
	 *  
	 * @param location The location the player wants to build a settlement on
	 * @return true if the location is open, not on water, connected to one of the
	 * player's roads, and the player has the resources to build a settlement,
	 * false otherwise
	 */
	public boolean canBuildSettlement(int player_index, VertexLocation location);
	public boolean buildSettlement(int player_index, VertexLocation location);
	/**
	 * Checks that the player has a settlement on the location where the player wants
	 * to build a city and that the player has 3 ore and 2 wheat (possibly vice versa)
	 * 
	 * @param location The location where the player wants to build a city
	 * @return true if it was successful
	 */
	public boolean canBuildCity(int player_index, VertexLocation location);
	public boolean buildCity(int player_index, VertexLocation location);

	/**
	 * Checks that the player has the resources that he is offering in the trade
	 * 
	 * @param resources the resource cards in question
	 * @return true if the player has the resources to offer a trade, false otherwise
	 */
	public boolean canOfferTrade(int player_index, ResourceList resources);
	public boolean offerTrade(int player_index, ResourceList resources, int otherPlayerIndex);

	/**
	 * Checks that the player has a city or a settlement at the location and has either 
	 * 2 resources corresponding to the type of harbor or 3 resources corresponding to
	 * the type of harbor
	 * 
	 * @param location The location of the port so it can be checked against the player
	 * @param trade The cards that are being traded with the bank
	 * @return true if the player has the resources to make a maritime trade, false
	 * otherwise
	 */
	public boolean canMaritimeTrade(int player_index, int ratio, ResourceType resource_in, ResourceType resource_out);
	public boolean maritimeTrade(int player_index, int ratio, ResourceType resource_in, ResourceType resource_out);

	/**
	 * Checks the turn tracker to make sure that the client model status is "playing"
	 * 
	 * @return true if the client model status is "playing", false otherwise
	 */
	public boolean canFinishTurn(int player_index);
	public boolean finishTurn(int player_index);

	/**
	 * Checks that the player has 1 sheep, 1 wheat, and 1 ore and that 
	 * the bank has dev cards left
	 * 
	 * @return true if the player has the resources to buy a dev card and 
	 * the bank has dev cards left, false otherwise
	 */
	public boolean canBuyDevCard(int player_index);
	public boolean buyDevCard(int player_index);

	//dev cards

	/**
	 * Checks that the two resources the player specifies are in the bank
	 * 
	 * @param type1 The first type of resource that the player wants
	 * @param type2 The second type of resource that the player wants
	 * @return true if the resources the player specifies are in the bank, false
	 * otherwise
	 */
	public boolean canPlayYearOfPlenty(int player_index, ResourceType type1, ResourceType type2);
	public boolean playYearOfPlenty(int player_index, ResourceType type1, ResourceType type2);
	/**
	 * Checks that the first road location is connected to one of the player's
	 * roads, the second road location is connected to one of the player's roads
	 * or the previous location, neither location is on water, the player has
	 * two roads
	 * 
	 * @param location1 The first road location
	 * @param location2 The second road location
	 * @return true if the first road location is connected to one of the player's
	 * roads, the second location is connected to one of the player's roads or the
	 * previous location, neither location is on water, and the player has two
	 * roads, false otherwise
	 */
	public boolean canPlayRoadBuilding(int player_index, EdgeLocation location1, EdgeLocation location2);
	public boolean playRoadBuilding(int player_index, EdgeLocation location1, EdgeLocation location2);

	/**
	 * Checks that the robber isn't being kept in the same place and that the 
	 * player to rob has cards
	 * 
	 * @param oldLocation The location the robber is coming from
	 * @param newLocation The location the robber is going to
	 * @param victimIndex The player being robbed
	 * @return true if successful
	 */
	public boolean canPlaySoldier(int player_index, HexLocation newLocation, int victimIndex);
	public boolean playSoldier(int player_index, HexLocation new_location, int victim_index);

	/**
	 *
	 * @param player_index
	 * @param victim_player_index
	 * @param location
	 * @return
	 */
	public boolean robPlayer(int player_index, int victim_player_index, HexLocation location);

	/**
	 * Checks that the it is the player's turn and that the player has the monopoly dev card
	 * @return true
	 */
	public boolean canPlayMonopoly(int player_index);
	public boolean playMonopoly(int player_index, ResourceType resource_type);

	/**
	 * Checks that the it is the player's turn and that the player has the monument dev card
	 * @return true
	 */
	public boolean canPlayMonument(int player_index);
	public boolean playMonument(int player_index);

}
