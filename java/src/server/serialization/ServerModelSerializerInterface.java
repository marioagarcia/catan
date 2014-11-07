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
	 * Deserializes a json string into a SaveGameRequestParameters
	 * 
	 * @param jsonString A json representation of SaveGameRequestParameters
	 * @return A SaveGameRequestParameters object consisting of a game id (int) and game name (string)
	 */
	public SaveGameRequestParameters deserializeSaveGameRequest(String jsonString);
	
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
	 * @param gameCommands 
	 * @return
	 */
	public String serializeGetGameCommands(GameCommands gameCommands);
	
	public AIRequestParameters deserializeAIRequest(String jsonString);
	
	public String serializeGetListAI(String[] aiList);
	
	public SendChatParameters deserializeSendChat(String jsonString);
	
	public RollNumberParameters deserializeRollNumber(String jsonString);
	
	public RobPlayerParameters deserializeRobPlayer(String jsonString);
	
	public FinishTurnParameters deserializeFinishTurn(String jsonString);
	
	public BuyDevCardParameters deserializeBuyDevCard(String jsonString);
	
	public YearOfPlentyParameters deserializeYearOfPlenty(String jsonString);
	
	public RoadBuildingParameters deserializeRoadBuilding(String jsonString);
	
	public SoldierParameters deserializeSoldier(String jsonString);
	
	public MonopolyParameters deserializeMonopoly(String jsonString);
	
	public MonumentParameters deserializeMonument(String jsonString);
	
	public BuildRoadParameters deserializeBuildRoad(String jsonString);
	
	public BuildSettlementParameters deserializeSettlement(String jsonString);
	
	public BuildCityParameters deserializeBuildCity(String jsonString);
	
	public OfferTradeParameters deserializeOfferTrade(String jsonString);
	
	public AcceptTradeParameters deserializeAcceptTrade(String jsonString);
	
	public MaritimeTradeParameters deserializeMaritimeTrade(String jsonString);
	
	public DiscardCardsParameters serializeDiscardCards(String jsonString);
	
	public String serializeGameModel(GameData gameData);
	
	

}
