package state;

import client.map.MapController;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class FirstRoundState extends GameState{
	
	private boolean playedSettlement;
	private boolean playedRoad;
	
	public FirstRoundState(MapController c){
		super(c);
		
		playedSettlement = false;
		playedRoad = false;
	}
	
	@Override
	public boolean canBuildRoad(EdgeLocation location){ 
		
		return (facade.canBuildRoad(location) && !playedRoad);
	}
	
	@Override
	public boolean buildRoad(EdgeLocation location){
		
		playedRoad = facade.buildRoad(location);
		
		//After placing road in setup, your turn should end automatically
		if (playedRoad){
			facade.finishTurn();
		}
		return playedRoad;
	}
	
	@Override
	public boolean canBuildSettlement(VertexLocation location){ 
		
		return (facade.canBuildSettlement(location) && !playedSettlement); 
	}
	
	@Override
	public boolean buildSettlement(VertexLocation location){ 
		
		playedSettlement = facade.buildSettlement(location);
		return playedSettlement;
	}

}
