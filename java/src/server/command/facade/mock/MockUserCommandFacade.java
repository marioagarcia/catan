package server.command.facade.mock;

import server.command.facade.UserCommandFacadeInterface;
import shared.serialization.parameters.CredentialsParameters;

public class MockUserCommandFacade implements UserCommandFacadeInterface{

	@Override
	public Boolean login(CredentialsParameters params) {
		return true;
	}

	@Override
	public Boolean register(CredentialsParameters params) {
		return true;
	}
	
}
