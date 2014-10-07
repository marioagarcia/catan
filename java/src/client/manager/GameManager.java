package client.manager;

import java.util.ArrayList;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.serialization.ModelSerializer;
import shared.serialization.parameters.*;
import client.communication.server.ServerPoller;
import client.communication.server.ServerPollerInterface;
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
import client.model.map.BoardMap;
import client.model.map.HexInterface;
import client.model.player.Player;
import client.model.player.PlayerInfo;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;
import client.roll.DiceRoller;

public class GameManager implements GameManagerInterface {

	private ServerProxy serverProxy;
	private ModelSerializer modelSerializer;
	private ServerPoller serverPoller;
	private ArrayList<GameInfo> gameList;
	private Player localPlayer;
	private GameInfo currentGame;
	private GameLog gameLog;
	private GameCommands gameCommands;
	private TurnTracker turnTracker;
	private DiceRoller diceRoller;
	private BoardMap boardMap;
	private DevCardBank devCardBank;
	private ResourceCardBank resCardBank;
	private ArrayList<Player> allPlayers;

	public GameManager(ServerProxy serverProxy) {

		this.serverProxy = serverProxy;

		serverPoller = new ServerPoller();
		serverPoller.registerObserver(pollerObserver);

		modelSerializer = new ModelSerializer();

		diceRoller = new DiceRoller();
		gameList = null;
		localPlayer = null;
		currentGame = null;
		gameCommands = null;
		turnTracker = null;
		boardMap = null;
		devCardBank = null;
		resCardBank = null;
		allPlayers = null;
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

	public boolean validatePlayer() {
		return serverProxy.validatePlayer(localPlayer);
	}

	@Override
	public boolean populateGameList() {
		String json_string = serverProxy.listGames();

		gameList = modelSerializer.deserializeGamesList(json_string);

		if(gameList != null)
			return true;

		return false;
	}

	@Override
	public boolean createNewGame(String gameName, boolean randTiles, boolean randNumbers, boolean randPorts) {

		CreateGameRequestParameters param = new CreateGameRequestParameters(randTiles, randNumbers, randPorts, gameName);

		String json_string = modelSerializer.serializeCreateGameRequest(param);

		String game_info_json = serverProxy.createGame(json_string);

		currentGame = modelSerializer.deserializeGameInfo(game_info_json);

		if(currentGame != null)
			return true;

		return false;
	}

	@Override
	public boolean canJoinGame(CatanColor color, GameInfo game) {
		return (validatePlayer() && game.validateColor(color));
	}

	@Override
	public boolean joinGame(CatanColor color, GameInfo game) {
		JoinGameRequestParameters param = new JoinGameRequestParameters(game.getId(), color.toString().toLowerCase());

		String json_string = modelSerializer.serializeJoinGameRequest(param);

		String result = serverProxy.joinGame(json_string);

		if(result == "Success") {
			
			localPlayer = new Player();
			
			for (int i = 0; i < currentGame.getPlayers().size(); i++) {
				PlayerInfo player_info = currentGame.getPlayers().get(i);
				
				if(player_info.getId() == serverProxy.getPlayerId()) {
					localPlayer.setPlayerId(serverProxy.getPlayerId());
					localPlayer.setName(player_info.getName());
					localPlayer.setPlayerIndex(player_info.getPlayerIndex());
				}

			}

			currentGame = game;
			
			return true;
		}

		return false;
	}

	@Override
	public boolean saveGame() {
		int player_index = localPlayer.getPlayerIndex();

		SaveGameRequestParameters param = new SaveGameRequestParameters(player_index, currentGame.getTitle());

		String json_string = modelSerializer.serializeSaveGameRequest(param);

		//TODO serverProxy.saveGame(json_string);

		return true;
	}

	public boolean resetFromGameModel(String json_model) {

		GameData game_data = modelSerializer.deserializeGameModel(json_model);

		//reset model classes 
		populateGameList();

		int player_index;
		if(localPlayer != null) {
			player_index = localPlayer.getPlayerIndex();
		}
		else return false;

		localPlayer = game_data.getPlayerList().get(player_index);

		turnTracker = game_data.getTurnTracker();

		boardMap = game_data.getBoardMap();

		devCardBank = game_data.getDevCardBank();

		resCardBank = game_data.getResourceCardBank();

		allPlayers = game_data.getPlayerList();

		return true;
	}

	@Override
	public boolean resetGame() { 
		String json_model = serverProxy.resetGame();

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
	}

	//TODO
	@Override
	public boolean getGameCommands() {
		String json_string = serverProxy.getGameCommands();

		gameCommands = modelSerializer.deserializeGetGameCommands(json_string);
		if(gameCommands != null)
			return true;

		return false;
	}

	//TODO
	@Override
	public boolean postGameCommands() {
		if(gameCommands == null)
			return false;

		String json_string = null; //modelSerializer.serializePostGameCommands(gameCommands);

		serverProxy.postGameCommands(json_string);

		return true;
	}

	@Override
	public boolean sendChat(String chatMessage) {
		int player_index = localPlayer.getPlayerIndex();

		SendChatParameters param = new SendChatParameters(player_index, chatMessage);

		String json_string = modelSerializer.serializeSendChat(param);

		String json_model = serverProxy.sendChat(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
	}

	@Override
	public boolean canRoll() {
		return turnTracker.canRoll(localPlayer.getPlayerIndex());
	}

	@Override
	public boolean roll() {
		int player_index = localPlayer.getPlayerIndex();
		int number = diceRoller.roll();

		String json_string = modelSerializer.serializeRollNumber(new RollNumberParameters(player_index, number));

		String json_model = serverProxy.rollNumber(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
	}

	//TODO
	@Override
	public boolean robPlayer(int victimPlayerIndex, HexLocation location) {
		int player_index = localPlayer.getPlayerIndex();

		RobPlayerParameters param = new RobPlayerParameters(player_index, victimPlayerIndex, location);

		@SuppressWarnings("unused")
		String json_string = modelSerializer.serializeRobPlayer(param);

		String json_model = null; //serverProxy.robPlayer(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
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

		String json_model = serverProxy.finishTurn(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
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

		String json_model = serverProxy.buildRoad(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
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

		String json_model = serverProxy.buildSettlement(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
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

		String json_model = serverProxy.buildCity(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
	}

	@Override
	public boolean canOfferTrade(TradeInterface trade) {
		return localPlayer.canOfferTrade(trade);
	}

	@Override
	public boolean offerTrade(GMDomesticTradeInterface trade, int otherPlayerIndex) {
		int local_player_index = localPlayer.getPlayerIndex();

		ResourceList resource_list = new ResourceList(trade.getBrickCount(), trade.getOreCount(), trade.getSheepCount(), trade.getWheatCount(), trade.getWoodCount());

		String json_string = modelSerializer.serializeOfferTrade(new OfferTradeParameters(local_player_index, resource_list, otherPlayerIndex));

		String json_model = serverProxy.offerTrade(json_string);

		if(resetFromGameModel(json_model))
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
		String json_model = serverProxy.acceptTrade(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
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

		String json_model = serverProxy.discardCards(json_string); //this should throw an exception	

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
	}

	@Override
	public boolean canMaritimeTrade(VertexLocation location, MaritimeTrade trade) {
		return (localPlayer.canOfferTrade(trade) && boardMap.canMaritimeTrade(location, localPlayer.getPlayerIndex()));
	}

	@Override
	public boolean maritimeTrade(VertexLocation location, MaritimeTrade trade) {
		int player_index = localPlayer.getPlayerIndex();
		String resource_in = trade.getResourceIn().toString().toLowerCase();
		String resource_out = trade.getResourceOut().toString().toLowerCase();

		MaritimeTradeParameters param = new MaritimeTradeParameters(player_index, trade.getRatio(), resource_in, resource_out);

		String json_string = modelSerializer.serializeMaritimeTrade(param);

		String json_model = serverProxy.offerTrade(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
	}

	private ServerPoller.ModelStateObserver pollerObserver = new ServerPoller.ModelStateObserver() {

		@Override
		public void modelChanged(String json_model) {

			resetFromGameModel(json_model);
		}
	};

	public ServerProxy getServerProxy() {
		return serverProxy;
	}

	public ModelSerializer getModelSerializer() {
		return modelSerializer;
	}

	public ServerPoller getServerPoller() {
		return serverPoller;
	}

	public ArrayList<GameInfo> getGameList() {
		return gameList;
	}

	public Player getLocalPlayer() {
		return localPlayer;
	}

	public GameInfo getCurrentGame() {
		return currentGame;
	}

	public GameLog getGameLog() {
		return gameLog;
	}

	public TurnTracker getTurnTracker() {
		return turnTracker;
	}

	public DiceRoller getDiceRoller() {
		return diceRoller;
	}

	public BoardMap getBoardMap() {
		return boardMap;
	}

	public DevCardBank getDevCardBank() {
		return devCardBank;
	}

	public ResourceCardBank getResCardBank() {
		return resCardBank;
	}

	public ArrayList<Player> getAllPlayers() {
		return allPlayers;
	}

	public ServerPoller.ModelStateObserver getPollerObserver() {
		return pollerObserver;
	}

	public void setGameCommands(GameCommands gameCommands) {
		this.gameCommands = gameCommands;
	}
}