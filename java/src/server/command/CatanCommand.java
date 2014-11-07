package server.command;

import shared.serialization.parameters.MasterParameterInterface;

public abstract class CatanCommand implements CatanCommandInterface {
	
	protected boolean success;

	@Override
	public void execute(MasterParameterInterface parameter_package) {
		// TODO Auto-generated method stub

	}

	protected void setSuccess(boolean success){
		this.success = success;
	}
	
	@Override
	public boolean wasSuccessful() {
		return success;
	}

}
