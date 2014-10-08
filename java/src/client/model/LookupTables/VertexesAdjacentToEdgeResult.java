package client.model.LookupTables;

import java.util.HashSet;
import java.util.Set;

import shared.locations.VertexLocation;

public class VertexesAdjacentToEdgeResult {
	private VertexLocation clockwisePreceeding;
	private VertexLocation clockwiseSucceeding;
	
	public VertexesAdjacentToEdgeResult(VertexLocation clockwisePreceeding, VertexLocation clockwiseSucceeding){
		this.clockwisePreceeding = clockwisePreceeding;
		this.clockwiseSucceeding = clockwiseSucceeding;
	}

	public VertexLocation getClockwisePreceeding(boolean normalize){
		if(normalize)
			return this.clockwisePreceeding.getNormalizedLocation();
		return this.clockwisePreceeding;
	}
	
	public VertexLocation getClockwiseSucceeding(boolean normalize){
		if(normalize)
			return this.clockwiseSucceeding.getNormalizedLocation();
		return this.clockwiseSucceeding;
	}
	
	public Set<VertexLocation> asSet(){
		Set<VertexLocation> result = new HashSet<VertexLocation>();
		
		result.add(this.getClockwisePreceeding(true));
		result.add(this.getClockwiseSucceeding(true));
		
		return result;
	}
}
