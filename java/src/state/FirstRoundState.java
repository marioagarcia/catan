package state;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class FirstRoundState extends GameState
{
	private boolean roadPlaced = false;
	private boolean settlementPlaced = false;
	
	@Override
	public boolean canBuildRoad(EdgeLocation location){ 
		return facade.canBuildRoad(location);
	}
	
	@Override
	public boolean buildRoad(EdgeLocation location){
		if (facade.buildRoad(location)){
			roadPlaced = true;
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean canBuildSettlement(VertexLocation location){ 
		return facade.canBuildSettlement(location); 
	}
	
	@Override
	public boolean buildSettlement(VertexLocation location){ 
		if (facade.buildSettlement(location)){
			settlementPlaced = true;
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean canFinishTurn(){ 
		
		//Specific States will only exist if it is the local Player's turn. After the player finishes their turn,
		//set the controller state back to default where everything returns false (possible problem with discard cards)
		return (roadPlaced && settlementPlaced);
	}
	
	@Override
	public boolean finishTurn(){ 
		if (facade.finishTurn()){
			roadPlaced = false;
			settlementPlaced = false;
			
			controller.setGameState(new GameState());
			
			return true;
		}
		else{
			return false;
		}
	}

}
