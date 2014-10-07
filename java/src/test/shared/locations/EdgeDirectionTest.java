package test.shared.locations;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.locations.EdgeDirection;

public class EdgeDirectionTest {

	@Test
	public void testGetAdjacent() {
		EdgeDirection direction = EdgeDirection.North;
		EdgeDirection[] result1 = direction.getAdjacent(true, true);
		assertEquals(2, result1.length);
		assertEquals(EdgeDirection.NorthWest, result1[0]);
		assertEquals(EdgeDirection.NorthEast, result1[1]);
		
		direction = EdgeDirection.South;
		EdgeDirection[] result2 = direction.getAdjacent(true, true);
		assertEquals(2, result2.length);
		assertEquals(EdgeDirection.SouthEast, result2[0]);
		assertEquals(EdgeDirection.SouthWest, result2[1]);
		
		direction = EdgeDirection.NorthWest;
		EdgeDirection[] result3 = direction.getAdjacent(true, true);
		assertEquals(2, result3.length);
		assertEquals(EdgeDirection.SouthWest, result3[0]);
		assertEquals(EdgeDirection.North, result3[1]);
		
		EdgeDirection[] result4 = direction.getAdjacent(false, true);
		assertEquals(1, result4.length);
		assertEquals(EdgeDirection.North, result4[0]);
		
		EdgeDirection[] result5 = direction.getAdjacent(true, false);
		assertEquals(1, result5.length);
		assertEquals(EdgeDirection.SouthWest, result5[0]);
		
		EdgeDirection[] result6 = direction.getAdjacent(false, false);
		assertEquals(0, result6.length);
	}

}
