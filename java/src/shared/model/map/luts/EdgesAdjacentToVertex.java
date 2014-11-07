package shared.model.map.luts;

import shared.locations.VertexLocation;
import shared.model.map.BoardMap;

public class EdgesAdjacentToVertex {
	
	
	public static EdgesAdjacentToVertexResult findEdgesAdjacentToVertex(VertexLocation location, BoardMap map){
		return EdgesAdjacentToVertexLookupTable.getInstance().getEdgesAdjacentToVertex(location, map);
	}
	
	
}
