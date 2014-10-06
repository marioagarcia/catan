package client.logging.chat;

public interface MessageInterface {
	/**
	 * Used to extract the message content
	 * @return String containing message content
	 */
	public void setMessageContent(String content);
	/**
	 * returns the player who corresponds to this message
	 * @return client.model.player.PlayerInterface object
	 *  representing the player who sent the message
	 */
	public void setPlayerName(String playerName);
}
