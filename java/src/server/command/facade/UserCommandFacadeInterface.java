package server.command.facade;

import shared.serialization.parameters.CredentialsParameters;

public interface UserCommandFacadeInterface {

	/**
	 * Creates a Login command object and calls execute on it
	 * 
	 * @param params An object containing the username and password of the
	 * player logging in
	 * @return Whether or not the log in was successful; true if successful,
	 * false otherwise
	 */
	public Boolean login(CredentialsParameters params);
	
	/**
	 * Creates a Register command object and calls execute on it
	 * 
	 * @param params An object containing the username and password of the
	 * user the is registering
	 * @return Whether or not registration was successful; true if successful,
	 * false otherwise
	 */
	public Boolean register(CredentialsParameters params);
	
}
