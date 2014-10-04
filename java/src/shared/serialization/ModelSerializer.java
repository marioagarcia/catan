package shared.serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shared.definitions.CatanColor;
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
import shared.serialization.parameters.EdgeLocationParameters;
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
import shared.serialization.parameters.VertexLocationParameters;
import shared.serialization.parameters.YearOfPlentyParameters;

import com.google.gson.*;

import client.manager.GameData;
import client.model.GameInfo;
import client.model.card.ResourceList;
import client.model.map.Hex;
import client.model.piece.RoadInterface;
import client.model.player.PlayerInfo;
import client.model.player.PlayerInterface;

public class ModelSerializer implements ModelSerializerInterface {

	public enum ObjectType {
		GAME_LIST
	}
	
	@Override
	public String serializeCredentials(CredentialsParameters credentials){
		Gson gson = new Gson();
		String jsonString = gson.toJson(credentials);
		
		return jsonString;
	}
	
	@Override
	public ArrayList<GameInfo> getGamesList(String jsonString){
		
		ArrayList<GameInfo> gamesList = new ArrayList<GameInfo>();
		
		JsonParser parser = new JsonParser();
		
		JsonElement element = parser.parse(jsonString);
		
		JsonArray gameArray = element.getAsJsonArray();
		for(int i = 0; i < gameArray.size(); i++){
			GameInfo gameInfo = new GameInfo();
			
			JsonObject gameObject = (JsonObject)gameArray.get(i);
			gameInfo.setTitle(gameObject.get("title").getAsString());
			gameInfo.setId(gameObject.get("id").getAsInt());
			
			JsonArray playerArray = (JsonArray)gameObject.get("players");
			for(int j = 0; j < playerArray.size(); j++){
				PlayerInfo playerInfo = new PlayerInfo();
				
				JsonObject playerObject = (JsonObject)playerArray.get(j);
				
				playerInfo.setId(playerObject.get("id").getAsInt());
				playerInfo.setName(playerObject.get("name").getAsString());
				
				String color = playerObject.get("color").getAsString();
				
				switch (color){
					case "red":
						playerInfo.setColor(CatanColor.RED);
						break;
					case "orange":
						playerInfo.setColor(CatanColor.ORANGE);
						break;
					case "yellow":
						playerInfo.setColor(CatanColor.YELLOW);
						break;
					case "blue":
						playerInfo.setColor(CatanColor.BLUE);
						break;
					case "green":
						playerInfo.setColor(CatanColor.GREEN);
						break;
					case "purple":
						playerInfo.setColor(CatanColor.PURPLE);
						break;
					case "puce":
						playerInfo.setColor(CatanColor.PUCE);
						break;
					case "white":
						playerInfo.setColor(CatanColor.WHITE);
						break;
					case "brown":
						playerInfo.setColor(CatanColor.BROWN);
						break;
				}
				gameInfo.addPlayer(playerInfo);
			}
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
	public String serializeCards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoadInterface deserializeRoad(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerInterface deserializePlayer(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData deserializeGameModel(String data) {
		GameData gameData = new GameData();
		Gson gson = new Gson();
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(data);
		 
		JsonObject mainObject = element.getAsJsonObject();
		JsonObject subObject = mainObject.getAsJsonObject("deck");
		int yop = subObject.getAsJsonPrimitive("yearOfPlenty").getAsInt();
		int mply = subObject.getAsJsonPrimitive("monopoly").getAsInt();
		int sold = subObject.getAsJsonPrimitive("soldier").getAsInt();
		int rdblg = subObject.getAsJsonPrimitive("roadBuilding").getAsInt();
		int mnmt = subObject.getAsJsonPrimitive("monument").getAsInt();
		
		//Parse the map into an arraylist of hexes
		ArrayList<Hex> hexes = new ArrayList<Hex>();
		subObject = mainObject.getAsJsonObject("map");
		JsonArray array = subObject.getAsJsonArray("hexes");
		for(int i = 0; i < array.size(); i++){
			subObject = (JsonObject)array.get(i);
			
			String resource;
			if(subObject.getAsJsonPrimitive("resource") == null){
				resource = "desert";
			}else{
				resource = subObject.getAsJsonPrimitive("resource").getAsString();
			}
			
			//subObject = (JsonObject)subObject.get("location");
			int x = ((JsonObject)subObject.get("location")).getAsJsonPrimitive("x").getAsInt();
			int y = ((JsonObject)subObject.get("location")).getAsJsonPrimitive("y").getAsInt();
			int number;
			if(resource.equals("desert")){
				number = Integer.MAX_VALUE;
			}else{
				number = subObject.get("number").getAsInt();
			}
			
			//hexes.add(new Hex(new HexLocation(x, y), resource, number));
		}
		System.out.println(hexes);
		return gameData;
	}
	
	public Object deserialize(String JSONString, ObjectType objectType) {
		
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
		
		ms.deserializeGameModel(content);
		
		
	}
	
}
