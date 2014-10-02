package client.manager;

import java.util.ArrayList;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.serialization.ModelSerializer;
import client.communication.server.ServerProxy;
import client.model.GameInfo;
import client.model.card.MaritimeTrade;
import client.model.card.TradeInterface;
import client.model.map.HexInterface;
import client.model.player.PlayerInfo;
import client.model.player.PlayerInterface;

public class GameManager implements GameManagerInterface {
	
	ServerProxy serverProxy;
	//ModelSerializer modelSerializer;
	//ServerPoller serverPoller;
	ArrayList<GameInfo> gameList;
	ModelSerializer modelSerializer;
	boolean isUserLoggedIn;
	PlayerInfo localPlayer;
	public GameManager() {
		/*
		serverProxy = serverProxy.getInstance();
		modelSerializer = modelSerializer.getInstance();
		//serverPoller = serverPoller.getInstance();
		*/
		modelSerializer = new ModelSerializer();
		isUserLoggedIn = false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void populateGameList() {
		String gameListStr = serverProxy.listGames();
		gameList = (ArrayList<GameInfo>)modelSerializer.deserialize(gameListStr, ModelSerializer.ObjectType.GAME_LIST);
	}
	
	@Override
	public PlayerInterface loginPlayer(String username, String password) {
		
		isUserLoggedIn = true;
		return null;
	}

	@Override
	public boolean canJoinGame(PlayerInfo player, GameInfo game) {
		if(isUserLoggedIn) {
			//TODO check for valid catan color type. maybe move it down to catan.color class
			if(game.playerCanJoin(player))
				return true;
		}		
		return false;
	}

	@Override
	public boolean getGameModel() {
		//TODO make data holding class and merge method
		return false;
	}

	@Override
	public boolean resetGame() { //we don't need parameters for this
		// before hand, take a snap shot of the game right after the players joined
		// load that snap shot here
		return false;
	}

	@Override
	public void joinGame(int gameId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGameStatus() {
		// TODO Auto-generated method stub
		
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
	public boolean canAcceptTrade(TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptTrade(TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canDiscardCards() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean discardCards() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRoll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean roll() {
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
	public boolean offerTrade(TradeInterface trade, int otherPlayerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMaritimeTrade(HexInterface location, MaritimeTrade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean maritimeTrade(HexInterface location, MaritimeTrade trade) {
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
	public boolean canPlayDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playYearOfPlenty() {
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
	public boolean canPlaySoldier(HexInterface oldLocation,
			HexInterface newLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playSoldier(HexInterface oldLocation,
			HexInterface newLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonopoly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playMonopoly() {
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

	@Override
	public PlayerInterface registerPlayer(PlayerInfo playerInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logoutPlayer(int playerId) {
		// TODO Auto-generated method stub
		
	}

}
