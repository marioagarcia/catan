package client.model.logging;

import client.model.logging.chat.GameChatInterface;
import client.model.logging.history.GameHistoryLogInterface;

public interface GameLogInterface 
{
	/**
	 * returns an object extending ChatInterface which contains the game chat
	 * @return ChatInterface
	 */
	public GameChatInterface getGameChat();

	public void setGameChat(GameChatInterface gameChat);
	
	/**
	 * returns a history of all the moves in the game so far
	 * @return HistoryLogInterface
	 */
	public GameHistoryLogInterface getGameHistoryLog();
	
	public void setGameHistoryLog(GameHistoryLogInterface gameHistoryLog);
}
