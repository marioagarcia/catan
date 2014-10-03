package shared.serialization.parameters;

public class BuildCityParameters {

	private String type;
	private int playerIndex;
	private VertexLocationParameters vertexLocation;
	
	public BuildCityParameters(int index, VertexLocationParameters location){
		type = "buildCity";
		playerIndex = index;
		vertexLocation = location;
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
}
