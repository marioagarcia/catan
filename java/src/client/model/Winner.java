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
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLocalPlayer ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + playerIndex;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Winner other = (Winner) obj;
		if (isLocalPlayer != other.isLocalPlayer)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (playerIndex != other.playerIndex)
			return false;
		return true;
	}

	public void update() {
		setChanged();
		notifyObservers();
	}

}
