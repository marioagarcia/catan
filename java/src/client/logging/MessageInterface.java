package client.logging;

import client.model.player.PlayerInterface;

public interface MessageInterface {
	/**
	 * Used to extract the message contents
	 * @return String containing message contents
	 */
	public String getMessageContents();
	/**
	 * returns the player who corresponds to this message
	 * @return client.model.player.PlayerInterface object
	 *  representing the player who sent the message
	 */
	public PlayerInterface getPlayer();
}
