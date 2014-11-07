package server.facade;

import java.util.Observer;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.GameInfo;
import shared.model.card.MaritimeTrade;
import shared.model.card.ResourceList;
import shared.model.card.TradeInterface;
import shared.model.manager.GameList;
import shared.model.manager.interfaces.GMDomesticTradeInterface;

public class ServerModelFacade implements ServerModelFacadeInterface {

	@Override
	public void addObserver(int game_id, Observer observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean loginPlayer(int game_id, String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerPlayer(int game_id, String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewGame(String gameName, boolean randTiles,
			boolean randNumbers, boolean randPorts) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canJoinGame(int game_id, int player_id, CatanColor color) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean joinGame(int game_id, int player_id, CatanColor color) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveGame(int game_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadGame(int game_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateGameModel(int game_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resetGame(int game_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getGameCommands(int game_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postGameCommands(int game_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSendChat(int game_id, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendChat(int game_id, int player_index, String chatMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAcceptTrade(int game_id, int player_index,
			TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptTrade(int game_id, int player_index,
			TradeInterface trade, boolean accept) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canDiscardCards(int game_id, int player_index,
			ResourceList list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean discardCards(int game_id, int player_index, ResourceList list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRoll(int game_id, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean roll(int game_id, int player_index, int number) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildRoad(int game_id, int player_index,
			EdgeLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildRoad(int game_id, int player_index,
			EdgeLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildSettlement(int game_id, int player_index,
			VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildSettlement(int game_id, int player_index,
			VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildCity(int game_id, int player_index,
			VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildCity(int game_id, int player_index,
			VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canOfferTrade(int game_id, int player_index,
			TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerTrade(int game_id, int player_index,
			GMDomesticTradeInterface trade, int otherPlayerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMaritimeTrade(int game_id, int player_index,
			EdgeLocation location, MaritimeTrade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean maritimeTrade(int game_id, int player_index,
			EdgeLocation location, MaritimeTrade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canFinishTurn(int game_id, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishTurn(int game_id, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuyDevCard(int game_id, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buyDevCard(int game_id, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty(int game_id, int player_index,
			ResourceType type1, ResourceType type2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playYearOfPlenty(int game_id, int player_index,
			ResourceType type1, ResourceType type2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayRoadBuilding(int game_id, int player_index,
			EdgeLocation location1, EdgeLocation location2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playRoadBuilding(int game_id, int player_index,
			EdgeLocation location1, EdgeLocation location2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaySoldier(int game_id, int player_index,
			HexLocation oldLocation, HexLocation newLocation, int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playSoldier(int game_id, int player_index,
			HexLocation newLocation, int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonopoly(int game_id, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playMonopoly(int game_id, int player_index,
			ResourceType resourceType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonument(int game_id, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playMonument(int game_id, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameList getGameList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
