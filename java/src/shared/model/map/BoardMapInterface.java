package shared.model.map;

import shared.locations.HexLocation;

/**
 * This class models the Map composed of the different hexes
 *
 */
public interface BoardMapInterface {

	/**
	 * returns the hex at the following location
	 * @param location the location to be associated with the hex requested
	 * @return HexInterface the hex associated with the given location
	 * @throws HexNotFoundException if the hex doesn't exist
	 */
	public HexInterface getHex(HexLocation location) throws HexNotFoundException;
	
}
