package server.command;

import shared.serialization.parameters.MasterParameterInterface;

public interface CatanCommandInterface{
	
	//Should these return 'object' to be cast back on the other end? Or maybe json depending where there serializing happens?
	public void execute(MasterParameterInterface parameter_package);
	
	public boolean wasSuccessful();
}
