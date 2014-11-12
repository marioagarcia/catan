package server.command;

import shared.definitions.ResourceType;
import shared.serialization.parameters.YearOfPlentyParameters;

/**
 * A Command object that updates the game to reflect a player using the year of plenty dev card
 * @author Kevin
 */
public class PlayYearOfPlenty extends CatanCommand {

	private String resourceString1 = null;
	private String resourceString2 = null;
	/**
	 * Initializes the PleyYearOfPlenty with the data needed to update the game model
	 * @param parameters An object containing the index of the player using the card, and the two resources their will receive
	 * @param game_id The ID of the game this action should be applied to
	 */
	public PlayYearOfPlenty(YearOfPlentyParameters parameters, int game_id){
		
		this.gameId = game_id;
		this.playerIndex = parameters.getPlayerIndex();
		
		this.resourceString1 = parameters.getResource1();
		this.resourceString2 = parameters.getResource2();
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canPlayYearOfPlenty(int, int, shared.definitions.ResourceType, shared.definitions.ResourceType) canPlayYearOfPlenty} 
	 * with the parameters provided to the constructor to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#playYearOfPlenty(int, int, shared.definitions.ResourceType, shared.definitions.ResourceType) playYearOfPlenty}
	 */
	@Override
	public void execute() {
		
		if (facadeInstance.canPlayYearOfPlenty(gameId, playerIndex, ResourceType.valueOf(resourceString1), ResourceType.valueOf(resourceString2))){
			
			success = facadeInstance.playYearOfPlenty(gameId, playerIndex, ResourceType.valueOf(resourceString1), ResourceType.valueOf(resourceString2));
		}
		
	}
}
