package shared.serialization.parameters;

public class RollNumberParameters {

	private String type;
	private int playerIndex;
	private int number;
	
	public RollNumberParameters(int index, int num){
		type = "rollNumber";
		playerIndex = index;
		number = num;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
