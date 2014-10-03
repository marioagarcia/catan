package client.communication.facade;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import client.manager.GameManagerInterface;
import client.model.GameInfo;
import client.model.card.MaritimeTrade;
import client.model.card.ResourceList;
import client.model.card.TradeInterface;
import client.model.map.HexInterface;
import client.model.player.PlayerInfo;
import client.model.player.PlayerInterface;

public class ModelFacade implements ModelFacadeInterface {
	
	private GameManagerInterface gameManager;
	
	public ModelFacade(GameManagerInterface gameManager)
	{
		this.gameManager = gameManager;
	}

	@Override
	public boolean canJoinGame(PlayerInfo player, GameInfo game) {
		return this.gameManager.canJoinGame(player, game);
	}

	@Override
	public void joinGame(int gameId) {
		this.gameManager.joinGame(gameId);
	}

	@Override
	public boolean resetGame() {
		return this.resetGame();
	}

	@Override
	public void saveGameStatus() {
		this.gameManager.saveGameStatus();
	}

	@Override
	public boolean getGameCommands() {
		return this.gameManager.getGameCommands();
	}

	@Override
	public boolean postGameCommands() {
		return this.gameManager.postGameCommands();
	}

	@Override
	public boolean canAcceptTrade(TradeInterface trade) {
		return this.gameManager.canAcceptTrade(trade);
	}

	@Override
	public boolean acceptTrade(TradeInterface trade) {
		return this.gameManager.acceptTrade(trade);
	}

	@Override
	public boolean canDiscardCards() {
		return this.gameManager.canDiscardCards();
	}

	@Override
	public boolean discardCards(ResourceList list) {
		return this.gameManager.discardCards(list);
	}

	@Override
	public boolean canRoll() {
		return this.canRoll();
	}

	@Override
	public boolean roll() {
		return this.gameManager.roll();
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		return this.gameManager.canBuildRoad(location);
	}

	@Override
	public boolean buildRoad(EdgeLocation location) {
		return this.buildRoad(location);
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		return this.gameManager.canBuildSettlement(location);
	}

	@Override
	public boolean buildSettlement(VertexLocation location) {
		return this.gameManager.buildSettlement(location);
	}

	@Override
	public boolean canBuildCity(VertexLocation location) {
		return this.gameManager.canBuildCity(location);
	}

	@Override
	public boolean buildCity(VertexLocation location) {
		return this.gameManager.buildCity(location);
	}

	@Override
	public boolean canOfferTrade(TradeInterface trade) {
		return this.gameManager.canOfferTrade(trade);
	}

	@Override
	public boolean offerTrade(TradeInterface trade, int otherPlayerIndex) {
		return this.gameManager.offerTrade(trade, otherPlayerIndex);
	}

	@Override
	public boolean canMaritimeTrade(HexInterface location, MaritimeTrade trade) {
		return this.gameManager.canMaritimeTrade(location, trade);
	}

	@Override
	public boolean maritimeTrade(HexInterface location, MaritimeTrade trade) {
		return this.gameManager.maritimeTrade(location, trade);
	}

	@Override
	public boolean canFinishTurn() {
		return this.gameManager.canFinishTurn();
	}

	@Override
	public boolean finishTurn() {
		return this.gameManager.finishTurn();
	}

	@Override
	public boolean canBuyDevCard() {
		return this.gameManager.canBuyDevCard();
	}

	@Override
	public boolean buyDevCard() {
		return this.gameManager.buyDevCard();
	}

	@Override
	public boolean canPlayYearOfPlenty() {
		return this.gameManager.canPlayYearOfPlenty();
	}

	@Override
	public boolean playYearOfPlenty() {
		return this.gameManager.playYearOfPlenty();
	}

	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2) {
		return this.gameManager.canPlayRoadBuilding(location1, location2);
	}

	@Override
	public boolean playRoadBuilding(EdgeLocation location1, EdgeLocation location2) {
		return this.gameManager.playRoadBuilding(location1, location2);
	}

	@Override
	public boolean canPlaySoldier(HexInterface oldLocation, HexInterface newLocation) {
		return this.gameManager.canPlaySoldier(oldLocation, newLocation);
	}

	@Override
	public boolean playSoldier(HexInterface oldLocation, HexInterface newLocation) {
		return this.gameManager.playSoldier(oldLocation, newLocation);
	}

	@Override
	public boolean canPlayMonopoly() {
		return this.gameManager.canPlayMonopoly();
	}

	@Override
	public boolean playMonopoly() {
		return this.gameManager.playMonopoly();
	}

	@Override
	public boolean canPlayMonument() {
		return this.gameManager.canPlayMonument();
	}

	@Override
	public boolean playMonument() {
		return this.gameManager.playMonument();
	}

	@Override
	public void populateGameList() {
		this.gameManager.populateGameList();
	}

	@Override
	public PlayerInterface registerPlayer(PlayerInfo playerInfo) {
		return this.gameManager.registerPlayer(playerInfo);
	}

	@Override
	public PlayerInterface loginPlayer(String username, String password) {
		return this.gameManager.loginPlayer(username, password);
	}

	@Override
	public void logoutPlayer() {
		return this.gameManager.logoutPlayer();
	}

}
