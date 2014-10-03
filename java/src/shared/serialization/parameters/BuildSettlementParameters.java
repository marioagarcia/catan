package shared.serialization.parameters;

public class BuildSettlementParameters extends MasterParameterInterface{
	
	private String type;
	private int playerIndex;
	private VertexLocationParameters vertexLocation;
	private Boolean free;
	
	public BuildSettlementParameters(int index, VertexLocationParameters location, Boolean isFree){
		
		type = "buildSettlement";
		playerIndex = index;
		vertexLocation = location;
		free = isFree;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public VertexLocationParameters getVertexLocation() {
		return vertexLocation;
	}

	public void setVertexLocation(VertexLocationParameters vertexLocation) {
		this.vertexLocation = vertexLocation;
	}

	public Boolean getFree() {
		return free;
	}

	public void setFree(Boolean free) {
		this.free = free;
	}
}
