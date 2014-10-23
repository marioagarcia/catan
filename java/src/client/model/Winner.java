package client.model;

import java.util.Observable;

public class Winner extends Observable {
	
	private String name;
	private int playerIndex;
	private boolean isLocalPlayer;
	
	public Winner() {
		name = null;
		playerIndex = -1;
		isLocalPlayer = false;
	}
	
	public boolean isLocalPlayer() {
		return isLocalPlayer;
	}
	
	public void setLocalPlayer(boolean i_won) {
		isLocalPlayer = i_won;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int player_index) {
		this.playerIndex = player_index;
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}

}
