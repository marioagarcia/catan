package client.logging;

import client.logging.chat.GameChatInterface;
import client.logging.history.HistoryLog;
import client.logging.history.GameHistoryLogInterface;

public class GameLog implements GameLogInterface {

	private GameHistoryLogInterface gameHistoryLog;
	private GameChatInterface gameChat;
	
	public GameLog() {
		gameHistoryLog = new HistoryLog();
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
}
