package server.command.facade;

import shared.model.manager.GameData;
import shared.serialization.parameters.*;

public interface MovesCommandFacadeInterface {

	/**
	 * Creates a SendChat command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player sending the chat 
	 * and the chat message
	 * @param gameId The id of the game where the chat occurred
	 * @return The updated version of the game model
	 */
	public boolean sendChat(SendChatParameters params, int gameId);
	
	/**
	 * Creates a RollNumber command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player who rolled and the number they rolled
	 * @param gameId The id of the game where the roll occurred
	 * @return The updated version of the game model
	 */
	public boolean rollNumber(RollNumberParameters params, int gameId);
	
	/**
	 * Creates a RobPlayer command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player doing the robbing, the index of the player
	 * being robbed, and the new location of the robber
	 * @param gameId The id of the game where the robbing occurred
	 * @return The updated version of the game model
	 */
	public boolean robPlayer(RobPlayerParameters params, int gameId);
	
	/**
	 * Creates a FinishTurn command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player who's turn is ending
	 * @param gameId The id of the game where the turn occurred
	 * @return The updated version of the game model
	 */
	public boolean finishTurn(FinishTurnParameters params, int gameId);
	
	/**
	 * Creates a BuyDevCard command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player buying the dev card
	 * @param gameId The id of the game where the dev card buying occurred
	 * @return The updated version of the game model
	 */
	public boolean buyDevCard(BuyDevCardParameters params, int gameId);
	
	/**
	 * Creates a PlayYearOfPlenty command object and calls execute on it 
	 * 
	 * @param params An object containing the index of the player playing the year of
	 * plenty dev card, and the two resources they player is requesting
	 * @param gameId The id of the game where the year of plenty was played
	 * @return The updated version of the game model
	 */
	public boolean playYearOfPlenty(YearOfPlentyParameters params, int gameId);
	
	/**
	 * Creates a PlayRoadBuilding command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player playing the road
	 * building dev card, and two locations, one for each road the player is building
	 * @param gameId The id of the game where the road building was played
	 * @return The updated version of the game model
	 */
	public boolean playRoadBuilding(RoadBuildingParameters params, int gameId);
	
	/**
	 * Creates a PlaySoldier command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player playing the soldier
	 * dev card and doing the robbing, the index of the player being robbed, and the
	 * new location of the robber
	 * @param gameId The id of the game where the soldier was played
	 * @return The updated version of the game model
	 */
	public boolean playSoldier(SoldierParameters params, int gameId);
	
	/**
	 * Creates a PlayMonopoly command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player playing the monopoly
	 * dev card and the resource they are going to monopolize
	 * @param gameId The id of the game where the monopoly was played
	 * @return The updated version of the game model
	 */
	public boolean playMonopoly(MonopolyParameters params, int gameId);
	
	/**
	 * Creates a PlayMonument command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player playing the monument
	 * dev card
	 * @param gameId The id of the game where the roll occurred
	 * @return The updated version of the game model
	 */
	public boolean playMonument(MonumentParameters params, int gameId);
	
	/**
	 * Creates a BuildRoad command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player building the road,
	 * the location of the road being built, and a Boolean stating whether it is
	 * free for the player to build the road (i.e. it is setup round)
	 * @param gameId The id of the game where the road was built
	 * @return The updated version of the game model
	 */
	public boolean buildRoad(BuildRoadParameters params, int gameId);
	
	/**
	 * Creates a BuildSettlement command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player building the settlement,
	 * the location of the settlement being built, and a Boolean stating whether it is
	 * free for the player to build the settlement (i.e. it is setup round)
	 * @param gameId The id of the game where the settlement was built
	 * @return The updated version of the game model
	 */
	public boolean buildSettlement(BuildSettlementParameters params, int gameId);
	
	/**
	 * Creates a BuildCity command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player building the city and
	 * the location of the city being built
	 * @param gameId The id of the game where the city was built
	 * @return The updated version of the game model
	 */
	public boolean buildCity(BuildCityParameters params, int gameId);
	
	/**
	 * Creates an OfferTrade command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player offering the trade, the
	 * index of the player being offered the trade, and a ResourceList that indicates
	 * what resources are being offered (positive numbers) and which resources are being
	 * requested (negative numbers)
	 * @param gameId The id of the game where the trade was offered
	 * @return The updated version of the game model
	 */
	public boolean offerTrade(OfferTradeParameters params, int gameId);
	
	/**
	 * Creates an AcceptTrade command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player who was offered the trade
	 * and a Boolean representing whether the player accepts the trade or not
	 * @param gameId The id of the game where the trade was accepted
	 * @return The updated version of the game model
	 */
	public boolean acceptTrade(AcceptTradeParameters params, int gameId);
	
	/**
	 * Creates a MaritimeTrade command object and calls execute on it
	 * 
	 * @param params The index of the player doing the maritime trade, the ratio or number
	 * of resources the player is giving, the resource the player is giving, and the resource
	 * the player is getting
	 * @param gameId The id of the game where the maritime trade occurred
	 * @return The updated version of the game model
	 */
	public boolean maritimeTrade(MaritimeTradeParameters params, int gameId);
	
	/**
	 * Creates a DiscardCards command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player discarding cards, and a
	 * resource list indicating the resources and many of each resource is being discarded
	 * @param gameId The id of the game where the cards were discarded
	 * @return The updated version of the game model
	 */
	public boolean discardCards(DiscardCardsParameters params, int gameId);
}
