package client.model.map.luts;

import client.model.map.BoardMap;
import shared.locations.VertexLocation;

public class EdgesAdjacentToVertex {
	
	
	public static EdgesAdjacentToVertexResult findEdgesAdjacentToVertex(VertexLocation location, BoardMap map){
		return EdgesAdjacentToVertexLookupTable.getInstance().getEdgesAdjacentToVertex(location, map);
	}
	
	
}
