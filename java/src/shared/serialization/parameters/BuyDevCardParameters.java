package shared.serialization.parameters;

public class BuyDevCardParameters {
	
	private String type;
	private int playerIndex;

	public BuyDevCardParameters(int index){
		type = "buyDevCard";
		playerIndex = index;
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
}
