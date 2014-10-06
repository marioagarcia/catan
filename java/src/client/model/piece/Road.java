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
	public void setPlayerId(int id) {
		this.playerIndex = id;
	}

	@Override
	public int getPlayerId() {
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
		returnString += (playerIndex + "\n");
		returnString += location.toString();
		
		return returnString;
	}

	@Override
	public void setRoad(int playerIndex, EdgeLocation edgeLocation) {
		this.playerIndex = playerIndex;
		this.location = edgeLocation;
	}

}
