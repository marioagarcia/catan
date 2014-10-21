package shared.serialization.interfaces;

import shared.definitions.PortType;
import shared.locations.EdgeLocation;

public interface SerializerPortInterface {
	
	public abstract void setPort(int ratio, PortType resource, EdgeLocation location);
	
}
