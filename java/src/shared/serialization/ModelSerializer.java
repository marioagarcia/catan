package shared.serialization;

import java.util.ArrayList;

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
		System.out.println(jsonString);
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
		System.out.println(data);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(data);
		 
		JsonObject object = element.getAsJsonObject();
		object = object.getAsJsonObject("deck");
		System.out.println(object);
		object = element.getAsJsonObject();
		object = object.getAsJsonObject("map");
		System.out.println(object);
		
		return null;
	}
	
	public Object deserialize(String JSONString, ObjectType objectType) {
		
		return null;
	}

	public static void main(String[] args){
		ModelSerializer ms = new ModelSerializer();
		
		//String str = ""brick":1,"ore":2,"sheep":3,"wheat":4,"wood":5},"chat":{"lines":[{"message":"Thanks for nothing","source":"Casey"},{"message":"Thanks for everything","source":]},"log":{"lines":[{; 
		//System.out.println(str);
		//String str = "[ { \"title\": \"Default Game\", \"id\": 0, \"players\": [ { \"color\": \"orange\", \"name\": \"Sam\", \"id\": 0 }, { \"color\": \"blue\", \"name\": \"Brooke\", \"id\": 1 }, { \"color\": \"red\", \"name\": \"Pete\", \"id\": 10 }, { \"color\": \"green\", \"name\": \"Mark\", \"id\": 11 } ] }, { \"title\": \"AI Game\", \"id\": 1, \"players\": [ { \"color\": \"orange\", \"name\": \"Pete\", \"id\": 10 }, { \"color\": \"puce\", \"name\": \"Scott\", \"id\": -2 }, { \"color\": \"purple\", \"name\": \"Steve\", \"id\": -2 }, { \"color\": \"green\", \"name\": \"Hannah\", \"id\": -2 } ] }, { \"title\": \"Empty Game\", \"id\": 2, \"players\": [ { \"color\": \"orange\", \"name\": \"Sam\", \"id\": 0 }, { \"color\": \"blue\", \"name\": \"Brooke\", \"id\": 1 }, { \"color\": \"red\", \"name\": \"Pete\", \"id\": 10 }, { \"color\": \"green\", \"name\": \"Mark\", \"id\": 11 } ] } ]";
		
		//Gson gson = new Gson();
		//gson.toJson(str);
		//ms.deserializeGameModel(str);
		ArrayList<MasterParameterInterface> list = new ArrayList<MasterParameterInterface>();
		SendChatParameters scp = new SendChatParameters(8, "Does this work?");
		OfferTradeParameters otp = new OfferTradeParameters(96, new ResourceList(5,1,4,2,3), 69);
		list.add(scp);
		list.add(otp);
		
		DiscardCardsParameters params = new DiscardCardsParameters(7, new ResourceList(5, 4, 3, 2, 1));
		ms.serializePostGameCommands(list);
		
	}
	
}
