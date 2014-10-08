package client.model.LookupTables;

import shared.locations.VertexLocation;

public class EdgesAdjacentToVertex {
	
	
	public static EdgesAdjacentToVertexResult findEdgesAdjacentToVertex(VertexLocation location){
		return EdgesAdjacentToVertexLookupTable.getInstance().getEdgesAdjacentToVertex(location);
	}
	
	
}
