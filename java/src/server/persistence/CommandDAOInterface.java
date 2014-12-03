package server.persistence;

import java.util.ArrayList;

public interface CommandDAOInterface {

	public abstract boolean saveCommand(CommandDTO data);
	public abstract ArrayList<CommandDTO> getUnappliedCommands(int game_id);
	public abstract boolean updateCommand(CommandDTO data);
	public abstract boolean deleteCommand();
}
