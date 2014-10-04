package shared.serialization.interfaces;

import shared.locations.EdgeDirection;

public interface EdgeLocationInterface {

	public abstract void setEdgeLocation(HexLocationInterface hexLocation, EdgeDirection edgeDirection);
	
}
