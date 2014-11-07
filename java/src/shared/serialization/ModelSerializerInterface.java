package shared.serialization;

import java.util.ArrayList;

import client.manager.GameCommands;
import shared.model.GameInfo;
import shared.serialization.parameters.AcceptTradeParameters;
import shared.serialization.parameters.BuildCityParameters;
import shared.serialization.parameters.BuildRoadParameters;
import shared.serialization.parameters.BuildSettlementParameters;
import shared.serialization.parameters.BuyDevCardParameters;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.CredentialsParameters;
import shared.serialization.parameters.DiscardCardsParameters;
import shared.serialization.parameters.FinishTurnParameters;
import shared.serialization.parameters.JoinGameRequestParameters;
import shared.serialization.parameters.LoadGameRequestParameters;
import shared.serialization.parameters.AIRequestParameters;
import shared.serialization.parameters.MaritimeTradeParameters;
import shared.serialization.parameters.MasterParameterInterface;
import shared.serialization.parameters.MonopolyParameters;
import shared.serialization.parameters.MonumentParameters;
import shared.serialization.parameters.OfferTradeParameters;
import shared.serialization.parameters.RoadBuildingParameters;
import shared.serialization.parameters.RobPlayerParameters;
import shared.serialization.parameters.RollNumberParameters;
import shared.serialization.parameters.SaveGameRequestParameters;
import shared.serialization.parameters.SendChatParameters;
import shared.serialization.parameters.SoldierParameters;
import shared.serialization.parameters.YearOfPlentyParameters;

/**
 * This class handles all serialization of data going to the server and 
 * deserialization of data coming from the server.
 */
public interface ModelSerializerInterface 
{
	/**
	 * Serializes a users credentials into a Json string.  Used to register and login
	 * 
	 * @param credentials The userame and password of the user
	 * @return A Json string of the serialized credentials
	 */
	public String serializeCredentials(CredentialsParameters credentials);
	
	/**
	 * Deserializes and parses the Json string into a list of games
	 * 
	 *  @param jsonString The string representation of the list of games
	 *  @return The list of games that was just deserialized
	 */
	public ArrayList<GameInfo> deserializeGamesList(String jsonString);
	
	/**
	 * Serializes a create games request into a Json string
	 * 
	 * @param params An object containing the game's name (string) as well as 3
	 * booleans that signify whether you want to randomize tiles, numbers, and ports
	 * @return A Json string of the serialized create game request
	 */
	public String serializeCreateGameRequest(CreateGameRequestParameters params);
	
	/**
	 * Deserializes and parses a Json string into a GameInfo object
	 * 
	 * @param jsonString The Json string representation of a GameInfo object
	 * @return A GameInfo object containing a game title, game ID, and list of PlayerInfos
	 */
	public GameInfo deserializeGameInfo(String jsonString);
	
	/**
	 * Serializes a join game request into a Json string
	 * 
	 * @param params An object containing a player's ID and a player's chosen color
	 * @return A Json string of the serialized join game request
	 */
	public String serializeJoinGameRequest(JoinGameRequestParameters params);
	
	/**
	 * Serializes a save game request into a Json string
	 * 
	 * @param params An object containing the game ID and the name you want to save
	 * the game under
	 * @return A Json string of the serialized save game request
	 */
	public String serializeSaveGameRequest(SaveGameRequestParameters params);
	
	/**
	 * Serializes a load game request into a Json string
	 * 
	 * @param params An object containing the name of the saved game you want 
	 * to restore
	 * @return A Json string of the serialized load game request
	 */
	public String serializeLoadGameRequest(LoadGameRequestParameters params);
	
	//Get Game Model is the last method, deserializeGameModel()
	
	//Reset Game should go here
	
	/**
	 * Serializes a post game commands request into a Json string
	 * 
	 * @param params A list of all of the game commands that have occurred in the game
	 * @return A Json string of the serialized list of game commands
	 */
	public String serializePostGameCommands(ArrayList<MasterParameterInterface> params);
	
	/**
	 * Deserializes and parses a Json string into a list of game commands
	 * 
	 * @param jsonString The Json string representation of the game commands
	 * @return A list of game commands
	 */
	public GameCommands deserializeGetGameCommands(String jsonString);
	
	/**
	 * Serializes an AI request into a Json string
	 * 
	 * @param params An object containing the name of the requested AI player (string)
	 * @return A Json string of the serialized AI request
	 */
	public String serializeAIRequest(AIRequestParameters params);
	
	//List AI should go here
	
	/**
	 * Serializes a send chat request into a Json string
	 * 
	 * @param params An object containing the type of action (string) being taken,
	 * the index of the player sending the chat (int), and the content of the chat (string)
	 * @return A Json string of the serialized send chat request
	 */
	public String serializeSendChat(SendChatParameters params);
	
	/**
	 * Serializes a roll number request into a Json string
	 * 
	 * @param params An object containing the type of action (string) being taken,
	 * the index of the player sending the roll request (int), and the number the
	 * player rolled (int)
	 * @return A Json string of the serialized roll number request
	 */
	public String serializeRollNumber(RollNumberParameters params);
	
	/**
	 * Serializes a rob player request into a Json string
	 * 
	 * @param params An object containing the type of action (string) being taken,
	 * the index of the player doing the robbing (int), the index of the player
	 * being robbed (int), and the new location of the robber (HexLocation)
	 * @return A Json string of the serialized rob player request
	 */
	public String serializeRobPlayer(RobPlayerParameters params);
	
	/**
	 * Serializes a finish turn request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * and the index of the player sending the request (int)
	 * @return A Json string of the serialized finish turn request
	 */
	public String serializeFinishTurn(FinishTurnParameters params);
	
	/**
	 * Serializes a buy dev card request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * and the index of the player sending the request (int)
	 * @return A Json string of the serialized buy dev card request
	 */
	public String serializeBuyDevCard(BuyDevCardParameters params);
	
	/**
	 * Serializes a play year of plenty request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * the index of the player sending the request, and the two resources being
	 * requested (2 strings, one for each resource)
	 * @return A Json string of the serialized play year of plenty request
	 */
	public String serializeYearOfPlenty(YearOfPlentyParameters params);
	
	/**
	 * Serializes a play road build request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * the index of the player sending the request (int), and the two edge locations where
	 * you want to build the roads (2 EdgeLocations - an EdgeLocation contains an x (int), y (int), 
	 * and direction (string)) 
	 * @return A Json string of the serialized play road build request
	 */
	public String serializeRoadBuilding(RoadBuildingParameters params);
	
	/**
	 * Serializes a play soldier request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * the index of the player sending the request (int), the index of the player being
	 * robbed (int), the new location of the robber (HexLocation)
	 * @return A Json string of the serialied play soldier request
	 */
	public String serializeSoldier(SoldierParameters params);
	
	/**
	 * Serializes a play monopoly request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * the index of the player sending the request (int), and the resource being
	 * requested (string)
	 * @return A Json string of the serialized play monopoly request 
	 */
	public String serializeMonopoly(MonopolyParameters params);
	
	/**
	 * Serializes a play monument request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * and the index of the player sending the request (int)
	 * @return A Json string of the serialized play monument request
	 */
	public String serializeMonument(MonumentParameters params);
	
	/**
	 * Serializes a build road request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * the index of the player sending the request (int), the location where the player
	 * wants to build a road (EdgeLocation), and a boolean representing whether 
	 * it is the setup round or not
	 * @return A Json string of the serialized build road request
	 */
	public String serializeBuildRoad(BuildRoadParameters params);
	
	/**
	 * Serializes a build settlement request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string), 
	 * the index of the player sending the request (int), the location where the player
	 * wants to build a settlement (VertexLocation), and a boolean representing whether
	 * it is the setup round or not
	 * @return A Json string of the serialized build settlement request
	 */
	public String serializeBuildSettlement(BuildSettlementParameters params);
	
	/**
	 * Serializes a build city request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * the index of the player sending the request (int), and the location where the 
	 * player wants to build a city (VertexLocation)
	 * @return A Json string of the serialized build city request
	 */
	public String serializeBuildCity(BuildCityParameters params);
	
	/**
	 * Serializes an offer trade request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * the index of the player sending the request (int), the index of the player
	 * the trade is being offered to (int), and 5 ints representing the resources
	 * in the trade, positive ints for cards being offered and negative ints for
	 * cards being requested
	 * @return A Json string of the serialized offer trade request
	 */
	public String serializeOfferTrade(OfferTradeParameters params);
	
	/**
	 * Serializes an accept trade request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * the index of the player accepting or rejecting the trade, and a boolean
	 * representing whether the player accepts or rejects the trade
	 * @return A Json string of the serialized accept trade request
	 */
	public String serializeAcceptTrade(AcceptTradeParameters params);
	
	/**
	 * Serializes a maritime trade request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * the index of the player sending the request, the ratio of the trade (int, optional),
	 * the resource being given (string, optional), the resource being requested (string)
	 * @return A Json string of the serialized maritime trade request
	 */
	public String serializeMaritimeTrade(MaritimeTradeParameters params);
	
	/**
	 * Serializes a discard cards request into a Json string
	 * 
	 * @param params An object containing the type of action being taken (string),
	 * the index of the player sending the request, and 5 ints representing the resources
	 * being discarded
	 * @return the json string of the cards to be discarded
	 */
	public String serializeDiscardCards(DiscardCardsParameters params);
	
	//Change Log level should go here
	
	/**
	 * Deserializes a CatanModel's current state/data and populates/updates a CatanModel with
	 * the state/data that has been deserialized
	 * 
	 * @param data The string from the server that needs to be deserialized
	 * @return CatanModel The updated CatanModel that was just deserialized
	 */
	public client.manager.GameData deserializeGameModel(String data);
}