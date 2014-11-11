package server.command;

import server.facade.ServerModelFacade;

/**
 * Provides a default implementation of the setSuccess and wasSuccessful methods that the other command classes can extend
 * @author Kevin
 */
public abstract class CatanCommand implements CatanCommandInterface {
	
	protected boolean success;
	protected int playerIndex = -1;
	protected int gameId = -1;
	protected ServerModelFacade facadeInstance = ServerModelFacade.getInstance();

	@Override
	public abstract void execute();
	
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
