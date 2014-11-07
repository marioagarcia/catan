package shared.serialization.interfaces;

import java.util.ArrayList;

import shared.locations.HexLocation;
import shared.model.map.Hex;
import shared.model.map.Port;
import shared.model.piece.City;
import shared.model.piece.Road;
import shared.model.piece.Settlement;

public interface SerializerMapInterface {

	public abstract void setMap(ArrayList<Hex> hexList, ArrayList<Road> roadList, 
								ArrayList<City> cityList, ArrayList<Settlement> settlementList,
								int radius, ArrayList<Port> portList, HexLocation robberLocation);
	
}