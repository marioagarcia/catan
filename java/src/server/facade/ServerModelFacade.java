package server.facade;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
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
		
		saveGame(0, "Initial game");
		
		currentGameId = loadSavedIDs();
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
	
	private boolean isSaved(int id){
		
		try {
			File id_file = new File(relative_file.getParentFile().getCanonicalPath() + File.separator + "data" + File.separator + "IDs.txt");
			
			if (!id_file.exists()){
				
				return false;
			}
			else{
				
				Charset charset = Charset.forName("US-ASCII");
				BufferedReader reader = Files.newBufferedReader(id_file.toPath(), charset);

				String line;
				
				while ((line = reader.readLine()) != null){
					
					int compare = Integer.parseInt(line);
					
					if (compare == id){
						return true;
					}
				}
				
				return false;
			}
		} 
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	private int loadSavedIDs(){
		
		try {
			File id_file = new File(relative_file.getParentFile().getCanonicalPath() + File.separator + "data" + File.separator + "IDs.txt");
			
			if (!id_file.exists()){
				
				return 0;
			}
			else{
				
				Charset charset = Charset.forName("US-ASCII");
				BufferedReader reader = Files.newBufferedReader(id_file.toPath(), charset);
				
				int starting_id = 0;
				String line;
				
				while ((line = reader.readLine()) != null){
					
					int compare = Integer.parseInt(line);
					
					if (compare >= starting_id){
						starting_id = compare + 1;
					}
				}
				
				return starting_id;
			}
		} 
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
		return -1;
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
	public boolean saveGame(int game_id, String file_name) {
		
		if (gamesList.containsKey(game_id)){
			String game_name = file_name;
			
			try {
				String folder = relative_file.getParentFile().getCanonicalPath() + File.separator + "data" + File.separator;
				File data_folder = new File(folder);
				
				if (!data_folder.exists()){
					data_folder.mkdir();
				}
				
				String path = folder + game_name;
				
				FileWriter writer = new FileWriter(new File(path));
				
				Gson serializer = new Gson();
				writer.write(serializer.toJson(gamesList.get(game_id)));
				writer.close();
				
				if (!isSaved(game_id)){
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(folder + "IDs.txt", true)));
					out.println(game_id);
					out.close();
				}
					
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}

	@Override
	public GameData loadGame(String game_name) {
		
		try {
			String folder = relative_file.getParentFile().getCanonicalPath() + File.separator + "data" + File.separator;
			File data_folder = new File(folder);
			
			if (!data_folder.exists()){
				return null;
			}
			
			String path = folder + game_name;
			File load_file = new File(path);
			
			if (!load_file.exists()){
				return null;
			}
			
			String content = new String(readAllBytes(get(path)));
			
			Gson serializer = new Gson();
			
			ServerGameManager game = serializer.fromJson(content, ServerGameManager.class);
			gamesList.put(game.getGameId(), game);

			return game.getGameData();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
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
