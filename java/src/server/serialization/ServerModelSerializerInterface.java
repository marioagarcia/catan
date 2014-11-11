package server.serialization;

import java.util.ArrayList;

import shared.model.GameInfo;
import shared.model.manager.GameCommands;
import shared.model.manager.GameData;
import shared.serialization.parameters.*;

public interface ServerModelSerializerInterface {
	
	/**
	 * Deserializes the json String into a CredentialsParameters object
	 * 
	 * @param jsonString The JSON representation of a CredentialsParameters object
	 * @return An object consisting of two strings,  username and password.
	 */
	public CredentialsParameters deserializeCredentials(String jsonString);
	
	
	/**
	 * Serializes a list of GameInfo objects into a json string
	 * 
	 * @param gamesList A list of GameInfo objects which consist of a game id (int), a
	 * game name (string), and a list of PlayerInfo objects which consists of a player
	 * name (string), player id (int), player index (int), and a color (CatanColor)
	 * @return A json representation of a list of GameInfo objects
	 */
	public String serializeGamesList(ArrayList<GameInfo> gamesList);
	
	/**
	 * Deserializes a json string into a CreateGameRequestParameters object
	 * 
	 * @param jsonString A json representation of a CreateGameRequestParameters 
	 * @return A CreateGameRequestParameters object consisting of a game name (string) and
	 * four booleans representing whether to randomly place tiles, randomly select numbers
	 * for the tiles, and randomly place ports
	 */
	public CreateGameRequestParameters deserializeCreateGameRequest(String jsonString);
	
	/**
	 * Serializes a GameInfo object into a json string
	 * 
	 * @param gameInfo An object consisting of a game name (string), a game id (int), and a
	 * list of PlayerInfo objects consisting of a player name (string), player id (int), 
	 * player index (int), and a color (CatanColor)
	 * @return A json representation of a GameInfo object
	 */
	public String serializeGameInfo(GameInfo gameInfo);
	
	/**
	 * Deserializes a json string into a JoinGameParameters object
	 * 
	 * @param jsonString A json representation of JoinGameParameters
	 * @return A JoinGameParameters object consisting of a game id (int) and a color (string)
	 */
	public JoinGameParameters deserializeJoinGameRequest(String jsonString);
	
	/**
	 * Deserializes a json string into a SaveGameRequestParameters object
	 * 
	 * @param jsonString A json representation of SaveGameRequestParameters
	 * @return A SaveGameRequestParameters object consisting of a game id (int) and game name (string)
	 */
	public SaveGameParameters deserializeSaveGameRequest(String jsonString);
	
	/**
	 * Deserializes a json string into a LoadGameRequestParameters object
	 * 
	 * @param jsonString A json representation of LoadGameRequestParameters
	 * @return A LoadGameRequestParameters object consisting of a game name (string)
	 */
	public LoadGameRequestParameters deserializeLoadGameRequest(String jsonString);
	
	/**
	 * Deserializes a json string into a LoadGameRequest object
	 * 
	 * @param jsonString A json representation of a list of MasterParameterInterfaces
	 * @return A list of MasterParameterInterfaces
	 */
	public ArrayList<MasterParameterInterface> deserializePostGameCommands(String jsonString);
	
	/**
	 * Serializes a GameCommands object into a json string
	 * 
	 * @param gameCommands An object consisting of a list of GameCommand objects which consists of
	 * a command type (string), a player index (int), and a message (string)
	 * @return A json representation of a GameCommands object
	 */
	public String serializeGetGameCommands(GameCommands gameCommands);
	
	/**
	 * Deserializes a json string into an AIRequestParameters object
	 * 
	 * @param jsonString A json representation of AIRequestParameters
	 * @return An AIRequestParameters object which consists of an AI type (string)
	 */
	public AIRequestParameters deserializeAIRequest(String jsonString);
	
	/**
	 * Serializes an array of AI types (strings) into a json string
	 * 
	 * @param aiList A list of AI types (strings)
	 * @return A json representation of an array of AI types (strings) 
	 */
	public String serializeGetListAI(String[] aiList);
	
	/**
	 * Deserializes a json string into a SendChatParameters object
	 * 
	 * @param jsonString A json representation of SendChatParameters
	 * @return A SendChatParameters object consisting of a type (string), a player
	 * index (int), and a message (string)
	 */
	public SendChatParameters deserializeSendChat(String jsonString);
	
	/**
	 * Deserializes a json string into a RollNumberParameters object
	 * 
	 * @param jsonString A json representation of RollNumberParameters
	 * @return A RollNumberParameters object consisting of a type (string), a player
	 * index (int), and a rolled number (int)
	 */
	public RollNumberParameters deserializeRollNumber(String jsonString);
	
	/**
	 * Deserializes a jsonString into a RobPlayerParameters object
	 * 
	 * @param jsonString A json representation of RobPlayerParameters
	 * @return A RobPlayerParameters object consisting of a type (string), a player index (int),
	 * a victim index (int), and a location (HexLocation)
	 */
	public RobPlayerParameters deserializeRobPlayer(String jsonString);
	
	/**
	 * Deserializes a jsonString into a FinishTurnParameters object
	 * 
	 * @param jsonString A json representation of FinishTurnParameters
	 * @return A FinishTurnParameters object consisting of a type (string) and a player index (int)
	 */
	public FinishTurnParameters deserializeFinishTurn(String jsonString);
	
	/**
	 * Deserializes a jsonString into a BuyDevCardParameters object
	 * 
	 * @param jsonString A json representation of BuyDevCardParameters
	 * @return A BuyDevCardParameters object consisting of a type (string) and a player index (int)
	 */
	public BuyDevCardParameters deserializeBuyDevCard(String jsonString);
	
	/**
	 * Deserializes a jsonString into a YearOfPlentyParameters object
	 * 
	 * @param jsonString A json representation of YearOfPlentyParameters
	 * @return A YearOfPlentyParameters object consisting of a type (string), player index (int), and
	 * two resources (strings)
	 */
	public YearOfPlentyParameters deserializeYearOfPlenty(String jsonString);
	
	/**
	 * Deserializes a jsonString into a RoadBuildingParameters object
	 * 
	 * @param jsonString A json representation of RoadBuildingParameters
	 * @return A RoadBuildingParameters object consisting of a type (string), a player index (int), and
	 * two locations (EdgeLocationParameters)
	 */
	public RoadBuildingParameters deserializeRoadBuilding(String jsonString);
	
	/**
	 * Deserializes a jsonString into a SoldierParameters object
	 * 
	 * @param jsonString A json representation of SoldierParameters
	 * @return A SoldierParameters object consisting of a type (string), a player index (int), a victim
	 * index (int), and location (HexLocation)
	 */
	public SoldierParameters deserializeSoldier(String jsonString);
	
	/**
	 * Deserializes a jsonString into a MonopolyParameters object
	 * 
	 * @param jsonString A json representation of MonopolyParameters
	 * @return A MonopolyParameters object consisting of a type (string), a resource (string), and a player
	 * index (int)
	 */
	public MonopolyParameters deserializeMonopoly(String jsonString);
	
	/**
	 * Deserializes a jsonString into a MonumentParameters object
	 * 
	 * @param jsonString A json representation of MonumentParameters
	 * @return A MonumentParameters object consisting of a type (string) and player index (int)
	 */
	public MonumentParameters deserializeMonument(String jsonString);
	
	/**
	 * Deserializes a jsonString into a BuildRoadParameters object
	 * 
	 * @param jsonString A json representation of BuildRoadParameters
	 * @return A BuildRoadParameters object consisting of a type (string), a player index (int), a road location
	 * (EdgeLocationParameters), and a Boolean representing whether it is free to place this road or not, i.e. whether
	 * it is the setup round
	 */
	public BuildRoadParameters deserializeBuildRoad(String jsonString);
	
	/**
	 * Deserializes a jsonString into a BuildSettlementParameters object
	 * 
	 * @param jsonString A json representation of BuildSettlementParameters
	 * @return A BuildSettlementParameters object consisting of a type (string), a player index (int), a vertex location 
	 * (VertexLocationParameters), and a Boolean representing whether it is free to place this road or not, i.e. whether
	 * it is the setup round
	 */
	public BuildSettlementParameters deserializeSettlement(String jsonString);
	
	/**
	 * Deserializes a jsonString into a BuildCityParameters object
	 * 
	 * @param jsonString A json representation of BuildCityParameters
	 * @return A BuildCityParameters object consisting of a type (string), a player index (int), a vertex location 
	 * (VertexLocationParameters)
	 */
	public BuildCityParameters deserializeBuildCity(String jsonString);
	
	/**
	 * Deserializes a jsonString into a OfferTradeParameters object
	 * 
	 * @param jsonString A json representation of OfferTradeParameters
	 * @return An OfferTradeParameters object consisting of a type (string), a player index (int), a resource list, and a
	 * receiver index (int)
	 */
	public OfferTradeParameters deserializeOfferTrade(String jsonString);
	
	/**
	 * Deserializes a jsonString into a AcceptTradeParameters object
	 * 
	 * @param jsonString A json representation of AcceptTradeParameters
	 * @return An AcceptTradeParameters object consisting of a type (string), a player index (int), and a Boolean representing
	 * whether the player accepts the trade offer or not
	 */
	public AcceptTradeParameters deserializeAcceptTrade(String jsonString);
	
	/**
	 * Deserializes a jsonString into a MaritimeTradeParameters object
	 * 
	 * @param jsonString A json representation of MaritimeTradeParameters
	 * @return A MaritimeTradeParameters object consisting of a type (string), a player index (int), a ratio (int), an input
	 * resource (string), and output resource (string)
	 */
	public MaritimeTradeParameters deserializeMaritimeTrade(String jsonString);
	
	/**
	 * Deserializes a jsonString into a DiscardCardsParameters object
	 * 
	 * @param jsonString A json representation of DiscardCardsParameters
	 * @return A DiscardCardsParameters object consisting of a type (string), a player index (int), and discarded cards 
	 * (SerializerResourceListInterface)
	 */
	public DiscardCardsParameters deserializeDiscardCards(String jsonString);
	
	/**
	 * Serializes a GameData object into a json string
	 * 
	 * @param gameData An object that represents the game model
	 * @return A json representation of a GameData object
	 */
	public String serializeGameModel(GameData gameData);
	
	

}
