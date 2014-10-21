package state;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.map.MapController;

public class PlayingState extends GameState{
	public PlayingState(MapController c){
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
	
	@Override
	public boolean canBuildCity(VertexLocation location){ 
		
		return facade.canBuildCity(location);
	}
	
	@Override
	public boolean buildCity(VertexLocation location){ 
		
		return facade.buildCity(location);
	}
	
	@Override
	public boolean canPlayYearOfPlenty(ResourceType type1, ResourceType type2){ 
		
		return facade.canPlayYearOfPlenty(type1, type2); 
	}
	
	@Override
	public boolean playYearOfPlenty(ResourceType type1, ResourceType type2){ 
		
		return facade.playYearOfPlenty(type1, type2);
	}
	
	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2){ 
		
		return facade.canPlayRoadBuilding(location1, location2);
	}
	
	@Override
	public boolean playRoadBuilding(EdgeLocation location1, EdgeLocation location2){ 
		
		return facade.playRoadBuilding(location1, location2); 
	}
	
	@Override
	public boolean canPlaySoldier(HexLocation oldLocation, HexLocation newLocation, int victimIndex){ 
		
		return facade.canPlaySoldier(oldLocation, newLocation, victimIndex);
	}
	
	@Override
	public boolean playSoldier(HexLocation newLocation, int victimIndex){ 
		
		return facade.playSoldier(newLocation, victimIndex); 
	}
	
	@Override
	public boolean canPlayMonopoly(){ 
		
		return facade.canPlayMonopoly();
	}
	
	@Override
	public boolean playMonopoly(ResourceType resourceType){ 
		
		return facade.playMonopoly(resourceType);
	}
	
	@Override
	public boolean canPlayMonument(){ 
		
		return facade.canPlayMonument();
	}
	
	@Override
	public boolean playMonument(){ 
		
		return facade.playMonument();
	}
}
