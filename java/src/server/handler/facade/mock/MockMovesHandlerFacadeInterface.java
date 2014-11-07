package server.handler.facade.mock;

import shared.model.manager.GameData;
import shared.serialization.parameters.*;

public interface MockMovesHandlerFacadeInterface {

	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player sending the chat 
	 * and the chat message
	 * @return A hard coded game model
	 */
	public GameData sendChat(SendChatParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player who rolled and the number they rolled
	 * @return A hard coded game model
	 */
	public GameData rollNumber(RollNumberParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player doing the robbing, the index of the player
	 * being robbed, and the new location of the robber
	 * @return A hard coded game model
	 */
	public GameData robPlayer(RobPlayerParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player who's turn is ending
	 * @return A hard coded game model
	 */
	public GameData finishTurn(FinishTurnParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player buying the dev card
	 * @return A hard coded game model
	 */
	public GameData buyDevCard(BuyDevCardParameters params);
	
	/**
	 * Retrieves a hard coded GameData object 
	 * 
	 * @param params An object containing the index of the player playing the year of
	 * plenty dev card, and the two resources they player is requesting
	 * @return A hard coded game model
	 */
	public GameData playYearOfPlenty(YearOfPlentyParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player playing the road
	 * building dev card, and two locations, one for each road the player is building
	 * @return A hard coded game model
	 */
	public GameData playRoadBuilding(RoadBuildingParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player playing the soldier
	 * dev card and doing the robbing, the index of the player being robbed, and the
	 * new location of the robber
	 * @return A hard coded game model
	 */
	public GameData playSoldier(SoldierParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player playing the monopoly
	 * dev card and the resource they are going to monopolize
	 * @return A hard coded game model
	 */
	public GameData playMonopoly(MonopolyParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player playing the monument
	 * dev card
	 * @return A hard coded game model
	 */
	public GameData playMonument(MonumentParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player building the road,
	 * the location of the road being built, and a Boolean stating whether it is
	 * free for the player to build the road (i.e. it is setup round)
	 * @return A hard coded game model
	 */
	public GameData buildRoad(BuildRoadParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player building the settlement,
	 * the location of the settlement being built, and a Boolean stating whether it is
	 * free for the player to build the settlement (i.e. it is setup round)
	 * @return A hard coded game model
	 */
	public GameData buildSettlement(BuildSettlementParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player building the city and
	 * the location of the city being built
	 * @return A hard coded game model
	 */
	public GameData buildCity(BuildCityParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player offering the trade, the
	 * index of the player being offered the trade, and a ResourceList that indicates
	 * what resources are being offered (positive numbers) and which resources are being
	 * requested (negative numbers)
	 * @return A hard coded game model
	 */
	public GameData offerTrade(OfferTradeParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player who was offered the trade
	 * and a Boolean representing whether the player accepts the trade or not
	 * @return A hard coded game model
	 */
	public GameData acceptTrade(AcceptTradeParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params The index of the player doing the maritime trade, the ratio or number
	 * of resources the player is giving, the resource the player is giving, and the resource
	 * the player is getting
	 * @return A hard coded game model
	 */
	public GameData maritimeTrade(MaritimeTradeParameters params);
	
	/**
	 * Retrieves a hard coded GameData object
	 * 
	 * @param params An object containing the index of the player discarding cards, and a
	 * resource list indicating the resources and many of each resource is being discarded
	 * @return A hard coded game model
	 */
	public GameData discardCards(DiscardCardsParameters params);
}
