package client.model.map;

import java.util.Map;

import client.model.map.HexBorderInterface.HexBorderType;
import client.model.map.HexCornerInterface.HexCornerType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;

public class Hex implements HexInterface {
	
	private HexLocation location;
	private HexType type;
	private int number;
	private Map<VertexDirection, HexCornerInterface> corners;
	private Map<EdgeLocation, HexBorderInterface> borders;
	
	public Hex(Map<VertexDirection, HexCornerInterface> corners,
			Map<EdgeLocation, HexBorderInterface> borders,
			HexLocation location,
			HexType type,
			int number){
		
		this.corners = corners;
		this.borders = borders;
		this.location = location;
		this.type = type;
		this.number = number;
	}

	@Override
	public HexLocation getLocation() {
		return this.location;
	}

	@Override
	public HexType getType() {
		return this.type;
	}

	@Override
	public void setType(HexType type) {
		this.type = type;
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public HexCornerInterface getCorner(VertexDirection type) {
		return this.corners.get(type);
	}

	@Override
	public HexBorderInterface getBorder(EdgeDirection type) {
		return this.borders.get(type);
	}

}
