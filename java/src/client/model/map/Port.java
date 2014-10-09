package client.model.map;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.serialization.interfaces.SerializerPortInterface;

public class Port implements SerializerPortInterface {

	private ResourceType resource; // If omitted, then it's for any resource, Optional
	private EdgeLocation location;
	private int ratio;
	
	public Port(ResourceType resource, EdgeLocation location, int ratio){
		this.resource = resource;
		this.location = location;
		this.ratio = ratio;
	}

	public ResourceType getResource() {
		return resource;
	}

	public void setResource(ResourceType resource) {
		this.resource = resource;
	}

	public EdgeLocation getLocation() {
		return this.location;
	}

	public void setLocation(EdgeLocation location) {
		this.location = location;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	@Override
	public void setPort(int ratio, ResourceType resource, EdgeLocation direction) {
		
		this.ratio = ratio;
		this.resource = resource;
		this.location = direction;
		
	}
	
	@Override
	public String toString(){
		String returnString = "";
		
		returnString += "Ratio: " + ratio + " -- " + location.toString() + "\n";
		
		return returnString;
	}
}
