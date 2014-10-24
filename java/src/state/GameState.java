package state;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.communication.facade.ModelFacade;
import client.map.MapController;
import client.model.player.RobPlayerInfo;

public class GameState
{
	protected ModelFacade facade = null;
	protected MapController controller = null;
	
	public GameState(MapController c){
		facade = ModelFacade.getInstance(null);
		controller = c;
	}
	
	public boolean canBuildRoad(EdgeLocation location){ return false; }
	public boolean buildRoad(EdgeLocation location){ return false; }
	
	public boolean canBuildSettlement(VertexLocation location){ return false; }
	public boolean buildSettlement(VertexLocation location){ return false; }
	
	public boolean canBuildCity(VertexLocation location){ return false; }
	public boolean buildCity(VertexLocation location){ return false; }
	
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2){ return false; }
	public boolean playRoadBuilding(EdgeLocation location1, EdgeLocation location2){ return false; }
	
	public boolean canPlaySoldier(HexLocation oldLocation, HexLocation newLocation, int victimIndex){ return false; }
	public boolean playSoldier(HexLocation newLocation, int victimIndex){ return false; }
	
	public boolean canPlayMonument(){ return false; }
	public boolean playMonument(){ return false; }
	
	public boolean canPlaceRobber(HexLocation hexLoc){ return false; }
	public boolean PlaceRobber(HexLocation hexLoc){ return false; }
	
	public void robPlayer(RobPlayerInfo victim, HexLocation hexLoc){}
}
