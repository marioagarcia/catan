package shared.model.logging.chat;

public interface MessageInterface {
	/**
	 * Used to set the message content
	 * @param content the message content
	 */
	public void setMessageContent(String content);
	
	/**
	 * sets the player who sent the message
	 * @param playerName the player that sent the chat
	 */
	public void setPlayerName(String playerName);
	
	/**
	 * Used to extract the message content
	 */
	public String getMessageContent();
	
	/**
	 * returns the player who corresponds to this message
	 */
	public String getPlayerName();
}
