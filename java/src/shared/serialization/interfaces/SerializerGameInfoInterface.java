package shared.serialization.interfaces;

import java.util.ArrayList;

public interface SerializerGameInfoInterface {

	public abstract void setGameInfo(String gameTitle, int gameId, ArrayList<SerializerPlayerInterface> playerList);
	
}
