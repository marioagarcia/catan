package client.model.logging.history;

public interface LogLineInterface 
{
	/**
	 * gives you the String form of the MoveLogInterface object
	 * @return String containing a readable version of the object
	 */
	public String getMove();
	
	public void setMove(String move);
	
	/**
	 * allows you to view the player corresponding
	 * to the log entry
	 * @return String the corresponding player's name
	 */
	public String getPlayerName();
	
	public void setPlayerName(String playerName);
	
	
}
