package shared.serialization.parameters;

public class MonopolyParameters extends MasterParameterInterface{
	
	private String type;
	private String resource;
	private int playerIndex;
	
	public MonopolyParameters(String rsrce, int index){
		type = "Monopoly";
		resource = rsrce;
		playerIndex = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
}
