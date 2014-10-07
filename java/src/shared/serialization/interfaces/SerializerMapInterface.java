package shared.serialization.interfaces;

import java.util.ArrayList;

import client.model.map.Hex;
import client.model.map.Port;
import client.model.piece.City;
import client.model.piece.Road;
import client.model.piece.Settlement;
import shared.locations.HexLocation;

public interface SerializerMapInterface {

	public abstract void setMap(ArrayList<Hex> hexList, ArrayList<Road> roadList, 
								ArrayList<City> cityList, ArrayList<Settlement> settlementList,
								int radius, ArrayList<Port> portList, HexLocation robberLocation);
	
}