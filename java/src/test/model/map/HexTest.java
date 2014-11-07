package test.model.map;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.HexType;
import shared.locations.HexLocation;
import shared.model.map.Hex;

public class HexTest {

	@Test
	public void testCRUD() {

		//create
		HexLocation hexLocation = new HexLocation(5,5);
		HexType hexType = HexType.BRICK;
		int number = 0;
		Hex hex = new Hex(hexLocation, hexType, number);
		
		//read
		assertEquals(hexLocation, hex.getLocation());
		assertEquals(hexType, hex.getType());
		assertEquals(number, hex.getNumber());
		
		
		//update
		HexLocation secondHexLocation = new HexLocation(4,4);
		HexType secondHexType = HexType.SHEEP;
		int secondNumber = 1;
		
		hex.setHex(secondHexLocation, secondHexType, secondNumber);
		
		//read again
		assertEquals(secondHexLocation, hex.getLocation());
		assertEquals(secondHexType, hex.getType());
		assertEquals(secondNumber, hex.getNumber());
		
		//objects not currently persistently saved, no need for deletion testing
		
		//test toString
		System.out.print(hex.toString());
		assertTrue("Hex [location=HexLocation [x=4, y=4], resource=SHEEP, number=1]".equals(hex.toString()));
	}

}
