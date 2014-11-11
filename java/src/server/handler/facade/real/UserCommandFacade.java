package server.handler.facade.real;

import server.handler.facade.UserCommandFacadeInterface;
import shared.serialization.parameters.CredentialsParameters;

public class UserCommandFacade implements UserCommandFacadeInterface{

	@Override
	public Boolean login(CredentialsParameters params) {
		System.out.println("Trying to login!");
		return null;
	}

	@Override
	public Boolean register(CredentialsParameters params) {
		System.out.println("Trying to register!");
		return null;
	}

}
