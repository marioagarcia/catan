package server.command;

import shared.locations.HexLocation;
import shared.serialization.parameters.SoldierParameters;

/**
 * A Command object that updates the state of the game to reflect a player using the soldier dev card
 * @author Kevin
 */
public class PlaySoldier extends CatanCommand {

	private HexLocation oldLocation = null;
	private HexLocation newLocation = null;
	private int victimIndex = -1;
	
	/**
	 * Initializes the PlaySoldier object with the data necessary to update the game model
	 * @param parameters An object containing the index of the player using this dev card, the new location to move the robber to, and the index of the robbing victim
	 * @param game_id The ID of the game this action will be applied to
	 */
	public PlaySoldier(SoldierParameters parameters, int game_id){
		
		this.gameId = game_id;
		this.playerIndex = parameters.getPlayerIndex();
		
		//Need a way to get the robber's current location for can method. Or assume that this will be checked in the manager/map
		//oldLocation = map.getRobberLocation()
		
		int x1 = parameters.getLocation().getX();
		int y1 = parameters.getLocation().getY();
		
		newLocation = new HexLocation(x1, y1);
		
		victimIndex = parameters.getVictimIndex();
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#canPlaySoldier(int, int, shared.locations.HexLocation, shared.locations.HexLocation, int) canPlaySoldier} 
	 * with the parameters provided to the constructor to ensure this move is valid
	 * If valid, a call is then made to {@link server.facade.ServerModelFacade#playSoldier(int, int, shared.locations.HexLocation, int) playSoldier}
	 */
	@Override
	public void execute() {
		
		if (facadeInstance.canPlaySoldier(gameId, playerIndex, oldLocation, newLocation, victimIndex)){
			
			success = facadeInstance.playSoldier(gameId, playerIndex, newLocation, victimIndex);
		}
		
	}
}
