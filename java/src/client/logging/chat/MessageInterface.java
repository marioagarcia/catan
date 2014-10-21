package client.logging.chat;

public interface MessageInterface {
	/**
	 * Used to extract the message content
	 * @param content the message content
	 */
	public void setMessageContent(String content);
	
	/**
	 * returns the player who corresponds to this message
	 * @param playerName the player that sent the chat
	 */
	public void setPlayerName(String playerName);
	
	/**
	 * Used to extract the message content
	 * @param content the message content
	 */
	public String getMessageContent();
	
	/**
	 * returns the player who corresponds to this message
	 * @param playerName the player that sent the chat
	 */
	public String getPlayerName();
}
