package client.manager;

import java.util.ArrayList;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.serialization.ModelSerializer;
import shared.serialization.parameters.*;
import client.communication.server.ServerProxy;
import client.logging.GameLog;
import client.manager.interfaces.GMBoardMapInterface;
import client.manager.interfaces.GMDomesticTradeInterface;
import client.model.GameInfo;
import client.model.card.DevCardBank;
import client.model.card.MaritimeTrade;
import client.model.card.ResourceCardBank;
import client.model.card.ResourceList;
import client.model.card.TradeInterface;
import client.model.map.HexInterface;
import client.model.player.Player;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;
import client.roll.DiceRoller;

public class GameManager implements GameManagerInterface {
	
	ServerProxy serverProxy;
	ArrayList<GameInfo> gameList;
	ModelSerializer modelSerializer;
	Player localPlayer;
	GameInfo currentGame;
	GameLog gameLog;
	GameCommands gameCommands;
	TurnTracker turnTracker;
	DiceRoller diceRoller;
	GMBoardMapInterface boardMap;
	DevCardBank devCardBank;
	ResourceCardBank resCardBank;
	ArrayList<Player> allPlayers;
	
	public GameManager() {
		serverProxy = null; //serverProxy.getInstance();
		//serverPoller = serverPoller.getInstance();
		modelSerializer = new ModelSerializer(); //modelSerializer.getInstance();
	}

	@Override
	public boolean loginPlayer(String username, String password) {
		CredentialsParameters credentials = new CredentialsParameters(username, password);
		
		String json_string = modelSerializer.serializeCredentials(credentials);
		
		serverProxy.login(json_string);
		
		return true;
	}
	
	@Override
	public boolean registerPlayer(String username, String password) {
		CredentialsParameters credentials = new CredentialsParameters(username, password);
		
		String json_string = modelSerializer.serializeCredentials(credentials);
		
		serverProxy.register(json_string);
		
		return true;
	}

	private boolean validatePlayer() {
		return serverProxy.validatePlayer(localPlayer);
	}

	@Override
	public void populateGameList() {
		String JSONSring = serverProxy.listGames();
		//gameList = modelSerializer.deserializeGamesList(JSONSring);
	}

	@Override
	public boolean canJoinGame(CatanColor color, GameInfo game) {
		return (validatePlayer() && game.validateColor(color));
	}
	
	@Override
	public void joinGame(CatanColor color, GameInfo game) {
		JoinGameRequestParameters param = new JoinGameRequestParameters(game.getId(), color.toString().toLowerCase());
		String json_string = modelSerializer.serializeJoinGameRequest(param);
		String result = serverProxy.joinGame(json_string);
		if(result == "Success") {
			currentGame = game;
		}
		else {
			//TODO: figure out how to respond with the bad response that 			
		}
	}
	
	@Override
	public boolean saveGame() {
		int player_index = localPlayer.getPlayerIndex();
		
		SaveGameRequestParameters param = new SaveGameRequestParameters(player_index, currentGame.getTitle());
		
		String json_string = modelSerializer.serializeSaveGameRequest(param);
		
		//TODO serverProxy.saveGame(json_string);
		
		return true;
	}

	@Override
	public boolean getGameModel() {
		//TODO
		return false;
	}

	//TODO
	private boolean resetFromGameModel(GameData gameModel) {
		//reset model classes
		return false;
	}

	@Override
	public boolean resetGame() { 
		String json_string = serverProxy.resetGame();
		GameData reset_game_data = modelSerializer.deserializeGameModel(json_string);
		if(resetFromGameModel(reset_game_data))
			return true;
		else
			return false;
	}

	//TODO
	@Override
	public boolean getGameCommands() {
		String json_string = serverProxy.getGameCommands();
		
		//gameCommands = modelSerializer.deserializeGetGameCommands(json_string);
		
		return true;
	}

	//TODO
	@Override
	public boolean postGameCommands() {
		String json_string = null; //modelSerializer.serializePostGameCommands(gameCommands);
		
		serverProxy.postGameCommands(json_string);
		
		return true;
	}

	@Override
	public boolean sendChat(String chatMessage) {
		int player_index = localPlayer.getPlayerIndex();
		SendChatParameters param = new SendChatParameters(player_index, chatMessage);
		String json_string = modelSerializer.serializeSendChat(param);
		GameData reset_game_data = modelSerializer.deserializeGameModel(json_string);
		if(resetFromGameModel(reset_game_data))
			return true;
		else
			return false;
	}

	@Override
	public boolean canAcceptTrade(TradeInterface trade) {
		boolean player_condition_met = localPlayer.canAcceptTrade(trade);
		boolean turn_condition_met = (turnTracker.getCurrentTurn() == localPlayer.getPlayerIndex());
		
		return (player_condition_met && turn_condition_met);
	}

	@Override
	public boolean acceptTrade(TradeInterface trade, boolean accept) {
		int player_index = localPlayer.getPlayerIndex();
		
		String json_string = modelSerializer.serializeAcceptTrade(new AcceptTradeParameters(player_index, accept));
		serverProxy.acceptTrade(json_string);
		
		return false;
	}

	@Override
	public boolean canDiscardCards(ResourceList list) {
		return localPlayer.canDiscardCards(list);
	}

	@Override
	public boolean discardCards(ResourceList list) {
		int player_index = localPlayer.getPlayerIndex();
		
		String json_string = modelSerializer.serializeDiscardCards(new DiscardCardsParameters(player_index, list));
		
		serverProxy.discardCards(json_string); //this should throw an exception	
		
		return true;
	}

	@Override
	public boolean canRoll() {
		int player_index = localPlayer.getPlayerIndex();
		
		return turnTracker.canRoll(player_index);
	}

	@Override
	public int roll() {
		int player_index = localPlayer.getPlayerIndex();
		int number = diceRoller.roll();
		
		String json_string = modelSerializer.serializeRollNumber(new RollNumberParameters(player_index, number));
		
		serverProxy.rollNumber(json_string);
		
		return 0;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		
		return boardMap.canBuildRoad(location, player_index);
	}

	@Override
	public boolean buildRoad(EdgeLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		boolean isFree = (TurnTracker.Status.FIRST_ROUND == turnTracker.getStatus());
		
		BuildRoadParameters param = new BuildRoadParameters(player_index, new EdgeLocationParameters(location), isFree);
		String json_string = modelSerializer.serializeBuildRoad(param);
		
		serverProxy.rollNumber(json_string);
		
		return true;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		
		return boardMap.canBuildSettlement(location, player_index);
	}

	@Override
	public boolean buildSettlement(VertexLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		boolean isFree = (TurnTracker.Status.FIRST_ROUND == turnTracker.getStatus());
		
		BuildSettlementParameters param = new BuildSettlementParameters(player_index, new VertexLocationParameters(location), isFree);
		
		String json_string = modelSerializer.serializeBuildSettlement(param);
		serverProxy.rollNumber(json_string);
		return true;
	}

	@Override
	public boolean canBuildCity(VertexLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		
		return boardMap.canBuildCity(location, player_index);
	}

	@Override
	public boolean buildCity(VertexLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		
		BuildCityParameters param = new BuildCityParameters(player_index, new VertexLocationParameters(location));
		
		String json_string = modelSerializer.serializeBuildCity(param);
		serverProxy.rollNumber(json_string);
		
		return true;
	}

	@Override
	public boolean canOfferTrade(TradeInterface trade) {
		return localPlayer.canOfferTrade(trade);
	}

	//TODO
	@Override
	public boolean offerTrade(TradeInterface trade, int otherPlayerIndex) {
		GMDomesticTradeInterface trade_i = null;
		int local_player_index = localPlayer.getPlayerIndex();
		
		ResourceList resource_list = new ResourceList(trade_i.getBrickCount(), trade_i.getOreCount(), trade_i.getSheepCount(), trade_i.getWheatCount(), trade_i.getWoodCount());
		
		String json_string = modelSerializer.serializeOfferTrade(new OfferTradeParameters(local_player_index, resource_list, otherPlayerIndex));
		serverProxy.offerTrade(json_string);
		
		return true;
	}

	@Override
	public boolean canMaritimeTrade(HexInterface location, MaritimeTrade trade) {
		return localPlayer.canOfferTrade(trade);
	}

	@Override
	public boolean maritimeTrade(HexInterface location, MaritimeTrade trade) {
		int player_index = localPlayer.getPlayerIndex();
		String resource_in = trade.getResourceIn().toString().toLowerCase();
		String resource_out = trade.getResourceOut().toString().toLowerCase();
		
		MaritimeTradeParameters param = new MaritimeTradeParameters(player_index, trade.getRatio(), resource_in, resource_out);
		
		String json_string = modelSerializer.serializeMaritimeTrade(param);
		serverProxy.offerTrade(json_string);
		
		return true;
	}

	@Override
	public boolean canFinishTurn() {
		return (turnTracker.getStatus() == Status.PLAYING);
	}

	@Override
	public boolean finishTurn() {
		int player_index = localPlayer.getPlayerIndex();
		
		FinishTurnParameters param = new FinishTurnParameters(player_index);
		
		String json_string = modelSerializer.serializeFinishTurn(param);
		
		serverProxy.finishTurn(json_string);		
		
		return true;
	}

	@Override
	public boolean canBuyDevCard() {
		boolean player_condition_met = localPlayer.canBuyDevCard();
		boolean turn_condition_met = turnTracker.canBuyDevCard(localPlayer.getPlayerIndex());
		boolean deck_condition_met = true; //devCardBank.containsAnyCard();
		
		return (player_condition_met && turn_condition_met && deck_condition_met );
	}

	@Override
	public boolean buyDevCard() {
		int player_index = localPlayer.getPlayerIndex();
		
		BuyDevCardParameters param = new BuyDevCardParameters(player_index);
		
		String json_string = modelSerializer.serializeBuyDevCard(param);
		serverProxy.buyDevCard(json_string);
		
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty(ResourceType type1, ResourceType type2) {
		boolean player_condition_met = localPlayer.canPlayYearOfPlenty();
		boolean resource_bank_condition_met = (resCardBank.containsCard(type1) && resCardBank.containsCard(type2));
		boolean turn_condition_met = turnTracker.canPlayDevCard(localPlayer.getPlayerIndex());
 
		return (player_condition_met && turn_condition_met && resource_bank_condition_met);
	}

	@Override
	public boolean playYearOfPlenty(ResourceType type1, ResourceType type2) {
		int player_index = localPlayer.getPlayerIndex();
		String resourceOne = type1.toString().toLowerCase();
		String resourceTwo = type2.toString().toLowerCase();
		
		YearOfPlentyParameters param = new YearOfPlentyParameters(player_index, resourceOne, resourceTwo);
		
		String json_string = modelSerializer.serializeYearOfPlenty(param);
		serverProxy.playYearOfPlenty(json_string);
		
		return false;
	}

	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2) {
		boolean player_condition_met = localPlayer.canPlayRoadBuilding();
		boolean turn_condition_met = turnTracker.canPlayDevCard(localPlayer.getPlayerIndex());
		boolean map_condition_met = true; //boardMap.canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2, localPlayer.getPlayerIndex());
		
		return (player_condition_met && turn_condition_met && map_condition_met);
	}

	@Override
	public boolean playRoadBuilding(EdgeLocation location1,	EdgeLocation location2) {
		int player_index = localPlayer.getPlayerIndex();
		EdgeLocationParameters edge_location1_param = new EdgeLocationParameters(location1);
		EdgeLocationParameters edge_location2_param = new EdgeLocationParameters(location2);
		
		RoadBuildingParameters param = new RoadBuildingParameters(player_index, edge_location1_param, edge_location2_param);
		
		String json_string = modelSerializer.serializeRoadBuilding(param);
		serverProxy.playRoadBuilding(json_string);
		
		return true;
	}

	@Override
	public boolean canPlaySoldier(HexLocation oldLocation,	HexLocation newLocation, int victimIndex) {
		boolean player_condition_met = localPlayer.canPlaySoldier();
		boolean turn_condition_met = turnTracker.canPlayDevCard(localPlayer.getPlayerIndex());
		boolean map_condition_met = true; //boardMap.canPlaySoldier(HexInterface oldLocation, HexInterface newLocation);
		boolean robbed_player_condition_met = true; //currentGame.playerCanBeRobbed(victimIndex);
		
		return (player_condition_met && turn_condition_met && map_condition_met && robbed_player_condition_met);
	}

	@Override
	public boolean playSoldier(HexLocation newLocation, int victimIndex) {
		int player_index = localPlayer.getPlayerIndex();
		
		SoldierParameters param = new SoldierParameters(player_index, victimIndex, newLocation);
		
		String json_string = modelSerializer.serializeSoldier(param);
		
		serverProxy.playSoldier(json_string);
		
		return true;
	}

	@Override
	public boolean canPlayMonopoly() {
		boolean player_condition_met = true;//localPlayer.canPlayDevCard(devCardType);
		boolean turn_condition_met = turnTracker.canPlayDevCard(localPlayer.getPlayerIndex());
		
		return (player_condition_met && turn_condition_met);
	}

	@Override
	public boolean playMonopoly(ResourceType resourceType) {
		int player_index = localPlayer.getPlayerIndex();
		String resource = resourceType.toString().toLowerCase();
		
		String json_string = modelSerializer.serializeMonopoly(new MonopolyParameters(resource, player_index));
		
		serverProxy.playMonopoly(json_string);
		
		return true;
	}

	@Override
	public boolean canPlayMonument() {
		boolean player_condition_met = localPlayer.canPlayMonument();
		boolean turn_condition_met = turnTracker.canPlayDevCard(localPlayer.getPlayerIndex());
		
		return (player_condition_met && turn_condition_met);
	}

	@Override
	public boolean playMonument() {
		int player_index = localPlayer.getPlayerIndex();
		
		String json_string = modelSerializer.serializeMonument(new MonumentParameters(player_index));
		
		serverProxy.playMonopoly(json_string);
		
		return true;
	}

	//TODO
	@Override
	public boolean creatNewGame(String gameName, boolean randTiles,	boolean randNumbers, boolean randPorts) {
		
		CreateGameRequestParameters param = new CreateGameRequestParameters(randTiles, randNumbers, randPorts, gameName);
		
		String json_string = modelSerializer.serializeCreateGameRequest(param);
		
		String game_info_json = serverProxy.createGame(json_string);
		
		currentGame = null; //modelSerializer.deserializeGameInfo(game_info_json);
		
		return false;
	}

}
