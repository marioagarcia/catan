package server.handler.facade.real;

import server.handler.facade.MovesCommandFacadeInterface;
import shared.model.manager.GameData;
import shared.serialization.parameters.AcceptTradeParameters;
import shared.serialization.parameters.BuildCityParameters;
import shared.serialization.parameters.BuildRoadParameters;
import shared.serialization.parameters.BuildSettlementParameters;
import shared.serialization.parameters.BuyDevCardParameters;
import shared.serialization.parameters.DiscardCardsParameters;
import shared.serialization.parameters.FinishTurnParameters;
import shared.serialization.parameters.MaritimeTradeParameters;
import shared.serialization.parameters.MonopolyParameters;
import shared.serialization.parameters.MonumentParameters;
import shared.serialization.parameters.OfferTradeParameters;
import shared.serialization.parameters.RoadBuildingParameters;
import shared.serialization.parameters.RobPlayerParameters;
import shared.serialization.parameters.RollNumberParameters;
import shared.serialization.parameters.SendChatParameters;
import shared.serialization.parameters.SoldierParameters;
import shared.serialization.parameters.YearOfPlentyParameters;

public class MovesCommandFacade implements MovesCommandFacadeInterface{

	@Override
	public GameData sendChat(SendChatParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData rollNumber(RollNumberParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData robPlayer(RobPlayerParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData finishTurn(FinishTurnParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData buyDevCard(BuyDevCardParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData playYearOfPlenty(YearOfPlentyParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData playRoadBuilding(RoadBuildingParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData playSoldier(SoldierParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData playMonopoly(MonopolyParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData playMonument(MonumentParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData buildRoad(BuildRoadParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData buildSettlement(BuildSettlementParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData buildCity(BuildCityParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData offerTrade(OfferTradeParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData acceptTrade(AcceptTradeParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData maritimeTrade(MaritimeTradeParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData discardCards(DiscardCardsParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

}
