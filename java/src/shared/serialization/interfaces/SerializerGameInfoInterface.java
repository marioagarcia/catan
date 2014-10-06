package shared.serialization.interfaces;

import java.util.ArrayList;

import client.model.player.PlayerInfo;

public interface SerializerGameInfoInterface {

	public abstract void setGameInfo(String gameTitle, int gameId, ArrayList<PlayerInfo> playerList);
	
}
