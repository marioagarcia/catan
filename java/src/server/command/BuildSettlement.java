package server.command;

import shared.serialization.parameters.BuildSettlementParameters;

/**
 * A Command object that updates the model to reflect a new settlement being placed on the board
 * @author Kevin
 */
public class BuildSettlement extends CatanCommand {

	/**
	 * Initializes the BuildSettlement object with the data it needs to update the model
	 * @param parameters An object containing the index of the player building the settlement, the location of the new settlement, and whether it is free
	 * @param game_id The integer ID of the game the operation is to be performed on. Must be an ID for an existing game
	 */
	public BuildSettlement(BuildSettlementParameters parameters, int game_id){
		
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canBuildSettlement(shared.locations.VertexLocation) canBuildSettlement} to ensure the move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#buildSettlement(shared.locations.VertexLocation) buildSettlement}
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
