package client.model.map;

import shared.locations.EdgeLocation;

public class VertexObject {
	
	int ownerIndex;
	EdgeLocation location;
	
	public VertexObject(int index, EdgeLocation loc){
		ownerIndex = index;
		location = loc;
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
	
}
