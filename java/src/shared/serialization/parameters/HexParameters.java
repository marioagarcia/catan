package shared.serialization.parameters;

import shared.locations.HexLocation;

public class HexParameters {

	private String resource;
	private HexLocation location;
	private Integer number;
	
	public HexParameters(String resource, HexLocation location, Integer number){
		this.resource = resource;
		this.location = location;
		this.number = number;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public HexLocation getLocation() {
		return location;
	}

	public void setLocation(HexLocation location) {
		this.location = location;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
