package client.logging.history;

public class LogLine implements LogLineInterface {

	int playerId;
	String gameCommand;
	
	LogLine(int playerId, String gameCommand) {
		setPlayerId(playerId);
		setCommand(gameCommand);
	}

	@Override
	public int getPlayerId() {
		return playerId;
	}

	@Override
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	@Override
	public String getCommand() {
		return gameCommand;
	}

	@Override
	public void setCommand(String command) {
		this.gameCommand = command;
	}

}
