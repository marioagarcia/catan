package test.shared.locations;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

public class EdgeLocationTest {

	@Test
	public void testGetAdjacent() {
		HexLocation hexLocation = new HexLocation(0,0);

		//0,0 NorthEast
		EdgeLocation edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.NorthEast);
		
		EdgeLocation[] result1 = edgeLocation.getAdjacent(true, true);
		
		assertEquals(4, result1.length);
		assertEquals(0, result1[0].getHexLoc().getX());
		assertEquals(0, result1[0].getHexLoc().getY());
		assertEquals(EdgeDirection.North, result1[0].getDir());
		
		assertEquals(0, result1[1].getHexLoc().getX());
		assertEquals(0, result1[1].getHexLoc().getY());
		assertEquals(EdgeDirection.SouthEast, result1[1].getDir());
		
		assertEquals(1, result1[2].getHexLoc().getX());
		assertEquals(-1, result1[2].getHexLoc().getY());
		assertEquals(EdgeDirection.South, result1[2].getDir());
		
		assertEquals(1, result1[3].getHexLoc().getX());
		assertEquals(-1, result1[3].getHexLoc().getY());
		assertEquals(EdgeDirection.NorthWest, result1[3].getDir());
		
		//0,2 South
		hexLocation = new HexLocation(0,2);
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.South);
		
		EdgeLocation[] result2 = edgeLocation.getAdjacent(true, true);
		
		assertEquals(2, result2.length);
		assertEquals(0, result2[0].getHexLoc().getX());
		assertEquals(2, result2[0].getHexLoc().getY());
		assertEquals(EdgeDirection.SouthEast, result2[0].getDir());
		
		assertEquals(0, result2[1].getHexLoc().getX());
		assertEquals(2, result2[1].getHexLoc().getY());
		assertEquals(EdgeDirection.SouthWest, result2[1].getDir());
		
		//0,2 NorthWest
		hexLocation = new HexLocation(-2,2);
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.NorthWest);
		
		EdgeLocation[] result3 = edgeLocation.getAdjacent(true, true);
		
		assertEquals(2, result3.length);
		assertEquals(-2, result3[0].getHexLoc().getX());
		assertEquals(2, result3[0].getHexLoc().getY());
		assertEquals(EdgeDirection.SouthWest, result3[0].getDir());
		
		assertEquals(-2, result3[1].getHexLoc().getX());
		assertEquals(2, result3[1].getHexLoc().getY());
		assertEquals(EdgeDirection.North, result3[1].getDir());
		
		
		
		//test removing one side (
		hexLocation = new HexLocation(0,0);
		edgeLocation = new EdgeLocation(hexLocation, EdgeDirection.NorthEast);
		
		
		EdgeLocation[] result4 = edgeLocation.getAdjacent(false, true);
		
//		assertEquals(2, result4.length);
//		assertEquals(0, result4[0].getHexLoc().getX());
//		assertEquals(0, result4[0].getHexLoc().getY());
//		assertEquals(EdgeDirection.North, result4[0].getDir());
//		
//		assertEquals(0, result4[1].getHexLoc().getX());
//		assertEquals(0, result4[1].getHexLoc().getY());
//		assertEquals(EdgeDirection.SouthEast, result4[1].getDir());
//		
//		assertEquals(1, result4[2].getHexLoc().getX());
//		assertEquals(-1, result4[2].getHexLoc().getY());
//		assertEquals(EdgeDirection.South, result4[2].getDir());
//		
//		assertEquals(1, result4[3].getHexLoc().getX());
//		assertEquals(-1, result4[3].getHexLoc().getY());
//		assertEquals(EdgeDirection.NorthWest, result4[3].getDir());	
		
		
		
		for(EdgeLocation location : result4){
			System.out.println(location);
		}
	}

}
