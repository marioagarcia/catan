package shared.serialization.parameters;

public class SendChatParameters extends MasterParameterInterface{

	private String type;
	private int playerIndex;
	private String content;
	
	public SendChatParameters(int index, String cont){
		type = "sendChat";
		playerIndex = index;
		content = cont;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
