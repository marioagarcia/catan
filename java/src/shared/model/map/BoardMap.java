package shared.model.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.card.ResourceList;
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
import shared.shared.utils.CatanUtils;
import shared.model.card.MaritimeTrade;

public class BoardMap implements BoardMapInterface, GMBoardMapInterface, SerializerMapInterface {
	private Map<HexLocation, HexInterface> hexes;
	private Map<EdgeLocation, Road> roads;
	private Map<VertexLocation, City> cities;
	private Map<VertexLocation, Settlement> settlements;
	private Map<EdgeLocation, Port> ports;
    private int player_with_longest_road = -1;
    private int longest_road_length = -1;
    private static final int MIN_NUMBER_ROADS_FOR_LONGEST = 5;

	private final int DEFAULT_RADIUS = -1; //TODO we need to determine what this should be
	
	private int radius;
	private HexLocation robberLocation;

    private class LongestRoadParams{
        public Map<EdgeLocation, Road> roads;
        public int road_player_index;
        public EdgeLocation current_location;
        public int length;

        public LongestRoadParams(Map<EdgeLocation, Road> roads, int length, int index, EdgeLocation current_location){
            this.roads = roads;
            this.length = length;
            this.road_player_index = index;
            this.current_location = current_location;
        }

    }

    private Map<EdgeLocation, Road> deep_copy_road_map(Map<EdgeLocation, Road> roads_in){
        Map<EdgeLocation, Road> roads = new HashMap<EdgeLocation, Road>();

        for(EdgeLocation location : roads_in.keySet()){
            Road new_road = new Road(roads_in.get(location).getPlayerIndex(), roads_in.get(location).getLocation());
            roads.put(location, new_road);
        }
        return roads;
    }
	
	public BoardMap(){
		this.hexes = new HashMap<HexLocation, HexInterface>();
		this.roads = new HashMap<EdgeLocation, Road>();
		this.cities = new HashMap<VertexLocation, City> ();
		this.settlements = new HashMap<VertexLocation, Settlement> ();
		this.ports = new HashMap<EdgeLocation, Port> ();
	}
	
	public ArrayList<ResourceList> getRollResult(int number){
		ArrayList<ResourceList> results = new ArrayList<ResourceList>();
		
		for(int i = 0; i < 4; i++){
			results.add(new ResourceList());
		}
		
		for(HexInterface hex : this.hexes.values()){
			if(hex.getNumber() == number){
				for(VertexDirection direction : VertexDirection.values()){
					VertexLocation location = new VertexLocation(hex.getLocation(), direction);

					for(City city : this.cities.values()){
						if(city.getLocation().getNormalizedLocation().equals(location.getNormalizedLocation())){
							results.get(city.getPlayerIndex()).incrementResourceByType(CatanUtils.toResourceType(hex.getType()));
							results.get(city.getPlayerIndex()).incrementResourceByType(CatanUtils.toResourceType(hex.getType()));
						}
					}
					for(Settlement settlement : this.settlements.values()){
						if(settlement.getLocation().getNormalizedLocation().equals(location.getNormalizedLocation())){
							results.get(settlement.getPlayerIndex()).incrementResourceByType(CatanUtils.toResourceType(hex.getType()));
						}
					}
				}
			}
		}
		
		return results;
	}
	
	private Set<HexLocation> getAllowedLandHexLocations(){
		Set<HexLocation> hexes = new HashSet<HexLocation>();
		
		int[] x_values = {-2,-1,0,1,2};
		int[] start = {0,-1,-2,-2,-2};
		int[] end = {2,2,2,1,0};
		
		for(int i = 0; i < x_values.length; i++)
			for(int y = start[i]; y <= end[i]; y++)
				hexes.add(new HexLocation(x_values[i],y));
		return hexes;
	}

	private Map<EdgeLocation, Port> generatePorts(boolean random){
		Map<EdgeLocation, Port> ports = new HashMap<EdgeLocation, Port>();

        ports.put(new EdgeLocation(new HexLocation(1,-3), EdgeDirection.South),
                new Port(PortType.ORE,   new EdgeLocation(new HexLocation(1,-3), EdgeDirection.South), 2));
        ports.put(new  EdgeLocation(new HexLocation(3,-3), EdgeDirection.SouthEast),
                new Port(PortType.THREE, new EdgeLocation(new HexLocation(3,-3), EdgeDirection.SouthWest), 3));
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

		if(random){
            for(EdgeLocation location : ports.keySet()){
                int index;
                do{
                    index = (int)(Math.random() * 31) % PortType.values().length;
                } while(index > 0);

                ports.get(location).setResource(PortType.values()[index]);
                if(ports.get(location).getResource() == PortType.THREE){
                    ports.get(location).setRatio(3);
                }
                else{
                    ports.get(location).setRatio(2);
                }
			}
		}
		return ports;
	}
	
	private Map<HexLocation, HexInterface> generateHexes(boolean random_hexes, boolean random_chits) {
		Random random = new Random(System.currentTimeMillis());
		Map<HexLocation, HexInterface> hexes = new HashMap<HexLocation, HexInterface>();
		
//		for(HexLocation location : this.getAllowedWaterHexLocations()){
//			hexes.put(location, new Hex(location, HexType.WATER, -1));
//		}
		
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
			
			for(HexType type : number_of_each_hex_type.keySet()){
				for(int i = 0; i < number_of_each_hex_type.get(type); i++){
					int index;
                    do {
                        index = random.nextInt() % land_hexes.size();
                    } while(index < 0);
					
					HexLocation location = land_hexes.get(index);
					land_hexes.remove(index);
					hexes.put(location, new Hex(location, type, -1));
				}
			}
			Integer[] chits_array = {2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
			if(random_chits){
				//add numbers randomly to the hexes
				ArrayList<Integer> chits = new ArrayList<Integer>(Arrays.asList(chits_array));
				for(HexLocation location : hexes.keySet()){
					if(hexes.get(location).getType() == HexType.DESERT || hexes.get(location).getType() == HexType.WATER){
						continue;
					}
					int index;
                    do{
                        index = random.nextInt() % chits.size();
                    } while( index < 0);
					
					int chit = chits.get(index);
					chits.remove(index);
					
					HexInterface hex = hexes.get(location);
					hex.setNumber(chit);
					hexes.put(location, hex);
				}
			}
			else{
				for(int i = 0; i < chits_array.length * 31; i++){
					int first_location;
                    int second_location;
                    do{
                        first_location= random.nextInt() % chits_array.length;
                    } while(first_location < 0);

					do {
                         second_location = random.nextInt() % chits_array.length;
                    } while(second_location < 0);
					
					int temp = chits_array[first_location];
					chits_array[first_location] = chits_array[second_location];
					chits_array[second_location] = temp;
				}
				
				int index = 0;
				for(HexLocation location : hexes.keySet()){
					if(hexes.get(location).getType() == HexType.WATER || hexes.get(location).getType() == HexType.DESERT){
						continue;
					}
					
					HexInterface hex = hexes.get(location);
					hex.setNumber(chits_array[index++]);
					hexes.put(location, hex);
				}
			}
			
		}
		else {
			hexes.put(new HexLocation(-2,0), new Hex(new HexLocation(-2,0), HexType.ORE, 5));
			hexes.put(new HexLocation(1,-1), new Hex(new HexLocation(1,-1), HexType.ORE, 9));
			hexes.put(new HexLocation(-1,2), new Hex(new HexLocation(-1,2), HexType.ORE, 3));
			
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
			
			hexes.put(new HexLocation(0,-2), new Hex(new HexLocation(0,-2), HexType.DESERT, -1));
		}
		
		return hexes;
	}

	/**
	 * create a new board map
	 * @param random_chits boolean whether or not to place the chits randomly
	 * @param random_hexes boolean whether or not to place the hex types randomly
	 * @param random_ports boolean whether or not to place the ports randomly
	 */
	public BoardMap(boolean random_chits, boolean random_hexes, boolean random_ports) {
		
		this.hexes = this.generateHexes(random_hexes, random_chits);
		this.ports = this.generatePorts(random_ports);
		this.roads = new HashMap<EdgeLocation, Road>();
		this.cities = new HashMap<VertexLocation, City>();
		this.settlements = new HashMap<VertexLocation, Settlement>();
		this.setRadius(this.DEFAULT_RADIUS);
		
		for(HexLocation location : this.hexes.keySet()){
			if(this.hexes.get(location).getType() == HexType.DESERT){
				this.robberLocation = location;
				break;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BoardMap [hexes=" + hexes + ", roads=" + roads + ", cities="
				+ cities + ", settlements=" + settlements + ", ports=" + ports
				+  ", DEFAULT_RADIUS=" + DEFAULT_RADIUS + ", radius=" + radius
                + ", robberLocation=" + robberLocation + "]";
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

    public ResourceList getResourcesByVertexLocation(VertexLocation vertex){
        ResourceList resources = new ResourceList();
        vertex = vertex.getNormalizedLocation();

        for(HexInterface hex : this.hexes.values()){
            if(hex.getType() == HexType.DESERT){
                continue;
            }
            for(VertexDirection direction : VertexDirection.values()){
                VertexLocation potential_location = new VertexLocation(hex.getLocation(), direction).getNormalizedLocation();

                if(potential_location.equals(vertex)){
                    ResourceType resource_type = ResourceType.valueOf(hex.getType().toString());
                    resources.setResourceByType(resource_type, resources.getResourceByType(resource_type) + 1);
                    break;
                }
            }
        }
        return resources;
    }

    /**
     * this method will return the index of the player with the longest road
     * @return index of player with longest road
     */
    public int getLongestRoadIndex(){
        for(EdgeLocation location : this.roads.keySet()){
            Map<EdgeLocation, Road> roads = this.deep_copy_road_map(this.roads);

            roads.remove(location);

            LongestRoadParams params = new LongestRoadParams(roads, 1, this.roads.get(location).getPlayerIndex(), location);

            int length = this.recGetLongestRoadIndex(params);
            if(length >=MIN_NUMBER_ROADS_FOR_LONGEST && length > this.longest_road_length){
                this.longest_road_length = length;
                this.player_with_longest_road = this.roads.get(location).getPlayerIndex();
            }
        }

        return this.player_with_longest_road;
    }

    /**
     * this should be called on each road in the game
     * @param params LongestRoadParams object
     * @return  int longest road length thus far
     */
    private int recGetLongestRoadIndex(LongestRoadParams params){
        EdgeLocation[] potential_adjacent = params.current_location.getAdjacent(true, true, this);
        int longest = params.length;

        for(EdgeLocation location : potential_adjacent){
            if(params.roads.containsKey(location.getNormalizedLocation()) && params.roads.get(location.getNormalizedLocation()).getPlayerIndex() == params.road_player_index){
                Map<EdgeLocation, Road> roads = this.deep_copy_road_map((params.roads));
                roads.remove(location);

                LongestRoadParams child_params = new LongestRoadParams(roads, params.length + 1, params.road_player_index, location);
                int result = recGetLongestRoadIndex(child_params);
                if(result > longest){
                    longest =  result;
                }
            }
        }

        return longest;
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
        ArrayList<VertexLocation> adjacent_vertex_locations = new ArrayList<VertexLocation>();
		
		for(VertexDirection vertex_direction : adjacentVertexes){
			VertexLocation vertex_location = new VertexLocation(location.getHexLoc(), vertex_direction).getNormalizedLocation();
            adjacent_vertex_locations.add(vertex_location);
			
			if(this.cities.containsKey(vertex_location) && this.cities.get(vertex_location).getPlayerIndex() == playerIndex ||
			   this.settlements.containsKey(vertex_location) && this.settlements.get(vertex_location).getPlayerIndex() == playerIndex){
				return true;
			}
		}
		
		boolean canBuildClockwisePrevious = true;
		boolean canBuildClockwiseNext = true;

		//check if other players have cities/settlements which interrupt your road
		if(this.cities.containsKey(adjacent_vertex_locations.get(0)) && this.cities.get(adjacent_vertex_locations.get(0)).getPlayerIndex() != playerIndex)
			canBuildClockwisePrevious = false;
		else if(this.settlements.containsKey(adjacent_vertex_locations.get(0)) && this.settlements.get(adjacent_vertex_locations.get(0)).getPlayerIndex() != playerIndex)
			canBuildClockwisePrevious = false;
		
		if(this.cities.containsKey(adjacent_vertex_locations.get(1)) && this.cities.get(adjacent_vertex_locations.get(1)).getPlayerIndex() != playerIndex)
			canBuildClockwiseNext = false;
		else if(this.settlements.containsKey(adjacent_vertex_locations.get(1)) && this.settlements.get(adjacent_vertex_locations.get(1)).getPlayerIndex() != playerIndex)
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

		return existsAdjacentRoad && this.settlements.containsKey(location) && this.settlements.get(location).getPlayerIndex() == playerIndex;
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
        return !oldLocation.equals(newLocation);
	}

	public Set<Port> getPortsByPlayer(Player player){
		return this.getPortsByPlayer(player.getPlayerIndex());
	}

    private Set<Port> getPortsByPlayer(int player_index){
        System.out.println("this " + this);
        Set<Port> ports = new HashSet<Port>();

        for(EdgeLocation location : this.ports.keySet()){
            Set<VertexLocation> vertexes = VertexesAdjacentToEdge.get(this.ports.get(location).getLocation()).asSet();
            for(VertexLocation vertex : vertexes){
                if(this.settlements.containsKey(vertex) && this.settlements.get(vertex).getPlayerIndex() == player_index ||
                        this.cities.containsKey(vertex) && this.cities.get(vertex).getPlayerIndex() == player_index){

                    ports.add(this.ports.get(location));
                    break;
                }
            }
        }

        return ports;
    }



    public EdgeLocation getLocationForMaritimeTrade(MaritimeTrade trade, int player_index){
        Set<Port> ports = this.getPortsByPlayer(player_index);

        for(Port port : ports){
            if(trade.getRatio() != port.getRatio()){
                continue;
            }
            if((port.getResource() == PortType.THREE) || (trade.getResourceIn().toString().equals(port.getResource().toString()))){
                return port.getLocation();
            }
        }
        return null;
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
	public void playSoldier(HexLocation new_location, int player_index) {
		assert this.canPlaySoldier(getRobberLocation(), new_location, player_index);
		
		this.robberLocation = new_location;
	}
}
