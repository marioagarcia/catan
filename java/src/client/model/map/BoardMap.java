package client.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import client.manager.interfaces.GMBoardMapInterface;
import client.model.LookupTables.EdgesAdjacentToVertex;
import client.model.LookupTables.EdgesAdjacentToVertexResult;
import client.model.LookupTables.VertexesAdjacentToEdge;
import client.model.piece.City;
import client.model.piece.Road;
import client.model.piece.Settlement;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.serialization.interfaces.SerializerMapInterface;

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
	
	public boolean isValid(EdgeLocation location){
		location = location.getNormalizedLocation();
		for(HexInterface hex : this.hexes.values()){
			if(location.equals(new EdgeLocation(hex.getLocation(), EdgeDirection.North).getNormalizedLocation()) ||
					location.equals(new EdgeLocation(hex.getLocation(), EdgeDirection.NorthEast).getNormalizedLocation()) ||
					location.equals(new EdgeLocation(hex.getLocation(), EdgeDirection.NorthWest).getNormalizedLocation()) || 
					location.equals(new EdgeLocation(hex.getLocation(),     EdgeDirection.South).getNormalizedLocation()) ||
					location.equals(new EdgeLocation(hex.getLocation(), EdgeDirection.SouthEast).getNormalizedLocation()) ||
					location.equals(new EdgeLocation(hex.getLocation(), EdgeDirection.SouthWest).getNormalizedLocation())){
				return true;
			}
				
		}
		return false;
	}

	@Override
	public HexInterface getHex(HexLocation location) throws HexNotFoundException {
		if(!this.hexes.containsKey(location))
			throw new HexNotFoundException();
		
		return this.hexes.get(location);
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location, int playerIndex) {
		location = location.getNormalizedLocation();
		if(roads.containsKey(location)){
			return false;
		}
		
		VertexDirection[] adjacentVertexes = VertexDirection.getAdjacent(location.getDir());
		
		boolean canBuildClockwisePrevious = true;
		boolean canBuildClockwiseNext = true;

		//check if other players have cities/settlements which interrupt your road
		if(this.cities.containsKey(adjacentVertexes[0]) && this.cities.get(adjacentVertexes[0]).getPlayerIndex() != playerIndex)
			canBuildClockwisePrevious = false;
		else if(this.settlements.containsKey(adjacentVertexes[0]) && this.settlements.get(adjacentVertexes[0]).getPlayerIndex() != playerIndex)
			canBuildClockwisePrevious = false;
		
		if(this.cities.containsKey(adjacentVertexes[1]) && this.cities.get(adjacentVertexes[1]).getPlayerIndex() != playerIndex)
			canBuildClockwiseNext = false;
		else if(this.settlements.containsKey(adjacentVertexes[1]) && this.settlements.get(adjacentVertexes[1]).getPlayerIndex() != playerIndex)
			canBuildClockwiseNext = false;
		
		
		EdgeLocation[] adjacentLocations = location.getAdjacent(canBuildClockwisePrevious, canBuildClockwiseNext);

		//test if the player owns an adjacent road
		boolean ownsAdjacentRoad = false;
		for(EdgeLocation individualLocation : adjacentLocations)
			if(roads.containsKey(individualLocation) && roads.get(individualLocation).getPlayerIndex() == playerIndex){
				ownsAdjacentRoad = true;
				break;
			}
		return ownsAdjacentRoad;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location, int playerIndex, boolean setupPhase) {
		location = location.getNormalizedLocation();
		//get the edges adjacent to the requested VertexLocation
		EdgesAdjacentToVertexResult edges = EdgesAdjacentToVertex.findEdgesAdjacentToVertex(location, this);
		
		//get all of the Vertexes around the VertexLocation
		Set<VertexLocation> pertinentVertexes = new HashSet<VertexLocation>();

		//create a Set of all the neighboring vertexes
		pertinentVertexes.add(location);
		if(edges.getExterior(true) != null)
			pertinentVertexes.addAll(VertexesAdjacentToEdge.get(edges.getExterior(true)).asSet());
		pertinentVertexes.addAll(VertexesAdjacentToEdge.get(edges.getInteriorClockwisePreceeding(true)).asSet());
		pertinentVertexes.addAll(VertexesAdjacentToEdge.get(edges.getInteriorClockwiseSucceeding(true)).asSet());
		
		//check if any of the neighboring locations already have a building on them
		for(VertexLocation individualPertinentVertex : pertinentVertexes)
			if(this.cities.containsKey(individualPertinentVertex) || this.settlements.containsKey(individualPertinentVertex))
				return false;
		//check if you have a road on an adjacent edge
		if(!setupPhase)
			for(EdgeLocation individualPertinentEdge : edges.asSet()){
				if(this.roads.containsKey(individualPertinentEdge) && this.roads.get(individualPertinentEdge).getPlayerIndex() == playerIndex )
					return true;
			}
		
		return false;
	}

	@Override
	public boolean canBuildCity(VertexLocation location, int playerIndex) {
		location = location.getNormalizedLocation();
		//get the edges adjacent to the requested VertexLocation
		EdgesAdjacentToVertexResult edges = EdgesAdjacentToVertex.findEdgesAdjacentToVertex(location, this);
		
		//get all of the Vertexes around the VertexLocation
		Set<VertexLocation> pertinentVertexes = new HashSet<VertexLocation>();

		//create a Set of all the neighboring vertexes
		if(edges.getExterior(true) != null)
			pertinentVertexes.addAll(VertexesAdjacentToEdge.get(edges.getExterior(true)).asSet());
		pertinentVertexes.addAll(VertexesAdjacentToEdge.get(edges.getInteriorClockwisePreceeding(true)).asSet());
		pertinentVertexes.addAll(VertexesAdjacentToEdge.get(edges.getInteriorClockwiseSucceeding(true)).asSet());
		pertinentVertexes.remove(location);
		
		//check if any of the neighboring locations already have a building on them
		for(VertexLocation individualPertinentVertex : pertinentVertexes)
			if(this.cities.containsKey(individualPertinentVertex) || this.settlements.containsKey(individualPertinentVertex))
				return false;
		
		boolean existsAdjacentRoad = false;
		//check if you have a road on an adjacent edge
		for(EdgeLocation individualPertinentEdge : edges.asSet())
			if(this.roads.containsKey(individualPertinentEdge) && this.roads.get(individualPertinentEdge).getPlayerIndex() == playerIndex )
				existsAdjacentRoad = true;
		
		if(!existsAdjacentRoad){
			return false;
		}
		
		return this.settlements.containsKey(location) && this.settlements.get(location).getPlayerIndex() == playerIndex;
	}

	@Override
	public boolean canMaritimeTrade(VertexLocation location, int playerIndex) {
		location = location.getNormalizedLocation();
		
		boolean buildingValid = false;
		if(this.settlements.containsKey(location) && this.settlements.get(location).getPlayerIndex() == playerIndex)
			buildingValid = true;
		if(this.cities.containsKey(location) && this.cities.get(location).getPlayerIndex() == playerIndex)
			buildingValid = true;
		
		if(!buildingValid)
			return false;
		
		for(EdgeLocation potentialPort : EdgesAdjacentToVertex.findEdgesAdjacentToVertex(location, this).asSet()){
			for(EdgeLocation port : this.ports.keySet()){
				if(potentialPort.equals(port))
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2, int playerIndex) {
		location1 = location1.getNormalizedLocation();
		location2 = location2.getNormalizedLocation();
		if(this.canBuildRoad(location1, playerIndex)){
			Road road = new Road(playerIndex, location1);
			this.roads.put(location1, road);
			if(this.canBuildRoad(location2,  playerIndex)){
				roads.remove(location1);
				return true;
			}
			else{
				roads.remove(location1);
			}
		}
		if(this.canBuildRoad(location2, playerIndex)){
			Road road = new Road(playerIndex, location2);
			this.roads.put(location2, road);
			if(this.canBuildRoad(location1,  playerIndex)){
				roads.remove(location2);
				return true;
			}
			else{
				roads.remove(location2);
			}
		}
		return false;
	}

	@Override
	public boolean canPlaySoldier(HexLocation oldLocation, HexLocation newLocation, int targetPlayerIndex) {
		if(oldLocation.equals(newLocation)){
			return false;
		}
		return true;
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
			
			Road road = roadList.get(i);
			road = new Road(road.getPlayerIndex(), road.getLocation().getNormalizedLocation());

			this.roads.put(road.getLocation(), road);
		}
		
		//cities
		for(int i = 0; i < cityList.size(); i++){
			cityList.get(i).setLocation(cityList.get(i).getLocation().getNormalizedLocation());
			this.cities.put(cityList.get(i).getLocation(), cityList.get(i));
		}
		
		//settlements
		for(int i = 0; i < settlementList.size(); i++){
			settlementList.get(i).setLocation(settlementList.get(i).getLocation().getNormalizedLocation());
			this.settlements.put(settlementList.get(i).getLocation(), settlementList.get(i));
		}
		
		//ports
		for(int i = 0; i < portList.size(); i++){
			portList.get(i).setLocation(portList.get(i).getLocation().getNormalizedLocation());
			this.ports.put(portList.get(i).getLocation(), portList.get(i));
		}
		
		
		this.radius = radius;
		this.robberLocation = robberLocation;
	}
	
	public int getRadius() {
		return this.radius;
	}
	
	public HexLocation getRobberLocation() {
		return this.robberLocation;
	}

}
