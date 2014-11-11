package shared.serialization.parameters;

public class GameListParameters {

	private GameInfoParameters[] gameList;
	
	public GameListParameters(GameInfoParameters[] gameList){
		this.gameList = gameList;
	}

	public GameInfoParameters[] getGameList() {
		return gameList;
	}

	public void setGameList(GameInfoParameters[] gameList) {
		this.gameList = gameList;
	}
}
