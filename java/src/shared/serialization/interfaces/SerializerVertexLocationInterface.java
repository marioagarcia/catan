package shared.serialization.interfaces;

import shared.locations.HexLocation;
import shared.locations.VertexDirection;

public interface SerializerVertexLocationInterface {

	public abstract void setVertexLocation(HexLocation hexLocation, VertexDirection vertexDirection);
	
}
