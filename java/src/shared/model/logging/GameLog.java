package shared.model.logging;

import shared.model.logging.chat.GameChat;
import shared.model.logging.chat.GameChatInterface;
import shared.model.logging.history.GameHistoryLogInterface;
import shared.model.logging.history.HistoryLog;

public class GameLog implements GameLogInterface {

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
}
