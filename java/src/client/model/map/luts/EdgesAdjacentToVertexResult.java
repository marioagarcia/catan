package client.model.map.luts;

import java.util.HashSet;
import java.util.Set;

import client.model.map.BoardMap;
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
		if(this.exterior == null)
			return null;
		if(normalize)
			return exterior.getNormalizedLocation();
		return exterior;
	}

	public EdgesAdjacentToVertexResult(EdgeLocation exterior,
			EdgeLocation interiorClockwisePreceeding,
			EdgeLocation interiorClockwiseSucceeding,
			BoardMap map) {
		
		
		if(map.isValid(exterior)){
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
	
	public Set<EdgeLocation> asSet(){
		Set<EdgeLocation> result = new HashSet<EdgeLocation>();
		if( this.getExterior(true) != null )
			result.add(this.getExterior(true));
		
		result.add(this.getInteriorClockwisePreceeding(true));
		result.add(this.getInteriorClockwiseSucceeding(true));
		
		return result;
	}
	
	
}
