package shared.serialization.interfaces;

import shared.definitions.ResourceType;

public interface SerializerPortInterface {

	//Don't do this yet Chris; I'm not sure how to handle a port's direction yet, or where to derive it from
	public abstract void setPort(int ratio, ResourceType resource, String direction, SerializerHexLocationInterface hexLocation);
	
}
