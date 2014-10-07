package client.manager;

import java.util.ArrayList;
import java.util.Collection;

public class GameCommands {

	private ArrayList<GameCommand> commandList;
	
	public GameCommands() {
		commandList = new ArrayList<GameCommand>();
	}
	
	public void addCommand( GameCommand command) {
		commandList.add(command);
	}
	
	public Collection<GameCommand> getAllCommands() {
		return commandList;
	}
}
