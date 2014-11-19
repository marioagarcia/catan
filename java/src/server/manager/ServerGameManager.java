package server.manager;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.card.*;
import shared.model.logging.GameLog;
import shared.model.logging.chat.GameChat;
import shared.model.logging.chat.Message;
import shared.model.logging.history.HistoryLog;
import shared.model.logging.history.LogLine;
import shared.model.manager.GameData;
import shared.model.map.BoardMap;
import shared.model.player.Player;
import shared.model.player.Players;
import shared.model.turntracker.TurnTracker;
import shared.model.turntracker.TurntrackerInterface.Status;

import java.util.ArrayList;

public class ServerGameManager implements ServerGameManagerInterface {

	private static final int MAX_POINTS = 10;
	public static final int TOTAL_PLAYERS = 4;
	private String title = null;
	private int gameId;
	BoardMap boardMap = null;
	TurnTracker turnTracker = null;
	Players players = null;
	ResourceCardBank resourceCardBank = null;
	DevCardBank devCardBank = null;
	GameLog gameLog = null;
	DomesticTrade domesticTrade = null;
	private boolean modelChanged;
	private int version;
	private int winner = -1;


	public ServerGameManager(String gameName, int id, boolean randTiles, boolean randNumbers, boolean randPorts) {
		
		title = gameName;

		gameId = id;
		
		boardMap = new BoardMap(randNumbers, randTiles, randPorts);
		
		turnTracker = new TurnTracker();
		
		players = new Players();

		resourceCardBank = ResourceCardBank.createResourceCardBankForNewGame();
		
		devCardBank = DevCardBank.createBankForNewGame();
		
		gameLog = new GameLog(new HistoryLog(), new GameChat());

		setupGame();
		
	}

	private void setupGame() {

		version = 0;

		turnTracker.setStatus(Status.FIRST_ROUND);

		modelChanged = false;
	}

	public boolean containsPlayerId(int player_id) {
		
		return players.containsPlayer(player_id);
	}

	@Override
	public GameData getGameData() {

		GameData gameData = new GameData();

		gameData.setBoardMap(boardMap);
		gameData.setDevCardBank(devCardBank);
		gameData.setResourceCardBank(resourceCardBank);
		gameData.setDomesticTrade(domesticTrade);
		gameData.setPlayers(players);
		gameData.setTurnTracker(turnTracker);
		gameData.setWinner(getWinner());
		gameData.setVersion(getVersion());
		gameData.setGameLog(gameLog);

		return gameData;
	}

	@Override
	public boolean addAIPlayer(String ai_type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] listAIPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canSendChat(int player_index) {
		
		for(Player player : players.getPlayerList()) {
		
			if(player.getPlayerIndex() == player_index)
			
				return true;
		}
		
		return false;
	}

	@Override
	public boolean sendChat(int player_index, String chatMessage) {
		
		Message message = new Message(chatMessage, players.getPlayer(player_index).getName());
		
		gameLog.getGameChat().addMessage(message);

		modelChanged = true;
		
		return true;
	}

	@Override
	public boolean canAcceptTrade(int player_index) {
		
		boolean player_condition_met = players.getPlayer(player_index).canAcceptTrade(domesticTrade);

		boolean status_met = (turnTracker.getStatus() == Status.PLAYING);

		boolean turn_condition_met = (turnTracker.getCurrentTurn() != player_index);

		return (player_condition_met && status_met && turn_condition_met);
	}

	@Override
	public boolean acceptTrade(int player_index, boolean accept) {

		String name = players.getPlayer(player_index).getName();
		
		if(accept) {

			//Adjust the resources of the player who accepted the trade
			players.getPlayer(player_index).acceptTrade(domesticTrade);

			//Adjust the resources of the player who offered the trade
			players.getPlayer(domesticTrade.getSender()).makeDomesticTrade(domesticTrade);

			//add to the history log
			gameLog.getGameHistoryLog().addLogLine(new LogLine(name, "The trade was accepted"));
		}
		else {
			//add to the history log
			gameLog.getGameHistoryLog().addLogLine(new LogLine(name, "The trade was not accepted"));
		}

		domesticTrade = null;

		modelChanged = true;
		
		return true;
	}

	@Override
	public boolean canDiscardCards(int player_index, ResourceList list) {
		
		return (players.getPlayer(player_index).canDiscardCards(list) && 
				(turnTracker.getStatus() == Status.PLAYING ||
				turnTracker.getStatus() == Status.DISCARDING));
	}

	@Override
	public boolean discardCards(int player_index, ResourceList list) {

		boolean all_discarded = true;
		
		players.getPlayer(player_index).discardCards(list);

		for (Player player : players.getPlayerList()) {

			if(!player.hasDiscarded()) {

				all_discarded = false;
			}
		}

		if(all_discarded) {

			turnTracker.setStatus(Status.ROBBING);

			for(Player player : players.getPlayerList()) {
				player.setDiscarded(true);
			}
		}

		modelChanged = true;
		
		return true;
	}

	@Override
	public boolean canRoll(int player_index) {

		return turnTracker.canRoll(player_index);
	}

	@Override
	public boolean roll(int player_index, int number_rolled) {

		if(number_rolled == 7) {

			turnTracker.setStatus(Status.DISCARDING);

			for(Player player : players.getPlayerList()) {
				player.setDiscarded(false);
			}
		}
		else {

			ArrayList<ResourceList> resources = boardMap.getRollResult(number_rolled);

			int temp_index = 0;

			for(ResourceList resource_list : resources) {

				players.getPlayer(temp_index++).addRollResources(resource_list);
			}

			turnTracker.setStatus(Status.PLAYING);
		}

		//add to history log
		log(("Rolled a " + number_rolled), player_index);


		modelChanged = true;
		
		return true;
	}

	@Override
	public boolean canBuildRoad(int player_index, EdgeLocation location) {

		boolean in_setup_phase = (turnTracker.getStatus() == Status.FIRST_ROUND || turnTracker.getStatus() == Status.SECOND_ROUND);

		int num_roads = boardMap.getNumberOfRoadsByPlayerIndex(player_index);

		if(turnTracker.getStatus() == Status.FIRST_ROUND && num_roads > 0) {
			return false;
		}

		if(turnTracker.getStatus() == Status.SECOND_ROUND && num_roads > 1) {
			return false;
		}

		boolean board_map_condition_met = boardMap.canBuildRoad(location, player_index, turnTracker.getStatus());

		boolean road_building_condition_met = (players.getPlayer(player_index).canBuildRoad() || in_setup_phase);

		boolean player_turn_condition_met = turnTracker.getCurrentTurn() == player_index;

		boolean setup_phase_condition_met = (turnTracker.getStatus() == Status.PLAYING || in_setup_phase);

		return (board_map_condition_met &&
				road_building_condition_met &&
				player_turn_condition_met &&
				setup_phase_condition_met);
	}

	@Override
	public boolean buildRoad(int player_index, EdgeLocation location) {

		boolean isFree = (Status.FIRST_ROUND == turnTracker.getStatus() ||
						  Status.SECOND_ROUND == turnTracker.getStatus());

		players.getPlayer(player_index).buildRoad(isFree);

		boardMap.buildRoad(location, player_index, turnTracker.getStatus());

		 updateLongestRoadPoints();

		//add to history log
		log("built a road", player_index);

		modelChanged = true;
		
		return true;
	}
	
	private void updateLongestRoadPoints(){
		
		int current_longest_index = turnTracker.getPlayerWithLongestRoad();
		int new_longest_index = boardMap.getLongestRoadIndex();
		
		if (current_longest_index != -1)
		{
			players.getPlayer(current_longest_index).setVictoryPoints(players.getPlayer(current_longest_index).getVictoryPoints() - 2);
		}
		if (new_longest_index != -1)
		{
			players.getPlayer(new_longest_index).setVictoryPoints(players.getPlayer(new_longest_index).getVictoryPoints() + 2);
		}
			
		turnTracker.setPlayerWithLongestRoad(new_longest_index);
	}

	@Override
	public boolean canBuildSettlement(int player_index, VertexLocation location) {

		boolean in_setup_phase = (turnTracker.getStatus() == Status.FIRST_ROUND ||
								  turnTracker.getStatus() == Status.SECOND_ROUND);

		int num_settlements = boardMap.getNumberOfSettlementsByPlayerIndex(player_index);

		if(turnTracker.getStatus() == Status.FIRST_ROUND && num_settlements > 0) {
			return false;
		}

		if(turnTracker.getStatus() == Status.SECOND_ROUND && num_settlements > 1) {
			return false;
		}

		boolean board_map_condition_met = boardMap.canBuildSettlement(location, player_index, in_setup_phase);

		boolean settlement_building_condition_met = (players.getPlayer(player_index).canBuildSettlement() || in_setup_phase);

		boolean player_turn_condition_met = turnTracker.getCurrentTurn() == player_index;

		boolean setup_phase_condition_met = (turnTracker.getStatus() == Status.PLAYING || in_setup_phase);

		return (board_map_condition_met &&
				settlement_building_condition_met &&
				player_turn_condition_met &&
				setup_phase_condition_met);
	}

	@Override
	public boolean buildSettlement(int player_index, VertexLocation location) {

		boolean isFree = (Status.FIRST_ROUND == turnTracker.getStatus() ||
				          Status.SECOND_ROUND == turnTracker.getStatus());

		players.getPlayer(player_index).buildSettlement(isFree);

		boardMap.buildSettlement(location, player_index, isFree);

		if(turnTracker.getStatus() == Status.SECOND_ROUND) {

			ResourceList resources = boardMap.getResourcesByVertexLocation(location);

			players.getPlayer(player_index).addRollResources(resources);
		}

		//add to history log
		log("built a settlement", player_index);

		modelChanged = true;

		return true;
	}

	@Override
	public boolean canBuildCity(int player_index, VertexLocation location) {

		boolean in_first_round = (turnTracker.getStatus() == Status.FIRST_ROUND);

		boolean board_map_condition_met = boardMap.canBuildCity(location, player_index);

		boolean player__condition_met = players.getPlayer(player_index).canBuildCity();

		boolean turn_condition_met = turnTracker.getCurrentTurn() == player_index;

		boolean turn_tracker_condition_met = turnTracker.getStatus() == Status.PLAYING && !in_first_round;

		return ( board_map_condition_met && player__condition_met && turn_condition_met && turn_tracker_condition_met);
	}

	@Override
	public boolean buildCity(int player_index, VertexLocation location) {

		players.getPlayer(player_index).buildCity();

		boardMap.buildCity(location, player_index);

		//add to history log
		log("upgraded to a city", player_index);

		modelChanged = true;
		
		return true;
	}

	@Override
	public boolean canOfferTrade(int player_index, ResourceList resources) {
		
		boolean player_can_trade = players.getPlayer(player_index).canOfferTrade(new DomesticTrade(player_index, 0, resources));

		boolean correct_status = turnTracker.getStatus() == Status.PLAYING;

		boolean correct_turn = turnTracker.getCurrentTurn() == player_index;

		return player_can_trade && correct_status && correct_turn;
	}

	@Override
	public boolean offerTrade(int player_index, ResourceList resources,	int otherPlayerIndex) {

		domesticTrade = new DomesticTrade(player_index, otherPlayerIndex, resources);

		return true;
	}

	@Override
	public boolean canMaritimeTrade(int player_index, int ratio, ResourceType resource_in, ResourceType resource_out) {

		MaritimeTrade maritime_trade = new MaritimeTrade();
		maritime_trade.setRatio(ratio);
		maritime_trade.setResourceIn(resource_in);
		maritime_trade.setResourceOut(resource_out);

		EdgeLocation location = boardMap.getLocationForMaritimeTrade(maritime_trade, player_index);

		boolean player_condition_met = players.getPlayer(player_index).canMaritimeTrade(maritime_trade);

		boolean turn_tracker_condition_met = turnTracker.getStatus() == Status.PLAYING;

		boolean player_turn_condition_met = turnTracker.getCurrentTurn() == player_index;

		if(player_condition_met && turn_tracker_condition_met && player_turn_condition_met) {

			return maritime_trade.getRatio() == 4 || boardMap.canMaritimeTrade(location, player_index);
		}

		return false;
	}

	@Override
	public boolean maritimeTrade(int player_index, int ratio, ResourceType resource_in, ResourceType resource_out) {

		MaritimeTrade maritime_trade = new MaritimeTrade();
		maritime_trade.setRatio(ratio);
		maritime_trade.setResourceIn(resource_in);
		maritime_trade.setResourceOut(resource_out);

		players.getPlayer(player_index).makeMaritimeTrade(maritime_trade);

		resourceCardBank.makeMaritimeTrade(maritime_trade);

		modelChanged = true;

		return false;
	}

	@Override
	public boolean canFinishTurn(int player_index) {

		if(turnTracker.getCurrentTurn() == player_index) {

			if(turnTracker.getStatus() == Status.FIRST_ROUND ||
					turnTracker.getStatus() == Status.SECOND_ROUND) {

				int num_roads = boardMap.getNumberOfRoadsByPlayerIndex(player_index);
				int num_settlements = boardMap.getNumberOfSettlementsByPlayerIndex(player_index);

				return !(turnTracker.getStatus() == Status.FIRST_ROUND && (num_roads < 1 || num_settlements < 1)) ||
						!(turnTracker.getStatus() == Status.SECOND_ROUND && (num_roads < 2 || num_settlements < 2));

			}
			else {
				return turnTracker.getStatus() == Status.PLAYING;
			}
		}

		return false;
	}

	@Override
	public boolean finishTurn(int player_index) {

		if(turnTracker.getStatus() == Status.FIRST_ROUND) {

			if(doneWithFirstRound()) {

				turnTracker.setStatus(Status.SECOND_ROUND);
			}
			else {

				nextPlayerTurn(player_index);
			}
		}
		else if(turnTracker.getStatus() == Status.SECOND_ROUND) {

			if(doneWithSecondRound()) {

				turnTracker.setStatus(Status.ROLLING);
			}
			else {

				prevPlayerTurn(player_index);
			}
		}
		else {

			turnTracker.setStatus(Status.ROLLING);

			nextPlayerTurn(player_index);
		}

		players.getPlayer(player_index).endTurn();

		//add to history log
		String name = players.getPlayer(player_index).getName();
		gameLog.getGameHistoryLog().addLogLine(new LogLine(name, (name + "'s turn just ended")));

		modelChanged = true;

		return true;
	}

	private void nextPlayerTurn(int player_index) {

		turnTracker.setCurrentTurn((player_index + 1) % TOTAL_PLAYERS);
	}

	private void prevPlayerTurn(int player_index) {

		if (player_index == 0) throw new AssertionError();

		turnTracker.setCurrentTurn(player_index - 1);
	}

	private boolean doneWithFirstRound() {

		boolean done = true;

		for(Player player : players.getPlayerList()) {

			if(player.getRoads() != 14) {

				done = false;
			}
		}

		return done;
	}

	private boolean doneWithSecondRound() {

		boolean done = true;

		for(Player player : players.getPlayerList()) {

			if(player.getRoads() != 13) {

				done = false;
			}
		}

		return done;
	}

	@Override
	public boolean canBuyDevCard(int player_index) {

		boolean player_condition_met = players.getPlayer(player_index).canBuyDevCard();

		boolean turn_tracker_condition_met = turnTracker.getStatus() == Status.PLAYING;

		boolean deck_condition_met = devCardBank.containsAnyCard();

		return (player_condition_met && turn_tracker_condition_met && deck_condition_met);
	}

	@Override
	public boolean buyDevCard(int player_index) {

		DevCardType card_type = devCardBank.buyDevCard();

		players.getPlayer(player_index).buyDevCard(card_type);

		//add to history log
		log("bought a development card", player_index);

		modelChanged = true;

		return true;
	}

	@Override
	public boolean canPlayYearOfPlenty(int player_index, ResourceType type1, ResourceType type2) {

		boolean player_condition_met = players.getPlayer(player_index).canPlayYearOfPlenty();

		boolean resource_bank_condition_met = (resourceCardBank.containsCards(type1, type2));

		boolean turn_condition_met = turnTracker.canPlayDevCard(player_index);

		return (player_condition_met && turn_condition_met && resource_bank_condition_met);
	}

	@Override
	public boolean playYearOfPlenty(int player_index, ResourceType type1, ResourceType type2) {

		players.getPlayer(player_index).playYearOfPlenty(type1, type2);

		devCardBank.addCard(DevCardType.YEAR_OF_PLENTY);

		resourceCardBank.yearOfPlentyPlayed(type1, type2);

		//add to history log
		String resource1_name = type1.name().toLowerCase();
		String resource2_name = type2.name().toLowerCase();

		String message = "used Year of Plenty and got a " + resource1_name + " and a " + resource2_name;

		log(message, player_index);

		modelChanged = true;

		return true;
	}

	@Override
	public boolean canPlayRoadBuilding(int player_index, EdgeLocation location1, EdgeLocation location2) {

		boolean map_condition_met = boardMap.canPlayRoadBuilding(location1, location2, player_index);

		boolean player_condition_met = players.getPlayer(player_index).canPlayRoadBuilding();

		boolean turn_condition_met = turnTracker.getCurrentTurn() == player_index;

		boolean status_condition_met = turnTracker.getStatus() == Status.PLAYING;

		return (map_condition_met && player_condition_met && turn_condition_met && status_condition_met);
	}

	@Override
	public boolean playRoadBuilding(int player_index, EdgeLocation location1, EdgeLocation location2) {

		players.getPlayer(player_index).playRoadBuilding();

		boardMap.playRoadBuilding(location1, location2, player_index);
		
		updateLongestRoadPoints();

		devCardBank.addCard(DevCardType.ROAD_BUILD);

		//add to history log
		log("built two roads", player_index);

		modelChanged = true;

		return true;
	}

	@Override
	public boolean canPlaySoldier(int player_index, HexLocation old_loc, HexLocation new_loc, int victim_index) {

		boolean map_condition_met = boardMap.canPlaySoldier(old_loc, new_loc, player_index);

		boolean player_condition_met = players.getPlayer(player_index).canPlaySoldier();

		boolean turn_condition_met = turnTracker.getCurrentTurn() == player_index;

		boolean status_condition_met = turnTracker.getStatus() == Status.PLAYING;

		return (map_condition_met && player_condition_met && turn_condition_met && status_condition_met);
	}

	@Override
	public boolean playSoldier(int player_index, HexLocation new_location, int victim_index) {

		boardMap.playSoldier(new_location, player_index);

		ResourceType resource = players.getPlayer(victim_index).rob();

		players.getPlayer(player_index).playSoldier(resource);

		devCardBank.addCard(DevCardType.SOLDIER);

		//add to history log
		log("used a soldier", player_index);

		modelChanged = true;

		return true;
	}

	@Override
	public boolean robPlayer(int player_index, int victim_player_index, HexLocation location) {

		System.out.println("Robbing index: " + player_index);
		System.out.println("Victim robbing index: " + victim_player_index);
		System.out.println("New robber location: " + location.getY() + ", " + location.getY());
		if(victim_player_index != -1) {

			ResourceType resource_type = players.getPlayer(victim_player_index).rob();
			System.out.println("Stole resource");
			players.getPlayer(player_index).addResourceCard(resource_type);
			System.out.println("Added resource");
			//add to history log
			String victim_name = players.getPlayer(victim_player_index).getName();
			System.out.println("Victim name: " + victim_name);
			log(("moved the robber and robbed " + victim_name), player_index);
			System.out.println("Logging");
		}
		else {

			//add to history log
			log("moved the robber and robbed no one", player_index);
		}

		System.out.println("Moving Robber");
		System.out.println("Old location: " + boardMap.getRobberLocation().getX() + ", " + boardMap.getRobberLocation().getY());
		boardMap.setRobberLocation(location);
		System.out.println("New location: " + boardMap.getRobberLocation().getX() + ", " + boardMap.getRobberLocation().getY());
		
		turnTracker.setStatus(Status.PLAYING);

		modelChanged = true;
		
		return true;
	}

	@Override
	public boolean canPlayMonopoly(int player_index) {

		boolean player_condition_met = players.getPlayer(player_index).canPlayMonopoly();

		boolean turn_condition_met = turnTracker.getCurrentTurn() == player_index;

		boolean status_condition_met = turnTracker.getStatus() == Status.PLAYING;

		return (player_condition_met && turn_condition_met && status_condition_met);
	}

	@Override
	public boolean playMonopoly(int player_index, ResourceType resource_type) {

		int count = 0;

		for(Player player : players.getPlayerList()) {

			if(player.getPlayerIndex() != player_index) {
				count += player.giveUpCards(resource_type);
			}
		}

		players.getPlayer(player_index).playMonopoly(resource_type, count);

		devCardBank.addCard(DevCardType.MONOPOLY);

		//add to history log
		log(("stole everyone's " + resource_type.name().toLowerCase()), player_index);

		modelChanged = true;

		return true;
	}

	@Override
	public boolean canPlayMonument(int player_index) {

		boolean player_condition_met = players.getPlayer(player_index).canPlayMonument();

		boolean status_condition_met = turnTracker.getStatus() == Status.PLAYING;

		boolean turn_condition_met = turnTracker.getCurrentTurn() == player_index;

		return (player_condition_met && status_condition_met && turn_condition_met);
	}

	@Override
	public boolean playMonument(int player_index) {

		players.getPlayer(player_index).playMonument();

		devCardBank.addCard(DevCardType.MONUMENT);

		//add to history log
		log("built a monument and gained a victory point", player_index);

		modelChanged = true;

		return true;
	}

	public int getVersion() {

		if(modelChanged)
			version++;

		return version;
	}

	public int getWinner() {

		if (winner == -1){
			for(Player player : players.getPlayerList()) {
	
				if( player.getPoints() >= MAX_POINTS ) {
	
					winner = player.getPlayerIndex();
	
					//add to history log
					log("wins the game!!!", winner);
				}
			}
		}

		return winner;
	}

	public String getGameTitle() { return title; }

	public int getGameId() { return gameId; }

	public Players getPlayers() { return players; }

	private void log(String log_message, int player_index) {

		String name = players.getPlayer(player_index).getName();

		gameLog.getGameHistoryLog().addLogLine(new LogLine(name, (name + " " + log_message)));

	}
}
