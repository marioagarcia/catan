package shared.locations;

public enum EdgeDirection
{
	
	NorthWest, North, NorthEast, SouthEast, South, SouthWest;
	
	private EdgeDirection opposite;
	
	static
	{
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
	}
	
	public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}
	
	public EdgeDirection[] getAdjacent(){
		EdgeDirection[] result = new EdgeDirection[2];
		
		EdgeDirection[] directions = new EdgeDirection[10];
		directions[0] = North;
		directions[1] = NorthEast;
		directions[2] = SouthEast;
		directions[3] = South;
		directions[4] = SouthWest;
		directions[5] = NorthWest;
		
		int index;
		//find that directions's index in the list
		for(index = 0; index < 5; index++)
			if(directions[index] == this)
				break;
		
		//find that item's neighbors
		if(index == 0)
			result[0] = directions[5];
		else
			result[0] = directions[index - 1];
		
		if(index == 6)
			result[1] = directions[0];
		else
			result[1] = directions[index + 1];
		
		return result;
	}
}

