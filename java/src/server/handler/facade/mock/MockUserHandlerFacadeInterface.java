package server.handler.facade.mock;

import shared.serialization.parameters.CredentialsParameters;

public interface MockUserHandlerFacadeInterface {

	/**
	 * Randomly selects either true or false
	 * 
	 * @param params An object containing the username and password of the
	 * player logging in
	 * @return True or false, whichever was randomly selected
	 */
	public Boolean login(CredentialsParameters params);
	
	/**
	 * Randomly selects either true or false
	 * 
	 * @param params An object containing the username and password of the
	 * user the is registering
	 * @return True or false, whichever was randomly selected
	 */
	public Boolean register(CredentialsParameters params);
}
