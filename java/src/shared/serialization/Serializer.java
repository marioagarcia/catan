package shared.serialization;


import client.manager.GameData;
import client.model.card.CardInventoryInterface;
import client.model.piece.RoadInterface;
import client.model.player.PlayerInterface;

public class Serializer implements ModelSerializerInterface{

	@Override
	public String serializeCards(CardInventoryInterface inventory_instance,
			int playerId) {
		// TODO Does a player have a cardinventory?  Should we create a single object that stores
		// a cardInventory and a playerID?
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
		// TODO Auto-generated method stub
		return new GameData();
	}

}
