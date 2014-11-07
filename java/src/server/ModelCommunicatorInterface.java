package server;

import client.manager.GameData;
import client.manager.GameList;

public interface ModelCommunicatorInterface {

	public GameData getGameModel();
	public GameList getGameList();
	public String[] getAIList();
}
