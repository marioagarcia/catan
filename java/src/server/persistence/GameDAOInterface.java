package server.persistence;

import java.util.ArrayList;

public interface GameDAOInterface {

	public abstract boolean saveGame(GameDTO data);
	public abstract ArrayList<GameDTO> getAllGames();
	public abstract GameDTO getGame(int game_id);
	public abstract boolean updateGame(GameDTO data, int game_id);
	public abstract boolean deleteGame(int game_id);
}
