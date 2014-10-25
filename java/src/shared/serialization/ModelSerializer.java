package shared.serialization;

import java.util.ArrayList;

import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.serialization.parameters.AcceptTradeParameters;
import shared.serialization.parameters.BuildCityParameters;
import shared.serialization.parameters.BuildRoadParameters;
import shared.serialization.parameters.BuildSettlementParameters;
import shared.serialization.parameters.BuyDevCardParameters;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.CredentialsParameters;
import shared.serialization.parameters.DiscardCardsParameters;
import shared.serialization.parameters.FinishTurnParameters;
import shared.serialization.parameters.JoinGameRequestParameters;
import shared.serialization.parameters.LoadGameRequestParameters;
import shared.serialization.parameters.AIRequestParameters;
import shared.serialization.parameters.MaritimeTradeParameters;
import shared.serialization.parameters.MasterParameterInterface;
import shared.serialization.parameters.MonopolyParameters;
import shared.serialization.parameters.MonumentParameters;
import shared.serialization.parameters.OfferTradeParameters;
import shared.serialization.parameters.RoadBuildingParameters;
import shared.serialization.parameters.RobPlayerParameters;
import shared.serialization.parameters.RollNumberParameters;
import shared.serialization.parameters.SaveGameRequestParameters;
import shared.serialization.parameters.SendChatParameters;
import shared.serialization.parameters.SoldierParameters;
import shared.serialization.parameters.YearOfPlentyParameters;

import com.google.gson.*;

import client.manager.GameCommand;
import client.manager.GameCommands;
import client.manager.GameData;
import client.model.GameInfo;
import client.model.card.DevCardBank;
import client.model.card.DevCardList;
import client.model.card.DomesticTrade;
import client.model.card.ResourceCardBank;
import client.model.card.ResourceList;
import client.model.logging.GameLog;
import client.model.logging.chat.GameChat;
import client.model.logging.chat.Message;
import client.model.logging.history.HistoryLog;
import client.model.logging.history.LogLine;
import client.model.map.BoardMap;
import client.model.map.Hex;
import client.model.map.Port;
import client.model.piece.City;
import client.model.piece.Road;
import client.model.piece.Settlement;
import client.model.player.Player;
import client.model.player.PlayerInfo;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;

public class ModelSerializer implements ModelSerializerInterface {
	
	@Override
	public String serializeCredentials(CredentialsParameters credentials){
		Gson gson = new Gson();
		String jsonString = gson.toJson(credentials);
		
		return jsonString;
	}
	
	@Override
	public ArrayList<GameInfo> deserializeGamesList(String jsonString){
		
		ArrayList<GameInfo> gamesList = new ArrayList<GameInfo>();
		
		JsonParser parser = new JsonParser();
		
		JsonElement element = parser.parse(jsonString);
		
		JsonArray gameArray = element.getAsJsonArray();
		for(int i = 0; i < gameArray.size(); i++){
			GameInfo gameInfo = new GameInfo();
			
			JsonObject gameObject = (JsonObject)gameArray.get(i);
			String gameTitle = gameObject.get("title").getAsString();
			int gameID = gameObject.get("id").getAsInt();
			
			JsonArray playerArray = (JsonArray)gameObject.get("players");
			ArrayList <PlayerInfo> playerList = new ArrayList<PlayerInfo>();
			
			for(int j = 0; j < playerArray.size(); j++){
				PlayerInfo playerInfo = new PlayerInfo();
				
				JsonObject playerObject = (JsonObject)playerArray.get(j);
				
				if(playerObject.get("id") != null){
					int playerID = playerObject.get("id").getAsInt();
					String playerName = playerObject.get("name").getAsString();
					CatanColor playerColor = getPlayerColor(playerObject.get("color").getAsString());
				
					playerInfo.setPlayerInfo(playerColor, playerName, playerID);
					playerList.add(playerInfo);
				}
				
			}
			
			gameInfo.setGameInfo(gameTitle, gameID, playerList);
			gamesList.add(gameInfo);
		}
		
		return gamesList;
	}
	
	@Override
	public String serializeCreateGameRequest(CreateGameRequestParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public GameInfo deserializeGameInfo(String jsonString){
		
		GameInfo gameInfo = new GameInfo();
		
		JsonParser parser = new JsonParser();		
		JsonObject gameObject = parser.parse(jsonString).getAsJsonObject();
		
		String gameTitle = gameObject.get("title").getAsString();
		int gameID = gameObject.get("id").getAsInt();
		
		JsonArray playerArray = (JsonArray)gameObject.get("players");
		ArrayList <PlayerInfo> playerList = new ArrayList<PlayerInfo>();
		
		for(int j = 0; j < playerArray.size(); j++){
			PlayerInfo playerInfo = new PlayerInfo();
			
			JsonObject playerObject = (JsonObject)playerArray.get(j);
			if(playerObject.get("id") != null){
				int playerID = playerObject.get("id").getAsInt();
				String playerName = playerObject.get("name").getAsString();
				CatanColor playerColor = getPlayerColor(playerObject.get("color").getAsString());
			
				playerInfo.setPlayerInfo(playerColor, playerName, playerID);
				playerList.add(playerInfo);
			}
		}
		
		gameInfo.setGameInfo(gameTitle, gameID, playerList);
		return gameInfo;
	}
	
	@Override
	public String serializeJoinGameRequest(JoinGameRequestParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeSaveGameRequest(SaveGameRequestParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}

	@Override
	public String serializeLoadGameRequest(LoadGameRequestParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}

	@Override
	public String serializePostGameCommands(ArrayList<MasterParameterInterface> params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public GameCommands deserializeGetGameCommands(String jsonString){
		GameCommands gameCommands = new GameCommands();
		
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(jsonString).getAsJsonArray();
		
		for(int i = 0; i < array.size(); i++){
			String content = array.get(i).getAsJsonObject().get("content").getAsString();
			String type = array.get(i).getAsJsonObject().get("type").getAsString();
			int playerIndex = array.get(i).getAsJsonObject().get("playerIndex").getAsInt();
			
			GameCommand gameCommand = new GameCommand(content, type, playerIndex);
			gameCommands.addCommand(gameCommand);
		}
		
		return gameCommands;
	}
	
	@Override
	public String serializeAIRequest(AIRequestParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	public String[] deserializeGetListAI(String jsonString){
		
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(jsonString).getAsJsonArray();
		
		String[] listAI = new String[array.size()];
		
		for(int i = 0; i < array.size(); i++){
			listAI[i] = array.get(i).getAsString();
		}
		
		return listAI;
	}

	@Override
	public String serializeSendChat(SendChatParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeRollNumber(RollNumberParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeRobPlayer(RobPlayerParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeFinishTurn(FinishTurnParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeBuyDevCard(BuyDevCardParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeYearOfPlenty(YearOfPlentyParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeRoadBuilding(RoadBuildingParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeSoldier(SoldierParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeMonopoly(MonopolyParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeMonument(MonumentParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeBuildRoad(BuildRoadParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeBuildSettlement(BuildSettlementParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeBuildCity(BuildCityParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeOfferTrade(OfferTradeParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeAcceptTrade(AcceptTradeParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeMaritimeTrade(MaritimeTradeParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}
	
	@Override
	public String serializeDiscardCards(DiscardCardsParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
	}

	@Override
	public GameData deserializeGameModel(String data) {
		GameData gameData = new GameData();
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(data);
		JsonObject mainObject = element.getAsJsonObject();
		
		//Parse deck and build DevCardBank
		JsonObject subObject = mainObject.getAsJsonObject("deck");
		
		DevCardBank devCardBank = getDevCardBank(subObject);
		gameData.setDevCardBank(devCardBank);
		//Done building DevCardBank
	
	//Parse Map
		//Parse hexes and build list of hexes
		ArrayList<Hex> hexList = new ArrayList<Hex>();
		
		subObject = mainObject.getAsJsonObject("map");
		JsonArray array = subObject.getAsJsonArray("hexes");
		
		for(int i = 0; i < array.size(); i++){
			subObject = (JsonObject)array.get(i);
			
			HexType resource = null;
			if(subObject.getAsJsonPrimitive("resource") == null){
				resource = HexType.DESERT;
			}else{
				resource = getHexType((JsonObject)subObject);
			}
			
			HexLocation hexLocation = getHexLocation((JsonObject)subObject.get("location"));
			
			int number;
			if(resource == HexType.DESERT){
				number = -1;
			}else{
				number = subObject.get("number").getAsInt();
			}
			
			//Hex hex = new Hex(hexLocation, resource, number);
			hexList.add(new Hex(hexLocation, resource, number));
		}
		//Done building the list of hexes
		
		//Parse roads and build a list of roads
		ArrayList<Road> roadList = new ArrayList<Road>();
		
		subObject = mainObject.getAsJsonObject("map");
		array = subObject.getAsJsonArray("roads");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			
			int playerIndex = subObject.get("owner").getAsInt();
			subObject = (JsonObject)subObject.get("location");

			HexLocation hexLocation = getHexLocation(subObject);
			EdgeDirection edgeDirection = getEdgeDirection(subObject);
			
			EdgeLocation edgeLocation = new EdgeLocation(hexLocation, edgeDirection);
			
			Road road = new Road(playerIndex, edgeLocation);
			roadList.add(road);
		}
		//Done building the list of roads	
		//Parse cities and build list of cities
		ArrayList<City> cityList = new ArrayList<City>();
		
		subObject = mainObject.getAsJsonObject("map");
		array = subObject.getAsJsonArray("cities");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			
			int playerIndex = subObject.get("owner").getAsInt();
			subObject = (JsonObject)subObject.get("location");
			
			VertexDirection vertexDirection = getVertexDirection((JsonObject)subObject);
			HexLocation hexLocation = getHexLocation(subObject);
			
			VertexLocation vertexLocation = new VertexLocation(hexLocation, vertexDirection);
			
			City city = new City();
			city.setCity(playerIndex, vertexLocation);
			
			cityList.add(city);
		}
		//Done building list of cities
		
		//Parse settlements and build list of settlements
		ArrayList<Settlement> settlementList = new ArrayList<Settlement>();
		
		subObject = mainObject.getAsJsonObject("map");
		array = subObject.getAsJsonArray("settlements");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			
			int playerIndex = subObject.get("owner").getAsInt();
			subObject = (JsonObject)subObject.get("location");
			
			VertexDirection vertexDirection = getVertexDirection((JsonObject)subObject);
			HexLocation hexLocation = getHexLocation(subObject);
			
			VertexLocation vertexLocation = new VertexLocation(hexLocation, vertexDirection);
			Settlement settlement = new Settlement(playerIndex, vertexLocation);
			
			settlementList.add(settlement);
		}
		//Done building list of settlements	
		//Parse radius
		subObject = mainObject.getAsJsonObject("map");
		int radius = subObject.get("radius").getAsInt();
		//Done getting radius
		
		//Parse ports and build list of ports
		array = subObject.getAsJsonArray("ports");
		ArrayList<Port> portList = new ArrayList<Port>();
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			
			int ratio = subObject.get("ratio").getAsInt();
			
			PortType resource;
			resource = getPortType(subObject);

			EdgeDirection edgeDirection = getEdgeDirection(subObject);
			HexLocation hexLocation = getHexLocation((JsonObject)subObject.get("location"));
			
			EdgeLocation edgeLocation = new EdgeLocation(hexLocation, edgeDirection);
			
			Port port = new Port(resource, edgeLocation, ratio);
			portList.add(port);
		}
		//Done building port list	
		//Parse robber
		subObject = mainObject.getAsJsonObject("map");
		subObject = (JsonObject)subObject.get("robber");
		
		HexLocation robberLocation = getHexLocation(subObject);
		//Done parsing robber
		
		BoardMap gameMap = new BoardMap();
		gameMap.setMap(hexList, roadList, cityList, settlementList,
					   radius, portList, robberLocation);
		
		gameData.setBoardMap(gameMap);
	//Done parsing map
		
		//Parse players and build player list
		ArrayList<Player> playerList = getPlayers(mainObject);
		gameData.setPlayerList(playerList);
		//Done parsing players

	//Parse GameLog
		GameLog gameLog = new GameLog();
		
		//Parse Log
		HistoryLog historyLog = new HistoryLog();
		
		subObject = mainObject.getAsJsonObject("log");
		array = subObject.getAsJsonArray("lines");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			String source = subObject.get("source").getAsString();
			String content = subObject.get("message").getAsString();
			
			LogLine logLine = new LogLine(source, content);
			historyLog.addLogLine(logLine);
		}
		gameLog.setGameHistoryLog(historyLog);
		//Done parsing Log
		
		//Parse Chat
		GameChat gameChat = new GameChat();
		
		subObject = mainObject.getAsJsonObject("chat");
		array = subObject.getAsJsonArray("lines");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			String source = subObject.get("source").getAsString();
			String content = subObject.get("message").getAsString();
			
			Message message = new Message(content, source);
			gameChat.addMessage(message);
		}
		//Done parsing Chat
		gameLog.setGameChat(gameChat);
		
		gameData.setGameLog(gameLog);
	//Done parsing GameLog
		
		//Parse Bank
		subObject = mainObject.getAsJsonObject("bank");
		
		ResourceCardBank resourceCardBank = getResourceCardBank(subObject);
		gameData.setResourceCardBank(resourceCardBank);
		//Done parsing bank
		
		//Parse Turn Tracker
		subObject = mainObject.getAsJsonObject("turnTracker");
		
		TurnTracker turnTracker = getTurnTracker(subObject);
		gameData.setTurnTracker(turnTracker);
		//Done parsing turn tracker

		//Parse Trade Offer
		gameData.setDomesticTrade(getTradeOffer(mainObject));
		
		int winner = mainObject.get("winner").getAsInt();
		int version = mainObject.get("version").getAsInt();
		
		gameData.setWinner(winner);
		gameData.setVersion(version);
		
		return gameData;
	}
	
	public DevCardBank getDevCardBank(JsonObject object){
		
		DevCardBank devCardBank = new DevCardBank();
		
		int yearOfPlenty = object.getAsJsonPrimitive("yearOfPlenty").getAsInt();
		int monopoly = object.getAsJsonPrimitive("monopoly").getAsInt();
		int soldier = object.getAsJsonPrimitive("soldier").getAsInt();
		int roadBuild = object.getAsJsonPrimitive("roadBuilding").getAsInt();
		int monument = object.getAsJsonPrimitive("monument").getAsInt();
		
		devCardBank.setDeck(yearOfPlenty, monopoly, soldier, roadBuild, monument);
		
		return devCardBank;
	}
	
	public ResourceList getResourceList(JsonObject object){
		
		int brick = object.get("brick").getAsInt();
		int ore = object.get("ore").getAsInt();
		int sheep = object.get("sheep").getAsInt();
		int wheat = object.get("wheat").getAsInt();
		int wood = object.get("wood").getAsInt();
		
		return new ResourceList(brick, ore, sheep, wheat, wood);
	}
	
	public ResourceCardBank getResourceCardBank(JsonObject object){
		
		ResourceCardBank bank = new ResourceCardBank();
		
		ResourceList resourceList = getResourceList(object);
		
		bank.setBank(resourceList.getBrick(), resourceList.getWood(), 
					 resourceList.getSheep(), resourceList.getWheat(),
					 resourceList.getOre());
		
		return bank;
	}
	
	public DevCardList getDevCardList(JsonObject object){
		DevCardList devCardList = new DevCardList();
		
		int yearOfPlenty = object.get("yearOfPlenty").getAsInt();
		int monopoly = object.get("monopoly").getAsInt();
		int soldier = object.get("soldier").getAsInt();
		int roadBuild = object.get("roadBuilding").getAsInt();
		int monument = object.get("monument").getAsInt();
		
		devCardList.setDevCardList(yearOfPlenty, monopoly, soldier, roadBuild, monument);
		return devCardList;
		
	}
	
	public CatanColor getPlayerColor(String color){
		
		CatanColor playerColor = null;
		
		switch (color){
			case "red":
				playerColor = CatanColor.RED;
				break;
			case "orange":
				playerColor = CatanColor.ORANGE;
				break;
			case "yellow":
				playerColor = CatanColor.YELLOW;
				break;
			case "blue":
				playerColor = CatanColor.BLUE;
				break;
			case "green":
				playerColor = CatanColor.GREEN;
				break;
			case "purple":
				playerColor = CatanColor.PURPLE;
				break;
			case "puce":
				playerColor = CatanColor.PUCE;
				break;
			case "white":
				playerColor = CatanColor.WHITE;
				break;
			case "brown":
				playerColor = CatanColor.BROWN;
				break;
		}
		
		return playerColor;
	}
	
	public PortType getPortType(JsonObject object){
		PortType resource = null;
		
		if(object.getAsJsonPrimitive("resource") == null){
			resource = PortType.THREE;
		}else{
			switch(object.getAsJsonPrimitive("resource").getAsString()){
			case "brick":
				resource = PortType.BRICK;
				break;
			case "wood":
				resource = PortType.WOOD;
				break;
			case "ore":
				resource = PortType.ORE;
				break;
			case "sheep":
				resource = PortType.SHEEP;
				break;
			case "wheat":
				resource = PortType.WHEAT;
				break;
			}
		}
		
		return resource;
	}
	
	public HexType getHexType(JsonObject object){
		
		HexType resource = null;
		
		switch(object.getAsJsonPrimitive("resource").getAsString()){
		case "brick":
			resource = HexType.BRICK;
			break;
		case "wood":
			resource = HexType.WOOD;
			break;
		case "ore":
			resource = HexType.ORE;
			break;
		case "sheep":
			resource = HexType.SHEEP;
			break;
		case "wheat":
			resource = HexType.WHEAT;
			break;
		}
		
		return resource;
	}
	
	public HexLocation getHexLocation(JsonObject object){
		
		int x = object.get("x").getAsInt();
		int y = object.get("y").getAsInt();
		
		return new HexLocation(x, y);
	}
	
	public EdgeDirection getEdgeDirection(JsonObject object){
		
		EdgeDirection edgeDirection = null;
		
		switch(object.get("direction").getAsString()){
			
			case "N":
				edgeDirection = EdgeDirection.North;
				break;
			case "NE":
				edgeDirection = EdgeDirection.NorthEast;
				break;
			case "NW":
				edgeDirection = EdgeDirection.NorthWest;
				break;
			case "S":
				edgeDirection = EdgeDirection.South;
				break;
			case "SE":
				edgeDirection = EdgeDirection.SouthEast;
				break;
			case "SW":
				edgeDirection = EdgeDirection.SouthWest;
				break;
		}
		return edgeDirection;
	}
	
	public VertexDirection getVertexDirection(JsonObject object){
		
		VertexDirection vertexDirection = null;
		
		switch(object.get("direction").getAsString()){
		
			case "NE":
				vertexDirection = VertexDirection.NorthEast;
				break;
			case "E":
				vertexDirection = VertexDirection.East;
				break;
			case "SE":
				vertexDirection = VertexDirection.SouthEast;
				break;
			case "SW":
				vertexDirection = VertexDirection.SouthWest;
				break;
			case "W":
				vertexDirection = VertexDirection.West;
				break;
			case "NW":
				vertexDirection = VertexDirection.NorthWest;
				break;
		}
		
		return vertexDirection;
	}
	
	public Status getCurrentStatus(String status){

		Status gameStatus = null;
		
		switch(status){
			case "FirstRound":
				gameStatus = Status.FIRST_ROUND;
				break;
			case "SecondRound":
				gameStatus = Status.SECOND_ROUND;
				break;
			case "Playing":
				gameStatus = Status.PLAYING;
				break;
			case "Robbing":
				gameStatus = Status.ROBBING;
				break;
			case "Discarding":
				gameStatus = Status.DISCARDING;
				break;
			case "Rolling":
				gameStatus = Status.ROLLING;
				break;
		}
		return gameStatus;
	}
	
	public ArrayList<Player> getPlayers(JsonObject object){
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		JsonArray array = object.getAsJsonArray("players");
		
		for(int i = 0; i < array.size(); i++){
			if(!array.get(i).isJsonNull()){
				JsonObject subObject = array.get(i).getAsJsonObject();
				
				JsonObject playerObject = subObject.getAsJsonObject("resources");
				ResourceList resourceList = getResourceList(playerObject);
				
				playerObject = subObject.getAsJsonObject("oldDevCards");
				DevCardList oldDevCards = getDevCardList(playerObject);
				
				playerObject = subObject.getAsJsonObject("newDevCards");
				DevCardList newDevCards = getDevCardList(playerObject);
				
				int roads = subObject.get("roads").getAsInt();
				int cities = subObject.get("cities").getAsInt();
				int settlements = subObject.get("settlements").getAsInt();
				int soldiers = subObject.get("soldiers").getAsInt();
				int victoryPoints = subObject.get("victoryPoints").getAsInt();
				int monuments = subObject.get("monuments").getAsInt();
				boolean playedDevCard = subObject.get("playedDevCard").getAsBoolean();
				boolean discarded = subObject.get("discarded").getAsBoolean();
				int playerID = subObject.get("playerID").getAsInt();
				int playerIndex = subObject.get("playerIndex").getAsInt();
				String name = subObject.get("name").getAsString();
				CatanColor playerColor = getPlayerColor(subObject.get("color").getAsString());
				
				Player player = new Player();
				player.setPlayer(resourceList, oldDevCards, newDevCards, roads, 
								 cities, settlements, soldiers, victoryPoints, 
								 monuments, playedDevCard, discarded, playerID, 
								 playerIndex, name, playerColor);
				playerList.add(player);
			}
		}
		
		return playerList;
	}
	
	public TurnTracker getTurnTracker(JsonObject object){	
		
		Status status = getCurrentStatus(object.get("status").getAsString());
		int currentTurn = object.get("currentTurn").getAsInt();
		int longestRoad = object.get("longestRoad").getAsInt();
		int largestArmy = object.get("largestArmy").getAsInt();
		
		return new TurnTracker(status, currentTurn, longestRoad, largestArmy);
	}
	
	public DomesticTrade getTradeOffer(JsonObject object){
		DomesticTrade tradeOffer = null;
		
		object = object.getAsJsonObject("tradeOffer");
		
		if(object != null){
			int sender = object.get("sender").getAsInt();
			int receiver = object.get("receiver").getAsInt();
			
			object = object.getAsJsonObject("offer");
			
			int brick = object.get("brick").getAsInt();
			int ore = object.get("ore").getAsInt();
			int sheep = object.get("sheep").getAsInt();
			int wheat = object.get("wheat").getAsInt();
			int wood = object.get("wood").getAsInt();
			
			ResourceList resourceList = new ResourceList(brick, ore, sheep, wheat, wood);
			
			tradeOffer = new DomesticTrade(sender, receiver, resourceList);
		}
		
		return tradeOffer;
	}

/*	public static void main(String[] args){
		ModelSerializer ms = new ModelSerializer();
		
		File file = new File(args[0]);
		
		String content = "";
		try {
			content = new Scanner(file).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ms.deserializeGameModel(content);
		
	}*/
	
}
