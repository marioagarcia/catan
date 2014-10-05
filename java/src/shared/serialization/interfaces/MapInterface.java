package shared.serialization.interfaces;

import java.util.ArrayList;

public interface MapInterface {

	public abstract void setMap(ArrayList<HexInterface> hexList, ArrayList<RoadInterface> roadList, 
								ArrayList<CityInterface> cityList, ArrayList<SettlementInterface> settlementList,
								int radius, ArrayList<PortInterface> portList, HexLocationInterface robberLocation);
	
}
