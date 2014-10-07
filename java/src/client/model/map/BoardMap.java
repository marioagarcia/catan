package client.model.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import client.manager.interfaces.GMBoardMapInterface;
import client.model.piece.City;
import client.model.piece.Road;
import client.model.piece.Settlement;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.serialization.interfaces.SerializerCityInterface;
import shared.serialization.interfaces.SerializerHexInterface;
import shared.serialization.interfaces.SerializerMapInterface;
import shared.serialization.interfaces.SerializerPortInterface;
import shared.serialization.interfaces.SerializerRoadInterface;
import shared.serialization.interfaces.SerializerSettlementInterface;

public class BoardMap implements BoardMapInterface, GMBoardMapInterface, SerializerMapInterface {
	private Map<HexLocation, HexInterface> hexes;
	private Map<EdgeLocation, Road> roads;
	private Map<VertexLocation, City> cities;
	private Map<VertexLocation, Settlement> settlements;
	private Map<EdgeLocation, Port> ports;
	
	private int radius;
	private HexLocation robberLocation;
	
	public BoardMap(){
		this.hexes = new HashMap<HexLocation, HexInterface>();
		this.roads = new HashMap<EdgeLocation, Road>();
		this.cities = new HashMap<VertexLocation, City> ();
		this.settlements = new HashMap<VertexLocation, Settlement> ();
		this.ports = new HashMap<EdgeLocation, Port> ();
	}

	@Override
	public HexInterface getHex(HexLocation location) throws HexNotFoundException {
		if(!this.hexes.containsKey(location))
			throw new HexNotFoundException();
		
		return this.hexes.get(location);
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location, int playerIndex) {
		if(roads.containsKey(location)){
			return false;
		}
		
		VertexDirection[] adjacentVertexes = VertexDirection.getAdjacent(location.getDir());
		
		boolean canBuildClockwisePrevious = true;
		boolean canBuildClockwiseNext = true;

		if(this.cities.containsKey(adjacentVertexes[0]) && this.cities.get(adjacentVertexes[0]).getPlayerIndex() != playerIndex)
			canBuildClockwisePrevious = false;
		else if(this.settlements.containsKey(adjacentVertexes[0]) && this.settlements.get(adjacentVertexes[0]).getPlayerIndex() != playerIndex)
			canBuildClockwisePrevious = false;
		
		if(this.cities.containsKey(adjacentVertexes[1]) && this.cities.get(adjacentVertexes[1]).getPlayerIndex() != playerIndex)
			canBuildClockwiseNext = false;
		else if(this.settlements.containsKey(adjacentVertexes[1]) && this.settlements.get(adjacentVertexes[1]).getPlayerIndex() != playerIndex)
			canBuildClockwiseNext = false;
		
		EdgeLocation[] adjacentLocations = location.getAdjacent(canBuildClockwisePrevious, canBuildClockwiseNext);
		
		boolean ownsAdjacentRoad = false;
		for(EdgeLocation individualLocation : adjacentLocations)
			if(roads.containsKey(individualLocation) && roads.get(individualLocation).getPlayerIndex() == playerIndex)
				ownsAdjacentRoad = true;
		
		return ownsAdjacentRoad;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location, int playerIndex) {
		
		//if there is already a building there
		if(this.cities.containsKey(location) || this.settlements.containsKey(location))
			return false;
		
		EdgeLocation[] potentialRoads = EdgeLocation.getAdjacent(location);
		
		for(EdgeLocation individualPotentialRoad : potentialRoads)
			if(this.roads.containsKey(individualPotentialRoad) && this.roads.get(individualPotentialRoad).getPlayerIndex() == playerIndex)
				return true;
		
		return false;
	}

	@Override
	public boolean canBuildCity(VertexLocation location, int playerIndex) {
		if(this.settlements.containsKey(location) && this.settlements.get(location).getPlayerIndex() == playerIndex)
			return true;
		return false;
	}

	@Override
	public boolean canMaritimeTrade(VertexLocation location, int player_index) {
		for(EdgeLocation port : this.ports.keySet()){
			EdgeLocation[] potentialPorts = EdgeLocation.getAdjacent(location);
			for(EdgeLocation potentialPort : potentialPorts)
				if(potentialPort == port)
					return true;
		}
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
	public void setMap(ArrayList<Hex> hexList, ArrayList<Road> roadList, ArrayList<City> cityList, 
			ArrayList<Settlement> settlementList, int radius,  ArrayList<Port> portList, HexLocation robberLocation) {
		
		//hexes
		for(int i = 0; i < hexList.size(); i++){
			this.hexes.put(hexList.get(i).getLocation(), hexList.get(i));
		}
		
		//roads
		for(int i = 0; i < roadList.size(); i++){
			this.roads.put(roadList.get(i).getLocation(), roadList.get(i));
		}
		
		//cities
		for(int i = 0; i < cityList.size(); i++){
			this.cities.put(cityList.get(i).getLocation(), cityList.get(i));
		}
		
		//settlements
		for(int i = 0; i < settlementList.size(); i++){
			this.settlements.put(settlementList.get(i).getLocation(), settlementList.get(i));
		}
		
		//ports
		for(int i = 0; i < portList.size(); i++){
			this.ports.put(portList.get(i).getLocation(), portList.get(i));
		}
		
		
		this.radius = radius;
		this.robberLocation = robberLocation;
	}

}
