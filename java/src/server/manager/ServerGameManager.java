package server.manager;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.GameInfo;
import shared.model.GameModel;
import shared.model.card.MaritimeTrade;
import shared.model.card.ResourceList;
import shared.model.card.TradeInterface;
import shared.model.player.Players;

public class ServerGameManager implements ServerGameManagerInterface {
	
	private String title = null;
	private int gameId;
	GameModel gameModel = null;
	
	public ServerGameManager(String gameName, boolean randTitles, boolean randNumbers, boolean randPorts) {
		
	}
	
	public String getGameTitle() {
		return title;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public Players getPlayers() {
		return null;
	}

	@Override
	public boolean canJoinGame(int playerId, CatanColor color, GameInfo game) {
		
//		for (GameInfo game_info : gameList.getGameList()) {
//			if(game.getId() == game_info.getId()) {
//				game = game_info;
//				break;
//			}
//		}
		
		return game.playerCanJoin(gameModel.getLocalPlayer());
	}
	
	public boolean containsPlayerId(int player_id) {
		return true;
	}

	@Override
	public boolean joinGame(int game_id, CatanColor color, GameInfo game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateGameModel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getGameCommands() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postGameCommands() {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendChat(int player_index, String chatMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAcceptTrade(int player_index, TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptTrade(int player_index, TradeInterface trade,
			boolean accept) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canDiscardCards(int player_index, ResourceList list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean discardCards(int player_index, ResourceList list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRoll(int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean roll(int player_index, int number) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean canOfferTrade(int player_index, TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerTrade(int player_index, TradeInterface trade,
			int otherPlayerIndex) {
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


}
