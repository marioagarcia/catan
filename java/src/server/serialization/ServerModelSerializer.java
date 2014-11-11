package server.serialization;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import shared.definitions.DevCardType;
import shared.definitions.HexType;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
import shared.model.GameInfo;
import shared.model.card.DevCardBank;
import shared.model.card.DevCardList;
import shared.model.card.DomesticTrade;
import shared.model.card.ResourceCardBank;
import shared.model.card.ResourceList;
import shared.model.logging.chat.GameChat;
import shared.model.logging.chat.Message;
import shared.model.logging.history.HistoryLog;
import shared.model.logging.history.LogLine;
import shared.model.manager.GameCommand;
import shared.model.manager.GameCommands;
import shared.model.manager.GameData;
import shared.model.map.BoardMap;
import shared.model.map.Hex;
import shared.model.map.Port;
import shared.model.piece.City;
import shared.model.piece.Road;
import shared.model.piece.Settlement;
import shared.model.player.Player;
import shared.model.player.PlayerInfo;
import shared.model.turntracker.TurnTracker;
import shared.serialization.parameters.AIRequestParameters;
import shared.serialization.parameters.AcceptTradeParameters;
import shared.serialization.parameters.BuildCityParameters;
import shared.serialization.parameters.BuildRoadParameters;
import shared.serialization.parameters.BuildSettlementParameters;
import shared.serialization.parameters.BuyDevCardParameters;
import shared.serialization.parameters.CityParameters;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.CredentialsParameters;
import shared.serialization.parameters.DevCardListParameters;
import shared.serialization.parameters.DiscardCardsParameters;
import shared.serialization.parameters.EdgeLocationParameters;
import shared.serialization.parameters.FinishTurnParameters;
import shared.serialization.parameters.GameInfoParameters;
import shared.serialization.parameters.GameModelParameters;
import shared.serialization.parameters.HexParameters;
import shared.serialization.parameters.LoadGameRequestParameters;
import shared.serialization.parameters.LogParameters;
import shared.serialization.parameters.MapParameters;
import shared.serialization.parameters.MaritimeTradeParameters;
import shared.serialization.parameters.MasterParameterInterface;
import shared.serialization.parameters.MessageParameters;
import shared.serialization.parameters.MonopolyParameters;
import shared.serialization.parameters.MonumentParameters;
import shared.serialization.parameters.OfferTradeParameters;
import shared.serialization.parameters.PlayerInfoParameters;
import shared.serialization.parameters.PlayerParameters;
import shared.serialization.parameters.PortParameters;
import shared.serialization.parameters.RoadBuildingParameters;
import shared.serialization.parameters.RoadParameters;
import shared.serialization.parameters.RobPlayerParameters;
import shared.serialization.parameters.RollNumberParameters;
import shared.serialization.parameters.SaveGameRequestParameters;
import shared.serialization.parameters.SendChatParameters;
import shared.serialization.parameters.SettlementParameters;
import shared.serialization.parameters.SoldierParameters;
import shared.serialization.parameters.TradeOfferParameters;
import shared.serialization.parameters.TurnTrackerParameters;
import shared.serialization.parameters.VertexLocationParameters;
import shared.serialization.parameters.YearOfPlentyParameters;

public class ServerModelSerializer implements ServerModelSerializerInterface{

	@Override
	public CredentialsParameters deserializeCredentials(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, CredentialsParameters.class);
	}

	@Override
	public String serializeGamesList(ArrayList<GameInfo> gamesList) {
		//This is the list of games that will be serialized
		GameInfoParameters[] serializableGameList = new GameInfoParameters[gamesList.size()];
		
		//Iterate through gamesList to populate serializableGameList
		for(int i = 0; i < gamesList.size(); i++){
			//Get each game info from the gamesList
			GameInfo gameInfo = gamesList.get(i);
			
			//Get the list of players from each game
			List<PlayerInfo> players = gamesList.get(i).getPlayers();
			
			//This is the list of players that will be serialized
			PlayerInfoParameters[] serializablePlayerList = new PlayerInfoParameters[4];
			
			//Initialize each PlayerInfoParameters in the serializable player list
			for(int j = 0; j < 4; j++){
				serializablePlayerList[j] = new PlayerInfoParameters();
			}
			
			//Iterate through players to populate serializablePlayerList
			for(int j = 0; j < players.size(); j++){
				//Get each playerInfo object from players
				PlayerInfo playerInfo = players.get(j);
				
				//Populate the serializable player info and add it to the serializable list of player infos
				serializablePlayerList[j] = new PlayerInfoParameters(playerInfo.getName(),
														 			 playerInfo.getColor().toString().toLowerCase(),
														 			 playerInfo.getId());
			}
			
			//Populate the serializable game info and add it to the serializable list of game infos
			serializableGameList[i] = new GameInfoParameters(gameInfo.getTitle(),
														  	 gameInfo.getId(),
														  	 serializablePlayerList);
		}
		//Serialize and return the serializable game list
		Gson gson = new Gson();
		return gson.toJson(serializableGameList);
	}

	@Override
	public CreateGameRequestParameters deserializeCreateGameRequest(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, CreateGameRequestParameters.class);
	}

	@Override
	public String serializeGameInfo(GameInfo gameInfo) {
		//Get the list of players from gameInfo
		List<PlayerInfo> players = gameInfo.getPlayers();
		
		//This will be the serializable list of players
		PlayerInfoParameters[] serializablePlayerList = new PlayerInfoParameters[4];
		
		//Initialize each PlayerInfoParameters in the serializable player list
		for(int i = 0; i < 4; i++){
			serializablePlayerList[i] = new PlayerInfoParameters();
		}
		
		//Iterate through each player and convert the playerInfo into a PlayerInfoParameters
		for(int i = 0; i < players.size(); i++){
			//Get each playerInfo from the list of players
			PlayerInfo player = players.get(i);
			
			//Convert the playerInfo into a PlayerInfoParameters (serializable player)
			PlayerInfoParameters serializablePlayer = new PlayerInfoParameters(player.getName(),
																			   player.getColor().toString(),
																			   player.getId());
			
			//Add the serializable player to the serializable list of players
			serializablePlayerList[i] = serializablePlayer;
		}
		
		//Create the GameInfoParameters (serializable game info)
		GameInfoParameters serializableGameInfo = new GameInfoParameters(gameInfo.getTitle(),
																		 gameInfo.getId(),
																		 serializablePlayerList);
		
		//Serialize and return the serializable game info
		Gson gson = new Gson();
		return gson.toJson(serializableGameInfo);
	}

	@Override
	public SaveGameRequestParameters deserializeSaveGameRequest(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, SaveGameRequestParameters.class);
	}

	@Override
	public LoadGameRequestParameters deserializeLoadGameRequest(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, LoadGameRequestParameters.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<MasterParameterInterface> deserializePostGameCommands(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, ArrayList.class);
	}

	@Override
	public String serializeGetGameCommands(GameCommands gameCommands) {
		//Get the list of game commands from gameCommands
		ArrayList<GameCommand> commandList = (ArrayList<GameCommand>)gameCommands.getAllCommands();
		
		Gson gson = new Gson();
		return gson.toJson(commandList.toArray());
	}

	@Override
	public AIRequestParameters deserializeAIRequest(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, AIRequestParameters.class);
	}

	@Override
	public String serializeGetListAI(String[] aiList) {

		Gson gson = new Gson();
		return gson.toJson(aiList);
	}

	@Override
	public SendChatParameters deserializeSendChat(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, SendChatParameters.class);
	}

	@Override
	public RollNumberParameters deserializeRollNumber(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, RollNumberParameters.class);
	}

	@Override
	public RobPlayerParameters deserializeRobPlayer(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, RobPlayerParameters.class);
	}

	@Override
	public FinishTurnParameters deserializeFinishTurn(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, FinishTurnParameters.class);
	}

	@Override
	public BuyDevCardParameters deserializeBuyDevCard(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, BuyDevCardParameters.class);
	}

	@Override
	public YearOfPlentyParameters deserializeYearOfPlenty(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, YearOfPlentyParameters.class);
	}

	@Override
	public RoadBuildingParameters deserializeRoadBuilding(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, RoadBuildingParameters.class);
	}

	@Override
	public SoldierParameters deserializeSoldier(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, SoldierParameters.class);
	}

	@Override
	public MonopolyParameters deserializeMonopoly(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, MonopolyParameters.class);
	}

	@Override
	public MonumentParameters deserializeMonument(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, MonumentParameters.class);
	}

	@Override
	public BuildRoadParameters deserializeBuildRoad(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, BuildRoadParameters.class);
	}

	@Override
	public BuildSettlementParameters deserializeSettlement(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, BuildSettlementParameters.class);
	}

	@Override
	public BuildCityParameters deserializeBuildCity(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, BuildCityParameters.class);
	}

	@Override
	public OfferTradeParameters deserializeOfferTrade(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, OfferTradeParameters.class);
	}

	@Override
	public AcceptTradeParameters deserializeAcceptTrade(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, AcceptTradeParameters.class);
	}

	@Override
	public MaritimeTradeParameters deserializeMaritimeTrade(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, MaritimeTradeParameters.class);
	}

	@Override
	public DiscardCardsParameters deserializeDiscardCards(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, DiscardCardsParameters.class);
	}

	@Override
	public String serializeGameModel(GameData gameData) {
		//Get the dev card bank from gameData
		DevCardBank devCardBank = gameData.getDevCardBank();
		
		//Convert the dev card bank into a deck parameters object
		DevCardListParameters deck = new DevCardListParameters(devCardBank.numberOfType(DevCardType.YEAR_OF_PLENTY),
												 devCardBank.numberOfType(DevCardType.MONOPOLY),
												 devCardBank.numberOfType(DevCardType.SOLDIER),
												 devCardBank.numberOfType(DevCardType.ROAD_BUILD),
												 devCardBank.numberOfType(DevCardType.MONUMENT));
		
	//Convert hexes, roads, cities, settlements, radius, ports, and robber into a MapParameters object
		//Get the board map from gameData
		BoardMap boardMap = gameData.getBoardMap();
		
		//Get the number of hexes in boardmap and create a serializable array of hexes based on that number
		int numHexes = boardMap.getHexes().size();
		HexParameters[] hexes = new HexParameters[numHexes];

		//Iterate through each hex in boardmap and put them in the serializable hex array
		int index = 0;
		for(Object hex : boardMap.getHexes().values()){
			Hex currHex = (Hex)hex;
			if(currHex.getType() == HexType.DESERT || currHex.getType() == HexType.WATER){
				hexes[index++] = new HexParameters(null, currHex.getLocation(), null);
			}else{
				hexes[index++] = new HexParameters(currHex.getType().toString().toLowerCase(),
												 currHex.getLocation(), currHex.getNumber());
			}
		}
		
		//Get the number of roads in boardmap and create a serializable array of roads based on that number
		int numRoads = boardMap.getRoads().size();
		RoadParameters[] roads = new RoadParameters[numRoads];
		
		//Iterate through each road in boardmap and put them in the serializable road array
		index = 0;
		for(Object road : boardMap.getRoads().values()){
			Road currRoad = (Road)road;
			EdgeLocationParameters edgeLocationParameters = new EdgeLocationParameters(currRoad.getLocation());
			roads[index++] = new RoadParameters(currRoad.getPlayerIndex(), edgeLocationParameters);
		}
		
		//Get the number of cities in boardmap and create a serializable array of cities based on that number
		int numCities = boardMap.getCities().size();
		CityParameters[] cities = new CityParameters[numCities];
		
		//Iterate through each city in boardmap and put them in the serializable city array
		index = 0;
		for(Object city : boardMap.getCities().values()){
			City currCity = (City)city;
			VertexLocationParameters location = new VertexLocationParameters(currCity.getLocation());
			cities[index++] = new CityParameters(currCity.getPlayerIndex(), location);
		}
		
		//Get the number of settlements in boardmap and create a serializable array of settlements based on that number
		int numSettlements = boardMap.getSettlements().size();
		SettlementParameters[] settlements = new SettlementParameters[numSettlements];
		
		//Iterate through each settlement in boardmap and put them in the serializable settlement array
		index = 0;
		for(Object settlement : boardMap.getSettlements().values()){
			Settlement currSettlement = (Settlement)settlement;
			VertexLocationParameters location = new VertexLocationParameters(currSettlement.getLocation());
			settlements[index++] = new SettlementParameters(currSettlement.getPlayerIndex(), location);
		}
		
		//Get the radius from boardMap
		int radius = boardMap.getRadius();
		
		//Get the number of ports in boardmap and create a serializable array of ports based on that number
		int numPorts = boardMap.getPorts().size();
		PortParameters[] ports = new PortParameters[numPorts];
		
		//Iterate through each port in boardmap and put them in the serializable port array
		index = 0;
		for(Object port : boardMap.getPorts().values()){
			Port currPort = (Port)port;
			HexLocation location = currPort.getLocation().getHexLoc();
			String resource = currPort.getResource().toString().toLowerCase();
			String direction = currPort.getLocation().getDir().toString();
			if(resource.equals("three")){
				ports[index++] = new PortParameters(currPort.getRatio(), null, direction, location);
			}else{
				ports[index++] = new PortParameters(currPort.getRatio(), resource, direction, location);
			}
		}
		
		//Get the robber from boardMap
		HexLocation robberLocation = boardMap.getRobberLocation();
		
		MapParameters map = new MapParameters(hexes, roads, cities, settlements, radius, ports, robberLocation);
	//Done converting boardMap into a MapParameters (map: hexes, roads, cities, settlements, radius, ports, robber)
		
		//Get the number of players in gameData and create a serializable array of players based on that number
		int numPlayers = gameData.getPlayers().getPlayerList().size();
		PlayerParameters[] players = new PlayerParameters[numPlayers];
		
		//Get the list of players from gameData
		ArrayList<Player> playerList = (ArrayList<Player>)gameData.getPlayers().getPlayerList();
		
		//Iterate through each player in playerList and put them in the serializable player array
		index = 0;
		for(Player player : playerList){
			players[index++] = convertPlayerToPlayerParameters(player);
		}
		
		//Get the number of log lines and create a serializable array of log lines based on that number
		int numLogLines = gameData.getGameLog().getGameHistoryLog().getSize();
		MessageParameters[] logLines = new MessageParameters[numLogLines];
		
		//Get the history log from gameData
		HistoryLog historyLog = (HistoryLog)gameData.getGameLog().getGameHistoryLog();
		
		//Iterate through each log line in history log and put them in the serializable log line array
		for(int i = 0; i < historyLog.getSize(); i++){
			LogLine logLine = (LogLine)historyLog.getLogLine(i);
			logLines[i] = new MessageParameters(logLine.getPlayerName(), logLine.getMove());
		}
		
		//Create a serializable LogParameters object with the serializable log line array
		LogParameters log = new LogParameters(logLines);
		
		//Get the number of chat lines and create a serializable array of chat lines based on that number
		int numChatLines = gameData.getGameLog().getGameChat().getSize();
		MessageParameters[] chatLines = new MessageParameters[numChatLines];
		
		//Get the chat log from gameData
		GameChat gameChat = (GameChat)gameData.getGameLog().getGameChat();
		
		//Iterate through each chat line in game chat and put them in the serializable chat line array
		for(int i = 0; i < gameChat.getSize(); i++){
			Message chatLine = (Message)gameChat.getMessage(i);
			chatLines[i] = new MessageParameters(chatLine.getPlayerName(), chatLine.getMessageContent());
		}
		
		//Create a serializable LogParameters object with the serializable chat line array
		LogParameters chat = new LogParameters(chatLines);
		
		//Get the resource list from gameData (already serializable, for once in my life!!!)
		ResourceList bank = convertCardBankToResourceList(gameData.getResourceCardBank());
		
		//Get the turn tracker from game data
		TurnTracker tt = gameData.getTurnTracker();
		
		//Convert the turn tracker from game data to a serializable TurnTrackerParameters object
		TurnTrackerParameters turnTracker = new TurnTrackerParameters(tt.statusToString(tt.getStatus()),
																	  tt.getCurrentTurn(),
																	  tt.getLongestRoadStatus(),
																	  tt.getLargestArmy());
		
		//This is the serializable trade object (TradeOfferParameters)
		TradeOfferParameters tradeOffer = null;
		
		//Get the trade offer from gameData (if there is one)
		if(gameData.getDomesticTrade() != null){
			DomesticTrade domesticTrade = gameData.getDomesticTrade();
			
			//Convert the trade offer to the serializable trade offer
			tradeOffer = new TradeOfferParameters(domesticTrade.getSender(), 
												  domesticTrade.getReceiver(),
												  domesticTrade.getResourceList());
		}
		
		//Get the winner's id from gameData (will be -1 if there is not a winner yet)
		int winner = gameData.getWinnerPlayerIndex();
		
		//Get the game's version number from gameData
		int version = gameData.getVersion();
		
		//Create the serializable game model object: deck, map, players, log, chat, bank, turnTracker, tradeOffer, winner, version
		GameModelParameters gameModel = new GameModelParameters(deck, map, players, log, chat, bank, turnTracker, tradeOffer, winner, version);
		
		Gson gson = new Gson();
		return gson.toJson(gameModel);
	}
	
	public ResourceList convertCardBankToResourceList(ResourceCardBank cardBank){
		
		int brick = cardBank.getNumberOfResourcesByType(ResourceType.BRICK);
		int ore = cardBank.getNumberOfResourcesByType(ResourceType.ORE);
		int sheep = cardBank.getNumberOfResourcesByType(ResourceType.SHEEP);
		int wheat = cardBank.getNumberOfResourcesByType(ResourceType.WHEAT);
		int wood = cardBank.getNumberOfResourcesByType(ResourceType.WOOD);
		
		return new ResourceList(brick, ore, sheep, wheat, wood);
	}
	
	public PlayerParameters convertPlayerToPlayerParameters(Player player){
		//Get resource list from player
		ResourceList resources = player.getResourceList();
		//Get old dev cards from player
		DevCardList oldDevCards = player.getOldDevCards();
		//Get new dev cards from player
		DevCardList newDevCards = player.getNewDevCards();
		
		return new PlayerParameters(resources, oldDevCards, newDevCards, player.getRoads(), player.getCities(),
									player.getSettlements(), player.getSoldiers(), player.getVictoryPoints(),
									player.getMonuments(), player.getPlayedDevCard(), player.isDiscarded(),
									player.getPlayerId(), player.getPlayerIndex(), player.getName(), 
									player.getColor().toString().toLowerCase()); 
	}
	
//	public static void main(String[] args){
/*		ArrayList<GameInfo> games = new ArrayList<GameInfo>();
		
		PlayerInfo p1 = new PlayerInfo();
		p1.setPlayerInfo(CatanColor.ORANGE, "Sam", 0);
		PlayerInfo p2 = new PlayerInfo();
		p2.setPlayerInfo(CatanColor.BLUE, "Brooke", 1);
		PlayerInfo p3 = new PlayerInfo();
		p3.setPlayerInfo(CatanColor.RED, "Pete", 10);
		PlayerInfo p4 = new PlayerInfo();
		p4.setPlayerInfo(CatanColor.GREEN, "Mark", 11);
		
		ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
		players.add(p1); players.add(p2); players.add(p3); players.add(p4);
		
		GameInfo g1 = new GameInfo();
		g1.setGameInfo("Default Game", 0, players);
		
		PlayerInfo p5 = new PlayerInfo();
		p5.setPlayerInfo(CatanColor.ORANGE, "Pete", 10);
		PlayerInfo p6 = new PlayerInfo();
		p6.setPlayerInfo(CatanColor.PUCE, "Steve", -2);
		PlayerInfo p7 = new PlayerInfo();
		p7.setPlayerInfo(CatanColor.BLUE, "Ken", -2);
		PlayerInfo p8 = new PlayerInfo();
		p8.setPlayerInfo(CatanColor.GREEN, "Scott", -2);
		
		players = new ArrayList<PlayerInfo>();
		players.add(p5); players.add(p6); players.add(p7); players.add(p8);
		
		GameInfo g2 = new GameInfo();
		g2.setGameInfo("AI Game", 1, players);
		
		PlayerInfo p9 = new PlayerInfo();
		p9.setPlayerInfo(CatanColor.ORANGE, "Sam", 0);
		PlayerInfo p10 = new PlayerInfo();
		p10.setPlayerInfo(CatanColor.BLUE, "Brooke", 1);
		PlayerInfo p11 = new PlayerInfo();
		p11.setPlayerInfo(CatanColor.RED, "Pete", 10);
		PlayerInfo p12 = new PlayerInfo();
		p12.setPlayerInfo(CatanColor.GREEN, "Mark", 11);
		
		players = new ArrayList<PlayerInfo>();
		players.add(p9); players.add(p10); players.add(p11); players.add(p12);
		
		GameInfo g3 = new GameInfo();
		g3.setGameInfo("Empty Game", 2, players);
		
		GameInfo g4 = new GameInfo();
		g4.setGameInfo("string", 3, new ArrayList<PlayerInfo>(4));
		
		games.add(g1); games.add(g2); games.add(g3);
		System.out.println(new ServerModelSerializer().serializeGamesList(games));*/
		
//	}
}



