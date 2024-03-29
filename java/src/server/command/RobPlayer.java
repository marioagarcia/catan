package server.command;

import server.facade.ServerModelFacade;
import shared.locations.HexLocation;
import shared.serialization.parameters.RobPlayerParameters;

/**
 * A Command object that updates the game model to reflect a player being robbed
 * @author Kevin
 */
public class RobPlayer extends CatanCommand {
	private int victimIndex;
	private HexLocation location;
	/**
	 * Initializes the RobPlayer object with the data necessary to update the game model
	 * @param parameters An object containing the index of the player moving the robber, the new location of the robber, and the index of the victim
	 * @param game_id The ID of the game this action will be applied to
	 */
	public RobPlayer(RobPlayerParameters parameters, int game_id){
		
		super(parameters.getPlayerIndex(), game_id);
		
		this.victimIndex = parameters.getVictimIndex();
		this.location = parameters.getLocation();
	}
	
	/**
	 * Updates the location of the robber, and steals one resource from the victim and gives it to the player who moved the robber
	 */
	@Override
	public void execute() {
		
		success = ServerModelFacade.getInstance().robPlayer(playerIndex, gameId, victimIndex, location);
		
		if (success){
			
			ServerModelFacade.getInstance().persistCommand(this, "RobPlayer", gameId);
		}
	}
}
