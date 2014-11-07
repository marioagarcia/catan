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
	 * Ensures the move is valid. If it is valid, the road will be added to the map, and the player's resources and piece count will be updated
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
