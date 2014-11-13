package server.command.facade.real;

import server.command.AcceptTrade;
import server.command.BuildCity;
import server.command.BuildRoad;
import server.command.BuildSettlement;
import server.command.BuyDevCard;
import server.command.DiscardCards;
import server.command.FinishTurn;
import server.command.MaritimeTrade;
import server.command.OfferTrade;
import server.command.PlayMonopoly;
import server.command.PlayMonument;
import server.command.PlayRoadBuilding;
import server.command.PlaySoldier;
import server.command.PlayYearOfPlenty;
import server.command.RobPlayer;
import server.command.RollNumber;
import server.command.SendChat;
import server.command.facade.MovesCommandFacadeInterface;
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
	public boolean sendChat(SendChatParameters params, int gameId) {
		SendChat command = new SendChat(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean rollNumber(RollNumberParameters params, int gameId) {
		RollNumber command = new RollNumber(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean robPlayer(RobPlayerParameters params, int gameId) {
		RobPlayer command = new RobPlayer(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean finishTurn(FinishTurnParameters params, int gameId) {
		FinishTurn command = new FinishTurn(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean buyDevCard(BuyDevCardParameters params, int gameId) {
		BuyDevCard command = new BuyDevCard(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean playYearOfPlenty(YearOfPlentyParameters params, int gameId) {
		PlayYearOfPlenty command = new PlayYearOfPlenty(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean playRoadBuilding(RoadBuildingParameters params, int gameId) {
		PlayRoadBuilding command = new PlayRoadBuilding(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean playSoldier(SoldierParameters params, int gameId) {
		PlaySoldier command = new PlaySoldier(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean playMonopoly(MonopolyParameters params, int gameId) {
		PlayMonopoly command = new PlayMonopoly(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean playMonument(MonumentParameters params, int gameId) {
		PlayMonument command = new PlayMonument(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean buildRoad(BuildRoadParameters params, int gameId) {
		BuildRoad command = new BuildRoad(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean buildSettlement(BuildSettlementParameters params, int gameId) {
		BuildSettlement command = new BuildSettlement(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean buildCity(BuildCityParameters params, int gameId) {
		BuildCity command = new BuildCity(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean offerTrade(OfferTradeParameters params, int gameId) {
		OfferTrade command = new OfferTrade(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean acceptTrade(AcceptTradeParameters params, int gameId) {
		AcceptTrade command = new AcceptTrade(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean maritimeTrade(MaritimeTradeParameters params, int gameId) {
		MaritimeTrade command = new MaritimeTrade(params, gameId);
		return command.wasSuccessful();
	}

	@Override
	public boolean discardCards(DiscardCardsParameters params, int gameId) {
		DiscardCards command = new DiscardCards(params, gameId);
		return command.wasSuccessful();
	}

}
