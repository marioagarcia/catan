package shared.serialization.interfaces;

import shared.definitions.ResourceType;

public interface HexInterface {

	public abstract void setHex(HexLocationInterface hexLocation, ResourceType resource, int number);
	
}
