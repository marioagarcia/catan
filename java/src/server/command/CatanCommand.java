package server.command;

import server.facade.ServerModelFacade;
import server.persistence.CommandDTO;

/**
 * Provides a default implementation of the setSuccess and wasSuccessful methods that the other command classes can extend
 * @author Kevin
 */
public abstract class CatanCommand implements CatanCommandInterface {
	
	protected boolean success = false;
	protected int playerIndex = -1;
	protected int gameId = -1;
	protected ServerModelFacade facadeInstance = ServerModelFacade.getInstance();
	protected CommandDTO dto = null;

	@Override
	public abstract void execute();
	
	@Override
	public CommandDTO toDTO(){
		return this.dto;
	}
	
	/**
	 * Changes the success of this command object. Will be called during the execute method 
	 * @param success True if the command was executed successfully, false otherwise 
	 */
	protected void setSuccess(boolean success){
		this.success = success;
	}
	
	/**
	 * @return True if the command was executed successfully, false otherwise
	 */
	@Override
	public boolean wasSuccessful() {
		return success;
	}
}
