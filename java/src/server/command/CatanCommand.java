package server.command;

public abstract class CatanCommand implements CatanCommandInterface {
	
	protected boolean success;

	@Override
	public abstract void execute();
	
	protected void setSuccess(boolean success){
		this.success = success;
	}
	
	@Override
	public boolean wasSuccessful() {
		return success;
	}
}
