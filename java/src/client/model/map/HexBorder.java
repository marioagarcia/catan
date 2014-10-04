package client.model.map;

import client.model.piece.RoadInterface;

public class HexBorder implements HexBorderInterface {
	
	private RoadInterface road;
	
	public HexBorder(){ }

	@Override
	public RoadInterface getRoad() {
		return this.road;
	}

	@Override
	public void setRoad(RoadInterface road) {
		this.road = road;
	}

	@Override
	public boolean canBuildRoad() {
		return this.road == null;
	}

}
