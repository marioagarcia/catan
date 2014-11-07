package server.command;

import shared.serialization.parameters.CredentialsParameters;

/**
 * A Command object that logs the user into the server
 * @author Kevin
 */
public class Login extends CatanCommand {

	/**
	 * Initializes the Login object with the data needed to log a player in to the server
	 * @param parameters an object containing the username and password
	 */
	public Login(CredentialsParameters parameters){
		
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#loginPlayer(int, String, String) loginPlayer}
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
