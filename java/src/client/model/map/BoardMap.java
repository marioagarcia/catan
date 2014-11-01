package client.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import client.manager.interfaces.GMBoardMapInterface;
import client.model.map.luts.EdgesAdjacentToVertex;
import client.model.map.luts.EdgesAdjacentToVertexResult;
import client.model.map.luts.VertexesAdjacentToEdge;
import client.model.piece.City;
import client.model.piece.Road;
import client.model.piece.Settlement;
import client.model.player.Player;
import client.model.turntracker.TurntrackerInterface.Status;
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
	
	public void setHexes(Map<HexLocation, HexInterface> hexes) {
		this.hexes = hexes;
	}

	public void setRoads(Map<EdgeLocation, Road> roads) {
		this.roads = roads;
	}

	public void setCities(Map<VertexLocation, City> cities) {
		this.cities = cities;
	}

	public void setSettlements(Map<VertexLocation, Settlement> settlements) {
		this.settlements = settlements;
	}

	public void setPorts(Map<EdgeLocation, Port> ports) {
		this.ports = ports;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setRobberLocation(HexLocation robberLocation) {
		this.robberLocation = robberLocation;
	}

	public Map<HexLocation, HexInterface> getHexes(){
		return hexes;
	}
	
	public Map<EdgeLocation, Road> getRoads(){
		return roads;
	}
	
	public Map<VertexLocation, City> getCities(){
		return cities;
	}
	
	public Map<VertexLocation, Settlement> getSettlements(){
		return settlements;
	}
	
	public Map<EdgeLocation, Port> getPorts(){
		return ports;
	}
	
	public boolean isValid(EdgeLocation location){
		location = location.getNormalizedLocation();
		for(HexInterface hex : this.hexes.values()){
			for(EdgeDirection direction : EdgeDirection.values()){
				EdgeLocation test_location = new EdgeLocation(hex.getLocation(), direction);
				if(test_location.getNormalizedLocation().equals(location)){
					return true;
				}
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
	public boolean canBuildRoad(EdgeLocation location, int playerIndex, Status status) {

		if(status == Status.FIRST_ROUND){
			if(this.getNumberOfSettlementsByPlayerIndex(playerIndex) != 1){
				return false;
			}
		} else if(status == Status.SECOND_ROUND){
			if(this.getNumberOfSettlementsByPlayerIndex(playerIndex) != 2){
				return false;
			}
		}
		
		if(status == Status.SECOND_ROUND || status == Status.FIRST_ROUND){
			boolean isNextToSettlement = false;
			
			Set<VertexLocation> vertexes = VertexesAdjacentToEdge.get(location).asSet();
			
			for(VertexLocation vertexLocation : vertexes){
				for(Settlement settlement : this.settlements.values()){
					if(settlement.getLocation().getNormalizedLocation().equals(vertexLocation.getNormalizedLocation())){
						if(settlement.getPlayerIndex() != playerIndex){
							return false;
						}
						
						Set<EdgeLocation> edgeLocations = EdgesAdjacentToVertex.findEdgesAdjacentToVertex(vertexLocation, this).asSet();
						
						for(EdgeLocation potentialLocation : edgeLocations){
							for(EdgeLocation existingRoad : this.roads.keySet()){
								if(existingRoad.getNormalizedLocation().equals(potentialLocation.getNormalizedLocation())){
									return false;
								}
							}
						}
						isNextToSettlement = true;
					}
				}
				
			}
			if(!isNextToSettlement){
				return false;
			}
		}
		
		location = location.getNormalizedLocation();
		if(roads.containsKey(location)){
			return false;
		}
		
		boolean location_exists = false;
		
		for(HexInterface hex : this.hexes.values()){
			for(EdgeDirection direction : EdgeDirection.values()){
				EdgeLocation test_location = new EdgeLocation(hex.getLocation(), direction).getNormalizedLocation();
				
				if(test_location.equals(location)){
					location_exists = true;
					break;
				}
			}
			if(location_exists){
				break;
			}
		}
		
		if(!location_exists){
			return false;
		}

		
		VertexDirection[] adjacentVertexes = VertexDirection.getAdjacent(location.getDir());
		
		for(VertexDirection vertex_direction : adjacentVertexes){
			VertexLocation vertex_location = new VertexLocation(location.getHexLoc(), vertex_direction).getNormalizedLocation();
			
			if(this.cities.containsKey(vertex_location) && this.cities.get(vertex_location).getPlayerIndex() == playerIndex ||
			   this.settlements.containsKey(vertex_location) && this.settlements.get(vertex_location).getPlayerIndex() == playerIndex){
				return true;
			}
		}
		
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
		
		EdgeLocation[] adjacentLocations = location.getAdjacent(canBuildClockwisePrevious, canBuildClockwiseNext, this);

		//test if the player owns an adjacent road
		boolean ownsAdjacentRoad = false;
		for(EdgeLocation individualLocation : adjacentLocations){
			if(roads.containsKey(individualLocation) && roads.get(individualLocation).getPlayerIndex() == playerIndex){
				ownsAdjacentRoad = true;
				break;
			}
		}
		return ownsAdjacentRoad;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location, int playerIndex, boolean setupPhase) {
		location = location.getNormalizedLocation();
		
		boolean location_exists = false;
		
		for(HexInterface hex : this.hexes.values()){
			for(VertexDirection direction : VertexDirection.values()){
				VertexLocation test_location = new VertexLocation(hex.getLocation(), direction).getNormalizedLocation();
				
				if(test_location.equals(location)){
					location_exists = true;
					break;
				}
			}
			if(location_exists){
				break;
			}
		}
		
		if(!location_exists){
			return false;
		}
		
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
		else{		
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
	public boolean canMaritimeTrade(EdgeLocation location, int playerIndex) {
		if(location == null){
			return false;
		}
		
		boolean buildingValid = false;

		for(VertexLocation vertex : VertexesAdjacentToEdge.get(location).asSet()){
			if(this.settlements.containsKey(vertex) && this.settlements.get(vertex).getPlayerIndex() == playerIndex || 
			   this.cities.containsKey(vertex) && this.cities.get(vertex).getPlayerIndex() == playerIndex){
					buildingValid = true;
					break;
				}
		}
		return buildingValid && this.ports.containsKey(location);
	}

	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2, int playerIndex) {
		location1 = location1.getNormalizedLocation();
		location2 = location2.getNormalizedLocation();
		if(this.canBuildRoad(location1, playerIndex, null)){
			Road road = new Road(playerIndex, location1);
			this.roads.put(location1, road);
			if(this.canBuildRoad(location2,  playerIndex, null)){
				roads.remove(location1);
				return true;
			}
			else{
				roads.remove(location1);
			}
		}
		if(this.canBuildRoad(location2, playerIndex, null)){
			Road road = new Road(playerIndex, location2);
			this.roads.put(location2, road);
			if(this.canBuildRoad(location1,  playerIndex, null)){
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
	
	public Set<Port> getPortsByPlayer(Player player){
		Set<Port> ports = new HashSet<Port>();
		
		for(EdgeLocation location : this.ports.keySet()){
			Set<VertexLocation> vertexes = VertexesAdjacentToEdge.get(this.ports.get(location).getLocation()).asSet();
			for(VertexLocation vertex : vertexes){
				if(this.settlements.containsKey(vertex) && this.settlements.get(vertex).getPlayerIndex() == player.getPlayerIndex() ||
						this.cities.containsKey(vertex) && this.cities.get(vertex).getPlayerIndex() == player.getPlayerIndex()){
					
						ports.add(this.ports.get(location));
						break;
				}
			}
		}
		
		return ports;
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
	
	public int getNumberOfSettlementsByPlayerIndex(int player_index){
		int number = 0;
		
		for(Settlement settlement : this.settlements.values()){
			if(settlement.getPlayerIndex() == player_index){
				number++;
			}
		}
		return number;
	}
	
	public int getNumberOfRoadsByPlayerIndex(int player_index){
		int number = 0;
		
		for(Road road : this.roads.values()){
			if(road.getPlayerIndex() == player_index){
				number++;
			}
		}
		return number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cities == null) ? 0 : cities.hashCode());
		result = prime * result + ((hexes == null) ? 0 : hexes.hashCode());
		result = prime * result + ((ports == null) ? 0 : ports.hashCode());
		result = prime * result + radius;
		result = prime * result + ((roads == null) ? 0 : roads.hashCode());
		result = prime * result
				+ ((robberLocation == null) ? 0 : robberLocation.hashCode());
		result = prime * result
				+ ((settlements == null) ? 0 : settlements.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardMap other = (BoardMap) obj;
		if (cities == null) {
			if (other.cities != null)
				return false;
		} else if (!cities.equals(other.cities))
			return false;
		if (hexes == null) {
			if (other.hexes != null)
				return false;
		} else if (!hexes.equals(other.hexes))
			return false;
		if (ports == null) {
			if (other.ports != null)
				return false;
		} else if (!ports.equals(other.ports))
			return false;
		if (radius != other.radius)
			return false;
		if (roads == null) {
			if (other.roads != null)
				return false;
		} else if (!roads.equals(other.roads))
			return false;
		if (robberLocation == null) {
			if (other.robberLocation != null)
				return false;
		} else if (!robberLocation.equals(other.robberLocation))
			return false;
		if (settlements == null) {
			if (other.settlements != null)
				return false;
		} else if (!settlements.equals(other.settlements))
			return false;
		return true;
	}
	
	public ArrayList<Integer> getRobbablePlayers(HexLocation location){
		
		ArrayList<Integer> players_on_hex = new ArrayList<Integer>();
		
		for(VertexDirection direction : VertexDirection.values()){
			VertexLocation vertex_location = new VertexLocation(location, direction).getNormalizedLocation();
			
			for (Map.Entry<VertexLocation, City> city : cities.entrySet()){
				if (city.getKey().getNormalizedLocation().equals(vertex_location)){
					
					int owner = city.getValue().getPlayerIndex();
					
					if (!players_on_hex.contains(owner)){
						players_on_hex.add(owner);
					}
				}
			}
			
			for (Map.Entry<VertexLocation, Settlement> settlement : settlements.entrySet()){
				if (settlement.getKey().getNormalizedLocation().equals(vertex_location)){
					
					int owner = settlement.getValue().getPlayerIndex();
					
					if (!players_on_hex.contains(owner)){
						players_on_hex.add(owner);
					}
				}
			}	
		}
		
		return players_on_hex;
	}
}
