package command;

import shared.serialization.parameters.MasterParameterInterface;

public interface CatanCommand{
	
	//Should these return 'object' to be cast back on the other end? Or maybe json depending where there serializing happens?
	public void execute(MasterParameterInterface parameter_package);
}
