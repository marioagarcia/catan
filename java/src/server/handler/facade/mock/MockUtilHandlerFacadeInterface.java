package server.handler.facade.mock;

public interface MockUtilHandlerFacadeInterface {

	/**
	 * Randomly selects either true or false
	 * 
	 * @param logLevel The desired logging level
	 * @return True or false, whichever was randomly selected
	 */
	public Boolean changeLogLevel(String logLevel);
}
