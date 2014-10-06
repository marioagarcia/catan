package client.model.piece;

import shared.serialization.interfaces.SerializerCityInterface;
import shared.serialization.interfaces.SerializerVertexLocationInterface;
import client.model.map.HexCornerInterface;

public class City implements CityInterface, SerializerCityInterface {
	
	private int playerId;
	private HexCornerInterface corner;
	GamePieceType type;
	
	public City(){
		this.type = GamePieceType.CITY;
	}

	@Override
	public void setPlayerId(int id) {
		this.playerId = id;
	}

	@Override
	public int getPlayerId() {
		return this.playerId;
	}

	@Override
	public void setLocation(HexCornerInterface corner) {
		this.corner = corner;
	}

	@Override
	public HexCornerInterface getLocation() {
		return this.corner;
	}

	@Override
	public GamePieceType getType() {
		return this.type;
	}

	@Override
	public void setCity(int playerIndex, SerializerVertexLocationInterface vertexLocation) {
		// TODO Auto-generated method stub
		
	}

}
