package shared.model.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.manager.interfaces.GMBoardMapInterface;
import shared.model.map.luts.EdgesAdjacentToVertex;
import shared.model.map.luts.EdgesAdjacentToVertexResult;
import shared.model.map.luts.VertexesAdjacentToEdge;
import shared.model.piece.City;
import shared.model.piece.Road;
import shared.model.piece.Settlement;
import shared.model.player.Player;
import shared.model.turntracker.TurntrackerInterface.Status;
import shared.serialization.interfaces.SerializerMapInterface;

public class BoardMap implements BoardMapInterface, GMBoardMapInterface, SerializerMapInterface {
	private Map<HexLocation, HexInterface> hexes;
	private Map<EdgeLocation, Road> roads;
	private Map<VertexLocation, City> cities;
	private Map<VertexLocation, Settlement> settlements;
	private Map<EdgeLocation, Port> ports;
	
	private final int numberOfPorts = 9;
	private final int DEFAULT_RADIUS = -1; //TODO we need to determine what this should be
	
	private int radius;
	private HexLocation robberLocation;
	
	public BoardMap(){
		this.hexes = new HashMap<HexLocation, HexInterface>();
		this.roads = new HashMap<EdgeLocation, Road>();
		this.cities = new HashMap<VertexLocation, City> ();
		this.settlements = new HashMap<VertexLocation, Settlement> ();
		this.ports = new HashMap<EdgeLocation, Port> ();
	}
	
	private Set<HexLocation> getAllowedLandHexLocations(){
		Set<HexLocation> hexes = new HashSet<HexLocation>();
		
		int[] x_values = {-2,-1,0,1,2};
		int[] start = {0,-1,-2,-2,-2};
		int[] end = {2,2,2,2,1};
		
		for(int i = 0; i < x_values.length; i++)
			for(int y = start[i]; y <= end[i]; y++)
				hexes.add(new HexLocation(x_values[i],y));

		return hexes;
	}
	
	private Set<HexLocation> getAllowedWaterHexLocations(){
		Set<HexLocation> hexes = new HashSet<HexLocation>();
		
		for(int i = 0; i < 3; i++){
			hexes.add(new HexLocation(-3,i));
			hexes.add(new HexLocation(-i,3));
			hexes.add(new HexLocation(i,-3));
			hexes.add(new HexLocation(3,-i));
		}
		
		hexes.add(new HexLocation(-2,-1));
		hexes.add(new HexLocation(-1,-2));
		hexes.add(new HexLocation(1,2));
		hexes.add(new HexLocation(2,1));
		
		return hexes;
	}
	
	private Set<EdgeLocation> generatePotentialPortLocations(){
		Set<HexLocation> water = this.getAllowedWaterHexLocations();
		Set<HexLocation> land = this.getAllowedLandHexLocations();
		Set<EdgeLocation> potentialPortLocations = new HashSet<EdgeLocation>();
		
		for(HexLocation hex : water){
			for(EdgeDirection direction : EdgeDirection.values()){
				if(land.contains(hex.getNeighborLoc(direction))){
					potentialPortLocations.add(new EdgeLocation(hex,direction));
				}	
			}
		}
		
		return potentialPortLocations;
	}
	
	private Map<EdgeLocation, Port> generatePorts(boolean random, int number_of_ports){
		Map<EdgeLocation, Port> ports = new HashMap<EdgeLocation, Port>();
		if(random){
			EdgeLocation[] potential_locations = this.generatePotentialPortLocations().toArray(new EdgeLocation[0]);
			
			ArrayList<PortType> port_types = new ArrayList<PortType>(Arrays.asList(PortType.values()));
			for(int i = 0; i < 3; i++){
				port_types.add(PortType.THREE);
			}
			
			//create 3:1 ports
			for(PortType type : port_types){
				int index;
				do{
					index = (int)Math.random() % potential_locations.length;
				} while(ports.keySet().contains(potential_locations[index]));
				
				ports.put(potential_locations[index], new Port(type, potential_locations[index], 3));
			}
		}
		else {
			ports.put(new EdgeLocation(new HexLocation(1,-3), EdgeDirection.South), 
					new Port(PortType.ORE,   new EdgeLocation(new HexLocation(1,-3), EdgeDirection.South), 2));
			ports.put(new  EdgeLocation(new HexLocation(3,-3), EdgeDirection.SouthEast), 
					new Port(PortType.THREE, new EdgeLocation(new HexLocation(3,-3), EdgeDirection.SouthEast), 3));
			ports.put(new EdgeLocation(new HexLocation(-3,0), EdgeDirection.SouthEast), 
					new Port(PortType.THREE, new EdgeLocation(new HexLocation(-3,0), EdgeDirection.SouthEast), 3));
			ports.put(new EdgeLocation(new HexLocation(-2,3), EdgeDirection.NorthEast), 
					new Port(PortType.BRICK, new EdgeLocation(new HexLocation(-2,3), EdgeDirection.NorthEast), 2));
			ports.put(new EdgeLocation(new HexLocation(-1,-2), EdgeDirection.South), 
					new Port(PortType.WHEAT, new EdgeLocation(new HexLocation(-1,-2), EdgeDirection.South), 2));
			ports.put(new EdgeLocation(new HexLocation(0,3), EdgeDirection.North),
					new Port(PortType.THREE, new EdgeLocation(new HexLocation(0,3), EdgeDirection.North), 3));
			ports.put(new EdgeLocation(new HexLocation(-3,2), EdgeDirection.NorthEast),
					new Port(PortType.WOOD, new EdgeLocation(new HexLocation(-3,2), EdgeDirection.NorthEast), 2));
			ports.put(new EdgeLocation(new HexLocation(2,1), EdgeDirection.NorthWest), 
					new Port(PortType.THREE, new EdgeLocation(new HexLocation(2,1), EdgeDirection.NorthWest), 3));
			ports.put(new EdgeLocation(new HexLocation(3,-1), EdgeDirection.NorthWest),
					new Port(PortType.SHEEP, new EdgeLocation(new HexLocation(3,-1), EdgeDirection.NorthWest), 2));
		}
		return ports;
	}
	
	private Map<HexLocation, HexInterface> generateHexes(boolean random_hexes, boolean random_chits) {
		//TODO random chits is not yet implemented
		Map<HexLocation, HexInterface> hexes = new HashMap<HexLocation, HexInterface>();
		
		for(HexLocation location : this.getAllowedWaterHexLocations()){
			hexes.put(location, new Hex(location, HexType.WATER, -1));
		}
		
		ArrayList<HexLocation> land_hexes = new ArrayList<HexLocation>();
		
		for(HexLocation location : this.getAllowedLandHexLocations()){
			land_hexes.add(location);
		}
		
		if(random_hexes){
			//distribute resources randomly on the Hexes
			Map<HexType, Integer> number_of_each_hex_type = new HashMap<HexType, Integer>();
			number_of_each_hex_type.put(HexType.BRICK, 3);
			number_of_each_hex_type.put(HexType.DESERT, 1);
			number_of_each_hex_type.put(HexType.ORE, 3);
			number_of_each_hex_type.put(HexType.SHEEP, 4);
			number_of_each_hex_type.put(HexType.WHEAT, 4);
			number_of_each_hex_type.put(HexType.WOOD, 4);
			
			for(HexType type : HexType.values()){
				for(int i = 0; i < number_of_each_hex_type.get(type); i++){
					int index = (int)Math.random() % land_hexes.size();
					
					HexLocation location = land_hexes.get(index);
					land_hexes.remove(index);
					hexes.put(location, new Hex(location, type, -1));
				}
			}
			
			//add numbers randomly to the hexes
			Integer[] chits_array = {2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
			ArrayList<Integer> chits = new ArrayList<Integer>(Arrays.asList(chits_array));
			
			for(HexLocation location : hexes.keySet()){
				int index = (int)Math.random() % chits.size();
				
				int chit = chits.get(index);
				chits.remove(index);
				
				HexInterface hex = hexes.get(location);
				hex.setNumber(chit);
				hexes.put(location, hex);
			}
			
		}
		else {
			hexes.put(new HexLocation(-2,0), new Hex(new HexLocation(-2,0), HexType.ORE, 5));
			hexes.put(new HexLocation(1,-1), new Hex(new HexLocation(1,-1), HexType.ORE,9));
			
			hexes.put(new HexLocation(-1,-1), new Hex(new HexLocation(-1,-1), HexType.BRICK, 8));
			hexes.put(new HexLocation(1,-2), new Hex(new HexLocation(1,-2), HexType.BRICK, 4));
			hexes.put(new HexLocation(1,0), new Hex(new HexLocation(1,0), HexType.BRICK, 5));
			
			hexes.put(new HexLocation(-2,2), new Hex(new HexLocation(-2,2), HexType.WOOD, 6));
			hexes.put(new HexLocation(0,1), new Hex(new HexLocation(0,1), HexType.WOOD, 4));
			hexes.put(new HexLocation(0,-1), new Hex(new HexLocation(0,-1), HexType.WOOD, 3));
			hexes.put(new HexLocation(2,-2), new Hex(new HexLocation(2,-2), HexType.WOOD, 11));
			
			hexes.put(new HexLocation(-1,0), new Hex(new HexLocation(-1,0), HexType.SHEEP, 10));
			hexes.put(new HexLocation(-1,1), new Hex(new HexLocation(-1,1), HexType.SHEEP, 9));
			hexes.put(new HexLocation(1,1), new Hex(new HexLocation(1,1), HexType.SHEEP, 10));
			hexes.put(new HexLocation(2,-1), new Hex(new HexLocation(2,-1), HexType.SHEEP, 12));
			
			hexes.put(new HexLocation(-2,1), new Hex(new HexLocation(-2,1), HexType.WHEAT, 2));
			hexes.put(new HexLocation(0,0), new Hex(new HexLocation(0,0), HexType.WHEAT, 11));
			hexes.put(new HexLocation(0,2), new Hex(new HexLocation(0,2), HexType.WHEAT, 8));
			hexes.put(new HexLocation(2,0), new Hex(new HexLocation(2,0), HexType.WHEAT, 6));
		}
		
		return hexes;
	}
	
	/**
	 * create a new board map
	 * @param random_chits boolean whether or not to place the chits randomly
	 * @param random_hexes boolean whether or not to place the hex types randomly
	 * @param random_ports boolean whether or not to place the ports randomly
	 * @return BoardMap the newly created map
	 */
	public BoardMap(boolean random_chits, boolean random_hexes, boolean random_ports) {
		
		this.hexes = this.generateHexes(random_hexes, random_chits);
		this.ports = this.generatePorts(random_ports, numberOfPorts);
		this.roads = new HashMap<EdgeLocation, Road>();
		this.cities = new HashMap<VertexLocation, City>();
		this.settlements = new HashMap<VertexLocation, Settlement>();
		this.radius = this.DEFAULT_RADIUS;
		
		for(HexLocation location : this.hexes.keySet()){
			if(this.hexes.get(location).getType() == HexType.DESERT){
				this.robberLocation = location;
				break;
			}
		}
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

	
	@Override
	public void buildRoad(EdgeLocation location, int player_index, Status status) {
		assert(this.canBuildRoad(location, player_index, status));
		
		this.roads.put(location, new Road(player_index, location));
	}

	
	@Override
	public void buildSettlement(VertexLocation location, int player_index, boolean setupPhase) {
		assert(this.canBuildSettlement(location, player_index, setupPhase));
		
		this.settlements.put(location, new Settlement(player_index, location));
	}

	
	@Override
	public void buildCity(VertexLocation location, int player_index) {
		assert(this.canBuildCity(location, player_index));
		
		this.settlements.remove(location);
		
		City city = new City();
		city.setLocation(location);
		city.setPlayerIndex(player_index);
		
		this.cities.put(location, city);
	}

	
	@Override
	public void playRoadBuilding(EdgeLocation location1, EdgeLocation location2, int player_index) {
		assert(this.canPlayRoadBuilding(location1, location2, player_index));
		
		this.roads.put(location1, new Road(player_index, location1));
		this.roads.put(location2, new Road(player_index, location2));
	}

	
	@Override
	public void playSoldier(HexLocation oldLocation, HexLocation newLocation, int player_index) {
		assert(this.canPlaySoldier(oldLocation, newLocation, player_index));
		
		this.robberLocation = newLocation;
	}
}
