package client.model.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Players extends Observable {

	private List<Player> playerList;
	private int localPlayerIndex;
	
	public Players() {
		playerList = new ArrayList<>();
	}
	
	public void addPlayer(Player new_player) {
		playerList.add(new_player);
	}
	
	public Player getPlayer(int player_index) {
		return playerList.get(player_index);
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	public int getLocalPlayerIndex() {
		return localPlayerIndex;
	}

	public void setLocalPlayerIndex(int localPlayerIndex) {
		this.localPlayerIndex = localPlayerIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((playerList == null) ? 0 : playerList.hashCode());
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
		Players other = (Players) obj;
		if (playerList == null) {
			if (other.playerList != null)
				return false;
		} else if (!playerList.equals(other.playerList))
			return false;
		return true;
	}
	
	public void update(Object arg){
		setChanged();
		notifyObservers(arg);
	}
	
}
