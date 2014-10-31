package shared.locations;

import java.util.ArrayList;
import java.util.Arrays;

import client.model.map.BoardMap;

/**
 * Represents the location of an edge on a hex map
 */
public class EdgeLocation
{
	
	private HexLocation hexLoc;
	private EdgeDirection dir;
	
	public EdgeLocation(HexLocation hexLoc, EdgeDirection dir)
	{
		setHexLoc(hexLoc);
		setDir(dir);
	}
	
	public HexLocation getHexLoc()
	{
		return hexLoc;
	}
	
	private void setHexLoc(HexLocation hexLoc)
	{
		if(hexLoc == null)
		{
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		this.hexLoc = hexLoc;
	}
	
	public EdgeDirection getDir()
	{
		return dir;
	}
	
	private void setDir(EdgeDirection dir)
	{
		this.dir = dir;
	}
	
	@Override
	public String toString()
	{
		return "EdgeLocation [hexLoc=" + hexLoc + ", dir=" + dir + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + ((hexLoc == null) ? 0 : hexLoc.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		EdgeLocation other = ((EdgeLocation)obj).getNormalizedLocation();
		
		if(this.getNormalizedLocation().dir != other.dir){
			return false;
		}
		if(this.hexLoc == null){
			if(other.hexLoc != null){
				return false;
			}
		}
		else if(!this.getNormalizedLocation().hexLoc.equals(other.hexLoc)){
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a canonical (i.e., unique) value for this edge location. Since
	 * each edge has two different locations on a map, this method converts a
	 * hex location to a single canonical form. This is useful for using hex
	 * locations as map keys.
	 * 
	 * @return Normalized hex location
	 */
	public EdgeLocation getNormalizedLocation()
	{
		
		// Return an EdgeLocation that has direction NW, N, or NE
		
		switch (dir)
		{
			case NorthWest:
			case North:
			case NorthEast:
				return this;
			case SouthWest:
			case South:
			case SouthEast:
				return new EdgeLocation(hexLoc.getNeighborLoc(dir),
										dir.getOppositeDirection());
			default:
				assert false;
				return null;
		}
	}
	
	public EdgeLocation[] getAdjacent(boolean allowClockwisePreceeding, boolean allowClockwiseSucceeding, BoardMap boardMap){
		
		ArrayList<EdgeLocation> result = new ArrayList<EdgeLocation>();
		EdgeDirection[] interiorDirections = this.getDir().getAdjacent(allowClockwisePreceeding, allowClockwiseSucceeding);
		
		int numberCompletedFromFirstSet = 0;
		if(allowClockwisePreceeding){
			EdgeLocation new_location =  new EdgeLocation(this.getHexLoc(), interiorDirections[numberCompletedFromFirstSet++]).getNormalizedLocation();
			if(boardMap.isValid(new_location)){
				result.add(new_location);
			}
		}
		
		if(allowClockwiseSucceeding){
			EdgeLocation new_location = new EdgeLocation(this.getHexLoc(), interiorDirections[numberCompletedFromFirstSet++]).getNormalizedLocation();
			if(boardMap.isValid(new_location)){
				result.add(new_location);
			}
		}
		
		HexLocation sisterHex = this.getHexLoc().getNeighborLoc(this.getDir());
		
		EdgeLocation sisterEdgeLocation = new EdgeLocation(sisterHex, this.getDir().getOppositeDirection());
		EdgeDirection[] exteriorDirections = sisterEdgeLocation.getDir().getAdjacent(allowClockwiseSucceeding, allowClockwisePreceeding);
		
		int numberCompletedFromSecondSet = 0;
		if(allowClockwisePreceeding){
			EdgeLocation new_location =  new EdgeLocation(sisterEdgeLocation.getHexLoc(), exteriorDirections[numberCompletedFromSecondSet++]).getNormalizedLocation();
			if(boardMap.isValid(new_location)){
				result.add(new_location);
			}
		}
		if(allowClockwiseSucceeding){
			EdgeLocation new_location = new EdgeLocation(sisterEdgeLocation.getHexLoc(), exteriorDirections[numberCompletedFromSecondSet++]).getNormalizedLocation();
			if(boardMap.isValid(new_location)){
				result.add(new_location);
			}
		}
		return result.toArray(new EdgeLocation[0]);
	}

	public static EdgeLocation[] getAdjacent(VertexLocation vertexLocation){
		//TODO this needs to capture the third road
		VertexDirection[] vertexDirections = new VertexDirection[6];
		vertexDirections[0] = VertexDirection.NorthEast;
		vertexDirections[1] = VertexDirection.East;
		vertexDirections[2] = VertexDirection.SouthEast;
		vertexDirections[3] = VertexDirection.SouthWest;
		vertexDirections[4] = VertexDirection.West;
		vertexDirections[5] = VertexDirection.NorthWest;
		
		EdgeDirection[] edgeDirections = new EdgeDirection[6];
		edgeDirections[0] = EdgeDirection.North;
		edgeDirections[1] = EdgeDirection.NorthEast;
		edgeDirections[2] = EdgeDirection.SouthEast;
		edgeDirections[3] = EdgeDirection.South;
		edgeDirections[4] = EdgeDirection.SouthWest;
		edgeDirections[5] = EdgeDirection.NorthWest;
		
		int indexFoundAt = 0;
		for(int i = 0; i < 6; i++)
			if(vertexDirections[i] == vertexLocation.getDir()){
				indexFoundAt = i;
				break;
			}
		
		EdgeLocation[] results = new EdgeLocation[2];
		
		HexLocation hexLocation = vertexLocation.getHexLoc();
		EdgeDirection edgeDirection1;
		EdgeDirection edgeDirection2;
		
		if(indexFoundAt == 5)
			edgeDirection2 = edgeDirections[0];
		else
			edgeDirection2 = edgeDirections[indexFoundAt + 1];
		
		if(indexFoundAt == 0)
			edgeDirection1 = edgeDirections[5];
		else
			edgeDirection1 = edgeDirections[indexFoundAt];
		
		
		results[0] = new EdgeLocation(hexLocation, edgeDirection1).getNormalizedLocation();
		results[1] = new EdgeLocation(hexLocation, edgeDirection2).getNormalizedLocation();
		
		return results;
	}

}

