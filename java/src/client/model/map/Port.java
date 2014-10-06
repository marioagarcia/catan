package client.model.map;

import shared.definitions.ResourceType;
import shared.locations.HexLocation;
import shared.serialization.interfaces.HexLocationInterface;
import shared.serialization.interfaces.PortInterface;

public class Port implements PortInterface {

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

	@Override
	public void setPort(int ratio, ResourceType resource, String direction,
			HexLocationInterface hexLocation) {
		// TODO Auto-generated method stub
		
	}
	
}
