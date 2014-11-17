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
System.out.println("\t\tMoves Command Facade: Send Chat");
		SendChat command = new SendChat(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean rollNumber(RollNumberParameters params, int gameId) {
System.out.println("\t\tMoves Command Facade: Roll Number");
		RollNumber command = new RollNumber(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean robPlayer(RobPlayerParameters params, int gameId) {
System.out.println("\t\tMoves Command Facade: Rob Player");
		RobPlayer command = new RobPlayer(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean finishTurn(FinishTurnParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Finish Turn");
		FinishTurn command = new FinishTurn(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean buyDevCard(BuyDevCardParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: But Dev Card");
		BuyDevCard command = new BuyDevCard(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean playYearOfPlenty(YearOfPlentyParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Play Year Of Plenty");
		PlayYearOfPlenty command = new PlayYearOfPlenty(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean playRoadBuilding(RoadBuildingParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Play Road Building");
		PlayRoadBuilding command = new PlayRoadBuilding(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean playSoldier(SoldierParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Play Soldier");
		PlaySoldier command = new PlaySoldier(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean playMonopoly(MonopolyParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Play Monopoly");
		PlayMonopoly command = new PlayMonopoly(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean playMonument(MonumentParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Play Monument");
		PlayMonument command = new PlayMonument(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean buildRoad(BuildRoadParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Build Road");
		BuildRoad command = new BuildRoad(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean buildSettlement(BuildSettlementParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Build Settlement");
		BuildSettlement command = new BuildSettlement(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean buildCity(BuildCityParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Build City");
		BuildCity command = new BuildCity(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean offerTrade(OfferTradeParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Offer Trade");
		OfferTrade command = new OfferTrade(params, gameId);
		try{
		command.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		return command.wasSuccessful();
	}

	@Override
	public boolean acceptTrade(AcceptTradeParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Accept Trade");
		AcceptTrade command = new AcceptTrade(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean maritimeTrade(MaritimeTradeParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Maritime Trade");
		MaritimeTrade command = new MaritimeTrade(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public boolean discardCards(DiscardCardsParameters params, int gameId) {
		System.out.println("\t\tMoves Command Facade: Discard Cards");
		DiscardCards command = new DiscardCards(params, gameId);
		command.execute();
		return command.wasSuccessful();
	}

}
