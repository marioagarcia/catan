package shared.serialization.interfaces;

import shared.locations.VertexDirection;

public interface SerializerVertexLocationInterface {

	public abstract void setVertexLocation(SerializerHexLocationInterface hexLocation, VertexDirection vertexDirection);
	
}
