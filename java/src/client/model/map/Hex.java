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
	
	@Override
	public String toString(){
		String returnString = "";
		if(resource != null)
			returnString += resource.toString() + "\n";
		returnString += location.toString() + "\n";
		returnString += number + "\n";
		return returnString;
	}

	@Override
	public void setHex(HexLocation hexLocation, HexType resource, int number) {
		this.location = hexLocation;
		this.resource = resource;
		this.number = number;
		
	}

}
