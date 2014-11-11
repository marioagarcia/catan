package shared.serialization.parameters;

import shared.locations.HexLocation;
import shared.model.map.Hex;
import shared.model.map.Port;
import shared.model.piece.City;
import shared.model.piece.Road;
import shared.model.piece.Settlement;

public class MapParameters {
	
	private HexParameters[] hexes;
	private RoadParameters[] roads;
	private CityParameters[] cities;
	private SettlementParameters[] settlements;
	private int radius;
	private PortParameters[] ports;
	private HexLocation robber;
	
	public MapParameters(HexParameters[] hexes, RoadParameters[] roads, CityParameters[] cities, SettlementParameters[] settlements, 
						 int radius, PortParameters[] ports, HexLocation robber){
		this.hexes = hexes;
		this.roads = roads;
		this.cities = cities;
		this.settlements = settlements;
		this.radius = radius;
		this.ports = ports;
		this.robber = robber;
	}

	public HexParameters[] getHexes() {
		return hexes;
	}

	public void setHexes(HexParameters[] hexes) {
		this.hexes = hexes;
	}

	public RoadParameters[] getRoads() {
		return roads;
	}

	public void setRoads(RoadParameters[] roads) {
		this.roads = roads;
	}

	public CityParameters[] getCities() {
		return cities;
	}

	public void setCities(CityParameters[] cities) {
		this.cities = cities;
	}

	public SettlementParameters[] getSettlements() {
		return settlements;
	}

	public void setSettlements(SettlementParameters[] settlements) {
		this.settlements = settlements;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public PortParameters[] getPorts() {
		return ports;
	}

	public void setPorts(PortParameters[] ports) {
		this.ports = ports;
	}

	public HexLocation getRobber() {
		return robber;
	}

	public void setRobber(HexLocation robber) {
		this.robber = robber;
	}
}
