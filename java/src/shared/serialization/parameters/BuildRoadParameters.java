package shared.serialization.parameters;

public class BuildRoadParameters {
	
	private String type;
	private int playerIndex;
	private EdgeLocationParameters roadLocation;
	private Boolean free;
	
	public BuildRoadParameters(int index, EdgeLocationParameters location, Boolean isFree){
		type = "buildRoad";
		playerIndex = index;
		roadLocation = location;
		free = isFree;
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

	public EdgeLocationParameters getRoadLocation() {
		return roadLocation;
	}

	public void setRoadLocation(EdgeLocationParameters roadLocation) {
		this.roadLocation = roadLocation;
	}

	public Boolean getFree() {
		return free;
	}

	public void setFree(Boolean free) {
		this.free = free;
	}
}
