package server.manager;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.card.DevCardBank;
import shared.model.card.MaritimeTrade;
import shared.model.card.ResourceList;
import shared.model.card.Trade;
import shared.model.logging.GameLog;
import shared.model.logging.chat.GameChat;
import shared.model.logging.chat.Message;
import shared.model.logging.history.HistoryLog;
import shared.model.map.BoardMap;
import shared.model.player.Player;
import shared.model.player.Players;
import shared.model.turntracker.TurnTracker;
import shared.model.turntracker.TurntrackerInterface.Status;

public class ServerGameManager implements ServerGameManagerInterface {
	
	private String title = null;
	private int gameId;
	BoardMap boardMap = null;
	TurnTracker turnTracker = null;
	Players players = null;
	DevCardBank devCardBank = null;
	GameLog gameLog = null;
	Trade trade = null;
	
	
	public ServerGameManager(String gameName, boolean randTiles, boolean randNumbers, boolean randPorts) {
		
		title = gameName;
		
		boardMap = new BoardMap(randNumbers, randTiles, randPorts);
		
		turnTracker = new TurnTracker();
		
		players = new Players();
		
		devCardBank = DevCardBank.createBankForNewGame();
		
		gameLog = new GameLog(new HistoryLog(), new GameChat());
		
	}
	
	public boolean containsPlayerId(int player_id) {
		
		return players.containsPlayer(player_id);
	}

	@Override
	public boolean updateGameModel() {
		// TODO Auto-generated method stub
		return false;
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
		
		return true;
	}

	@Override
	public boolean canAcceptTrade(int player_index) {
		
		boolean player_condition_met = players.getPlayer(player_index).canAcceptTrade(trade);
		boolean status_met = (turnTracker.getStatus() == Status.PLAYING);
		boolean turn_condition_met = (turnTracker.getCurrentTurn() != player_index);

		return (player_condition_met && status_met && turn_condition_met);
	}

	@Override
	public boolean acceptTrade(int player_index, boolean accept) {
		
		if(accept) {
			
			players.getPlayer(player_index).acceptTrade(trade);
		}
		else {
			
			//TODO what do we do here?
		}
		
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
		
		players.getPlayer(player_index).discardCards(list);
		
		return true;
	}

	@Override
	public boolean canRoll(int player_index) {

		return turnTracker.canRoll(player_index);
	}

	@Override
	public boolean roll(int player_index, int number) {
		
		return false;//TODO
	}

	@Override
	public boolean canBuildRoad(int player_index, EdgeLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildRoad(int player_index, EdgeLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildSettlement(int player_index, VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildSettlement(int player_index, VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildCity(int player_index, VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildCity(int player_index, VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canOfferTrade(int player_index, ResourceList resources) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerTrade(int player_index, ResourceList resources,	int otherPlayerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMaritimeTrade(int player_index, EdgeLocation location,
			MaritimeTrade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean maritimeTrade(int player_index, EdgeLocation location,
			MaritimeTrade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canFinishTurn(int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishTurn(int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuyDevCard(int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buyDevCard(int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty(int player_index, ResourceType type1,
			ResourceType type2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playYearOfPlenty(int player_index, ResourceType type1,
			ResourceType type2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayRoadBuilding(int player_index,
			EdgeLocation location1, EdgeLocation location2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playRoadBuilding(int player_index, EdgeLocation location1,
			EdgeLocation location2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaySoldier(int player_index, HexLocation oldLocation,
			HexLocation newLocation, int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playSoldier(int player_index, HexLocation newLocation,
			int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonopoly(int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playMonopoly(int player_index, ResourceType resourceType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonument(int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playMonument(int player_index) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getGameTitle() {
		return title;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public Players getPlayers() {
		return players;
	}

}
