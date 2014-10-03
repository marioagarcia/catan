package shared.serialization;

import java.util.ArrayList;

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
import client.model.GameInfo;

/**
 * This class handles all serialization of data going to the server and 
 * deserialization of data coming from the server.
 */
public interface ModelSerializerInterface 
{
	/**
	 * @TODO 
	 */
	public String serializeCredentials(CredentialsParameters credentials);
	
	/**
	 * @TODO
	 */
	public ArrayList<GameInfo> getGamesList(String jsonString);
	
	/**
	 * @TODO
	 */
	public String serializeCreateGameRequest(CreateGameRequestParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeJoinGameRequest(JoinGameRequestParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeSaveGameRequest(SaveGameRequestParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeLoadGameRequest(LoadGameRequestParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeAIRequest(AIRequestParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeSendChat(SendChatParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeRollNumber(RollNumberParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeRobPlayer(RobPlayerParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeFinishTurn(FinishTurnParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeBuyDevCard(BuyDevCardParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeYearOfPlenty(YearOfPlentyParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeRoadBuilding(RoadBuildingParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeSoldier(SoldierParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeMonopoly(MonopolyParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeMonument(MonumentParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeBuildRoad(BuildRoadParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeBuildSettlement(BuildSettlementParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeBuildCity(BuildCityParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeOfferTrade(OfferTradeParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeAcceptTrade(AcceptTradeParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeMaritimeTrade(MaritimeTradeParameters params);
	
	/**
	 * @TODO
	 */
	public String serializeDiscardCards(DiscardCardsParameters params);
	
	/**
	 * Serializes a player's cards and ID to send to the server
	 * 
	 * @param inventory_instance The cards that are being serialized
	 * @param playerId The ID of the player whose inventory contains the cards being serialized
	 * @return String The player's cards and ID after being serialized
	 */
	public String serializeCards();
	
	/**
	 * Deserializes a road from a string from the server
	 *  
	 * @param data The serialized version of the road 
	 * @return Road The road that was just deserialized
	 */
	public client.model.piece.RoadInterface deserializeRoad(String data);
	
	/**
	 * Deserializes a player from a string from the server
	 * 
	 * @param data The serialized version of the player
	 * @return Player The player that was just deserialized
	 */
	public client.model.player.PlayerInterface deserializePlayer(String data);
	
	/**
	 * Deserializes a CatanModel's current state/data and populates/updates a CatanModel with
	 * the state/data that has been deserialized
	 * 
	 * @param data The string from the server that needs to be deserialized
	 * @return CatanModel The updated CatanModel that was just deserialized
	 */
	public client.manager.GameData deserializeGameModel(String data);
}