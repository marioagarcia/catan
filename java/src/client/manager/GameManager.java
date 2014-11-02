package client.manager;

import java.util.ArrayList;
import java.util.Observer;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.serialization.ModelSerializer;
import shared.serialization.parameters.*;
import client.communication.server.ServerPoller;
import client.communication.server.ServerProxyInterface;
import client.manager.interfaces.GMDomesticTradeInterface;
import client.model.GameInfo;
import client.model.GameModel;
import client.model.card.MaritimeTrade;
import client.model.card.ResourceList;
import client.model.card.TradeInterface;
import client.model.logging.GameLog;
import client.model.logging.chat.GameChat;
import client.model.logging.history.HistoryLog;
import client.model.player.Player;
import client.model.player.Players;
import client.model.player.RobPlayerInfo;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;

public class GameManager implements GameManagerInterface {

	private ServerProxyInterface serverProxy;
	private ModelSerializer modelSerializer;
	private ServerPoller serverPoller;
	private GameList gameListContainer;
	private GameInfo currentGame;
	private GameLog gameLog;
	private GameCommands gameCommands;
	private GameModel gameModel;

	public GameManager(ServerProxyInterface serverProxy) {

		this.serverProxy = serverProxy;

		serverPoller = new ServerPoller();
		serverPoller.setProxy(this.serverProxy);
		serverPoller.registerModelObserver(pollerObserver);
		serverPoller.registerListObserver(listObserver);

		init();
	}

	private void init() {
		
		modelSerializer = new ModelSerializer();

		gameListContainer = new GameList();
		
		gameModel = new GameModel();

		//initialize the model classes that are not in the GameModel
		currentGame = new GameInfo();
		gameLog = new GameLog(new HistoryLog(), new GameChat());
		gameCommands = new GameCommands();

	}
	
	public void addObserver(Observer observer) {
		
		gameModel.addObserver(observer);
		
	}

	@Override
	public boolean loginPlayer(String username, String password) {
		CredentialsParameters credentials = new CredentialsParameters(username, password);

		String json_string = modelSerializer.serializeCredentials(credentials);

		if (!serverProxy.login(json_string).equals("400"))
		{
			gameModel.getLocalPlayer().setName(username);
			gameModel.getLocalPlayer().setPlayerId(serverProxy.getPlayerId());

			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean registerPlayer(String username, String password) {
		CredentialsParameters credentials = new CredentialsParameters(username, password);

		String json_string = modelSerializer.serializeCredentials(credentials);

		if (!serverProxy.register(json_string).equals("400"))
		{
			gameModel.getLocalPlayer().setName(username);
			gameModel.getLocalPlayer().setPlayerId(serverProxy.getPlayerId());

			return true;
		}
		else{
			return false;
		}
	}

	public boolean validatePlayer() {
		return serverProxy.validatePlayer(gameModel.getLocalPlayer());
	}

	public GameInfo[] populateGameList() {
		String json_string = serverProxy.listGames();

		gameListContainer.setGameList(modelSerializer.deserializeGamesList(json_string));

		if(gameListContainer.getGameList() != null){
			return gameListContainer.gameListToArray();
		}

		return null;
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
		GameInfo[] game_list = populateGameList();
		
		for (GameInfo game_info : game_list) {
			if(game.getId() == game_info.getId()) {
				game = game_info;
				break;
			}
		}
		
		return game.playerCanJoin(gameModel.getLocalPlayer());
	}

	@Override
	public boolean joinGame(CatanColor color, GameInfo game) {
		JoinGameRequestParameters param = new JoinGameRequestParameters(game.getId(), color.toString().toLowerCase());

		String json_string = modelSerializer.serializeJoinGameRequest(param);

		String result = serverProxy.joinGame(json_string);

		if(result.equals("Success")) {

			currentGame = game;

			resetFromGameModel(serverProxy.getGameModel(true));

			serverPoller.startPoller(3000);

			return true;
		}

		return false;
	}

	@Override
	public boolean saveGame() {
		//int player_index = localPlayer.getPlayerIndex();

		//SaveGameRequestParameters param = new SaveGameRequestParameters(player_index, currentGame.getTitle());

		//String json_string = modelSerializer.serializeSaveGameRequest(param);

		// serverProxy.saveGame(json_string);

		return true;
	}

	public boolean loadGame() {
		return true;
	}

	public boolean updateGameModel() {
		String json_model = serverProxy.getGameModel(true);
		return resetFromGameModel(json_model);
	}

	public boolean resetFromGameModel(String json_model) {
		
		GameInfo[] game_list = populateGameList();
		
		for (GameInfo game_info : game_list) {
			if(currentGame.getId() == game_info.getId()) {
				currentGame = game_info;
			}
		}

		GameData game_data = modelSerializer.deserializeGameModel(json_model);

		//reset model classes 
		populateGameList();

		if (gameModel.getLocalPlayer().getPlayerIndex() == -1){
			for (Player p : game_data.getPlayerList()){
				if (gameModel.getLocalPlayer().getId() == p.getId()){
					gameModel.getLocalPlayer().setPlayerIndex(p.getPlayerIndex());
				}
			}
		}
		
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();
		
		gameModel.setGameData(game_data);
		
		gameModel.getTurnTracker().setLocalPlayerIndex(player_index);

		gameModel.setLocalPlayer(gameModel.getPlayers().getPlayer(player_index));
		
		gameModel.update();

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

	@Override
	public boolean getGameCommands() {
		String json_string = serverProxy.getGameCommands();

		gameCommands = modelSerializer.deserializeGetGameCommands(json_string);
		if(gameCommands != null)
			return true;

		return false;
	}

	@Override
	public boolean postGameCommands() {
		if(gameCommands == null)
			return false;

		String json_string = null; //TODO modelSerializer.serializePostGameCommands(gameCommands);

		serverProxy.postGameCommands(json_string);

		return true;
	}

	public boolean addAIPlayer(String ai_type) {
		AIRequestParameters param = new AIRequestParameters(ai_type);
		String json_string = modelSerializer.serializeAIRequest(param);
		if(!serverProxy.postNewAI(json_string).equals("400")) {
			return true;
		}
		else {
			return false;
		}
	}

	public String[] listAIPlayers() {
		String json_string = serverProxy.getAIList();
		return modelSerializer.deserializeGetListAI(json_string);		
	}

	@Override
	public boolean canSendChat() {
		return true;
	}

	@Override
	public boolean sendChat(String chatMessage) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

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
		return gameModel.getTurnTracker().canRoll(gameModel.getLocalPlayer().getPlayerIndex());
	}

	@Override
	public boolean roll(int number) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();
		String json_string = modelSerializer.serializeRollNumber(new RollNumberParameters(player_index, number));

		String json_model = serverProxy.rollNumber(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
	}

	public boolean robPlayer(int victimPlayerIndex, HexLocation location) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

		RobPlayerParameters param = new RobPlayerParameters(player_index, victimPlayerIndex, location);

		String json_string = modelSerializer.serializeRobPlayer(param);

		String json_model = serverProxy.robPlayer(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
	}

	@Override
	public boolean canFinishTurn() {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

		if(gameModel.getTurnTracker().getCurrentTurn() == player_index) {

			if(gameModel.getTurnTracker().getStatus() == Status.FIRST_ROUND || 
			   gameModel.getTurnTracker().getStatus() == Status.SECOND_ROUND) {
				
				int num_roads = gameModel.getBoardMap().getNumberOfRoadsByPlayerIndex(player_index);
				int num_settlements = gameModel.getBoardMap().getNumberOfSettlementsByPlayerIndex(player_index);

				if(gameModel.getTurnTracker().getStatus() == 
				   Status.FIRST_ROUND && (num_roads < 1 || num_settlements < 1)) {
					return false;
				}

				if(gameModel.getTurnTracker().getStatus() == 
				   Status.SECOND_ROUND && (num_roads < 2 || num_settlements < 2)) {
					return false;
				}

				return true;
			}
			else {
				return gameModel.getTurnTracker().getStatus() == Status.PLAYING;
			}

		}

		return false;
	}

	@Override
	public boolean finishTurn() {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

		if(TurnTracker.Status.FIRST_ROUND == gameModel.getTurnTracker().getStatus()) {

			gameModel.getLocalPlayer().setPlacedFreeRoad(false);
			gameModel.getLocalPlayer().setPlacedFreeSettlement(false);
		}

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
		boolean player_condition_met = gameModel.getLocalPlayer().canBuyDevCard();
		boolean turn_condition_met = gameModel.getTurnTracker().canBuyDevCard(gameModel.getLocalPlayer().getPlayerIndex());
		boolean deck_condition_met = gameModel.getDevCardBank().containsAnyCard();

		return (player_condition_met && turn_condition_met && deck_condition_met);
	}

	@Override
	public boolean buyDevCard() {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

		BuyDevCardParameters param = new BuyDevCardParameters(player_index);

		String json_string = modelSerializer.serializeBuyDevCard(param);
		serverProxy.buyDevCard(json_string);

		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty(ResourceType type1, ResourceType type2) {
		boolean player_condition_met = gameModel.getLocalPlayer().canPlayYearOfPlenty();
		boolean resource_bank_condition_met = (gameModel.getResourceCardBank().containsCards(type1, type2));
		boolean turn_condition_met = gameModel.getTurnTracker().canPlayDevCard(gameModel.getLocalPlayer().getPlayerIndex());

		return (player_condition_met && turn_condition_met && resource_bank_condition_met);
	}

	@Override
	public boolean playYearOfPlenty(ResourceType type1, ResourceType type2) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();
		String resourceOne = type1.toString().toLowerCase();
		String resourceTwo = type2.toString().toLowerCase();

		YearOfPlentyParameters param = new YearOfPlentyParameters(player_index, resourceOne, resourceTwo);

		String json_string = modelSerializer.serializeYearOfPlenty(param);
		serverProxy.playYearOfPlenty(json_string);

		return false;
	}

	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2) {
		return (gameModel.getBoardMap().canPlayRoadBuilding(location1, location2, gameModel.getLocalPlayer().getPlayerIndex()) &&
				gameModel.getLocalPlayer().canPlayRoadBuilding() && 
				gameModel.getTurnTracker().getCurrentTurn() == gameModel.getLocalPlayer().getPlayerIndex() &&
				gameModel.getTurnTracker().getStatus() == Status.PLAYING);
	}

	@Override
	public boolean playRoadBuilding(EdgeLocation location1,	EdgeLocation location2) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();
		EdgeLocationParameters edge_location1_param = new EdgeLocationParameters(location1);
		EdgeLocationParameters edge_location2_param = new EdgeLocationParameters(location2);

		RoadBuildingParameters param = new RoadBuildingParameters(player_index, edge_location1_param, edge_location2_param);

		String json_string = modelSerializer.serializeRoadBuilding(param);
		serverProxy.playRoadBuilding(json_string);

		return true;
	}

	@Override
	public boolean canPlaySoldier(HexLocation oldLocation,	HexLocation newLocation, int victimIndex) {
		return (gameModel.getBoardMap().canPlaySoldier(oldLocation, newLocation, victimIndex) &&
				gameModel.getLocalPlayer().canPlaySoldier() &&
				gameModel.getTurnTracker().getCurrentTurn() == gameModel.getLocalPlayer().getPlayerIndex() &&
				gameModel.getTurnTracker().getStatus() == Status.PLAYING);
	}

	@Override
	public boolean playSoldier(HexLocation newLocation, int victimIndex) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

		SoldierParameters param = new SoldierParameters(player_index, victimIndex, newLocation);

		String json_string = modelSerializer.serializeSoldier(param);

		serverProxy.playSoldier(json_string);

		return true;
	}

	@Override
	public boolean canPlayMonopoly() {
		boolean player_condition_met = gameModel.getLocalPlayer().canPlayMonopoly();
		boolean turn_condition_met = gameModel.getTurnTracker().canPlayDevCard(gameModel.getLocalPlayer().getPlayerIndex());

		return (player_condition_met && turn_condition_met);
	}

	@Override
	public boolean playMonopoly(ResourceType resourceType) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();
		String resource = resourceType.toString().toLowerCase();

		String json_string = modelSerializer.serializeMonopoly(new MonopolyParameters(resource, player_index));

		serverProxy.playMonopoly(json_string);

		return true;
	}

	@Override
	public boolean canPlayMonument() {
		boolean player_condition_met = gameModel.getLocalPlayer().canPlayMonument();
		boolean turn_condition_met = gameModel.getTurnTracker().canPlayDevCard(gameModel.getLocalPlayer().getPlayerIndex());

		return (player_condition_met && turn_condition_met);
	}

	@Override
	public boolean playMonument() {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

		String json_string = modelSerializer.serializeMonument(new MonumentParameters(player_index));

		serverProxy.playMonument(json_string);

		return true;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();
		boolean in_setup_phase = (gameModel.getTurnTracker().getStatus() == Status.FIRST_ROUND || gameModel.getTurnTracker().getStatus() == Status.SECOND_ROUND);

		int num_roads = gameModel.getBoardMap().getNumberOfRoadsByPlayerIndex(player_index);

		if(gameModel.getTurnTracker().getStatus() == Status.FIRST_ROUND && num_roads > 0) {
			return false;
		}

		if(gameModel.getTurnTracker().getStatus() == Status.SECOND_ROUND && num_roads > 1) {
			return false;
		}

		return (gameModel.getBoardMap().canBuildRoad(location, player_index, gameModel.getTurnTracker().getStatus()) &&
				(gameModel.getLocalPlayer().canBuildRoad() || in_setup_phase)  && 
				gameModel.getTurnTracker().getCurrentTurn() == gameModel.getLocalPlayer().getPlayerIndex() &&
				(gameModel.getTurnTracker().getStatus() == Status.PLAYING || in_setup_phase));
	}

	@Override
	public boolean buildRoad(EdgeLocation location) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();
		boolean isFree = (TurnTracker.Status.FIRST_ROUND == gameModel.getTurnTracker().getStatus() || 
				TurnTracker.Status.SECOND_ROUND == gameModel.getTurnTracker().getStatus());
		
		if (isFree){
			gameModel.getLocalPlayer().setPlacedFreeRoad(true);
		}

		BuildRoadParameters param = new BuildRoadParameters(player_index, new EdgeLocationParameters(location), isFree);
		String json_string = modelSerializer.serializeBuildRoad(param);

		String json_model = serverProxy.buildRoad(json_string);

		if(resetFromGameModel(json_model)) {

			return true;
		}
		else
			return false;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();
		boolean in_setup_phase = (gameModel.getTurnTracker().getStatus() == Status.FIRST_ROUND || gameModel.getTurnTracker().getStatus() == Status.SECOND_ROUND);

		int num_settlements = gameModel.getBoardMap().getNumberOfSettlementsByPlayerIndex(player_index);

		if(gameModel.getTurnTracker().getStatus() == Status.FIRST_ROUND && num_settlements > 0) {
			return false;
		}

		if(gameModel.getTurnTracker().getStatus() == Status.SECOND_ROUND && num_settlements > 1) {
			return false;
		}

		return (gameModel.getBoardMap().canBuildSettlement(location, player_index, in_setup_phase) &&
				(gameModel.getLocalPlayer().canBuildSettlement() || in_setup_phase) &&
				gameModel.getTurnTracker().getCurrentTurn() == gameModel.getLocalPlayer().getPlayerIndex() &&
				(gameModel.getTurnTracker().getStatus() == Status.PLAYING || in_setup_phase));
	}

	@Override
	public boolean buildSettlement(VertexLocation location) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();
		boolean isFree = (TurnTracker.Status.FIRST_ROUND == gameModel.getTurnTracker().getStatus() ||
				TurnTracker.Status.SECOND_ROUND == gameModel.getTurnTracker().getStatus());
		
		if (isFree){
			gameModel.getLocalPlayer().setPlacedFreeSettlement(true);
		}

		location = location.getNormalizedLocation();

		BuildSettlementParameters param = new BuildSettlementParameters(player_index, new VertexLocationParameters(location), isFree);

		String json_string = modelSerializer.serializeBuildSettlement(param);

		String json_model = serverProxy.buildSettlement(json_string);

		if(resetFromGameModel(json_model)) {			
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean canBuildCity(VertexLocation location) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

		boolean in_first_round = (gameModel.getTurnTracker().getStatus() == Status.FIRST_ROUND);

		return (gameModel.getBoardMap().canBuildCity(location, player_index) &&
				gameModel.getLocalPlayer().canBuildCity() &&
				gameModel.getTurnTracker().getCurrentTurn() == gameModel.getLocalPlayer().getPlayerIndex() &&
				gameModel.getTurnTracker().getStatus() == Status.PLAYING && !in_first_round);
	}

	@Override
	public boolean buildCity(VertexLocation location) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

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
		boolean player_can_trade = gameModel.getLocalPlayer().canOfferTrade(trade);
		boolean correct_status = gameModel.getTurnTracker().getStatus() == Status.PLAYING;
		boolean correct_turn = gameModel.getTurnTracker().getCurrentTurn() == gameModel.getLocalPlayer().getPlayerIndex(); 

		return player_can_trade && correct_status && correct_turn;
	}

	@Override
	public boolean offerTrade(GMDomesticTradeInterface trade, int otherPlayerIndex) {
		int local_player_index = gameModel.getLocalPlayer().getPlayerIndex();

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
		boolean player_condition_met = gameModel.getLocalPlayer().canAcceptTrade(trade);
		boolean status_met = (gameModel.getTurnTracker().getStatus() == Status.PLAYING);
		boolean turn_condition_met = (gameModel.getTurnTracker().getCurrentTurn() != gameModel.getLocalPlayer().getPlayerIndex());

		return (player_condition_met && status_met && turn_condition_met);
	}

	@Override
	public boolean acceptTrade(TradeInterface trade, boolean accept) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

		String json_string = modelSerializer.serializeAcceptTrade(new AcceptTradeParameters(player_index, accept));
		String json_model = serverProxy.acceptTrade(json_string);

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
	}

	@Override
	public boolean canDiscardCards(ResourceList list) {
		return (gameModel.getLocalPlayer().canDiscardCards(list) && (gameModel.getTurnTracker().getStatus() == Status.PLAYING || gameModel.getTurnTracker().getStatus() == Status.DISCARDING));
	}

	@Override
	public boolean discardCards(ResourceList list) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();

		String json_string = modelSerializer.serializeDiscardCards(new DiscardCardsParameters(player_index, list));

		String json_model = serverProxy.discardCards(json_string); //this should throw an exception	

		if(resetFromGameModel(json_model))
			return true;
		else
			return false;
	}

	@Override
	public boolean canMaritimeTrade(EdgeLocation location, MaritimeTrade trade) {
		if(trade.getRatio() == 4){
			return (gameModel.getLocalPlayer().canMaritimeTrade(trade) &&
					gameModel.getTurnTracker().getStatus() == Status.PLAYING && 
					gameModel.getTurnTracker().getCurrentTurn() == gameModel.getLocalPlayer().getPlayerIndex());
		}
		return (gameModel.getLocalPlayer().canMaritimeTrade(trade) &&
				gameModel.getBoardMap().canMaritimeTrade(location, gameModel.getLocalPlayer().getPlayerIndex()) &&
				gameModel.getTurnTracker().getStatus() == Status.PLAYING && 
				gameModel.getTurnTracker().getCurrentTurn() == gameModel.getLocalPlayer().getPlayerIndex());
	}

	@Override
	public boolean maritimeTrade(EdgeLocation location, MaritimeTrade trade) {
		int player_index = gameModel.getLocalPlayer().getPlayerIndex();
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
	
	private ServerPoller.GameListObserver listObserver = new ServerPoller.GameListObserver() {

		@Override
		public void gameListChanged(String game_list_data) {

			gameListContainer.setGameList(modelSerializer.deserializeGamesList(game_list_data));

			gameListContainer.update();
		}
	};

	public void startPoller(int interval) {
		serverPoller.startPoller(interval);
	}

	public ServerProxyInterface getServerProxy() {
		return serverProxy;
	}

	public ModelSerializer getModelSerializer() {
		return modelSerializer;
	}

	public ServerPoller getServerPoller() {
		return serverPoller;
	}

	public GameList getGameList() {
		return gameListContainer;
	}

	public GameInfo getCurrentGame() {
		return currentGame;
	}

	public GameLog getGameLog() {
		return gameLog;
	}
	
	public Player getLocalPlayer() {
		return gameModel.getLocalPlayer();
	}
	
	public Players getAllPlayers() {
		return gameModel.getPlayers();
	}

	public ServerPoller.ModelStateObserver getPollerObserver() {
		return pollerObserver;
	}

	public void setGameCommands(GameCommands gameCommands) {
		this.gameCommands = gameCommands;
	}

	@Override
	public boolean isLocalPlayersTurn(){
		return (gameModel.getLocalPlayer().getPlayerIndex() == gameModel.getTurnTracker().getCurrentTurn());
	}

	public RobPlayerInfo[] getRobbablePlayers(HexLocation location){
		ArrayList<Integer> player_list = gameModel.getBoardMap().getRobbablePlayers(location);

		ArrayList<RobPlayerInfo> rob_array= new ArrayList<RobPlayerInfo>();
		RobPlayerInfo[] rob_list = new RobPlayerInfo[0];

		for (int player_index : player_list){

			int number_of_cards = gameModel.getPlayers().getPlayer(player_index).getNumberOfCards();

			if (gameModel.getLocalPlayer().getPlayerIndex() != player_index && number_of_cards > 0){
				RobPlayerInfo rob_player_info = new RobPlayerInfo();

				rob_player_info.setNumCards(gameModel.getPlayers().getPlayer(player_index).getNumberOfCards());

				CatanColor color = currentGame.getPlayers().get(player_index).getColor();
				String name = currentGame.getPlayers().get(player_index).getName();
				int id = currentGame.getPlayers().get(player_index).getId();

				rob_player_info.setPlayerInfo(color, name, id);
				rob_player_info.setPlayerIndex(player_index);

				rob_array.add(rob_player_info);
			}
		}

		return rob_array.toArray(rob_list);
	}

}