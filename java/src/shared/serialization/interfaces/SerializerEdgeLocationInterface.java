package shared.serialization.interfaces;

import shared.locations.EdgeDirection;

public interface SerializerEdgeLocationInterface {

	public abstract void setEdgeLocation(SerializerHexLocationInterface hexLocation, EdgeDirection edgeDirection);
	
}
