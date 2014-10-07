package test.shared.locations;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.locations.EdgeDirection;

public class EdgeDirectionTest {

	@Test
	public void testGetAdjacent() {
		EdgeDirection direction = EdgeDirection.North;
		EdgeDirection[] result1 = direction.getAdjacent();
		assertEquals(EdgeDirection.NorthWest, result1[0]);
		assertEquals(EdgeDirection.NorthEast, result1[1]);
		
		direction = EdgeDirection.South;
		EdgeDirection[] result2 = direction.getAdjacent();
		assertEquals(EdgeDirection.SouthEast, result2[0]);
		assertEquals(EdgeDirection.SouthWest, result2[1]);
		
		direction = EdgeDirection.NorthWest;
		EdgeDirection[] result3 = direction.getAdjacent();
		System.out.print(result3.length);
		assertEquals(EdgeDirection.SouthWest, result3[0]);
		assertEquals(EdgeDirection.North, result3[1]);
	}

}
