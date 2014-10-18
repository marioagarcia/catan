package client.model.map.luts;

import shared.locations.EdgeLocation;

public class VertexesAdjacentToEdge {
	public static VertexesAdjacentToEdgeResult get(EdgeLocation location){
		return VertexesAdjacentToEdgeLookupTable.getInstance().getLookupResult(location);
	}

}
