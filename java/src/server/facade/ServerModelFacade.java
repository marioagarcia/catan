package server.facade;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import server.manager.ServerGameManager;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.GameInfo;
import shared.model.card.ResourceList;
import shared.model.manager.GameData;
import shared.model.manager.GameList;
import shared.model.player.Player;
import shared.model.player.PlayerInfo;
import shared.model.player.Players;

public class ServerModelFacade implements ServerModelFacadeInterface {

	private static ServerModelFacade facadeInstance = null;
	private Map<Integer, ServerGameManager> gamesList;
	private static int currentGameId = 0;
	private UserManager userList = null;
	private File relative_file = new File(ServerModelFacade.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	
	protected ServerModelFacade()
	{
		gamesList = new HashMap<Integer, ServerGameManager>();
		userList = new UserManager();
		
		this.createNewGame("Default", true, true, true);
		
		registerPlayer("Sam", "sam");
		registerPlayer("Brooke", "brooke");
		registerPlayer("Bob", "bob");
		registerPlayer("Joe", "joe");
		
		joinGame(0, 0, CatanColor.ORANGE);
		joinGame(0, 1, CatanColor.BLUE);
		joinGame(0, 2, CatanColor.RED);
		joinGame(0, 3, CatanColor.GREEN);
		
		
		loadGames();
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

		return (gamesList.containsKey(game_id) && gamesList.get(game_id).containsPlayerId(player_id));
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
			gamesList.put(currentGameId, new ServerGameManager(gameName, currentGameId, randTiles, randNumbers, randPorts));
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
				p.setName(userList.getPlayerName(player_id));
				p.setPlayerId(player_id);
				
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
		
		if (gamesList.containsKey(game_id)){
			
			long original_time = gamesList.get(game_id).getTimeStamp();
			gamesList.get(game_id).setTimeStamp(0);
			
			String original_game_name = gamesList.get(game_id).getGameTitle();
			
			Date now = new Date();
			
			String game_file = original_game_name;
			game_file = game_file.replaceAll(" ", "_");
			
			try {
				String folder = relative_file.getParentFile().getCanonicalPath() + File.separator + "data" + File.separator;
				File data_folder = new File(folder);
				
				if (!data_folder.exists()){
					data_folder.mkdir();
				}
				
				String short_time = now.toString().split("MST")[0];
				gamesList.get(game_id).setGameTitle(gamesList.get(game_id).getGameTitle() + "_" + short_time);
				
				String path = folder + game_file;
				
				FileOutputStream fileOut = new FileOutputStream(path);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(gamesList.get(game_id));
				out.close();
				fileOut.close();	
				
				gamesList.get(game_id).setTimeStamp(original_time); //Resets original game back to no time stamp
				gamesList.get(game_id).setGameTitle(original_game_name);
				
				addLoadGame(path);
					
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}
	
	private void addLoadGame(String path)
	{
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
	        ServerGameManager new_game;
	        
	        new_game = (ServerGameManager) in.readObject();
	        
	        
	        boolean exists = false;
	        
	        for (Map.Entry<Integer, ServerGameManager> game : gamesList.entrySet()){
				
				if (game.getValue().getGameTitle().equals(new_game.getGameTitle())){
					exists = true;
					break;
				}	
			}
	                
	        if (!exists){
				new_game.setGameId(currentGameId++);
				gamesList.put(new_game.getGameId(), new_game);
	        }
			
			in.close();
	        fileIn.close();
		} 
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			System.out.println("Could not deserialize");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void loadGames() {
		
		try {
			String folder = relative_file.getParentFile().getCanonicalPath() + File.separator + "data" + File.separator;
			File data_folder = new File(folder);
			
			if (!data_folder.exists()){
				return;
			}
			
			for (File game : data_folder.listFiles()){		
				
				addLoadGame(game.getCanonicalPath());
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public GameData getGameModel(int game_id) {
		return gamesList.get(game_id).getGameData();
	}
	
	@Override
	public Boolean isCurrentVersion(int game_id, int version_number) {
		
		return gamesList.get(game_id).getVersion() == version_number;
	}

	@Override
	public boolean canSendChat(int game_id, int player_index) {
		
		return gamesList.get(game_id).canSendChat(player_index);
	}

	@Override
	public boolean sendChat(int game_id, int player_index, String chatMessage) {
		
		return gamesList.get(game_id).sendChat(player_index, chatMessage);
	}

	@Override
	public boolean canAcceptTrade(int game_id, int player_index) {
		
		return gamesList.get(game_id).canAcceptTrade(player_index);
	}

	@Override
	public boolean acceptTrade(int game_id, int player_index, boolean accept) {
		
		return gamesList.get(game_id).acceptTrade(player_index, accept);
	}

	@Override
	public boolean canDiscardCards(int game_id, int player_index,
			ResourceList list) {
		
		return gamesList.get(game_id).canDiscardCards(player_index, list);
	}

	@Override
	public boolean discardCards(int game_id, int player_index, ResourceList list) {

		return gamesList.get(game_id).discardCards(player_index, list);
	}

	@Override
	public boolean canRoll(int game_id, int player_index) {
		
		return gamesList.get(game_id).canRoll(player_index);
	}

	@Override
	public boolean roll(int game_id, int player_index, int number) {
		
		return gamesList.get(game_id).roll(player_index, number);
	}

	@Override
	public boolean canBuildRoad(int game_id, int player_index,
			EdgeLocation location) {

		return gamesList.get(game_id).canBuildRoad(player_index, location);
	}

	@Override
	public boolean buildRoad(int game_id, int player_index,
			EdgeLocation location) {
		
		return gamesList.get(game_id).buildRoad(player_index, location);
	}

	@Override
	public boolean canBuildSettlement(int game_id, int player_index,
			VertexLocation location) {
		
		return gamesList.get(game_id).canBuildSettlement(player_index, location);
	}

	@Override
	public boolean buildSettlement(int game_id, int player_index,
			VertexLocation location) {
		
		return gamesList.get(game_id).buildSettlement(player_index, location);
	}

	@Override
	public boolean canBuildCity(int game_id, int player_index,
			VertexLocation location) {
		
		return gamesList.get(game_id).canBuildCity(player_index, location);
	}

	@Override
	public boolean buildCity(int game_id, int player_index,
			VertexLocation location) {
		
		return gamesList.get(game_id).buildCity(player_index, location);
	}

	@Override
	public boolean canOfferTrade(int game_id, int player_index,
			ResourceList resources) {
		
		return gamesList.get(game_id).canOfferTrade(player_index, resources);
	}

	@Override
	public boolean offerTrade(int game_id, int player_index,
			ResourceList resources, int otherPlayerIndex) {
		
		return gamesList.get(game_id).offerTrade(player_index, resources, otherPlayerIndex);
	}

	@Override
	public boolean canMaritimeTrade(int game_id, int player_index,
			int ratio, ResourceType in, ResourceType out) {
		
		return gamesList.get(game_id).canMaritimeTrade(player_index, ratio, in, out);
	}

	@Override
	public boolean maritimeTrade(int game_id, int player_index,
			int ratio, ResourceType in, ResourceType out) {
		
		return gamesList.get(game_id).maritimeTrade(player_index, ratio, in, out);
	}

	@Override
	public boolean canFinishTurn(int game_id, int player_index) {
		
		return gamesList.get(game_id).canFinishTurn(player_index);
	}

	@Override
	public boolean finishTurn(int game_id, int player_index) {
		
		return gamesList.get(game_id).finishTurn(player_index);
	}

	@Override
	public boolean canBuyDevCard(int game_id, int player_index) {
		
		return gamesList.get(game_id).canBuyDevCard(player_index);
	}

	@Override
	public boolean buyDevCard(int game_id, int player_index) {
		
		return gamesList.get(game_id).buyDevCard(player_index);
	}

	@Override
	public boolean canPlayYearOfPlenty(int game_id, int player_index,
			ResourceType type1, ResourceType type2) {
		
		return gamesList.get(game_id).canPlayYearOfPlenty(player_index, type1, type2);
	}

	@Override
	public boolean playYearOfPlenty(int game_id, int player_index,
			ResourceType type1, ResourceType type2) {
		
		return gamesList.get(game_id).playYearOfPlenty(player_index, type1, type2);
	}

	@Override
	public boolean canPlayRoadBuilding(int game_id, int player_index,
			EdgeLocation location1, EdgeLocation location2) {
		
		return gamesList.get(game_id).canPlayRoadBuilding(player_index, location1, location2);
	}

	@Override
	public boolean playRoadBuilding(int game_id, int player_index,
			EdgeLocation location1, EdgeLocation location2) {
		
		return gamesList.get(game_id).playRoadBuilding(player_index, location1, location2);
	}

	@Override
	public boolean canPlaySoldier(int game_id, int player_index, HexLocation newLocation, int victimIndex) {
		
		return gamesList.get(game_id).canPlaySoldier(player_index, newLocation, victimIndex);
	}

	@Override
	public boolean playSoldier(int game_id, int player_index,
			HexLocation newLocation, int victimIndex) {
		
		return gamesList.get(game_id).playSoldier(player_index, newLocation, victimIndex);
	}

	@Override
	public boolean canPlayMonopoly(int game_id, int player_index) {
		
		return gamesList.get(game_id).canPlayMonopoly(player_index);
	}

	@Override
	public boolean playMonopoly(int game_id, int player_index,
			ResourceType resourceType) {
		
		return gamesList.get(game_id).playMonopoly(player_index, resourceType);
	}

	@Override
	public boolean canPlayMonument(int game_id, int player_index) {
		
		return gamesList.get(game_id).canPlayMonument(player_index);
	}

	@Override
	public boolean playMonument(int game_id, int player_index) {
		
		return gamesList.get(game_id).playMonument(player_index);
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
		return new AIManager();
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
				
				return generateGameInfo(game.getValue());
			}
		}
		
		return null;	
	}

	@Override
	public boolean robPlayer(int player_index, int game_id, int victim_index,
			HexLocation location) {
		
		return gamesList.get(game_id).robPlayer(player_index, victim_index, location);
	}	
}
