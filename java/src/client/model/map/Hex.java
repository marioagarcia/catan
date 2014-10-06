package client.model.map;

import java.util.Map;

import shared.definitions.HexType;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.serialization.interfaces.SerializerHexInterface;

public class Hex implements HexInterface, SerializerHexInterface {
	
	private HexLocation location;
	private HexType resource;
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
		this.resource = type;
		this.number = number;
	}
	
	public Hex(HexLocation location,
			HexType type,
			int number){
		
		this.location = location;
		this.resource = type;
		this.number = number;
	}

	@Override
	public HexLocation getLocation() {
		return this.location;
	}

	@Override
	public HexType getType() {
		return this.resource;
	}

	@Override
	public void setType(HexType type) {
		this.resource = type;
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String toString(){
		String returnString = "";
		if(resource != null)
			returnString += resource.toString() + "\n";
		returnString += location.toString() + "\n";
		returnString += number + "\n";
		return returnString;
	}

	@Override
	public HexCornerInterface getCorner(VertexDirection type) {
		return this.corners.get(type);
	}

	@Override
	public HexBorderInterface getBorder(EdgeDirection type) {
		return this.borders.get(type);
	}

	@Override
	public void setHex(HexLocation hexLocation, ResourceType resource, int number) {
		this.location = hexLocation;
		
		switch(resource){
			case WOOD:
				this.resource = HexType.WOOD;
				break;
			case BRICK:
				this.resource = HexType.BRICK;
				break;
			case SHEEP:
				this.resource = HexType.SHEEP;
				break;
			case WHEAT:
				this.resource = HexType.WHEAT;
				break;
			case ORE:
				this.resource = HexType.ORE;
				break;
			default:
				//should never happen
				break;
		}
		
		this.number = number;
		
	}

}
