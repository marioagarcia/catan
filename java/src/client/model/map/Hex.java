package client.model.map;

import java.util.Map;

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
<<<<<<< HEAD
	
	public String toString(){
		String returnString = resource + "\n";
		returnString += location.toString() + "\n";
		returnString += number + "\n";
		return returnString;
	}
=======

	@Override
	public HexCornerInterface getCorner(VertexDirection type) {
		return this.corners.get(type);
	}

	@Override
	public HexBorderInterface getBorder(EdgeDirection type) {
		return this.borders.get(type);
	}

>>>>>>> 27df1795a0de1cb279e9cc03fcf6f7779b9a73d5
}
