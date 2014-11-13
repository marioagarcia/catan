package server.command.facade.mock;

import server.command.facade.MovesCommandFacadeInterface;
import shared.model.manager.GameData;
import shared.serialization.parameters.*;

public class MockMovesCommandFacade implements MovesCommandFacadeInterface{

	@Override
	public boolean sendChat(SendChatParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean rollNumber(RollNumberParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean robPlayer(RobPlayerParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean finishTurn(FinishTurnParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean buyDevCard(BuyDevCardParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean playYearOfPlenty(YearOfPlentyParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean playRoadBuilding(RoadBuildingParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean playSoldier(SoldierParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean playMonopoly(MonopolyParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean playMonument(MonumentParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean buildRoad(BuildRoadParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean buildSettlement(BuildSettlementParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean buildCity(BuildCityParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean offerTrade(OfferTradeParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean acceptTrade(AcceptTradeParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean maritimeTrade(MaritimeTradeParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean discardCards(DiscardCardsParameters params, int gameId) {
		// TODO Auto-generated method stub
		return true;
	}
}
