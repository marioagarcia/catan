package client.communication.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.serialization.interfaces.SerializerResourceListInterface;
import client.communication.server.ServerMoxy;
import client.communication.server.ServerProxy;
import client.communication.server.ServerProxyInterface;
import client.manager.GameManager;
import client.manager.interfaces.GMDomesticTradeInterface;
import client.model.GameInfo;
import client.model.card.MaritimeTrade;
import client.model.card.ResourceList;
import client.model.card.TradeInterface;
import client.model.player.Player;
import client.model.player.PlayerInfo;
import client.model.player.Players;

public class ModelFacade implements ModelFacadeInterface {
	
	private static ModelFacade facadeInstance = null;
	private GameManager gameManager;
	
	protected ModelFacade(ServerProxyInterface proxy)
	{
		if (proxy != null)
		{
			this.gameManager = new GameManager(proxy);
		}
		else
		{
			this.gameManager = new GameManager(new ServerMoxy());
		}
	}
	
	public static ModelFacade getInstance(ServerProxyInterface proxy){
		if(facadeInstance == null){
			if(proxy == null){
				facadeInstance = new ModelFacade(new ServerProxy(null, null));
			}else
			{
				facadeInstance = new ModelFacade(proxy);
			}
		}
		return facadeInstance;
	}
	
	@Override
	public boolean canJoinGame(CatanColor color, GameInfo game) {
		return this.gameManager.canJoinGame(color, game);
	}

	@Override
	public boolean joinGame(CatanColor color, GameInfo game) {
		return this.gameManager.joinGame(color, game);
	}
	
	@Override
	public boolean loadGame() {
		return this.gameManager.loadGame();
	}
	
	@Override
	public boolean updateGameModel() {
		return this.gameManager.updateGameModel();
	}

	@Override
	public boolean resetGame() {
		return this.resetGame();
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
	public boolean acceptTrade(TradeInterface trade, boolean accept) {
		return this.gameManager.acceptTrade(trade, accept);
	}

	@Override
	public boolean canDiscardCards(ResourceList list) {
		return this.gameManager.canDiscardCards(list);
	}

	@Override
	public boolean discardCards(ResourceList list) {
		return this.gameManager.discardCards(list);
	}

	@Override
	public boolean canRoll() {
		return gameManager.canRoll();
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
		return this.gameManager.buildRoad(location);
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
	public boolean offerTrade(GMDomesticTradeInterface trade, int otherPlayerIndex) {
		return this.gameManager.offerTrade(trade, otherPlayerIndex);
	}

	@Override
	public boolean canMaritimeTrade(VertexLocation location, MaritimeTrade trade) {
		return this.gameManager.canMaritimeTrade(location, trade);
	}

	@Override
	public boolean maritimeTrade(VertexLocation location, MaritimeTrade trade) {
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
	public boolean canPlayYearOfPlenty(ResourceType resource1, ResourceType resource2) {
		return this.gameManager.canPlayYearOfPlenty(resource1, resource2);
	}

	@Override
	public boolean playYearOfPlenty(ResourceType resource1, ResourceType resource2) {
		return this.gameManager.playYearOfPlenty(resource1, resource2);
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
	public boolean canPlaySoldier(HexLocation oldLocation, HexLocation newLocation, int victim) {
		return this.gameManager.canPlaySoldier(oldLocation, newLocation, victim);
	}

	@Override
	public boolean playSoldier(HexLocation newLocation, int victim) {
		return this.gameManager.playSoldier(newLocation, victim);
	}

	@Override
	public boolean canPlayMonopoly() {
		return this.gameManager.canPlayMonopoly();
	}

	@Override
	public boolean playMonopoly(ResourceType resourceType) {
		return false; //this.gameManager.playMonopoly();
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
	public boolean registerPlayer(String username, String password) {
		return this.gameManager.registerPlayer(username, password);
	}

	@Override
	public boolean loginPlayer(String username, String password) {
		return this.gameManager.loginPlayer(username, password);
	}

	@Override
	public boolean canSendChat() {
		return this.gameManager.canSendChat();
	}

	@Override
	public boolean sendChat(String message) {
		return this.gameManager.sendChat(message);
	}

	@Override
	public boolean createNewGame(String gameName, boolean randTiles, boolean randNumbers, boolean randPorts) {
		return gameManager.createNewGame(gameName, randTiles, randNumbers, randPorts);
	}

	@Override
	public boolean saveGame() {
		return gameManager.saveGame();
	}
	
	public String[] getListAI(){
		return gameManager.listAIPlayers();
	}
	
	public boolean addAI(String botPlayer){
		return gameManager.addAIPlayer(botPlayer);
	}
	
	public void addAllPlayersObserver(Observer o){
		gameManager.getAllPlayers().addObserver(o);
	}
	
	public Player getLocalPlayer(){
		return gameManager.getLocalPlayer();
	}
	
	public GameInfo[] getGamesList(){
		return gameManager.populateGameList();
	}
	
	public Players getPlayers(){
		return gameManager.getAllPlayers();
	}
	
	public GameManager getManager(){
		return gameManager;
	}

}
