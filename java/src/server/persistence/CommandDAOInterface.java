package server.persistence;

import java.util.ArrayList;

public interface CommandDAOInterface {

	/**
	 * Serializes a command and saves it in the database
	 * 
	 * @param data An object containing the information needed to save a command: a parameter object, a type (string), and an id (int)
	 * @return True if the save was successful, false otherwise
	 */
	public abstract boolean saveCommand(CommandDTO data);
	
	/**
	 * Retrieves a list of commands associated with the given game id
	 * 
	 * @param game_id The id of the game whose commands are being retrieved
	 * @return An arraylist of the commands associated with the give game id
	 */
	public abstract ArrayList<CommandDTO> getUnappliedCommands(int game_id);
	
	/**
	 * Deletes the commands associated with a specific game, i.e. resets the list of commands for a game.
	 * 
	 * @param game_id The id of the game whose commands are being deleted
	 * @return True if the deletion was successful, false otherwise.
	 */
	public abstract boolean deleteGameCommands(int game_id);
}
