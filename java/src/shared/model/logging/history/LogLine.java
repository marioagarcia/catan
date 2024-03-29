package shared.model.logging.history;

import java.io.Serializable;

public class LogLine implements LogLineInterface, Serializable {

	private static final long serialVersionUID = 6566339212467420825L;
	
	String playerName;
	String move;
	
	public LogLine(String playerName, String move) {
		setPlayerName(playerName);
		setMove(move);
	}

	@Override
	public String getMove() {
		return move;
	}

	@Override
	public void setMove(String move) {
		this.move = move;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LogLine [playerName=" + playerName + ", move=" + move + "]";
	}

}
