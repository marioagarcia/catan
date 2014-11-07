package server.command;

import shared.serialization.parameters.BuildCityParameters;

/**
 * A Command object that updates the game to reflect that a player has built a city
 * @author Kevin
 */
public class BuildCity extends CatanCommand {

	/**
	 * Initializes the BuildCity object with the information it needs to make changes to the model
	 * @param parameters An object containing the player performing the move, and the location to place the new city 
	 * @param game_id The integer ID of the game the operation is to be performed on. Must be an ID for an existing game
	 */
	public BuildCity(BuildCityParameters parameters, int game_id){
		
	}
	
	/**
	 * Calls the {@link server.facade.ServerModelFacade#canBuildCity(int, int, shared.locations.VertexLocation) canBuildCity} to ensure the move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#buildCity(int, int, shared.locations.VertexLocation) buildCity}
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub		
	}
}
