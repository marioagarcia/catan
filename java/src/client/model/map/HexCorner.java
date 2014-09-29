package client.model.map;

public interface HexCorner {

	/**
	 * Gives the possible enum values for a hex corner
	 */
	public enum HexCornerType {
		HEXCORNERTOPLEFT,
		HEXCORNERTOPRIGHT,
		HEXCORNERTOP,
		HEXCORNERBOTTOMLEFT,
		HEXCORNERBOTTOMRIGHT,
		HEXCORNERBOTTOM
	}
}
