package client.model.map;

import shared.locations.EdgeLocation;
import client.model.piece.BuildingInterface;
import client.model.piece.GamePieceInterface.GamePieceType;
import client.model.player.PlayerInterface;

public class HexCorner implements HexCornerInterface {
	
	private BuildingInterface building;
	private int ownerIndex;
	private EdgeLocation location;
	
	public HexCorner(){
		
	}

	public int getOwnerIndex() {
		return ownerIndex;
	}

	public void setOwnerIndex(int ownerIndex) {
		this.ownerIndex = ownerIndex;
	}

	public EdgeLocation getLocation() {
		return location;
	}

	public void setLocation(EdgeLocation location) {
		this.location = location;
	}

	@Override
	public BuildingInterface getBuilding() {
		return this.building;
	}

	@Override
	public boolean canBuildSettlement(PlayerInterface player) {
		if(this.building == null){
			return true;
		}
		return false;
	}

	@Override
	public boolean canBuildCity(PlayerInterface player) {
		if(this.building == null){
			return false;
		}
		if(this.building.getPlayerId() != player.getId()){
			return false;
		}
		if(this.building.getType() != GamePieceType.SETTLEMENT){
			return false;
		}
		
		return true;
	}

	@Override
	public void buildBuilding(BuildingInterface building) {
		this.building = building;
	}

}
