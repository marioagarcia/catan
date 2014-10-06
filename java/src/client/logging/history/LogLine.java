package client.logging.history;

public class LogLine implements LogLineInterface {

	String playerName;
	String move;
	
	LogLine(String playerName, String move) {
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

}
