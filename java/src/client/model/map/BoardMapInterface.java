package client.model.map;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * This class models the Map composed of the different hexes
 *
 */
public interface BoardMapInterface {

	/**
	 * returns the hex at the following location
	 * @return
	 * @throws HexNotFoundException 
	 */
	public HexInterface getHex(HexLocation location) throws HexNotFoundException;
	
}
