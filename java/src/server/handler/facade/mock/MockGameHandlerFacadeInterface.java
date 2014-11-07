package server.handler.facade.mock;

import java.util.ArrayList;

import shared.model.manager.GameData;
import shared.serialization.parameters.MasterParameterInterface;

public interface MockGameHandlerFacadeInterface {

	/**
	 * Retrieves and returns a hard coded game model
	 * 
	 * @return A hard coded copy of the game model
	 */
	public GameData getModel();
	
	/**
	 * Retrieves and returns a hard coded game model
	 * 
	 * @return A hard coded copy of the game model
	 */
	public GameData reset();
	
	/**
	 * Retrieves and returns a hard coded game model
	 * 
	 * @param commandList A list of game commands
	 * @return A hard coded copy of the game model
	 */
	public GameData postCommands(ArrayList<MasterParameterInterface> commandList);
	
	/**
	 * Retrieves and returns a hard coded list of MasterParameterInterfaces
	 * 
	 * @return A hard coded list of game commands
	 */
	public ArrayList<MasterParameterInterface> getCommands();
	
	/**
	 * Retrieves a hard coded list of AI types and checks if the passed in AI type is in the list
	 * 
	 * @param aiType The type of AI being added to the game
	 * @return True if aiType is in the hard coded list of AI types, false otherwise
	 */
	public Boolean AddAI(String aiType);
	
	/**
	 * Retrieves a hard coded list of AI types
	 * 
	 * @return An array of hard coded AI types
	 */
	public String[] getListAI();
}
