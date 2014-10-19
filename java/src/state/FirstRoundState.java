package state;

import client.map.MapController;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class FirstRoundState extends GameState{
	
	public FirstRoundState(MapController c){
		super(c);
	}

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
			
			//In the three seconds it takes for the poller to update the model, we don't want the player to be able to take extra actions after they finish their turn
			controller.setGameState(new GameState(controller));
			
			return true;
		}
		else{
			return false;
		}
	}

}
