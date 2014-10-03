package client.model.map;

import shared.locations.HexLocation;

public class Hex {
	
	private HexLocation location;
	private String resource; // If not a desert, Optional
	private int number; // If not a desert, Optional
	
	public Hex(HexLocation loc, String rsrc, int num){
		location = loc;
		resource = rsrc;
		number = num;
	}

	public HexLocation getLocation() {
		return location;
	}

	public void setLocation(HexLocation location) {
		this.location = location;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
