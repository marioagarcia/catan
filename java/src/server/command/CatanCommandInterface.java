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
	
	/**
	 * Creates a Data Transfer Object with the relevant data from the Command Object. This DTO will be used to persist commands to a database
	 * @return An object that packages the parameters of the command object, so they can be reconstituted and applied to a game
	 */
	public CommandDTO toDTO();
}
