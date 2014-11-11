package server.command.facade;

public interface UtilCommandFacadeInterface {

	/**
	 * Creates a ChangeLogLevel command object and calls execute on it
	 * 
	 * @param logLevel The desired logging level
	 * @return Whether or not the log level change was successful; true if
	 * successful, false otherwise
	 */
	public Boolean changeLogLevel(String logLevel);
	
}
