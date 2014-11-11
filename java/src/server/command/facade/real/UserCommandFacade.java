package server.command.facade.real;

import server.command.Login;
import server.command.Register;
import server.command.facade.UserCommandFacadeInterface;
import shared.serialization.parameters.CredentialsParameters;

public class UserCommandFacade implements UserCommandFacadeInterface{

	@Override
	public Boolean login(CredentialsParameters params) {
		System.out.println("Trying to login!");
		//Create the login command object and call execute on it
		Login login = new Login(params);
		login.execute();
		
		return login.wasSuccessful();
	}

	@Override
	public Boolean register(CredentialsParameters params) {
		System.out.println("Trying to register!");
		//Create the register command object and call execute on it
		Register register = new Register(params);
		
		return register.wasSuccessful();
	}

}
