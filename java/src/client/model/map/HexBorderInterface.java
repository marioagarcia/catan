package client.model.map;

import client.model.piece.RoadInterface;

/**
 * This class enumerates the different types of hex borders
 */
public interface HexBorderInterface {
	
	public abstract RoadInterface getRoad();
	public abstract void setRoad(RoadInterface road);
	public abstract boolean canBuildRoad();

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
