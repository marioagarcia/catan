package client.communication.server;

import client.communication.server.ServerPoller.ModelStateObserver;

/**
 * Communicates with the game server to keep the model state in sync for all players in the game 
 *
 */
public interface ServerPollerInterface{	

	/**
	 * Begins the polling process
	 * @param interval Number of milliseconds between each poll to the server
	 */
	public void startPoller(long interval);
	
	/**
	 * Adds the observer to the ServerPoller's list of observers. Observers will be notified when the Catan game model changes
	 * @param new_observer An object that implements the ServerPoller.ModelStateObserver interface. 
	 */
	public void registerObserver(ModelStateObserver new_observer);
	
	/**
	 * @return A JSON String representing the current state of all model objects. This object must have a current ServerProxy object set before this method is called.
	 */
	public String getModelState();
	
	/**
	 * @param proxy The desired end point for all server calls. A ServerProxy object will communicate with the real game server. A ServerMoxy provides hard-coded responses for testing purposes.
	 */
	public void setProxy(ServerProxyInterface proxy);
	
}
