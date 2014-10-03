package client.model.piece;

import shared.locations.EdgeLocation;

public class Road implements RoadInterface {

	int playerIndex;
	EdgeLocation location;
	
	public Road(int id, EdgeLocation loc){
		playerIndex = id;
		location = loc;
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

}
