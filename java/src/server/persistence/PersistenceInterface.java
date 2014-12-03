package server.persistence;

import java.util.ArrayList;

public interface PersistenceInterface {

	public abstract void configure(DatabaseAccessFactoryInterface factory);

	public abstract boolean saveCommand(CommandDTO data);
	public abstract ArrayList<CommandDTO> getUnappliedCommands(int game_id);
	public abstract boolean updateCommand(CommandDTO data);
	public abstract boolean deleteCommand(int id);
	
	public abstract boolean saveGame(GameDTO data);
	public abstract ArrayList<GameDTO> getAllGames();
	public abstract GameDTO getGame(int game_id);
	public abstract boolean updateGame(GameDTO data, int game_id);
	public abstract boolean deleteGame(int game_id);
	
	public abstract boolean saveUser(UserDTO data);
	public abstract ArrayList<UserDTO> getAllUsers();
	public abstract UserDTO getUser(String username);
	public abstract boolean updateUser(UserDTO data);
	public abstract boolean deleteUser(String username);
}
