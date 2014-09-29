package client.model.map;

/**
 * This class enumerates the different types of hex borders
 */
public interface HexBorder {

	/**
	 * Gives all of the possible enum values for a hex border
	 */
	public enum HexBorderType {
		HEXBORDERTOPLEFT,
		HEXBORDERTOPRIGHT,
		HEXBORDERTOP,
		HEXBORDERBOTTOMLEFT,
		HEXBORDERBOTTOMRIGHT,
		HEXBORDERBOTTOM
	}
}
