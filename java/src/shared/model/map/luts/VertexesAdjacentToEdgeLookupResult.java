package shared.model.map.luts;

import shared.locations.VertexDirection;

class VertexesAdjacentToEdgeLookupResult {
	VertexDirection clockwisePreceeding;
	VertexDirection clockwiseSucceeding;
	
	public VertexesAdjacentToEdgeLookupResult(VertexDirection clockwisePreceeding, VertexDirection clockwiseSucceeding){
		this.clockwisePreceeding = clockwisePreceeding;
		this.clockwiseSucceeding = clockwiseSucceeding;
	}
	
	public VertexDirection getClockwisePreceeding(){
		return this.clockwisePreceeding;
	}
	
	public VertexDirection getClockwiseSucceeding(){
		return this.clockwiseSucceeding;
	}
}
