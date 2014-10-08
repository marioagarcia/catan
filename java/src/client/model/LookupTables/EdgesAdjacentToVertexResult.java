package client.model.LookupTables;

import shared.locations.EdgeLocation;

public class EdgesAdjacentToVertexResult {
	/**
	 * the location of the edge which is in the same Hex as
	 * the VertexLocation, but precedes it in a clockwise fashion
	 */
	private EdgeLocation interiorClockwisePreceeding;
	
	/**
	 * the EdgeLocation of the Edge which is in the same Hex as
	 * the VertexLocation, but succeeds it in a clockwise fashion
	 */
	private EdgeLocation interiorClockwiseSucceeding;
	
	/**
	 * the EdgeLocation of the Edge which is outside of the Hex with the VertexLocation
	 * but adjacent to it
	 */
	private EdgeLocation exterior;

	
	//-------------------------------------------------------------
	/**
	 * @return the interiorClockwisePreceeding
	 */
	public EdgeLocation getInteriorClockwisePreceeding(boolean normalize) {
		if(normalize)
			return interiorClockwisePreceeding.getNormalizedLocation();
		return interiorClockwisePreceeding;
	}

	/**
	 * @return the interiorClockwiseSucceeding
	 */
	public EdgeLocation getInteriorClockwiseSucceeding(boolean normalize) {
		if(normalize)
			return interiorClockwiseSucceeding.getNormalizedLocation();
		return interiorClockwiseSucceeding;
	}

	/**
	 * @return the exterior
	 */
	public EdgeLocation getExterior(boolean normalize) {
		if(normalize)
			return exterior.getNormalizedLocation();
		return exterior;
	}

	public EdgesAdjacentToVertexResult(EdgeLocation exterior,
			EdgeLocation interiorClockwisePreceeding,
			EdgeLocation interiorClockwiseSucceeding) {
		
		
		if(Math.abs(exterior.getHexLoc().getX()) < 3 && Math.abs(exterior.getHexLoc().getY()) < 3){
			this.exterior = exterior;
		}
		else{
			this.exterior = null;
		}
		this.interiorClockwisePreceeding = interiorClockwisePreceeding;
		this.interiorClockwiseSucceeding = interiorClockwiseSucceeding;
	}
	
	public boolean contains(EdgeLocation location){
		return this.exterior.getNormalizedLocation() == location.getNormalizedLocation() || 
				this.interiorClockwisePreceeding.getNormalizedLocation() == location.getNormalizedLocation() ||
				this.interiorClockwiseSucceeding.getNormalizedLocation() == location.getNormalizedLocation();
	}
	
	
}
