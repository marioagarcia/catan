package shared.serialization.parameters;

import shared.locations.EdgeLocation;

public class RoadBuildingParameters extends MasterParameterInterface{

	private String type;
	private int playerIndex;
	private EdgeLocationParameters spot1;
	private EdgeLocationParameters spot2;
	
	public RoadBuildingParameters(int index, EdgeLocationParameters location1, EdgeLocationParameters location2){
		type = "Road_Building";
		playerIndex = index;
		spot1 = location1;
		spot2 = location2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public EdgeLocationParameters getSpot1() {
		return spot1;
	}

	public void setSpot1(EdgeLocationParameters spot1) {
		this.spot1 = spot1;
	}

	public EdgeLocationParameters getSpot2() {
		return spot2;
	}

	public void setSpot2(EdgeLocationParameters spot2) {
		this.spot2 = spot2;
	}
}
