package server.command;

import server.persistence.CommandDTO;

/**
 * A Command object that performs a specific action on a game hosted by the server
 * If the command is valid, it can be executed to update the state of the model
 * @author Kevin
 *
 */
public interface CatanCommandInterface{
	
	/**
	 * Performs a specific action on the game
	 */
	public void execute();
	
	/**
	 * @return True if the command executed successfully, false otherwise
	 */
	public boolean wasSuccessful();
	
	public CommandDTO toDTO();
}
