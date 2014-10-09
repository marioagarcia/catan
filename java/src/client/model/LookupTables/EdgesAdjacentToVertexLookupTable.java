package client.model.LookupTables;

import java.util.HashMap;
import java.util.Map;

import client.model.map.BoardMap;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * this code contains a table which will give you all of the EdgeLocations adjacent to a VertexLocation
 * @author christopherbelyeu
 *
 */
class EdgesAdjacentToVertexLookupTable {
	
	private Map<VertexDirection, EdgesAdjacentToVertexLookupResult> table;
	
	private static EdgesAdjacentToVertexLookupTable instance = new EdgesAdjacentToVertexLookupTable();
	
	private EdgesAdjacentToVertexLookupTable(){
		table = new HashMap<VertexDirection, EdgesAdjacentToVertexLookupResult>();
		
		table.put(VertexDirection.NorthEast, new EdgesAdjacentToVertexLookupResult( 0,-1, EdgeDirection.SouthEast, EdgeDirection.North, 	  EdgeDirection.NorthEast));
		table.put(VertexDirection.East,  	 new EdgesAdjacentToVertexLookupResult( 1,-1, EdgeDirection.South, 	 EdgeDirection.NorthEast, EdgeDirection.SouthEast));
		table.put(VertexDirection.SouthEast, new EdgesAdjacentToVertexLookupResult( 1, 0, EdgeDirection.SouthWest, EdgeDirection.SouthEast, EdgeDirection.South));
		table.put(VertexDirection.SouthWest, new EdgesAdjacentToVertexLookupResult( 0, 1, EdgeDirection.NorthWest, EdgeDirection.South,     EdgeDirection.SouthWest));
		table.put(VertexDirection.West,      new EdgesAdjacentToVertexLookupResult(-1, 1, EdgeDirection.North, 	 EdgeDirection.SouthWest, EdgeDirection.NorthWest));
		table.put(VertexDirection.NorthWest, new EdgesAdjacentToVertexLookupResult(-1, 0, EdgeDirection.NorthEast, EdgeDirection.NorthWest, EdgeDirection.North));
	}

	public EdgesAdjacentToVertexResult getEdgesAdjacentToVertex(VertexLocation location, BoardMap map){
		EdgesAdjacentToVertexLookupResult result = table.get(location.getDir());
		EdgeLocation interiorPreceeding = new EdgeLocation(location.getHexLoc(), result.getClockwisePreceedingInternalEdge());
		EdgeLocation interiorSucceeding = new EdgeLocation(location.getHexLoc(), result.getClockwiseSucceedingInternalEdge());
		
		HexLocation exteriorHexLocation = new HexLocation(location.getHexLoc().getX() + result.getxOffset(), location.getHexLoc().getY() + result.getyOffset());
		EdgeLocation exterior = new EdgeLocation(exteriorHexLocation, result.getExternalEdge());
		return new EdgesAdjacentToVertexResult(exterior, interiorPreceeding, interiorSucceeding, map);
	}

	public static EdgesAdjacentToVertexLookupTable getInstance(){
		return instance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EdgesAdjacentToVertexLookupTable [table=" + table + "]";
	}

}
