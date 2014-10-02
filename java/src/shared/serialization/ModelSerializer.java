package shared.serialization;

import client.manager.GameManagerInterface;
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
	public GameManagerInterface deserializeGameModel(String data) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object deserialize(String JSONString, ObjectType objectType) {
		
		return null;
	}

}
