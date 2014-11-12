package server.command;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.serialization.parameters.RoadBuildingParameters;

/**
 * A Command object that updates the state of the game to reflect a player using the road building dev card
 * @author Kevin
 *
 */
public class PlayRoadBuilding extends CatanCommand {

	private EdgeLocation location1 = null;
	private EdgeLocation location2 = null;
	
	/**
	 * Initializes the PlayRoadBuilding object with the data necessary to update the model
	 * @param parameters An object containing the index of the player using this dev card, and the two locations they are placing their roads
	 * @param game_id The ID of the game this action will be applied to
	 */
	public PlayRoadBuilding(RoadBuildingParameters parameters, int game_id){
		
		this.gameId = game_id;
		this.playerIndex = parameters.getPlayerIndex();
		
		int x1 = parameters.getSpot1().getX();
		int y1 = parameters.getSpot1().getY();
		
		HexLocation hex_loc1 = new HexLocation(x1, y1);
		EdgeDirection direction1 = EdgeDirection.valueOf(parameters.getSpot1().getDirection());
		
		location1 = new EdgeLocation(hex_loc1, direction1);
		
		int x2 = parameters.getSpot2().getX();
		int y2 = parameters.getSpot2().getY();
		
		HexLocation hex_loc2 = new HexLocation(x2, y2);
		EdgeDirection direction2 = EdgeDirection.valueOf(parameters.getSpot2().getDirection());
		
		location2 = new EdgeLocation(hex_loc2, direction2);
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canPlayRoadBuilding(int, int, shared.locations.EdgeLocation, shared.locations.EdgeLocation) canPlayRoadBuilding} 
	 * with the parameters provided to the constructor to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#playRoadBuilding(int, int, shared.locations.EdgeLocation, shared.locations.EdgeLocation) playRoadBuilding}
	 */
	@Override
	public void execute() {
		
		if (facadeInstance.canPlayRoadBuilding(gameId, playerIndex, location1, location2)){
			
			success = facadeInstance.playRoadBuilding(gameId, playerIndex, location1, location2);
		}
		
	}
}
