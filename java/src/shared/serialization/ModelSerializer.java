package shared.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.serialization.interfaces.CityInterface;
import shared.serialization.interfaces.DevCardListInterface;
import shared.serialization.interfaces.GameCommandInterface;
import shared.serialization.interfaces.GameInfoInterface;
import shared.serialization.interfaces.HexInterface;
import shared.serialization.interfaces.MessageInterface;
import shared.serialization.interfaces.PlayerInterface;
import shared.serialization.interfaces.RoadInterface;
import shared.serialization.interfaces.SettlementInterface;
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

import client.manager.GameData;

public class ModelSerializer implements ModelSerializerInterface {
	
	@Override
	public String serializeCredentials(CredentialsParameters credentials){
		Gson gson = new Gson();
		String jsonString = gson.toJson(credentials);
		
		return jsonString;
	}
	
	@Override
	public ArrayList<GameInfoInterface> deserializeGamesList(String jsonString){
		
		//@TODO All commented lines in this method must be implemented with new classes
		
		ArrayList<GameInfoInterface> gamesList = new ArrayList();
		
		JsonParser parser = new JsonParser();
		
		JsonElement element = parser.parse(jsonString);
		
		JsonArray gameArray = element.getAsJsonArray();
		for(int i = 0; i < gameArray.size(); i++){
			//GameInfo gameInfo = new GameInfo();
			
			JsonObject gameObject = (JsonObject)gameArray.get(i);
			//gameInfo.setTitle(gameObject.get("title").getAsString());
			//gameInfo.setId(gameObject.get("id").getAsInt());
			
			JsonArray playerArray = (JsonArray)gameObject.get("players");
			for(int j = 0; j < playerArray.size(); j++){
				//PlayerInfo playerInfo = new PlayerInfo();
				
				JsonObject playerObject = (JsonObject)playerArray.get(j);
				
				//playerInfo.setId(playerObject.get("id").getAsInt());
				//playerInfo.setName(playerObject.get("name").getAsString());
				
				String color = playerObject.get("color").getAsString();
				
				CatanColor playerColor = getPlayerColor(color);
				
				//gameInfo.addPlayer(playerInfo);
			}
			//gamesList.add(gameInfo);
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
	public ArrayList<GameCommandInterface> deserializeGetGameCommands(String jsonString){
		ArrayList<GameCommandInterface> gameCommandsList = new ArrayList();
		
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(jsonString).getAsJsonArray();
		
		for(int i = 0; i < array.size(); i++){
			String content = array.get(i).getAsJsonObject().get("content").getAsString();
			String type = array.get(i).getAsJsonObject().get("type").getAsString();
			int playerIndex = array.get(i).getAsJsonObject().get("playerIndex").getAsInt();
			
			System.out.println("Game Command " + i);
			System.out.println("\tContent: " + content);
			System.out.println("\tType: " + type);
			System.out.println("\tPlayer Index: " + playerIndex + "\n");
		}
		
		return gameCommandsList;
	}
	
	@Override
	public String serializeAIRequest(AIRequestParameters params){
		Gson gson = new Gson();
		String jsonString = gson.toJson(params);
		
		return jsonString;
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
	
	/////////////////


	@Override
	public GameData deserializeGameModel(String data) {
		GameData gameData = new GameData();
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(data);
		
		//Parse deck and build DevCardBank
		JsonObject mainObject = element.getAsJsonObject();
		JsonObject subObject = mainObject.getAsJsonObject("deck");
		
		//@TODO Use getDevCardList()
		int yearOfPlenty = subObject.getAsJsonPrimitive("yearOfPlenty").getAsInt();
		int monopoly = subObject.getAsJsonPrimitive("monopoly").getAsInt();
		int soldier = subObject.getAsJsonPrimitive("soldier").getAsInt();
		int roadBuild = subObject.getAsJsonPrimitive("roadBuilding").getAsInt();
		int monument = subObject.getAsJsonPrimitive("monument").getAsInt();
		
		//@TODO Set the deck in GameData
		//Done building DevCardBank
	
	//Parse Map
		
		//Parse hexes and build list of hexes
		ArrayList<HexInterface> hexList = new ArrayList();
		
		subObject = mainObject.getAsJsonObject("map");
		JsonArray array = subObject.getAsJsonArray("hexes");
		
		for(int i = 0; i < array.size(); i++){
			subObject = (JsonObject)array.get(i);
			
			ResourceType resource = null;
			if(subObject.getAsJsonPrimitive("resource") == null){
				resource = null;
			}else{
				resource = getResource((JsonObject)subObject);
			}
			
			HexLocation hexLocation = getHexLocation((JsonObject)subObject.get("location"));
			//TODO Set hex location in GameData
			
			int number;
			if(resource == null){
				number = -1;
			}else{
				number = subObject.get("number").getAsInt();
			}
			
			//@TODO Add the HexInterface object to the hex list
			//hexList.add(new Hex(hexLocation, resource, number));
		}
		
		//@TODO Set hexes in GameData
		//Done building the list of hexes
		
		//Parse roads and build a list of roads
		ArrayList<RoadInterface> roadList = new ArrayList();
		
		subObject = mainObject.getAsJsonObject("map");
		array = subObject.getAsJsonArray("roads");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			
			int playerIndex = subObject.get("owner").getAsInt();
			subObject = (JsonObject)subObject.get("location");

			HexLocation hexLocation = getHexLocation(subObject);
			
			EdgeDirection edgeDirection = getEdgeDirection(subObject);
			
			EdgeLocation edgeLocation = new EdgeLocation(hexLocation, edgeDirection);
			
			//@TODO create road with player index and edgeLocation
			//@TODO add road to roadList
		}
		
		//@TODO Set road list in gameData
		//gameData.setRoadList(roadList);
		//Done building the list of roads
		
		//Parse cities and build list of cities
		ArrayList<CityInterface> cityList = new ArrayList();
		
		subObject = mainObject.getAsJsonObject("map");
		array = subObject.getAsJsonArray("cities");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			
			int playerIndex = subObject.get("owner").getAsInt();
			subObject = (JsonObject)subObject.get("location");
			
			VertexDirection vertexDirection = getVertexDirection((JsonObject)subObject);
			HexLocation hexLocation = getHexLocation(subObject);
			
			//@TODO Set vertexLocation with vertexDirection & hexLocation
			//@TODO Set city with playerIndex and vertexLocation
		}
		
		//@TODO Set city list in GameData
		//Done building list of cities
		
		//Parse settlements and build list of settlements
		ArrayList<SettlementInterface> settlementList = new ArrayList();
		
		subObject = mainObject.getAsJsonObject("map");
		array = subObject.getAsJsonArray("settlements");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			
			int playerIndex = subObject.get("owner").getAsInt();
			subObject = (JsonObject)subObject.get("location");
			
			VertexDirection vertexDirection = getVertexDirection((JsonObject)subObject);
			HexLocation hexLocation = getHexLocation(subObject);
			
			//@TODO Set vertexLocation with vertexDirection & hexLocation
			//@TODO Set settlement with playerIndex and vertexLocation
		}
		
		//@TODO Set settlement list in GameData
		//@TODO create function to handle cities and settlements; they are identical
		//Done building list of settlements
		
		//Parse radius
		subObject = mainObject.getAsJsonObject("map");
		int radius = subObject.get("radius").getAsInt();
		//Done getting radius
		
		//Parse ports and build list of ports
		array = subObject.getAsJsonArray("ports");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			
			int ratio = subObject.get("ratio").getAsInt();
			
			ResourceType resource;
			if(subObject.getAsJsonPrimitive("resource") == null){
				resource = null;
			}else{
				resource = getResource(subObject);
			}
			
			String direction = subObject.get("direction").getAsString();
			//@TODO Get the direction as a type; figure out what that means
			
			subObject = (JsonObject)subObject.get("location");
			HexLocation hexLocation = getHexLocation(subObject);
			
			//@TODO Create port with ratio, resource (optional), direction, hexLocation
			//@TODO Add port to port list
		}
		//@TODO Set the port list in GameData
		//Done building port list
		
		//Parse robber
		subObject = mainObject.getAsJsonObject("map");
		subObject = (JsonObject)subObject.get("robber");
		
		HexLocation robberLocation = getHexLocation(subObject);
		//Done parsing robber
		
	//Done parsing map
		
		//Parse players and build player list
		ArrayList<PlayerInterface> playerList = new ArrayList();
		
		array = mainObject.getAsJsonArray("players");
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			
			JsonObject playerObject = subObject.getAsJsonObject("resources");
			int brick = playerObject.get("brick").getAsInt();
			int ore = playerObject.get("ore").getAsInt();
			int sheep = playerObject.get("sheep").getAsInt();
			int wheat = playerObject.get("wheat").getAsInt();
			int wood = playerObject.get("wood").getAsInt();
			
			//@TODO Fill this in
			//ResourceList resourceList = new ResourceList(brick, ore, sheep, wheat, wood);
			
			playerObject = subObject.getAsJsonObject("oldDevCards");
			//@TODO Fill this in, don't forget to return a value from getDevCardList()!!!!!!!
			DevCardListInterface oldDevCardList = getDevCardList(playerObject);
			
			playerObject = subObject.getAsJsonObject("newDevCards");
			//@TODO Fill this in, don't forget to return a value from getDevCardList()!!!!!!!
			DevCardListInterface newDevCardList = getDevCardList(playerObject);
			
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
			String color = subObject.get("color").getAsString();
			CatanColor playerColor = getPlayerColor(color);
			
			//@TODO Build a player with all of this crap
			//@TODO Add the player to the player list
		}
		//@TODO Set the player list in gameData
		//Done parsing players
		
		//Parse Log
		ArrayList<MessageInterface> messageList = new ArrayList();
		subObject = mainObject.getAsJsonObject("log");
		array = subObject.getAsJsonArray("lines");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			String source = subObject.get("source").getAsString();
			String message = subObject.get("message").getAsString();
			
			//@TODO Create message out of source and message
			//@TODO Add message to messageList
		}
		//@TODO Set logMessageList in GameData; consider creating a getMessage()
		//Done parsing Log
		
		//Parse Chat
		messageList.clear();
		subObject = mainObject.getAsJsonObject("chat");
		array = subObject.getAsJsonArray("lines");
		
		for(int i = 0; i < array.size(); i++){
			subObject = array.get(i).getAsJsonObject();
			String source = subObject.get("source").getAsString();
			String message = subObject.get("message").getAsString();
			
			//@TODO Create message out of source and message
			//@TODO Add message to messageList
		}
		//@TODO Set chatMessageList in GameData; consider creating a getMessage()
		//Done parsing Chat
		
		//Parse Bank
		subObject = mainObject.getAsJsonObject("bank");
		
		int brick = subObject.get("brick").getAsInt();
		int wood = subObject.get("wood").getAsInt();
		int sheep = subObject.get("sheep").getAsInt();
		int wheat = subObject.get("wheat").getAsInt();
		int ore = subObject.get("ore").getAsInt();
		
		//@TODO Create a bank with the resources; create getResource()
		//@TODO Set bank in GameData
		//Done parsing bank
		
		//Parse Turn Tracker
		subObject = mainObject.getAsJsonObject("turnTracker");
		
		String status = subObject.get("status").getAsString();
		int currentTurn = subObject.get("currentTurn").getAsInt();
		int longestRoad = subObject.get("longestRoad").getAsInt();
		int largestArmy = subObject.get("largestArmy").getAsInt();
		
		//@TODO Create a turn tracker with status, currentTurn, longestRoad, largestArmy
		//@TODO Set turn tracker in GameData
		//Done parsing turn tracker
		
		int winner = mainObject.get("winner").getAsInt();
		int version = mainObject.get("version").getAsInt();
		
		
		System.out.println("Turn Tracker");
		System.out.println("\tStatus: " + status);
		System.out.println("\tCurrent Turn: " + currentTurn);
		System.out.println("\tLongest Road: " + longestRoad);
		System.out.println("\tLargest Army: " + largestArmy);

		System.out.println("Winner: " + winner);
		System.out.println("Version: " + version);
		
		
///////////////////////////////////////////////////////////////////////////
		return gameData;
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
	
	public ResourceType getResource(JsonObject object){
		
		ResourceType resource = null;
		
		switch(object.getAsJsonPrimitive("resource").getAsString()){
		case "brick":
			resource = ResourceType.BRICK;
			break;
		case "wood":
			resource = ResourceType.WOOD;
			break;
		case "ore":
			resource = ResourceType.ORE;
			break;
		case "sheep":
			resource = ResourceType.SHEEP;
			break;
		case "wheat":
			resource = ResourceType.WHEAT;
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
	
	public DevCardListInterface getDevCardList(JsonObject object){
		
		int yearOfPlenty = object.getAsJsonPrimitive("yearOfPlenty").getAsInt();
		int monopoly = object.getAsJsonPrimitive("monopoly").getAsInt();
		int soldier = object.getAsJsonPrimitive("soldier").getAsInt();
		int roadBuild = object.getAsJsonPrimitive("roadBuilding").getAsInt();
		int monument = object.getAsJsonPrimitive("monument").getAsInt();
		
		return null;
	}

	public static void main(String[] args){
		ModelSerializer ms = new ModelSerializer();
		
		File file = new File(args[0]);
		
		String content = "";
		try {
			content = new Scanner(file).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ms.deserializeGetGameCommands(content);
		
		
	}
	
}
