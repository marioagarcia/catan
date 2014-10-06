package shared.serialization.interfaces;

import shared.definitions.HexType;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

public interface SerializerHexInterface {

	public abstract void setHex(HexLocation hexLocation, HexType resource, int number);
	
}
