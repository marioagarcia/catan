package server.command.facade;

import java.util.ArrayList;

import shared.model.manager.GameData;
import shared.serialization.parameters.MasterParameterInterface;

public interface GameCommandFacadeInterface {
	
	/**
	 * Creates a ModelCommunicator object and calls getGameModel() on it
	 * 
	 * @return A copy of the game model
	 */
	public GameData getModel(int gameId);
	
	/**
	 * Creates a ResetGame command object and calls execute on it
	 * 
	 * @return A copy of the game model after it has been reset
	 */
	public GameData reset(int gameId);
	
	/**
	 * Creates a CatanCommand command object and calls execute on it
	 * 
	 * @param commandList A list of game commands
	 * @return A copy of the game model after the commands have been posted
	 */
	public GameData postCommands(ArrayList<MasterParameterInterface> commandList);
	
	/**
	 * Creates a ModelCommunicator object and calls getGameCommands() on it
	 * 
	 * @return A list of game commands
	 */
	public ArrayList<MasterParameterInterface> getCommands();
	
	/**
	 * Creates a PostNewAI command object and calls execute on it
	 * 
	 * @param aiType The type of AI being added to the game
	 * @return Whether or not the AI was successfully added; true if successful, false otherwise
	 */
	public Boolean AddAI(String aiType);
	
	/**
	 * Creates a ModelCommunicator object and calls getAIList() on it
	 * 
	 * @return An array of types of AI players
	 */
	public String[] getListAI();
	
}
