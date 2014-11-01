package state;

import shared.locations.HexLocation;
import client.map.MapController;
import client.model.player.RobPlayerInfo;

public class RobbingState extends GameState{

	public RobbingState(MapController c){
		super(c);
	}
	
	@Override
	public void robPlayer(RobPlayerInfo victim, HexLocation hexLoc){
		//Our robPlayer method requires a location, but this method assumes they have chosen the person on a correct location already.
		if (victim != null){
			facade.getManager().robPlayer(victim.getPlayerIndex(), hexLoc);
		}
		else{
			facade.getManager().robPlayer(-1,  hexLoc);
		}
	}
}
