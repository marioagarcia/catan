package client.facade;

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
import shared.model.manager.interfaces.GMDomesticTradeInterface;
import shared.model.player.Player;
import shared.model.player.Players;
import shared.model.player.RobPlayerInfo;
import client.communication.server.ServerMoxy;
import client.communication.server.ServerProxy;
import client.communication.server.ServerProxyInterface;
import client.manager.ClientGameManager;

public class ClientModelFacade implements ClientModelFacadeInterface {
	
	private static ClientModelFacade facadeInstance = null;
	private ClientGameManager gameManager;
	
	protected ClientModelFacade(ServerProxyInterface proxy)
	{
		if (proxy != null)
		{
			this.gameManager = new ClientGameManager(proxy);
		}
		else
		{
			this.gameManager = new ClientGameManager(new ServerMoxy());
		}
	}
	
	public static ClientModelFacade getInstance(ServerProxyInterface proxy){
		if(facadeInstance == null){
			if(proxy == null){
				facadeInstance = new ClientModelFacade(new ServerProxy(null, null));
			}else
			{
				facadeInstance = new ClientModelFacade(proxy);
			}
		}
		return facadeInstance;
	}
	
	public void addObserver(Observer observer) {
		gameManager.addObserver(observer);
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
	public boolean roll(int number) {
		return this.gameManager.roll(number);
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
	public boolean canMaritimeTrade(EdgeLocation location, MaritimeTrade trade) {
		return this.gameManager.canMaritimeTrade(location, trade);
	}

	@Override
	public boolean maritimeTrade(EdgeLocation location, MaritimeTrade trade) {
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
		return this.gameManager.playMonopoly(resourceType);
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
	public boolean saveGame(String filename) {
		return gameManager.saveGame(filename);
	}
	
	public String[] getListAI(){
		return gameManager.listAIPlayers();
	}
	
	public boolean addAI(String botPlayer){
		return gameManager.addAIPlayer(botPlayer);
	}
	
	public void addGameListObserver(Observer o){
		gameManager.getGameList().addObserver(o);
	}
	
	public Player getLocalPlayer(){
		return gameManager.getLocalPlayer();
	}
	
	public Players getAllPlayers() {
		return gameManager.getAllPlayers();
	}
	
	public GameInfo[] getGamesList(){
		return gameManager.populateGameList();
	}
	
	public ClientGameManager getManager(){
		return gameManager;
	}
	
	public void startListPoller(){
		gameManager.getServerPoller().startListPoller(2000);
	}
	
	public void stopListPoller(){
		gameManager.getServerPoller().stopListPoller();
	}
	
	public RobPlayerInfo[] getRobbablePlayers(HexLocation location){
		return gameManager.getRobbablePlayers(location);
	}
	
	public GameInfo getCurrentGame(){
		return gameManager.getCurrentGame();
	}
}
