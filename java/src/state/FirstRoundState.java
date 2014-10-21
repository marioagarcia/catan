package state;

import client.map.MapController;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class FirstRoundState extends GameState{
	
	public FirstRoundState(MapController c){
		super(c);
	}
	
	@Override
	public boolean canBuildRoad(EdgeLocation location){ 
		
		return facade.canBuildRoad(location);
	}
	
	@Override
	public boolean buildRoad(EdgeLocation location){
		
		return facade.buildRoad(location);
	}
	
	@Override
	public boolean canBuildSettlement(VertexLocation location){ 
		
		return facade.canBuildSettlement(location); 
	}
	
	@Override
	public boolean buildSettlement(VertexLocation location){ 
		
		return facade.buildSettlement(location);
	}

}
