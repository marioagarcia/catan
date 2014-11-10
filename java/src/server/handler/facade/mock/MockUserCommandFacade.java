package server.handler.facade.mock;

import server.handler.facade.UserCommandFacadeInterface;
import shared.serialization.parameters.CredentialsParameters;

public class MockUserCommandFacade implements UserCommandFacadeInterface{

	@Override
	public Boolean login(CredentialsParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean register(CredentialsParameters params) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
