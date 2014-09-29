package client.model.map;

public interface HarborHex extends HexInterface {
	
	/**
	 * Gives the possible values for a harbor hex
	 */
	public enum HarborHexType {
		BRICKHARBORHEX,
		OREHARBORHEX,
		SHEEPHARBORHEX,
		WHEATHARBORHEX,
		WOODHARBORHEX
	}
}
