package server.command;

import server.facade.ServerModelFacade;
import shared.serialization.parameters.CredentialsParameters;

/**
 * A Command object that logs the user into the server
 * @author Kevin
 */
public class Login extends CatanCommand {

	private String username = null;
	private String password = null;
	/**
	 * Initializes the Login object with the data needed to log a player in to the server
	 * @param parameters an object containing the username and password
	 */
	public Login(CredentialsParameters parameters){
		
		super(-1, -1);
		this.username = parameters.getUsername();
		this.password = parameters.getPassword();
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#loginPlayer(String, String) loginPlayer}
	 */
	@Override
	public void execute() {
		
		success = ServerModelFacade.getInstance().loginPlayer(username, password);
		
	}
}
