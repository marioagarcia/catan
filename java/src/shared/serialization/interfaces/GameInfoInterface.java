package shared.serialization.interfaces;

import java.util.ArrayList;

public interface GameInfoInterface {

	public abstract void setGameInfo(String gameTitle, int gameId, ArrayList<PlayerInterface> playerList);
	
}
