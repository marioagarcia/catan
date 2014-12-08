package server.persistence;

import java.util.ArrayList;

public interface CommandDAOInterface {

	/**
	 * Serializes a command and saves it in the database
	 * 
	 * @param command_blob A serialized representation of a command object
	 * @param type The specific type of command object associated with the command blob
	 * @param game_id The id of the game this command belongs to
	 * @return True if the save was successful, false otherwise
	 */
	public abstract boolean saveCommand(String command_blob, int game_id);
	
	/**
	 * Retrieves a list of serialized commands associated with the given game id
	 * 
	 * @param game_id The id of the game whose commands are being retrieved
	 * @return An arraylist of the serialized commands associated with the give game id
	 */
	public abstract ArrayList<String> getUnappliedCommands(int game_id);
	
	/**
	 * Deletes the commands associated with a specific game, i.e. resets the list of commands for a game.
	 * 
	 * @param game_id The id of the game whose commands are being deleted
	 * @return True if the deletion was successful, false otherwise.
	 */
	public abstract boolean deleteGameCommands(int game_id);
}
