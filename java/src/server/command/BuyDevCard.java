package server.command;

import shared.serialization.parameters.BuyDevCardParameters;

/**
 * A Command object that updates a given game to reflect the purchase of a development card
 * @author Kevin
 */
public class BuyDevCard extends CatanCommand {

	/**
	 * Initializes the BuyDevCard object with the data needed to perform this action
	 * @param parameters An object containing the index of the player buying the card
	 * @param game_id The integer ID of the game the operation is to be performed on. Must be an ID for an existing game
	 */
	public BuyDevCard(BuyDevCardParameters parameters, int game_id){
		
	}
	
	/**
	 * Calls {@link server.manager.ServerModelFacade#canBuyDevCard() canBuyDevCard} to ensure the move is valid
	 * If valid, a call is them made to {@link server.manager.ServerModelFacade#buyDevCard() buyDevCard}
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
