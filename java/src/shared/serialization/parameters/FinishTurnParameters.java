package shared.serialization.parameters;

public class FinishTurnParameters extends MasterParameterInterface{

	private String type;
	private int playerIndex;
	
	public FinishTurnParameters(int index){
		type = "finishTurn";
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
