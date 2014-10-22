package client.communication.server;

import client.model.player.Player;

/**
 * This interface is our last step to communicating with the server. It contains the methods that implement
 * the server's API. This interface calls handles the HTTP connection
 *
 */
public interface ServerProxyInterface{
	
	abstract int getPlayerId();
	abstract int getGameId();
	abstract int getLatestVersionNumber();
	
	/**
	 * Attempts to log a player into the game
	 * @param JSONString that contains a user name and a password
	 * @return String with the result 
	 */
	abstract String login(String JSONString);
	
	
	abstract String register(String JSONString);
	
	/**
	 * Retrieves data about each game currently running on the server
	 * @return JSONString containing data about all the games currently running on the server
	 */
	abstract String listGames();
	
	/**
	 * Creates a new, empty Settlers of Catan game on the server
	 * @param JSONString a json string with the info to create the game with
	 * @return JSONString containing the name, id, and list of players for the new game
	 */
	abstract String createGame(String JSONString);
	
	/**
	 * Adds a player to the game and sets their game cookie
	 * @param JSONString that contains a player cookie and a color
	 * @return JSONString a result
	 */
	abstract String joinGame(String JSONString);
	
	/**
	 * Requests the model of a given game
	 * received by the client
	 * @return JSONString for the model of the current game
	 */
	abstract String getGameModel();
	
	/**
	 * Resets the game to its initial state immediately after players joined
	 * @return String with the reset model after game was reset
	 */
	abstract String resetGame();
	
	/**
	 * Retrieves a list of all actions taken by all players in the current game
	 * @return JSONstring with the information requested
	 */
	abstract String getGameCommands();

	/**
	 * Adds a list of actions from a specific player to the current game
	 * @param JSONString with a player cookie and a list of commands
	 * @return JSONString with the result
	 */
	abstract String postGameCommands(String JSONString);

	/**
	 * Retrieves a list of all the available AI players to the list
	 * @return JSONString with list
	 */
	abstract String getAIList();

	/**
	 * Adds an AI player to the current game
	 * @param JSONString with the ai player to be added to the current game
	 * @return JSONString with the result
	 */
	abstract String postNewAI(String JSONString);
	
	/**
	 * Sets the level of detail used by the server's logger
	 * @param JSONString with the level of detail
	 * @return JSONString with the result
	 */
	abstract String utilChangeLogLevel(String JSONString);
	
	
	//move commands

	/**
	 * Posts a chat message from a Player to the server
	 * @param JSONString with a player cookie, a game id, and the content of the message
	 * @return JSONString with the result
	 */
	abstract String sendChat(String JSONString);

	/**
	 * Signals to the server that a Player has accepted the trade being offered to them
	 * @param JSONString with the player and the reply
	 * @return JSONString with the result
	 */
	abstract String acceptTrade(String JSONString);

	/**
	 * Informs the server that a Player is discarding cards from their hand
	 * @param JSONString with the player id and the list of cards to be discarded
	 * @return JSONString with the result
	 */
	abstract String discardCards(String JSONString);

	/**
	 * Updates the server with the number rolled by the current Player during their turn
	 * @param JSONString with a player id, a game id, and the number rolled (integer 2-12)
	 * @return JSONString with the result
	 * throws PlayerOutOfTurn
	 */
	abstract String rollNumber(String JSONString);

	/**
	 * Requests a road to be built for a player at a specific location
	 * @param JSONString with player id, game id, location, and a bool that says if the piece is for free
	 * @return JSONString with the result
	 * throws PlayerOutOfTurn
	 */
	abstract String buildRoad(String JSONString);
	
	/**
	 * Requests a settlement to be built for a player at a specific location
	 * @param JSONString with player id, game id, location, and a bool that says if the piece is for free
	 * @return JSONString with the result
	 * throws PlayerOutOfTurn
	 */
	abstract String buildSettlement(String JSONString);
	
	/**
	 * Requests a city to be built for a player at a specific location
	 * @param JSONString with player id, game id, location
	 * @return JSONString with the result
	 * throws PlayerOutOfTurn
	 */
	abstract String buildCity(String JSONString);
	
	/**
	 * Requests a trade to be done between two players
	 * @param JSONString with the offering player's id, the other player's index, and the cards to be offered
	 * @return JSONString with the result
	 * throws NotEnoughResources
	 */
	abstract String offerTrade(String JSONString);
	
	/**
	 * Requests a maritime trade to be done between two players
	 * @param JSONString with the player's id, ration of conversion, type of in-resource, type of out-resource
	 * @return JSONString with the result
	 * throws NotEnoughResources
	 */
	abstract String maritimeTrade(String JSONString);
	
	/**
	 * Notifies the server that the player has finished his turn
	 * @param JSONString with the player cookie
	 * @return JSONString with the result
	 */
	abstract String finishTurn(String JSONString);
	
	/**
	 * Notifies the server that the player wants to buy a developer card
	 * @param JSONString with the player cookie
	 * @return JSONString with the result
	 * throws NotEnoughResources, NoDevCardsLeft
	 */
	abstract String buyDevCard(String JSONString);
	
	//dev cards
	
	/**
	 * Notifies the server that the player wants to play a Year of Plenty card
	 * @param JSONString with the player cookie
	 * @return JSONString with the result
	 * throws NotEnoughResourcesInBank
	 */
	abstract String playYearOfPlenty(String JSONString);
	
	/**
	 * Notifies the server that the player wants to play a Year of Plenty card
	 * @param JSONString with the player cookie, location one and location two
	 * @return JSONString with the result
	 * throws RoadNotConnecteToPlayer, LocationOnWater, NotEnoughResources
	 */
	abstract String playRoadBuilding(String JSONString);
	
	/**
	 * Notifies the server that the player wants to play a Soldier card
	 * @param JSONString with the player cookie, hex-location one and index of victim player
	 * @return JSONString with the result
	 * throws RobberAlreadyAtLocation, VictimNotEnoughResources
	 */
	abstract String playSoldier(String JSONString);
	
	/**
	 * Notifies the server that the player wants to play a Monopoly card
	 * @param JSONString with the player cookie, and resource 
	 * @return JSONString with the result
	 */
	abstract String playMonopoly(String JSONString);
	
	/**
	 * Notifies the server that the player wants to play a Monument card
	 * @param JSONString with the player cookie, hex-location one and index of victim player
	 * @return JSONString with the result
	 */
	abstract String playMonument(String JSONString);
	
	abstract String robPlayer(String JSONString);
	
	abstract boolean validatePlayer(Player player);
}