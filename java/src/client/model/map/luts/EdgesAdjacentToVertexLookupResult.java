package client.model.map.luts;

import shared.locations.EdgeDirection;

class EdgesAdjacentToVertexLookupResult {
	
	private int xOffset;
	private int yOffset;
	private EdgeDirection externalEdge;
	private EdgeDirection clockwisePreceedingInternalEdge;
	private EdgeDirection clockwiseSucceedingInternalEdge;
	
	public EdgesAdjacentToVertexLookupResult(int xOffset, int yOffset,
			EdgeDirection externalEdge,
			EdgeDirection clockwisePreceedingInternalEdge,
			EdgeDirection clockwiseSucceedingInternalEdge) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.externalEdge = externalEdge;
		this.clockwisePreceedingInternalEdge = clockwisePreceedingInternalEdge;
		this.clockwiseSucceedingInternalEdge = clockwiseSucceedingInternalEdge;
	}
	
	
	/**
	 * @return the xOffset
	 */
	public int getxOffset() {
		return xOffset;
	}
	/**
	 * @return the yOffset
	 */
	public int getyOffset() {
		return yOffset;
	}
	/**
	 * @return the externalEdge
	 */
	public EdgeDirection getExternalEdge() {
		return externalEdge;
	}
	/**
	 * @return the clockwisePreceedingInternalEdge
	 */
	public EdgeDirection getClockwisePreceedingInternalEdge() {
		return clockwisePreceedingInternalEdge;
	}
	/**
	 * @return the clockwiseSucceedingInternalEdge
	 */
	public EdgeDirection getClockwiseSucceedingInternalEdge() {
		return clockwiseSucceedingInternalEdge;
	}

	@Override
	public String toString() {
		return "EdgesAdjacentToVertexLookupResult [xOffset=" + xOffset
				+ ", yOffset=" + yOffset + ", externalEdge=" + externalEdge
				+ ", clockwisePreceedingInternalEdge="
				+ clockwisePreceedingInternalEdge
				+ ", clockwiseSucceedingInternalEdge="
				+ clockwiseSucceedingInternalEdge + "]";
	}
}
