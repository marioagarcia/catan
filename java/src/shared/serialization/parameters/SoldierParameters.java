package shared.serialization.parameters;

import shared.locations.HexLocation;

public class SoldierParameters extends MasterParameterInterface{
	
	private String type;
	private int playerIndex;
	private int victimIndex;
	private HexLocation location;
	
	public SoldierParameters(int playerIndx, int victimIndx, HexLocation loc){
		type = "Soldier";
		playerIndex = playerIndx;
		victimIndex = victimIndx;
		location = loc;
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

	public int getVictimIndex() {
		return victimIndex;
	}

	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}

	public HexLocation getLocation() {
		return location;
	}

	public void setLocation(HexLocation location) {
		this.location = location;
	}
}
