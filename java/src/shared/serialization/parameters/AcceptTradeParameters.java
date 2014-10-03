package shared.serialization.parameters;

public class AcceptTradeParameters {

	private String type;
	private int playerIndex;
	private Boolean willAccept;
	
	public AcceptTradeParameters(int index, Boolean accept){
		type = "willAccept";
		playerIndex = index;
		willAccept = accept;
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

	public Boolean getWillAccept() {
		return willAccept;
	}

	public void setWillAccept(Boolean willAccept) {
		this.willAccept = willAccept;
	}
}
