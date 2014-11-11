package shared.serialization.parameters;

public class SettlementParameters {
	
	private int owner;
	private VertexLocationParameters location;
	
	public SettlementParameters(int owner, VertexLocationParameters location){
		this.owner = owner;
		this.location = location;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public VertexLocationParameters getLocation() {
		return location;
	}

	public void setLocation(VertexLocationParameters location) {
		this.location = location;
	}
}
