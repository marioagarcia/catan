package client.logging;

import client.logging.chat.ChatInterface;
import client.logging.history.HistoryLogInterface;

public interface GameLogInterface 
{
	
	/**
	 * returns an object extending ChatInterface which contains the game chat
	 * @return ChatInterface
	 */
	public ChatInterface getChat();
	
	/**
	 * returns a history of all the moves in the game so far
	 * @return HistoryLogInterface
	 */
	public HistoryLogInterface getGameHistory();
}
