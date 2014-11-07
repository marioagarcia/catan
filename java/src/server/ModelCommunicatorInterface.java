package server;

import shared.model.manager.GameData;
import shared.model.manager.GameList;

public interface ModelCommunicatorInterface {

	public GameData getGameModel();
	public GameList getGameList();
	public String[] getAIList();
}
