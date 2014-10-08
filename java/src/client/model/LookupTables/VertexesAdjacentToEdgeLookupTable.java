package client.model.LookupTables;

import java.util.HashMap;
import java.util.Map;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

class VertexesAdjacentToEdgeLookupTable {
	private Map<EdgeDirection, VertexesAdjacentToEdgeLookupResult> table;
	
	private static VertexesAdjacentToEdgeLookupTable instance = new VertexesAdjacentToEdgeLookupTable();
	
	public static VertexesAdjacentToEdgeLookupTable getInstance(){
		return instance;
	}
	
	private VertexesAdjacentToEdgeLookupTable(){
		this.table = new HashMap<EdgeDirection, VertexesAdjacentToEdgeLookupResult>();
		
		//populate table
		this.table.put(EdgeDirection.North, new VertexesAdjacentToEdgeLookupResult(VertexDirection.NorthWest, VertexDirection.NorthEast));
		this.table.put(EdgeDirection.NorthEast, new VertexesAdjacentToEdgeLookupResult(VertexDirection.NorthEast, VertexDirection.East));
		this.table.put(EdgeDirection.SouthEast, new VertexesAdjacentToEdgeLookupResult(VertexDirection.East, VertexDirection.SouthEast));
		this.table.put(EdgeDirection.South, new VertexesAdjacentToEdgeLookupResult(VertexDirection.SouthEast, VertexDirection.SouthWest));
		this.table.put(EdgeDirection.SouthWest, new VertexesAdjacentToEdgeLookupResult(VertexDirection.SouthWest, VertexDirection.West));
		this.table.put(EdgeDirection.NorthWest, new VertexesAdjacentToEdgeLookupResult(VertexDirection.West, VertexDirection.NorthWest));
	}
	
	public VertexesAdjacentToEdgeResult getLookupResult(EdgeLocation location){
		VertexesAdjacentToEdgeLookupResult lookupResult = this.table.get(location.getDir());
		
		VertexLocation clockwisePreceeding = new VertexLocation(location.getHexLoc(), lookupResult.getClockwisePreceeding());
		VertexLocation clockwiseSucceeding = new VertexLocation(location.getHexLoc(), lookupResult.getClockwiseSucceeding());
		
		return new VertexesAdjacentToEdgeResult( clockwisePreceeding, clockwiseSucceeding);
	}
}
