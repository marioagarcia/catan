package shared.model.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
public class GameCommands implements Serializable {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commandList == null) ? 0 : commandList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameCommands other = (GameCommands) obj;
		if (commandList == null) {
			if (other.commandList != null)
				return false;
		} else if (!commandList.equals(other.commandList))
			return false;
		return true;
	}
	
}
