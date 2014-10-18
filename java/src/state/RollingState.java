package state;

public class RollingState extends GameState
{
	public boolean canRoll(){ 
		return true; 
	}
	
	public boolean roll(){ 
		return false;
	}
}
