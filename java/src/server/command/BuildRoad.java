package server.command;

import server.facade.ServerModelFacade;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.serialization.parameters.BuildRoadParameters;

/**
 * A Command object the updates the model to reflect that a player has built a road
 * @author Kevin
 */
public class BuildRoad extends CatanCommand {

	private EdgeLocation location = null;
	
	/**
	 * Initializes the BuildRoad object with all the information necessary to make changes to the model
	 * @param parameters An object containing the index of the player building the road, its location, and whether it is free 
	 * @param game_id The integer ID of the game the operation is to be performed on. Must be an ID for an existing game
	 */
	public BuildRoad(BuildRoadParameters parameters, int game_id){
		
		
		super(parameters.getPlayerIndex(), game_id);
		
		int x = parameters.getRoadLocation().getX();
		int y = parameters.getRoadLocation().getY();
		
		HexLocation hex_loc = new HexLocation(x, y);
		EdgeDirection direction = EdgeDirection.convertShorthandDirection(parameters.getRoadLocation().getDirection());
		
		location = new EdgeLocation(hex_loc, direction);
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canBuildRoad(int, int, shared.locations.EdgeLocation) canBuildRoad} to ensure the move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#buildRoad(int, int, shared.locations.EdgeLocation) buildRoad}
	 */
	@Override
	public void execute() {
		
		if (ServerModelFacade.getInstance().canBuildRoad(gameId, playerIndex, location)){
			
			success = ServerModelFacade.getInstance().buildRoad(gameId, playerIndex, location);
			
			if (success){
				
				ServerModelFacade.getInstance().persistCommand(this, "BuildRoad", gameId);
			}
		}
		
	}
}
