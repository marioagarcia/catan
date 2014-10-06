package client.manager;

import shared.serialization.interfaces.SerializerGameCommandInterface;

public class GameCommand implements SerializerGameCommandInterface{
	
	String content;
	String type;
	int playerIndex;
	
	GameCommand(String content, String type, int playerIndex) {
		this.content = content;
		this.type = type;
		this.playerIndex = playerIndex;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Override
	public void setGameCommand(String content, String type, int playerIndex) {
		this.content = content;
		this.type = type;
		this.playerIndex = playerIndex;
	}

}
