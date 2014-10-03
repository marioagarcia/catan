package client.model.map;

import shared.locations.HexLocation;

public class Port {

	private String resource; // If omitted, then it's for any resource, Optional
	private HexLocation location;
	private String direction;
	private int ratio;
	
	public Port(String rsrc, HexLocation loc, String dir, int rat){
		resource = rsrc;
		location = loc;
		direction = dir;
		ratio = rat;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	
}
