package shared.serialization.interfaces;

import java.util.ArrayList;

public interface SerializerMapInterface {

	public abstract void setMap(ArrayList<SerializerHexInterface> hexList, ArrayList<SerializerRoadInterface> roadList, 
								ArrayList<SerializerCityInterface> cityList, ArrayList<SerializerSettlementInterface> settlementList,
								int radius, ArrayList<SerializerPortInterface> portList, SerializerHexLocationInterface robberLocation);
	
}
