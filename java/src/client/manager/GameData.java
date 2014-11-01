package client.manager;

import java.util.ArrayList;

import client.model.Winner;
import client.model.card.DevCardBank;
import client.model.card.DomesticTrade;
import client.model.card.ResourceCardBank;
import client.model.logging.GameLog;
import client.model.map.BoardMap;
import client.model.player.Player;
import client.model.player.Players;
import client.model.turntracker.TurnTracker;

public class GameData {

	//Deck - DevCardBank
	public DevCardBank devCardBank; //Done
	
	//Map
	public BoardMap boardMap;
	
	//Players
	public Players players;
	public ArrayList<Player> playerList;

	//GameLog
	public GameLog gameLog;
	
	//Bank - Resource List
	public ResourceCardBank resourceCardBank;
	
	//TurnTracker
	public TurnTracker turnTracker;

	//TradeOffer (if there is one, Optional)
	public DomesticTrade domesticTrade;

	//Winner (null if there is no winner yet)
	public Winner winner;
	
	//Version
	public int version;
	
	public GameData(){
		
	}

	public DevCardBank getDevCardBank(){
		return devCardBank;
	}
	
	public void setDevCardBank(DevCardBank deck){
		this.devCardBank = deck;
	}
	
	public ResourceCardBank getResourceCardBank() {
		return resourceCardBank;
	}

	public void setResourceCardBank(ResourceCardBank bank) {
		this.resourceCardBank = bank;
	}

	public BoardMap getBoardMap() {
		return boardMap;
	}

	public void setBoardMap(BoardMap boardMap) {
		this.boardMap = boardMap;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	public Players getPlayers() {
		return players;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	
	public void setPlayers(Players players) {
		this.players = players;
	}
	
	public GameLog getGameLog() {
		return gameLog;
	}

	public void setGameLog(GameLog gameLog) {
		this.gameLog = gameLog;
	}

	public DomesticTrade getDomesticTrade() {
		return domesticTrade;
	}

	public void setDomesticTrade(DomesticTrade domesticTrade) {
		this.domesticTrade = domesticTrade;
	}

	public TurnTracker getTurnTracker() {
		return turnTracker;
	}

	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Winner getWinner() {
		return winner;
	}

	public void setWinner(Winner winner) {
		this.winner = winner;
	}
	
}
