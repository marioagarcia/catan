package server.facade;

import java.util.Observer;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.GameInfo;
import shared.model.card.MaritimeTrade;
import shared.model.card.ResourceList;
import shared.model.card.TradeInterface;
import shared.model.manager.GameData;
import shared.model.manager.GameList;
import shared.model.manager.interfaces.GMDomesticTradeInterface;

public interface ServerModelFacadeInterface {
	/**
	 * adds an observer to the game's model
	 * this allows the observer to be notified when the game model is updated
	 * you must first be in a game to call this method
	 * @param observer the Observer which is to be added to the model
	 */
	public void addObserver(int game_id, Observer observer);

	/**
	 * sends a request to the server to login the player with the given credentials
	 * @param username the name of the user
	 * @param password the password of the user
	 * @return true if the player was successfully logged in
	 */
	public boolean loginPlayer(String username, String password);
	
	/**
	 * sends a request to the server to register the player with the given credentials
	 * @param username the name of the user
	 * @param password the password of the user
	 * @return true if the player was successfully registered
	 */
	public boolean registerPlayer(String username, String password);
	/**
	 * Sends a request to the server to create a new game with the given information
	 * @param gameName title of the game
	 * @param randTiles whether or not the player wants to randomize the tiles
	 * @param randNumbers whether or not the player wants to randomize the chits
	 * @param randPorts whether or not the player wants to randomize the ports
	 * @return true if a new game was created and the player joined it
	 */
	public boolean createNewGame(String gameName, boolean randTiles, boolean randNumbers, boolean randPorts);

	/**
	 * Checks that the player has a valid catan.user cookie set, the player
	 * is already in the game or there is an available spot in the game,
	 * and the color submitted is a valid color
	 * 
	 * @param game_id The game the player wants to join
	 * @param color The color the player submitted
	 * @return true if the player has a valid user cookie set, is already in
	 * the game or there is an available spot in the game, and the color
	 * is valid, false otherwise
	 */
	public boolean canJoinGame(int game_id, int player_id, CatanColor color);
	
	/**
	 * Adds the player to the specified game
	 * @param game_id the id of the desired game
	 * @param player_id the player that should be added
	 * @param color the color that the player choose
	 * @return  true if the player was successfully added
	 */
	public boolean joinGame(int game_id, int player_id, CatanColor color);
	

	/**
	 * Saves the current game
	 * @return true if the server was able to save the game
	 */
	public boolean saveGame(int game_id);
	
	/**
	 * Loads a game by the name you saved it under (note - that allows you to save multiple versions of the same game.) 
	 * The game_name is the same as the original game name.
	 * @return true if the game was correctly loaded
	 */
	public boolean loadGame(String game_name);
	
	/**
	 * Retrieves the game model associated with a game id
	 * 
	 * @param game_id The id of the game being retrieved
	 * @return The game model associated with the given id
	 */
	public GameData getGameModel(int game_id);
	
	/**
	 * Checks that the player has a valid user and a valid game id
	 * 
	 * @return true if the player has a valid user id and a valid game id, 
	 * false otherwise 
	 */
	public boolean resetGame(int game_id);
	
	/**
	 * Checks that the player has a valid user id and a valid game id
	 * 
	 * @return true if the player has a valid user id and a valid game id, 
	 * false otherwise  
	 */
	public boolean getGameCommands(int game_id);
	
	/**
	 * Checks that the player has a valid user id and a valid game id
	 * 
	 * @return true if the player has a valid user id and a valid game id, 
	 * false otherwise  
	 */
	public boolean postGameCommands(int game_id);
	
	/**
	 * There are no preconditions so just checks for a valid palyer and game cookie
	 * @return true if the user is in the game
	 */
	public boolean canSendChat(int game_id, int player_index);
	
	/**
	 * Adds a chat sent by the specific player
	 * @param game_id the specific game id
	 * @param player_index they player sending the chat
	 * @param chatMessage the message that was sent
	 * @return true if the chat was added successfully
	 */
	public boolean sendChat(int game_id, int player_index, String chatMessage);
	
	/**
	 * Checks that the player being offered the trade has the resources that the person whose
	 * turn it is wants
	 *
	 * @param trade representing the conditions of a trade. Resources, etc.
	 * @return true if the player has the resources for a trade, false otherwise
	 */
	public boolean canAcceptTrade(int game_id, int player_index, TradeInterface trade);
	
	/**
	 * Accepts or rejects the trade being offered
	 * @param game_id the specific game
	 * @param player_index the index of the player accepting or rejecting the trade
	 * @param trade the cards being offered
	 * @param accept true if the player accepted the offer
	 * @return true if the trade was recorded successfully
	 */
	public boolean acceptTrade(int game_id, int player_index, TradeInterface trade, boolean accept);
	
	/**
	 * Checks that a player has over 7 cards and that the player has the cards
	 * being discarded
	 * 
	 * @param list the list of cards to be discarded
	 * @return true if the player has over 7 cards and the player has the cards
	 * being discarded, false otherwise
	 */
	public boolean canDiscardCards(int game_id, int player_index, ResourceList list);
	
	/**
	 * discards card in the a players hand
	 * @param game_id the specific game 
	 * @param player_index the specific player in the specific game
	 * @param list the list of cards to be discarded
	 * @return true if the cards were discarded successfully
	 */
	public boolean discardCards(int game_id, int player_index, ResourceList list);
	
	/**
	 * Checks that it is the player's turn and that the model status is "rolling"
	 * 
	 * @return true if it is the player's turn and the model status is "rolling",
	 * false otherwise
	 */
	public boolean canRoll(int game_id, int player_index);
	
	/**
	 * rolls the number given for the specific player
	 * @param game_id the specific game 
	 * @param player_index the specific player in the specific game
	 * @param number the number rolled
	 * @return true if the rolled number was correctly recorded
	 */
	public boolean roll(int game_id, int player_index, int number);
	
	/**
	 * Checks that the road location is open, the road location is connected to 
	 * another road, the road location is not on water, the player 1 brick and 1 wood
	 * 
	 * @param location The location the player wants to build a road on
	 * @return true if the location is open, connected to another road, is not on water, 
	 * and if the player has the necessary resources, false otherwise
	 */
	public boolean canBuildRoad(int game_id, int player_index, EdgeLocation location);
	
	/**
	 * Builds a road for the specific player in the specific location
	 * @param game_id the specific game 
	 * @param player_index the specific player in the specific game
	 * @param location the location for the road to be built in
	 * @return true if the road was built correctly
	 */
	public boolean buildRoad(int game_id, int player_index, EdgeLocation location);
	
	/**
	 * Checks that the settlement location is open, not on water, connected to 
	 * one of the player's roads, and that the player has 1 wheat, 1 sheep, 1 brick, 1 wood
	 *  
	 * @param location The location the player wants to build a settlement on
	 * @return true if the location is open, not on water, connected to one of the
	 * player's roads, and the player has the resources to build a settlement,
	 * false otherwise
	 */
	public boolean canBuildSettlement(int game_id, int player_index, VertexLocation location);
	
	/**
	 * Builds a settlement for the specific player in the specific location
	 * @param game_id the specific game 
	 * @param player_index the specific player in the specific game
	 * @param location the location for the settlement to be built in
	 * @return true if the settlement was built correctly
	 */
	public boolean buildSettlement(int game_id, int player_index, VertexLocation location);
	
	/**
	 * Checks that the player has a settlement on the location where the player wants
	 * to build a city and that the player has 3 ore and 2 wheat (possibly vice versa)
	 * 
	 * @param location The location where the player wants to build a city
	 * @return true if it was successful
	 */
	public boolean canBuildCity(int game_id, int player_index, VertexLocation location);
	
	/**
	 * Builds a city for the specific player in the specific location
	 * @param game_id the specific game 
	 * @param player_index the specific player in the specific game
	 * @param location the location for the city to be built in
	 * @return true if the city was built correctly
	 */
	public boolean buildCity(int game_id, int player_index, VertexLocation location);
	
	/**
	 * Checks that the player has the resources that he is offering in the trade
	 * 
	 * @param trade the resources to offer
	 * @return true if the player has the resources to offer a trade, false otherwise
	 */
	public boolean canOfferTrade(int game_id, int player_index, TradeInterface trade);
	
	/**
	 * called when a trade is offered to a specific player
	 * @param game_id the specific game 
	 * @param player_index the specific player in the specific game
	 * @param trade the set of cards to be traded
	 * @param otherPlayerIndex self explanatory 
	 * @return true if the offer was traded correctly
	 */
	public boolean offerTrade(int game_id, int player_index, GMDomesticTradeInterface trade, int otherPlayerIndex);
	
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
	public boolean canMaritimeTrade(int game_id, int player_index, EdgeLocation location, MaritimeTrade trade);
	
	/**
	 * sets a trade with the cardBank and the specific player
	 * @param game_id the specific game 
	 * @param player_index the specific player in the specific game
	 * @param location the location of the port so it can be checked against the player
	 * @param trade the cards to be traded
	 * @return true if the cards were traded correctly
	 */
	public boolean maritimeTrade(int game_id, int player_index, EdgeLocation location, MaritimeTrade trade);
	
	/**
	 * Checks the turn tracker to make sure that the client model status is "playing"
	 * 
	 * @return true if the client model status is "playing", false otherwise
	 */
	public boolean canFinishTurn(int game_id, int player_index);
	
	/**
	 * finished the player's turn in the specific game
	 * @param game_id the specific game 
	 * @param player_index the specific player in the specific game
	 * @return true if the player's turn was finished
	 */
	public boolean finishTurn(int game_id, int player_index);
	
	/**
	 * Checks that the player has 1 sheep, 1 wheat, and 1 ore and that 
	 * the bank has dev cards left
	 * 
	 * @return true if the player has the resources to buy a dev card and 
	 * the bank has dev cards left, false otherwise
	 */
	public boolean canBuyDevCard(int game_id, int player_index);
	
	/**
	 * Gets a development card for the player 
	 * @param game_id The ID of the game to buy a dev card in
	 * @param player_index The index of the player buying the dev card
	 * @return true if the card was given to the player correctly
	 */
	public boolean buyDevCard(int game_id, int player_index);
	
	//dev cards
	
	/**
	 * Checks that the two resources the player specifies are in the bank
	 * 
	 * @param type1 The first type of resource that the player wants
	 * @param type2 The second type of resource that the player wants
	 * @return true if the resources the player specifies are in the bank, false
	 * otherwise
	 */
	public boolean canPlayYearOfPlenty(int game_id, int player_index, ResourceType type1, ResourceType type2);
	
	/**
	 * 
	 * @param game_id The ID of the game this card is being played in
	 * @param player_index The index of the player using this development card
	 * @param type1 The first type of resource that the player wants
	 * @param type2 The second type of resource that the player wants
	 * @return True if the card was played successfully, and the resources requested are in the bank
	 */
	public boolean playYearOfPlenty(int game_id, int player_index, ResourceType type1, ResourceType type2);
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
	public boolean canPlayRoadBuilding(int game_id, int player_index, EdgeLocation location1, EdgeLocation location2);
	
	/**
	 * Plays this dev card. Adds two roads for the given player to the map
	 * @param game_id The ID of the game this card is being used in
	 * @param player_index The index of the player using this development card
	 * @param location1 The location of the first road the player is building
	 * @param location2 The location of the second road the player is building
	 * @return True if the player has enough roads to place two roads, and the two locations are valid
	 */
	public boolean playRoadBuilding(int game_id, int player_index, EdgeLocation location1, EdgeLocation location2);
	
	/**
	 * Checks that the robber isn't being kept in the same place and that the 
	 * player to rob has cards
	 * 
	 * @param oldLocation The location the robber is coming from
	 * @param newLocation The location the robber is going to
	 * @param victimIndex The player being robbed
	 * @return true if successful 
	 */
	public boolean canPlaySoldier(int game_id, int player_index, HexLocation oldLocation, HexLocation newLocation, int victimIndex);
	
	/**
	 * Plays the soldier development card and allows the player to move the robber
	 * @param game_id The ID of the game this card is being played in
	 * @param player_index The index of the player using this card
	 * @param newLocation The new location of the robber
	 * @param victimIndex The index of the player to be robbed
	 * @return True if the robber is moved to a location other than his current, and the victim has cards to steal
	 */
	public boolean playSoldier(int game_id, int player_index, HexLocation newLocation, int victimIndex);
	
	/**
	 * Checks that the it is the player's turn and that the player has the monopoly dev card
	 * @return True if it is the player's turn, and they ave not already played a dev card this turn
	 */
	public boolean canPlayMonopoly(int game_id, int player_index);
	
	/**
	 * Plays the monopoly development card and allows the player to steal all of one type of resource form all other players
	 * @param game_id The ID of the game that this card is being played in
	 * @param player_index The index of the player using this card
	 * @param resourceType The type of resource to steal from all players
	 * @return True if the card was played successfully
	 */
	public boolean playMonopoly(int game_id, int player_index, ResourceType resourceType);
	
	/**
	 * Checks that the it is the player's turn and that the player has the monument dev card
	 * @return True if it is the player's turn and they have at least one monument dev card
	 */
	public boolean canPlayMonument(int game_id, int player_index);
	
	/**
	 * Uses the monument dev card and gives the player 1 victory point
	 * @param game_id The ID of the game the card is being used in
	 * @param player_index The index of the player using the card
	 * @return True if it is the player's turn and they have the monument card
	 */
	public boolean playMonument(int game_id, int player_index);
	
	/**
	 * @return An object containing the current list of games hosted on the server
	 */
	public GameList getGameList();
	
	/**
	 * Retrieves the AIManager which stores the possible AI players to choose from
	 * 
	 * @return An object that contains a list of strings that represent all of the possible AI players
	 */
	public AIManager getAIList();
	
	/**
	 * Retrieves the gameInfo with the given title (used just after a game has been created)
	 * 
	 * @param title The title of the gameInfo being retrieved
	 * @return A GameInfo object consisting of a title, an id, and a list of PlayerInfos
	 */
	public GameInfo getGameInfo(String title);
	
	/**
	 * Verifies the user's credentials in the list of registered users
	 * @param name The username to verify
	 * @param password The password associated with this user
	 * @param id The id associated with this user
	 * @return True if all the user credentials match those stored
	 */
	public boolean verifyUser(String name, String password, int id);
	
	/**
	 * Verifies that a given player is playing in the given game
	 * @param player_id The id of the player to verify
	 * @param game_id The id of the game being checked
	 * @return True if the given game contains the given player
	 */
	public boolean verifyGame(int player_id, int game_id);
}
