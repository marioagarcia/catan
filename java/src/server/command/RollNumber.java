package server.command;

import server.facade.ServerModelFacade;
import shared.serialization.parameters.RollNumberParameters;

/**
 * A Command object that updates the model to reflect a player rolling the dice
 * @author Kevin
 *
 */
public class RollNumber extends CatanCommand {

	private int number = -1;
	/**
	 * Initializes the RollNumber object with the data necessary to update the game model
	 * @param parameters An object containing the index of the player who rolled, and the number the rolled
	 * @param game_id The ID of the game this action should be applied to
	 */
	public RollNumber(RollNumberParameters parameters, int game_id){
		
		super(parameters.getPlayerIndex(), game_id);
		
		this.number = parameters.getNumber();
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canRoll(int, int) canRoll} with the parameters provided to the constructor to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#roll(int, int, int) roll}
	 */
	@Override
	public void execute() {
		
		if (ServerModelFacade.getInstance().canRoll(gameId, playerIndex)){
			
			success = ServerModelFacade.getInstance().roll(gameId, playerIndex, number);
			
			if (success){
				
				ServerModelFacade.getInstance().persistCommand(this, "RollNumber", gameId);
			}
		}
		
	}
}
