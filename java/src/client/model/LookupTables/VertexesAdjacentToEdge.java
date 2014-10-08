package client.model.LookupTables;

import shared.locations.EdgeLocation;

public class VertexesAdjacentToEdge {
	public static VertexesAdjacentToEdgeResult get(EdgeLocation location){
		return VertexesAdjacentToEdgeLookupTable.getInstance().getLookupResult(location);
	}

}
