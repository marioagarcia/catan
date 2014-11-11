package shared.serialization.parameters;

public class RoadParameters {
	
	private int owner;
	private EdgeLocationParameters location;
	
	public RoadParameters(int owner, EdgeLocationParameters location){
		this.owner = owner;
		this.location = location;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public EdgeLocationParameters getLocation() {
		return location;
	}

	public void setLocation(EdgeLocationParameters location) {
		this.location = location;
	}
}
