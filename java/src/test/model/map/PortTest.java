package test.model.map;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.model.map.Port;

public class PortTest {

	
	@Test
	public void testCRUD(){
		//create
		PortType resourceType = PortType.BRICK;
		HexLocation hexLocation = new HexLocation(5,5);
		EdgeDirection edgeDirection = EdgeDirection.North;
		EdgeLocation location = new EdgeLocation(hexLocation, edgeDirection);
		int ratio = 0;
		
		Port port = new Port(resourceType, location, ratio);
		
		//read
		assertEquals(resourceType, port.getResource());
		assertEquals(ratio, port.getRatio());
		assertEquals(location, port.getLocation());
		
		//update
		PortType secondResourceType = PortType.ORE;
		HexLocation secondHexLocation = new HexLocation(4,4);
		EdgeDirection secondEdgeDirection = EdgeDirection.South;
		EdgeLocation secondLocation = new EdgeLocation(secondHexLocation, secondEdgeDirection);
		int secondRatio = 1;
		
		port.setResource(secondResourceType);
		port.setLocation(secondLocation);
		port.setRatio(secondRatio);
		
		//read again
		assertEquals(secondResourceType, port.getResource());
		assertEquals(secondRatio, port.getRatio());
		assertEquals(secondLocation, port.getLocation());
		
		//test serializer functionality
		port.setPort(ratio, resourceType, location);
		
		assertEquals(resourceType, port.getResource());
		assertEquals(ratio, port.getRatio());
		assertEquals(location, port.getLocation());
	}
}
