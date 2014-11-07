package shared.model.manager;

import java.util.ArrayList;
import java.util.Observable;

import shared.model.GameInfo;

public class GameList extends Observable{
	
	private ArrayList<GameInfo> gameList;
	
	public GameList(){
		gameList = new ArrayList<GameInfo>();
	}
	
	public void setGameList(ArrayList<GameInfo> new_list){
		this.gameList = new_list;
	}
	
	public ArrayList<GameInfo> getGameList(){
		return gameList;
	}
	
	public GameInfo[] gameListToArray(){
		GameInfo[] gamesList = new GameInfo[gameList.size()];

		for(int i = 0; i < gameList.size(); i++){
			gamesList[i] = gameList.get(i);
		}

		return gamesList;
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}

	
}
