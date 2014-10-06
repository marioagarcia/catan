package client.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import client.manager.interfaces.GMBoardMapInterface;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.serialization.interfaces.CityInterface;
import shared.serialization.interfaces.HexLocationInterface;
import shared.serialization.interfaces.MapInterface;
import shared.serialization.interfaces.PortInterface;
import shared.serialization.interfaces.RoadInterface;
import shared.serialization.interfaces.SettlementInterface;

public class BoardMap implements BoardMapInterface, GMBoardMapInterface, MapInterface {
	Map<HexLocation, HexInterface> hexes;
	
	public BoardMap(){
		this.hexes = new HashMap<HexLocation, HexInterface>();
	}

	@Override
	public HexInterface getHex(HexLocation location) throws HexNotFoundException {
		if(!this.hexes.containsKey(location))
			throw new HexNotFoundException();
		
		return this.hexes.get(location);
	}

	@Override
	public HexCornerInterface getVertex(VertexLocation location) {
		return this.hexes.get(location.getHexLoc()).getCorner(location.getDir());
	}

	@Override
	public HexBorderInterface getHexBorder(EdgeLocation location) {
		return this.hexes.get(location.getHexLoc()).getBorder(location.getDir());
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildCity(VertexLocation location, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMaritimeTrade(HexInterface location, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2, int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaySoldier(HexInterface oldLocation, HexInterface newLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMap(
			ArrayList<shared.serialization.interfaces.HexInterface> hexList,
			ArrayList<RoadInterface> roadList,
			ArrayList<CityInterface> cityList,
			ArrayList<SettlementInterface> settlementList, int radius,
			ArrayList<PortInterface> portList,
			HexLocationInterface robberLocation) {
		
		
		
		
	}
	//TODO

}
