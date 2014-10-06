package client.manager;

import java.util.ArrayList;
import java.util.Collection;

public class GameCommands {

	ArrayList<GameCommand> commandList;
	
	GameCommands() {
		
	}
	
	public void addCommand( GameCommand command) {
		commandList.add(command);
	}
	
	public Collection<GameCommand> getAllCommands() {
		return commandList;
	}
}
