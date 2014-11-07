package server.command;

import shared.serialization.parameters.DiscardCardsParameters;

/**
 * A Command object that updates a given game to reflect that a player has discarded cards
 * @author Kevin
 */
public class DiscardCards extends CatanCommand {

	/**
	 * Initializes the DiscardCards object with the data needed to update the model 
	 * @param parameters An object containing the index of the player discarding, and the list of cards to get remove from the player's resources
	 * @param game_id The integer ID of the game this action is to be performed on. Must be an existing game
	 */
	public DiscardCards(DiscardCardsParameters parameters, int game_id){
		
	}
	
	/**
	 * Calls {@link server.manager.ServerModelFacade#canDiscardCards(shared.model.card.ResourceList) canDiscardCards} with the parameters provided to the constructor
	 * to ensure this move is valid
	 * If valid, a call is then made to {@link server.manager.ServerModelFacade#discardCards(shared.model.card.ResourceList) discardCards}
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
