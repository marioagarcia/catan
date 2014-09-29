package client.communication.server;

/**
 * Communicates with the game server to keep the model state in sync for all players in the game 
 *
 */
public interface ServerPollerInterface
{	
	/**
	 * @return A JSON String representing the current state of all model objects. This object must have a current ServerProxy object set before this method is called.
	 */
	public String getModelState();
	
	/**
	 * @param proxy The desired end point for all server calls. A ServerProxy object will communicate with the real game server. A ServerMoxy provides hard-coded responses for testing purposes.
	 */
	public void setProxy(ServerProxyInterface proxy);
}
