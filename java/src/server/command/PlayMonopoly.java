package server.command;

import server.facade.ServerModelFacade;
import shared.definitions.ResourceType;
import shared.serialization.parameters.MonopolyParameters;

/**
 * A Command object that updates the state of the game to reflect that a player has played the Monopoly dev card
 * @author Kevin
 *
 */
public class PlayMonopoly extends CatanCommand {

	private String resourceString = null;
	/**
	 * Initializes the PlayMonopoly object with the data necessary to change the state of the game to reflect a player using the monopoly card
	 * @param parameters An object containing the player index of the player using the card, and the resource to be taken
	 * @param game_id The integer ID of the game this action will be applied to
	 */
	public PlayMonopoly(MonopolyParameters parameters, int game_id){
		
		super(parameters.getPlayerIndex(), game_id);
		
		resourceString = parameters.getResource();
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canPlayMonopoly(int, int) canPlayMonopoly} with the parameters provided to the constructor
	 * to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#playMonopoly(int, int, shared.definitions.ResourceType) playMonopoly}
	 */
	@Override
	public void execute() {
		
		if (ServerModelFacade.getInstance().canPlayMonopoly(gameId, playerIndex)){
			
			success = ServerModelFacade.getInstance().playMonopoly(gameId, playerIndex, ResourceType.valueOf(resourceString.toUpperCase()));
			
			if (success){
				
				ServerModelFacade.getInstance().persistCommand(this, "PlayMonopoly", gameId);
			}
		}
		
	}
}
