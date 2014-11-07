package shared.model.logging;

import shared.model.logging.chat.GameChatInterface;
import shared.model.logging.history.GameHistoryLogInterface;

public interface GameLogInterface 
{
	/**
	 * returns an object extending ChatInterface which contains the game chat
	 * @return ChatInterface
	 */
	public GameChatInterface getGameChat();

	/**
	 * set the value of the GameChatInterface which the GameLogInterface contains
	 * @param gameChat
	 */
	public void setGameChat(GameChatInterface gameChat);
	
	/**
	 * returns a history of all the moves in the game so far
	 * @return HistoryLogInterface
	 */
	public GameHistoryLogInterface getGameHistoryLog();
	
	/**
	 * set the GameHistoryLogInterface object which the GameLogInterface contains
	 * @param gameHistoryLog
	 */
	public void setGameHistoryLog(GameHistoryLogInterface gameHistoryLog);
}
