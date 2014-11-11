package server.command;

import shared.serialization.parameters.CredentialsParameters;

/**
 * A command object that will add a new user to the list of registered users
 * @author Kevin
 */
public class Register extends CatanCommand {

	/**
	 * Initializes the Register object with the data necessary to register a new user
	 * @param parameters An object containing the username and password of the person wishing to register
	 */
	public Register(CredentialsParameters parameters){
		
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#registerPlayer(String, String) registerPlayer} 
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
