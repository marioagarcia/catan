package shared.model.logging.history;

public interface LogLineInterface 
{
	/**
	 * gives you the String form of the MoveLogInterface object
	 * @return String containing a readable version of the object
	 */
	public String getMove();
	
	/**
	 * set the string form of the move
	 * @param move information about the move
	 */
	public void setMove(String move);
	
	/**
	 * allows you to view the player corresponding
	 * to the log entry
	 * @return String the corresponding player's name
	 */
	public String getPlayerName();
	
	/**
	 * set the name of the player who made the move
	 * @param playerName
	 */
	public void setPlayerName(String playerName);
	
	
}
