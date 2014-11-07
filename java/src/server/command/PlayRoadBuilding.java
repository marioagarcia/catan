package server.command;

import shared.serialization.parameters.RoadBuildingParameters;

/**
 * A Command object that updates the state of the game to reflect a player using the road building dev card
 * @author Kevin
 *
 */
public class PlayRoadBuilding extends CatanCommand {

	/**
	 * Initializes the PlayRoadBuilding object with the data necessary to update the model
	 * @param parameters An object containing the index of the player using this dev card, and the two locations they are placing their roads
	 * @param game_id The ID of the game this action will be applied to
	 */
	public PlayRoadBuilding(RoadBuildingParameters parameters, int game_id){
		
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canPlayRoadBuilding(int, int, shared.locations.EdgeLocation, shared.locations.EdgeLocation) canPlayRoadBuilding} 
	 * with the parameters provided to the constructor to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#playRoadBuilding(int, int, shared.locations.EdgeLocation, shared.locations.EdgeLocation) playRoadBuilding}
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
