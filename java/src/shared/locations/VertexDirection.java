package shared.locations;

public enum VertexDirection
{
	West, NorthWest, NorthEast, East, SouthEast, SouthWest;
	
	private VertexDirection opposite;
	
	static
	{
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;
	}
	
	public VertexDirection getOppositeDirection()
	{
		return opposite;
	}
	
	public static VertexDirection[] getAdjacent(EdgeDirection edgeDirection){
		VertexDirection[] vertexDirections = new VertexDirection[6];
		vertexDirections[0] = NorthEast;
		vertexDirections[1] = East;
		vertexDirections[2] = SouthEast;
		vertexDirections[3] = SouthWest;
		vertexDirections[4] = West;
		vertexDirections[5] = NorthWest;
		
		EdgeDirection[] edgeDirections = new EdgeDirection[6];
		edgeDirections[0] = EdgeDirection.North;
		edgeDirections[1] = EdgeDirection.NorthEast;
		edgeDirections[2] = EdgeDirection.SouthEast;
		edgeDirections[3] = EdgeDirection.South;
		edgeDirections[4] = EdgeDirection.SouthWest;
		edgeDirections[5] = EdgeDirection.NorthWest;
		
		int foundIndex = 0;
		for(int i = 0; i < 6; i++)
			if(edgeDirection == edgeDirections[i]){
				foundIndex = i;
				break;
			}
		
		VertexDirection[] result = new VertexDirection[2];	
		
		if(foundIndex == 0)
			result[0] = vertexDirections[5];
		else
			result[0] = vertexDirections[foundIndex - 1];
		
		result[1] = vertexDirections[foundIndex];
		
		return result;
	}
}

