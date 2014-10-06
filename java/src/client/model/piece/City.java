package client.model.piece;

import shared.locations.VertexLocation;
import shared.serialization.interfaces.SerializerCityInterface;
import shared.serialization.interfaces.SerializerVertexLocationInterface;
import client.model.map.HexCornerInterface;

public class City implements CityInterface, SerializerCityInterface {
	
	private int playerIndex;
	private VertexLocation location;
	GamePieceType type;
	
	public City(){
		this.type = GamePieceType.CITY;
	}

	@Override
	public int getPlayerIndex() {
		return this.playerIndex;
	}

	@Override
	public void setLocation(VertexLocation location) {
		this.location = location;
	}

	@Override
	public VertexLocation getLocation() {
		return this.location;
	}

	@Override
	public GamePieceType getType() {
		return this.type;
	}

	@Override
	public void setCity(int playerIndex, VertexLocation vertexLocation) {
		this.location = vertexLocation;
		this.playerIndex = playerIndex;
	}

	@Override
	public void setPlayerIndex(int index) {
		this.playerIndex = index;
	}

}
