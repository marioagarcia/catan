package server.command;

import server.persistence.CommandDTO;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.serialization.parameters.BuildSettlementParameters;

/**
 * A Command object that updates the model to reflect a new settlement being placed on the board
 * @author Kevin
 */
public class BuildSettlement extends CatanCommand {

	private VertexLocation location = null;
	
	/**
	 * Initializes the BuildSettlement object with the data it needs to update the model
	 * @param parameters An object containing the index of the player building the settlement, the location of the new settlement, and whether it is free
	 * @param game_id The integer ID of the game the operation is to be performed on. Must be an ID for an existing game
	 */
	public BuildSettlement(BuildSettlementParameters parameters, int game_id){	
		
		this.playerIndex = parameters.getPlayerIndex();
		this.gameId = game_id;	
		
		int x = parameters.getVertexLocation().getX();
		int y = parameters.getVertexLocation().getY();

		HexLocation hex_loc = new HexLocation(x, y);

		VertexDirection direction = VertexDirection.convertShorthandDirection(parameters.getVertexLocation().getDirection());
		
		this.location = new VertexLocation(hex_loc, direction);
		
		this.dto = new CommandDTO(parameters, "BuildSettmentParameters", game_id);

	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canBuildSettlement(int, int, shared.locations.VertexLocation) canBuildSettlement} to ensure the move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#buildSettlement(int, int, shared.locations.VertexLocation) buildSettlement}
	 */
	@Override
	public void execute() {

		if (facadeInstance.canBuildSettlement(gameId, playerIndex, location)){
		
			success = facadeInstance.buildSettlement(gameId, playerIndex, location);

		}
		
	}
}
