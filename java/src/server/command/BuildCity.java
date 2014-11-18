package server.command;

import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.serialization.parameters.BuildCityParameters;

/**
 * A Command object that updates the game to reflect that a player has built a city
 * @author Kevin
 */
public class BuildCity extends CatanCommand {

	private VertexLocation location = null;
	/**
	 * Initializes the BuildCity object with the information it needs to make changes to the model
	 * @param parameters An object containing the player performing the move, and the location to place the new city 
	 * @param game_id The integer ID of the game the operation is to be performed on. Must be an ID for an existing game
	 */
	public BuildCity(BuildCityParameters parameters, int game_id){
		
		this.playerIndex = parameters.getPlayerIndex();
		this.gameId = game_id;
		
		int x = parameters.getVertexLocation().getX();
		int y = parameters.getVertexLocation().getY();
		
		HexLocation hex_loc = new HexLocation(x, y);
		VertexDirection direction = VertexDirection.valueOf(parameters.getVertexLocation().getDirection());
		
		this.location = new VertexLocation(hex_loc, direction);
	}
	
	/**
	 * Calls the {@link server.facade.ServerModelFacade#canBuildCity(int, int, shared.locations.VertexLocation) canBuildCity} to ensure the move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#buildCity(int, int, shared.locations.VertexLocation) buildCity}
	 */
	@Override
	public void execute() {
System.out.println("************************" + facadeInstance.canBuildCity(gameId, playerIndex, location) + "**************************");		
		if (facadeInstance.canBuildCity(gameId, playerIndex, location)){
			
			success = facadeInstance.buildCity(gameId, playerIndex, location);
		}
	}
}
