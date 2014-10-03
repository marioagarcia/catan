package shared.serialization;

import com.google.gson.*;
import client.manager.GameData;
import client.model.card.CardInventoryInterface;
import client.model.piece.RoadInterface;
import client.model.player.PlayerInterface;

public class ModelSerializer implements ModelSerializerInterface {

	public enum ObjectType {
		GAME_LIST
	}
	@Override
	public String serializeCards(CardInventoryInterface inventory_instance,
			int playerId) {
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
		
		String str = "{\"deck\":{\"yearOfPlenty\":2,\"monopoly\":2,\"soldier\":14,\"roadBuilding\":2,\"monument\":5},\"map\":{\"hexGrid\":{\"hexes\":[[{\"isLand\":false,\"location\":{\"x\":0,\"y\":-1},\"vertexes\":[{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":1,\"ownerID\":0}},{\"value\":{\"worth\":0,\"ownerID\":-1}}],\"edges\":[{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":0}},{\"value\":{\"ownerID\":-1}}]},{\"landtype\":\"Wood\",\"isLand\":true,\"location\":{\"x\":1,\"y\":-1},\"vertexes\":[{\"value\":{\"worth\":1,\"ownerID\":0}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}}],\"edges\":[{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}}]}],[{\"landtype\":\"Ore\",\"isLand\":true,\"location\":{\"x\":-1,\"y\":0},\"vertexes\":[{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":1,\"ownerID\":2}},{\"value\":{\"worth\":0,\"ownerID\":-1}}],\"edges\":[{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}}]},{\"isLand\":true,\"location\":{\"x\":0,\"y\":0},\"vertexes\":[{\"value\":{\"worth\":1,\"ownerID\":2}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":1,\"ownerID\":0}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":1,\"ownerID\":1}},{\"value\":{\"worth\":0,\"ownerID\":-1}}],\"edges\":[{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":0}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":2}}]},{\"landtype\":\"Brick\",\"isLand\":true,\"location\":{\"x\":1,\"y\":0},\"vertexes\":[{\"value\":{\"worth\":1,\"ownerID\":1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":2,\"ownerID\":3}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}}],\"edges\":[{\"value\":{\"ownerID\":1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":3}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}}]}],[{\"landtype\":\"Wheat\",\"isLand\":true,\"location\":{\"x\":-1,\"y\":1},\"vertexes\":[{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":1,\"ownerID\":2}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}}],\"edges\":[{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":2}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}}]},{\"landtype\":\"Sheep\",\"isLand\":true,\"location\":{\"x\":0,\"y\":1},\"vertexes\":[{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":1,\"ownerID\":1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}},{\"value\":{\"worth\":0,\"ownerID\":-1}}],\"edges\":[{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}},{\"value\":{\"ownerID\":-1}}]}]],\"offsets\":[1,0,0],\"radius\":2,\"x0\":1,\"y0\":1},\"radius\":2,\"numbers\":{\"2\":[{\"x\":1,\"y\":-1}],\"3\":[{\"x\":1,\"y\":0}],\"5\":[{\"x\":0,\"y\":1}],\"8\":[{\"x\":-1,\"y\":1}],\"12\":[{\"x\":-1,\"y\":0}]},\"ports\":[{\"ratio\":2,\"inputResource\":\"Ore\",\"validVertex1\":{\"direction\":\"SW\",\"x\":0,\"y\":-1},\"validVertex2\":{\"direction\":\"W\",\"x\":0,\"y\":-1},\"orientation\":\"SW\",\"location\":{\"x\":0,\"y\":-1}}]},\"players\":[{\"MAX_GAME_POINTS\":10,\"resources\":{\"brick\":0,\"wood\":0,\"sheep\":0,\"wheat\":0,\"ore\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":15,\"cities\":4,\"settlements\":5,\"soldiers\":0,\"victoryPoints\":0,\"monuments\":0,\"longestRoad\":false,\"largestArmy\":false,\"playedDevCard\":false,\"discarded\":false,\"playerID\":12,\"orderNumber\":0,\"name\":\"Pete\",\"color\":\"red\"},{\"MAX_GAME_POINTS\":10,\"resources\":{\"brick\":0,\"wood\":0,\"sheep\":0,\"wheat\":0,\"ore\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":15,\"cities\":4,\"settlements\":5,\"soldiers\":0,\"victoryPoints\":0,\"monuments\":0,\"longestRoad\":false,\"largestArmy\":false,\"playedDevCard\":false,\"discarded\":false,\"playerID\":12345,\"orderNumber\":1,\"name\":\"Brooke\",\"color\":\"blue\"},{\"MAX_GAME_POINTS\":10,\"resources\":{\"brick\":0,\"wood\":0,\"sheep\":0,\"wheat\":0,\"ore\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":15,\"cities\":4,\"settlements\":5,\"soldiers\":0,\"victoryPoints\":0,\"monuments\":0,\"longestRoad\":false,\"largestArmy\":false,\"playedDevCard\":false,\"discarded\":false,\"playerID\":3947,\"orderNumber\":2,\"name\":\"Mark\",\"color\":\"green\"},{\"MAX_GAME_POINTS\":10,\"resources\":{\"brick\":0,\"wood\":0,\"sheep\":0,\"wheat\":0,\"ore\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":15,\"cities\":4,\"settlements\":5,\"soldiers\":0,\"victoryPoints\":0,\"monuments\":0,\"longestRoad\":false,\"largestArmy\":false,\"playedDevCard\":false,\"discarded\":false,\"playerID\":8395739,\"orderNumber\":3,\"name\":\"Sam\",\"color\":\"orange\"}],\"log\":{\"lines\":[]},\"chat\":{\"lines\":[]},\"bank\":{\"brick\":24,\"wood\":24,\"sheep\":24,\"wheat\":24,\"ore\":24},\"turnTracker\":{\"status\":\"FirstRound\",\"currentTurn\":0},\"biggestArmy\":2,\"longestRoad\":-1,\"winner\":-1}";
		
		System.out.println(str);
		Gson gson = new Gson();
		//gson.toJson(str);
		ms.deserializeGameModel(str);
	}
	
}
