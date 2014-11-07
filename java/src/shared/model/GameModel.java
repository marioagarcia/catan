package shared.model;

import java.util.Observable;

import shared.model.card.DevCardBank;
import shared.model.card.DomesticTrade;
import shared.model.card.ResourceCardBank;
import shared.model.logging.GameLog;
import shared.model.map.BoardMap;
import shared.model.player.Player;
import shared.model.player.Players;
import shared.model.turntracker.TurnTracker;
import client.manager.GameData;

public class GameModel extends Observable{
	
	private GameData gameData;
	private Player localPlayer;
	
	public GameModel() {
		localPlayer = new Player();
	}
	
	/**
	 * update the GameData using the given GameData object
	 * @param game_data updated information about the game
	 */
	public void setGameData(GameData game_data) {
		gameData = game_data;
	}
	
	/**
	 * give the GameModel's current DevCardBank
	 * @return the DevCardBank
	 */
	public DevCardBank getDevCardBank(){
		return gameData.devCardBank;
	}
	
	/**
	 * give the GameModel's current ResourceCardBank
	 * @return ResourceCardBank
	 */
	public ResourceCardBank getResourceCardBank() {
		return gameData.resourceCardBank;
	}

	/**
	 * get the GameModel's current BoardMap
	 * @return BoardMap
	 */
	public BoardMap getBoardMap() {
		return gameData.boardMap;
	}

	/**
	 * get all of the players in the current game
	 * @return Players object
	 */
	public Players getPlayers() {
		return gameData.players;
	}
	
	/**
	 * get the player locally logged in to the game
	 * @return Player
	 */
	public Player getLocalPlayer() {
		return localPlayer;
	}
	
	/**
	 * updates local player to the given one
	 * @param p new local Player
	 */
	public void setLocalPlayer(Player p) {
		
		localPlayer.setCities(p.getCities());
		localPlayer.setColor(p.getColor());
		localPlayer.setMonuments(p.getMonuments());
		localPlayer.setNewDevCards(p.getNewDevCards());
		localPlayer.setOldDevCards(p.getOldDevCards());
		localPlayer.setDiscarded(p.isDiscarded());
		localPlayer.setPlayedDevCard(p.isPlayedDevCard());
		localPlayer.setResourceList(p.getResourceList());
		localPlayer.setRoads(p.getRoads());
		localPlayer.setSettlements(p.getSettlements());
		localPlayer.setSoldiers(p.getSoldiers());
		localPlayer.setVictoryPoints(p.getVictoryPoints());
	}
	
	/**
	 * 
	 * @return the game log with the commands for the current game
	 */
	public GameLog getGameLog() {
		return gameData.gameLog;
	}

	/**
	 * 
	 * @return the current domestic trade, if any
	 */
	public DomesticTrade getDomesticTrade() {
		return gameData.domesticTrade;
	}

	/**
	 * 
	 * @return the current TurnTracker
	 */
	public TurnTracker getTurnTracker() {
		return gameData.turnTracker;
	}

	/**
	 * 
	 * @return
	 */
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
