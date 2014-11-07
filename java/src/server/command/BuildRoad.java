package server.command;

import shared.serialization.parameters.BuildRoadParameters;

/**
 * A Command object the updates the model to reflect that a player has built a road
 * @author Kevin
 */
public class BuildRoad extends CatanCommand {

	/**
	 * Initializes the BuildRoad object with all the information necessary to make changes to the model
	 * @param parameters An object containing the index of the player building the road, its location, and whether it is free 
	 * @param game_id The integer ID of the game the operation is to be performed on. Must be an ID for an existing game
	 */
	public BuildRoad(BuildRoadParameters parameters, int game_id){
		
	}
	
	/**
	 * Calls {@link server.manager.ServerModelFacade#canBuildRoad(shared.locations.EdgeLocation) canBuildRoad} to ensure the move is valid
	 * If valid, a call is then made to {@link server.manager.ServerModelFacade#buildRoad(shared.locations.EdgeLocation) buildRoad}
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
