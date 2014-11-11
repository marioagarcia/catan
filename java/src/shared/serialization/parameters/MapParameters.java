package shared.serialization.parameters;

import shared.locations.HexLocation;
import shared.model.map.Hex;
import shared.model.map.Port;
import shared.model.piece.City;
import shared.model.piece.Road;
import shared.model.piece.Settlement;

public class MapParameters {
	
	private Hex[] hexes;
	private Road[] roads;
	private City[] cities;
	private Settlement[] settlements;
	private int radius;
	private Port[] ports;
	private HexLocation robber;
	
	public MapParameters(Hex[] hexes, Road[] roads, City[] cities, Settlement[] settlements, 
						 int radius, Port[] ports, HexLocation robber){
		this.hexes = hexes;
		this.roads = roads;
		this.cities = cities;
		this.settlements = settlements;
		this.radius = radius;
		this.ports = ports;
		this.robber = robber;
	}

	public Hex[] getHexes() {
		return hexes;
	}

	public void setHexes(Hex[] hexes) {
		this.hexes = hexes;
	}

	public Road[] getRoads() {
		return roads;
	}

	public void setRoads(Road[] roads) {
		this.roads = roads;
	}

	public City[] getCities() {
		return cities;
	}

	public void setCities(City[] cities) {
		this.cities = cities;
	}

	public Settlement[] getSettlements() {
		return settlements;
	}

	public void setSettlements(Settlement[] settlements) {
		this.settlements = settlements;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Port[] getPorts() {
		return ports;
	}

	public void setPorts(Port[] ports) {
		this.ports = ports;
	}

	public HexLocation getRobber() {
		return robber;
	}

	public void setRobber(HexLocation robber) {
		this.robber = robber;
	}
}
