package server.command.facade;

import shared.model.manager.GameData;
import shared.serialization.parameters.*;

public interface MovesCommandFacadeInterface {

	/**
	 * Creates a SendChat command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player sending the chat 
	 * and the chat message
	 * @return The updated version of the game model
	 */
	public GameData sendChat(SendChatParameters params);
	
	/**
	 * Creates a RollNumber command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player who rolled and the number they rolled
	 * @return The updated version of the game model
	 */
	public GameData rollNumber(RollNumberParameters params);
	
	/**
	 * Creates a RobPlayer command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player doing the robbing, the index of the player
	 * being robbed, and the new location of the robber
	 * @return The updated version of the game model
	 */
	public GameData robPlayer(RobPlayerParameters params);
	
	/**
	 * Creates a FinishTurn command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player who's turn is ending
	 * @return The updated version of the game model
	 */
	public GameData finishTurn(FinishTurnParameters params);
	
	/**
	 * Creates a BuyDevCard command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player buying the dev card
	 * @return The updated version of the game model
	 */
	public GameData buyDevCard(BuyDevCardParameters params);
	
	/**
	 * Creates a PlayYearOfPlenty command object and calls execute on it 
	 * 
	 * @param params An object containing the index of the player playing the year of
	 * plenty dev card, and the two resources they player is requesting
	 * @return The updated version of the game model
	 */
	public GameData playYearOfPlenty(YearOfPlentyParameters params);
	
	/**
	 * Creates a PlayRoadBuilding command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player playing the road
	 * building dev card, and two locations, one for each road the player is building
	 * @return The updated version of the game model
	 */
	public GameData playRoadBuilding(RoadBuildingParameters params);
	
	/**
	 * Creates a PlaySoldier command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player playing the soldier
	 * dev card and doing the robbing, the index of the player being robbed, and the
	 * new location of the robber
	 * @return The updated version of the game model
	 */
	public GameData playSoldier(SoldierParameters params);
	
	/**
	 * Creates a PlayMonopoly command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player playing the monopoly
	 * dev card and the resource they are going to monopolize
	 * @return The updated version of the game model
	 */
	public GameData playMonopoly(MonopolyParameters params);
	
	/**
	 * Creates a PlayMonument command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player playing the monument
	 * dev card
	 * @return The updated version of the game model
	 */
	public GameData playMonument(MonumentParameters params);
	
	/**
	 * Creates a BuildRoad command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player building the road,
	 * the location of the road being built, and a Boolean stating whether it is
	 * free for the player to build the road (i.e. it is setup round)
	 * @return The updated version of the game model
	 */
	public GameData buildRoad(BuildRoadParameters params);
	
	/**
	 * Creates a BuildSettlement command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player building the settlement,
	 * the location of the settlement being built, and a Boolean stating whether it is
	 * free for the player to build the settlement (i.e. it is setup round)
	 * @return The updated version of the game model
	 */
	public GameData buildSettlement(BuildSettlementParameters params);
	
	/**
	 * Creates a BuildCity command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player building the city and
	 * the location of the city being built
	 * @return The updated version of the game model
	 */
	public GameData buildCity(BuildCityParameters params);
	
	/**
	 * Creates an OfferTrade command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player offering the trade, the
	 * index of the player being offered the trade, and a ResourceList that indicates
	 * what resources are being offered (positive numbers) and which resources are being
	 * requested (negative numbers)
	 * @return The updated version of the game model
	 */
	public GameData offerTrade(OfferTradeParameters params);
	
	/**
	 * Creates an AcceptTrade command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player who was offered the trade
	 * and a Boolean representing whether the player accepts the trade or not
	 * @return The updated version of the game model
	 */
	public GameData acceptTrade(AcceptTradeParameters params);
	
	/**
	 * Creates a MaritimeTrade command object and calls execute on it
	 * 
	 * @param params The index of the player doing the maritime trade, the ratio or number
	 * of resources the player is giving, the resource the player is giving, and the resource
	 * the player is getting
	 * @return The updated version of the game model
	 */
	public GameData maritimeTrade(MaritimeTradeParameters params);
	
	/**
	 * Creates a DiscardCards command object and calls execute on it
	 * 
	 * @param params An object containing the index of the player discarding cards, and a
	 * resource list indicating the resources and many of each resource is being discarded
	 * @return The updated version of the game model
	 */
	public GameData discardCards(DiscardCardsParameters params);
}
