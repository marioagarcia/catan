package test.shared.locations;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.model.map.BoardMap;

public class EdgeLocationTest {

	@Test
	public void testGetAdjacent() {
		HexLocation hexLocation = new HexLocation(0,0);

		//0,0 NorthEast
		EdgeLocation edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.NorthEast);
        BoardMap map = new BoardMap(true, true, true);

		EdgeLocation[] result1 = edgeLocation.getAdjacent(true, true, map);

        assertEquals(4, result1.length);
        assertEquals(new EdgeLocation(new HexLocation(0,0), EdgeDirection.North), result1[0]);
        assertEquals(new EdgeLocation(new HexLocation(1,0), EdgeDirection.NorthWest), result1[1]);
        assertEquals(new EdgeLocation(new HexLocation(1,0), EdgeDirection.North), result1[2]);
        assertEquals(new EdgeLocation(new HexLocation(1,-1), EdgeDirection.NorthWest), result1[3]);

		//0,2 South
		hexLocation = new HexLocation(0,2);
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.South);

		EdgeLocation[] result2 = edgeLocation.getAdjacent(true, true, map);

		assertEquals(2, result2.length);
        assertEquals(new EdgeLocation(new HexLocation(1,2), EdgeDirection.NorthWest), result2[0]);
        assertEquals(new EdgeLocation(new HexLocation(-1,3), EdgeDirection.NorthEast), result2[1]);

		//0,2 NorthWest
		hexLocation = new HexLocation(-2,2);
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.NorthWest);

		EdgeLocation[] result3 = edgeLocation.getAdjacent(true, true, map);

		assertEquals(3, result3.length);
        assertEquals(new EdgeLocation(new HexLocation(-3,3), EdgeDirection.NorthEast), result3[0]);
        assertEquals(new EdgeLocation(new HexLocation(-2,2), EdgeDirection.North), result3[1]);
        assertEquals(new EdgeLocation(new HexLocation(-3,2), EdgeDirection.NorthEast), result3[2]);

		//test removing one side (
		hexLocation = new HexLocation(0,0);
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.NorthEast);


		EdgeLocation[] result4 = edgeLocation.getAdjacent(false, true, map);

		assertEquals(2, result4.length);
        assertEquals(new EdgeLocation(new HexLocation(1,0), EdgeDirection.NorthWest), result4[0]);
        assertEquals(new EdgeLocation(new HexLocation(1,0), EdgeDirection.North), result4[1]);
	}

}
