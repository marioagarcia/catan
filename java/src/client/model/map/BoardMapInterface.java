package client.model.map;
/**
 * This class models the Map composed of the different hexes
 *
 */
public interface BoardMapInterface {

	/**
	 * Returns whether or not a GamePiece can be added
	 * @return true if successful
	 */
	public abstract boolean CanPlaceGamePiece();
}
