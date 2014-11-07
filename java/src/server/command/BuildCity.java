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
	 * If the move is valid, places a city on the map and updates the player's resources, piece count, and victory points.
	 * After this method is called, the player will have 3 less wheat, 2 less ore, 1 more settlement, 1 less city, and 1 more victory point.
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub		
	}
}
