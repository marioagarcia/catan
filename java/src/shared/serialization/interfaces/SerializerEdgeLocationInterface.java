package shared.serialization.interfaces;

import shared.locations.EdgeDirection;
import shared.locations.HexLocation;

public interface SerializerEdgeLocationInterface {

	public abstract void setEdgeLocation(HexLocation hexLocation, EdgeDirection edgeDirection);
	
}
