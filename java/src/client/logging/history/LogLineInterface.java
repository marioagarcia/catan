package client.logging.history;

public interface LogLineInterface 
{
	/**
	 * gives you the String form of the MoveLogInterface object
	 * @return String containing a readable version of the object
	 */
	public String getCommand();
	
	public void setCommand(String command);
	
	/**
	 * allows you to view the player corresponding
	 * to the log entry
	 * @return PlayerInterface object of the player who made the move
	 */
	public int getPlayerId();
	
	public void setPlayerId(int playerId);
	
	
}
