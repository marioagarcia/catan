package server.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import server.manager.ServerGameManager;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.GameInfo;
import shared.model.card.MaritimeTrade;
import shared.model.card.ResourceList;
import shared.model.card.TradeInterface;
import shared.model.manager.GameData;
import shared.model.manager.GameList;
import shared.model.player.Player;
import shared.model.player.PlayerInfo;
import shared.model.player.Players;

public class ServerModelFacade implements ServerModelFacadeInterface {

	private static ServerModelFacade facadeInstance = null;
	private Map<Integer, ServerGameManager> gamesList;
	private static int currentGameId = 1;
	private UserManager userList = null;
	
	protected ServerModelFacade()
	{
		gamesList = new HashMap<Integer, ServerGameManager>();
		userList = new UserManager();
	}
	
	public static ServerModelFacade getInstance(){
		if(facadeInstance == null){
				facadeInstance = new ServerModelFacade();
		}
		return facadeInstance;
	}
	
	public int getPlayerId(String name){
		return userList.getPlayerId(name);
	}

	@Override
	public boolean loginPlayer(String username, String password) {
		return userList.canLogin(username, password);
	}

	@Override
	public boolean registerPlayer(String username, String password) {
		return userList.register(username, password);
	}
	
	@Override
	public boolean verifyUser(String name, String password, int id) {
		return userList.verifyUser(name, password, id);
	}
	
	@Override
	public boolean verifyGame(int player_id, int game_id) {
		if (gamesList.containsKey(game_id)){
			return gamesList.get(game_id).containsPlayerId(player_id);
		}
		else{
			return false;
		}
	}


	@Override
	public boolean createNewGame(String gameName, boolean randTiles,
			boolean randNumbers, boolean randPorts) {
		
		boolean exists = false;
		
		for (Map.Entry<Integer, ServerGameManager> game : gamesList.entrySet()){
			
			if (game.getValue().getGameTitle().equals(gameName)){
				exists = true;
			}	
		}
		
		if (!exists){
			gamesList.put(currentGameId, new ServerGameManager(gameName, randTiles, randNumbers, randPorts));
			currentGameId++;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean canJoinGame(int game_id, int player_id, CatanColor color) {
		
		if (gamesList.containsKey(game_id)){
			
			if (gamesList.get(game_id).containsPlayerId(player_id)){
				
				return (gamesList.get(game_id).getPlayers().getPlayerByID(player_id).getColor() == color);
				
			}
			else{
				return (gamesList.get(game_id).getPlayers().size() < 4 && !gamesList.get(game_id).getPlayers().isColorUsed(color));
			}
			
		}
		else{
			
			return false;
		}
	}

	@Override
	public boolean joinGame(int game_id, int player_id, CatanColor color) {
		
		if (gamesList.containsKey(game_id)){
			
			if (gamesList.get(game_id).containsPlayerId(player_id)){
				return true;
			}
			else{
				
				Player p = new Player();
				p.setColor(color);
				
				gamesList.get(game_id).getPlayers().addPlayer(p);
				return true;
			}
		}
		else{
			return false;
		}
	}

	@Override
	public boolean saveGame(int game_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GameData loadGame(String game_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData getGameModel(int game_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean resetGame(int game_id) {
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
			ResourceList resources) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerTrade(int game_id, int player_index,
			ResourceList resources, int otherPlayerIndex) {
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
		
		GameList current_list = new GameList();
		ArrayList<GameInfo> new_list = new ArrayList<GameInfo>();
		
		for (Map.Entry<Integer, ServerGameManager> game : gamesList.entrySet()){
			
			GameInfo new_info = generateGameInfo(game.getValue());
			new_list.add(new_info);
		}
		
		current_list.setGameList(new_list);
		return current_list;
	}

	@Override
	public AIManager getAIList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private GameInfo generateGameInfo(ServerGameManager game){
		
		GameInfo new_info = new GameInfo();
		Players players = game.getPlayers();
		
		ArrayList<PlayerInfo> player_info = new ArrayList<PlayerInfo>();
		
		for (Player player : players.getPlayerList()){
			
			PlayerInfo new_player_info = new PlayerInfo();
			new_player_info.setPlayerInfo(player.getColor(), player.getName(), player.getId());
			
			player_info.add(new_player_info);
		}
		
		new_info.setGameInfo(game.getGameTitle(), game.getGameId(), player_info);
		
		return new_info;
		
	}

	@Override
	public GameInfo getGameInfo(String title) {
		
		for (Map.Entry<Integer, ServerGameManager> game : gamesList.entrySet()){
			if (game.getValue().getGameTitle().equals(title)){
				
				GameInfo new_info = generateGameInfo(game.getValue());
				return new_info;
			}
		}
		
		return null;	
	}

	@Override
	public boolean robPlayer(int player_index, int game_id, int victim_index,
			HexLocation location) {
		
		return true;
	}

	
	
}
