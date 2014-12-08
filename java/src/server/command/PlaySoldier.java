package server.command;

import server.facade.ServerModelFacade;
import shared.locations.HexLocation;
import shared.serialization.parameters.SoldierParameters;

/**
 * A Command object that updates the state of the game to reflect a player using the soldier dev card
 * @author Kevin
 */
public class PlaySoldier extends CatanCommand {

	private HexLocation newLocation = null;
	private int victimIndex = -1;
	
	/**
	 * Initializes the PlaySoldier object with the data necessary to update the game model
	 * @param parameters An object containing the index of the player using this dev card, the new location to move the robber to, and the index of the robbing victim
	 * @param game_id The ID of the game this action will be applied to
	 */
	public PlaySoldier(SoldierParameters parameters, int game_id){
		
		super(parameters.getPlayerIndex(), game_id);
		
		int x1 = parameters.getLocation().getX();
		int y1 = parameters.getLocation().getY();
		
		newLocation = new HexLocation(x1, y1);
		
		victimIndex = parameters.getVictimIndex();
	}
	
	/**
	 * with the parameters provided to the constructor to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#playSoldier(int, int, shared.locations.HexLocation, int) playSoldier}
	 */
	@Override
	public void execute() {
		
		if (ServerModelFacade.getInstance().canPlaySoldier(gameId, playerIndex, newLocation, victimIndex)){
			try{		
				success = ServerModelFacade.getInstance().playSoldier(gameId, playerIndex, newLocation, victimIndex);
				
				if (success){
					
					//ServerModelFacade.getInstance().persistCommand(this, "PlaySoldier", gameId);
				}
		
			}
			catch(Exception e){
				System.out.println("Second Try/Catch Statement");
				e.printStackTrace();
			}
		}
	}
}
