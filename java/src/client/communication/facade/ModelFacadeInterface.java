package client.communication.facade;

import client.model.CatanModelInterface;

/**
 * This class ensures the game's data and state are properly stored in the Catan Model
 * as well as the server. 
 */
public interface ModelFacadeInterface {
	
	/*
	 * implementations of this interface should have a constructor which takes a 
	 * Poller object as a parameter, but this constructor cannot be created within 
	 * the interface
	 */
	/**
	 * Retrieves the CatanModel state/data from the server
	 * 
	 * @param modelId The id of the model whose state data is being retrieved
	 * @return CatanModel The data of the CatanModel
	 */
	public CatanModelInterface getModelData(int modelId);
	
	/**
	 * Sets the state/data of the CatanModel on the client side
	 * 
	 * @param model The client CatanModel whose state/data is being updated
	 */
	public void setClientModelData(CatanModelInterface model);
	
	/**
	 * Sets the state/data of the CatanModel no the server side
	 * 
	 * @param model The server CatanModel whose state/data is being updated
	 */
	public void setServerModelData(CatanModelInterface model);
}
