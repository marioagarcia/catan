package shared.model.manager;

import java.io.Serializable;
import java.util.ArrayList;

import shared.model.card.DevCardBank;
import shared.model.card.DomesticTrade;
import shared.model.card.ResourceCardBank;
import shared.model.logging.GameLog;
import shared.model.map.BoardMap;
import shared.model.player.Player;
import shared.model.player.Players;
import shared.model.turntracker.TurnTracker;

@SuppressWarnings("serial")
public class GameData implements Serializable {

	//Deck - DevCardBank
	public DevCardBank devCardBank; //Done
	
	//Map
	public BoardMap boardMap;
	
	//Players
	public Players players;

	//GameLog
	public GameLog gameLog;
	
	//Bank - Resource List
	public ResourceCardBank resourceCardBank;
	
	//TurnTracker
	public TurnTracker turnTracker;

	//TradeOffer (if there is one, Optional)
	public DomesticTrade domesticTrade;

	//Winner (-1 if there is no winner yet)
	public int winnerIndex;
	
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
		return new ArrayList<Player>(this.players.getPlayerList());
	}
	public Players getPlayers() {
		return players;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.players.setPlayerList(playerList);
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

	public int getWinnerPlayerIndex() {
		return winnerIndex;
	}

	public void setWinner(int winner_index) {
		this.winnerIndex = winner_index;
	}
	
}
