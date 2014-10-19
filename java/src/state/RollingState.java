package state;

import client.map.MapController;

public class RollingState extends GameState{
	public RollingState(MapController c){
		super(c);
	}

	public boolean canRoll(){ 
		return true; 
	}
	
	public boolean roll(){ 
		return false;
	}
}
