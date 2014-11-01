package client.model;

import java.util.Observable;

import client.manager.GameData;
import client.model.card.DevCardBank;
import client.model.card.DomesticTrade;
import client.model.card.ResourceCardBank;
import client.model.logging.GameLog;
import client.model.map.BoardMap;
import client.model.player.Player;
import client.model.player.Players;
import client.model.turntracker.TurnTracker;

public class GameModel extends Observable{
	
	private GameData gameData;
	private Player localPlayer;
	
	public GameModel() {
		localPlayer = new Player();
	}
	
	public void setGameData(GameData game_data) {
		gameData = game_data;
	}
	
	public DevCardBank getDevCardBank(){
		return gameData.devCardBank;
	}
	
	public ResourceCardBank getResourceCardBank() {
		return gameData.resourceCardBank;
	}

	public BoardMap getBoardMap() {
		return gameData.boardMap;
	}

	public Players getPlayers() {
		return gameData.players;
	}
	
	public Player getLocalPlayer() {
		return localPlayer;
	}
	
	public void setLocalPlayer(Player local_player) {
		localPlayer = local_player;
	}
	
	public GameLog getGameLog() {
		return gameData.gameLog;
	}

	public DomesticTrade getDomesticTrade() {
		return gameData.domesticTrade;
	}

	public TurnTracker getTurnTracker() {
		return gameData.turnTracker;
	}

	public int getVersion() {
		return gameData.version;
	}

	public int getWinnerPlayerIndex() {
		return gameData.winnerIndex;
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}

}
