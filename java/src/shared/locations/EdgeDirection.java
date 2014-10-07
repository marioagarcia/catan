package shared.locations;

import java.util.Arrays;

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
	
	public EdgeDirection[] getAdjacent(boolean allowClockwisePreceeding, boolean allowClockwiseSucceeding){
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
		
		int numberPushed = 0;
		//find that item's neighbors
		if(allowClockwisePreceeding)
			if(index == 0)
				result[numberPushed++] = directions[5];
			else
				result[numberPushed++] = directions[index - 1];
		
		if(allowClockwiseSucceeding)
			if(index == 5)
				result[numberPushed++] = directions[0];
			else
				result[numberPushed++] = directions[index + 1];
		
		return Arrays.copyOf(result, numberPushed);
	}
}

