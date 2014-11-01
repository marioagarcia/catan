package client.model;

import java.util.ArrayList;
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
	
	GameData gameData;
	
	public DevCardBank getDevCardBank(){
		return gameData.devCardBank;
	}
	
	public ResourceCardBank getResourceCardBank() {
		return gameData.resourceCardBank;
	}

	public BoardMap getBoardMap() {
		return gameData.boardMap;
	}

	public ArrayList<Player> getPlayerList() {
		return gameData.playerList;
	}
	
	public Players getPlayers() {
		return gameData.players;
	}
	
	public Player getLocalPlayer() {
		//return gameData.localPlayer;
		return null;
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

	public Winner getWinner() {
		return gameData.winner;
	}

}
