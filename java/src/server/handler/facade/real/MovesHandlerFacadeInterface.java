package server.handler.facade.real;

import shared.model.manager.GameData;
import shared.serialization.parameters.*;

public interface MovesHandlerFacadeInterface extends HandlerFacadeInterface{

	/**
	 * Creates a SendChat command object and calls execute on it
	 * 
	 * @param params
	 * @return
	 */
	public GameData sendChat(SendChatParameters params);
	
	/**
	 * Creates a RollNumber command object and calls execute on it
	 * 
	 * @param params
	 * @return
	 */
	public GameData rollNumber(RollNumberParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData robPlayer(RobPlayerParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData finishTurn(FinishTurnParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData buyDevCard(BuyDevCardParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData playYearOfPlenty(YearOfPlentyParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData playRoadBuilding(RoadBuildingParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData playSoldier(SoldierParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData playMonopoly(MonopolyParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData playMonument(MonumentParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData buildRoad(BuildRoadParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData buildSettlement(BuildSettlementParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData buildCity(BuildCityParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData offerTrade(OfferTradeParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData acceptTrade(AcceptTradeParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData maritimeTrade(MaritimeTradeParameters params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public GameData discardCards(DiscardCardsParameters params);
}
