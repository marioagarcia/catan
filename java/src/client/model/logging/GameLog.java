package client.model.logging;

import java.util.Observable;

import client.model.logging.chat.GameChat;
import client.model.logging.chat.GameChatInterface;
import client.model.logging.history.GameHistoryLogInterface;
import client.model.logging.history.HistoryLog;

public class GameLog extends Observable implements GameLogInterface {

	private GameHistoryLogInterface gameHistoryLog;
	private GameChatInterface gameChat;
	
	public GameLog() {
		gameHistoryLog = new HistoryLog();
		gameChat = new GameChat();
	}
	
	public GameLog(GameHistoryLogInterface historyLog, GameChatInterface gameChat) {
		this.gameHistoryLog = historyLog;
		this.gameChat = gameChat;
	}
	
	@Override
	public GameChatInterface getGameChat() {
		return gameChat;
	}

	@Override
	public void setGameChat(GameChatInterface gameChat) {
		this.gameChat = gameChat;
	}

	@Override
	public GameHistoryLogInterface getGameHistoryLog() {
		return gameHistoryLog;
	}

	@Override
	public void setGameHistoryLog(GameHistoryLogInterface gameHistoryLog) {
		this.gameHistoryLog = gameHistoryLog;
	}
	
	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
}
