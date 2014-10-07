package client.model.map;

import shared.definitions.HexType;
import shared.locations.HexLocation;
import shared.serialization.interfaces.SerializerHexInterface;

public class Hex implements HexInterface, SerializerHexInterface {
	
	private HexLocation location;
	private HexType resource;
	private int number;

	
	public Hex(HexLocation location, HexType type, int number){
		
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
	public void setHex(HexLocation hexLocation, HexType resource, int number) {
		this.location = hexLocation;
		this.resource = resource;
		this.number = number;
		
	}

	@Override
	public String toString() {
		return "Hex [location=" + location + ", resource=" + resource
				+ ", number=" + number + "]";
	}

}
