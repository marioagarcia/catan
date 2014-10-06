package shared.serialization.interfaces;

import shared.definitions.ResourceType;

public interface SerializerHexInterface {

	public abstract void setHex(SerializerHexLocationInterface hexLocation, ResourceType resource, int number);
	
}
