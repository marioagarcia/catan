package client.logging.history;

import client.model.player.PlayerInterface;

public interface MoveLogInterface 
{
	/**
	 * gives you the String form of the MoveLogInterface object
	 * @return String containing a readable version of the object
	 */
	public String getMoveAsString();
	
	/**
	 * allows you to view the player corresponding
	 * to the log entry
	 * @return PlayerInterface object of the player who made the move
	 */
	public PlayerInterface getPlayer();
}
