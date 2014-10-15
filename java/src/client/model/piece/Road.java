package client.model.piece;

import shared.locations.EdgeLocation;
import shared.serialization.interfaces.SerializerRoadInterface;

public class Road implements RoadInterface, SerializerRoadInterface {

	int playerIndex;
	EdgeLocation location;
	GamePieceType type;
	
	public Road(int id, EdgeLocation loc){
		playerIndex = id;
		location = loc;
		this.type = GamePieceType.ROAD;
	}
	
	@Override
	public void setPlayerIndex(int index) {
		this.playerIndex = index;
	}

	@Override
	public int getPlayerIndex() {
		return playerIndex;
	}

	@Override
	public void setLocation(EdgeLocation location) {
		this.location = location;
	}

	@Override
	public EdgeLocation getLocation() {
		return location;
	}

	@Override
	public GamePieceType getType() {
		return this.type;
	}
	
	@Override
	public String toString(){
		String returnString = "";
		returnString += (playerIndex + " -- ");
		returnString += location.toString() + "\n";
		
		return returnString;
	}

	@Override
	public void setRoad(int playerIndex, EdgeLocation edgeLocation) {
		this.playerIndex = playerIndex;
		this.location = edgeLocation;
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
		Road other = (Road) obj;
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
