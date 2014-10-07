package client.manager;

import java.util.ArrayList;

import shared.locations.HexLocation;
import client.logging.GameLog;
import client.logging.chat.Message;
import client.model.card.DevCardBank;
import client.model.card.DomesticTrade;
import client.model.card.ResourceCardBank;
import client.model.card.ResourceList;
import client.model.map.BoardMap;
import client.model.map.Hex;
import client.model.map.Port;
import client.model.piece.City;
import client.model.piece.Road;
import client.model.piece.Settlement;
import client.model.player.Player;
import client.model.turntracker.TurnTracker;

public class GameData {

	//Deck - DevCardBank
	DevCardBank devCardBank; //Done
	
	//Map
	BoardMap boardMap;
	
	//Players
	ArrayList<Player> playerList;

	//GameLog
	GameLog gameLog;
	
	//Bank - Resource List
	ResourceCardBank resourceCardBank;
	
	//TurnTracker
	TurnTracker turnTracker;

	//TradeOffer (if there is one, Optional)
	DomesticTrade domesticTrade;

	//Version
	int version;
	
	//Winner (-1 if there is no winner yet)
	int winner;
	
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

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
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

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	
	
}
