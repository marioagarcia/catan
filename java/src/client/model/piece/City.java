package client.model.piece;

import shared.locations.VertexLocation;
import shared.serialization.interfaces.SerializerCityInterface;

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

	public void setType(GamePieceType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + playerIndex;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (playerIndex != other.playerIndex)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
}
