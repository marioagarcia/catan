package client.model.map;

import shared.locations.VertexLocation;
import client.model.piece.BuildingInterface;
import client.model.player.PlayerInterface;

public class HexCorner implements HexCornerInterface {
	
	private int ownerIndex;
	private VertexLocation location;
	
	public HexCorner(){
		
	}
	
	public HexCorner(int ownerIndex, VertexLocation location){
		this.ownerIndex = ownerIndex;
		this.location = location;
	}

	public int getOwnerIndex() {
		return this.ownerIndex;
	}

	public void setOwnerIndex(int ownerIndex) {
		this.ownerIndex = ownerIndex;
	}

	public VertexLocation getLocation() {
		return location;
	}

	public void setLocation(VertexLocation location) {
		this.location = location;
	}

	@Override
	public boolean canBuildSettlement(PlayerInterface player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildCity(PlayerInterface player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void buildBuilding(BuildingInterface building) {
		// TODO Auto-generated method stub
		
	}

}
