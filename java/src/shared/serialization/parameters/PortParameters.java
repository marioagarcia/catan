package shared.serialization.parameters;

import shared.locations.HexLocation;

public class PortParameters {
	
	private int ratio;
	private String resource;
	private String direction;
	private HexLocation location;
	
	public PortParameters(int ratio, String resource, String direction, HexLocation location){
		this.ratio = ratio;
		this.resource = resource;
		this.location = location;
		
		switch(direction)
		{
			case "North":
				direction = "N";
				break;
			case "NorthWest": 
				direction = "NW";
				break;
			case "NorthEast":
				direction = "NE";
				break;
			case "South":
				direction = "S";
				break;
			case "SouthEast":
				direction = "SE";
				break;
			case "SouthWest":
				direction = "SW";
				break;
		}
		
		this.direction = direction;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public HexLocation getLocation() {
		return location;
	}

	public void setLocation(HexLocation location) {
		this.location = location;
	}
	
}
