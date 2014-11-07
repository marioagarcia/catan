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
	 * Builds a settlement if allowed, given the state of the game and the provided location. After this method is called, the player will
	 * have 1 less settlement, 1 less wheat, sheep, wood, and brick, and 1 more victory point
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
