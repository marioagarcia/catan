package state;

import shared.locations.HexLocation;
import client.map.MapController;
import client.model.player.RobPlayerInfo;

public class RobbingState extends GameState{

	public RobbingState(MapController c){
		super(c);
	}
	
	@Override
	public boolean canPlaceRobber(HexLocation hexLoc){ 
		
		return true;
	}
	
	@Override
	public boolean PlaceRobber(HexLocation hexLoc){
		
		return true; 
	}
	
	@Override
	public void robPlayer(RobPlayerInfo victim){
		//Our robPlayer method requires a location, but this method assumes they have chosen the person on a correct location already.
		facade.getManager().robPlayer(victim.getPlayerIndex(), null);
	}
}
