package server.manager;

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
import shared.model.facade.ModelFacadeInterface;
import shared.model.manager.interfaces.GMDomesticTradeInterface;

public class ServerModelFacade implements ModelFacadeInterface {

	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean loginPlayer(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerPlayer(String username, String password) {
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
	public boolean canJoinGame(CatanColor color, GameInfo game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean joinGame(CatanColor color, GameInfo game) {
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
	public boolean resetGame() {
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
	public boolean canSendChat() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendChat(String chatMessage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAcceptTrade(TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptTrade(TradeInterface trade, boolean accept) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canDiscardCards(ResourceList list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean discardCards(ResourceList list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRoll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean roll(int number) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildRoad(EdgeLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildSettlement(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildCity(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildCity(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canOfferTrade(TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerTrade(GMDomesticTradeInterface trade,
			int otherPlayerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMaritimeTrade(EdgeLocation location, MaritimeTrade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean maritimeTrade(EdgeLocation location, MaritimeTrade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canFinishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty(ResourceType type1, ResourceType type2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playYearOfPlenty(ResourceType type1, ResourceType type2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1,
			EdgeLocation location2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playRoadBuilding(EdgeLocation location1,
			EdgeLocation location2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaySoldier(HexLocation oldLocation,
			HexLocation newLocation, int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playSoldier(HexLocation newLocation, int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonopoly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playMonopoly(ResourceType resourceType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonument() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playMonument() {
		// TODO Auto-generated method stub
		return false;
	}

}
