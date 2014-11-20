package shared.model.piece;

import java.io.Serializable;

import shared.definitions.PieceType;
import shared.locations.VertexLocation;
import shared.serialization.interfaces.SerializerSettlementInterface;


@SuppressWarnings("serial")
public class Settlement implements SettlementInterface, SerializerSettlementInterface, Serializable {
	
	PieceType type;
	int playerIndex;
	VertexLocation location;
	
	public Settlement(){
		this.type = PieceType.SETTLEMENT;
		//TODO this should start out in the desert
	}
	
	public Settlement(int playerIndex, VertexLocation location){
		this.playerIndex = playerIndex;
		this.location = location;
	}

	@Override
	public PieceType getType() {
		return this.type;
	}

	@Override
	public void setPlayerIndex(int index) {
		this.playerIndex = index;
	}

	@Override
	public int getPlayerIndex() {
		return this.playerIndex;
	}

	@Override
	public void setLocation(VertexLocation corner) {
		this.location = corner;
	}

	@Override
	public VertexLocation getLocation() {
		return this.location;
	}

	@Override
	public void setCity(int playerIndex, VertexLocation vertexLocation) {
		this.playerIndex = playerIndex;
		this.location = vertexLocation;
	}

	@Override
	public String toString(){
		String returnString = "";
		
		returnString += playerIndex + " -- " + location.toString() + "\n";
		
		return returnString;
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
		Settlement other = (Settlement) obj;
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
