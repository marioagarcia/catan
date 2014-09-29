package client.model.piece;

import client.model.map.HexCorner;

/**
 * Represents an upgraded Settlement in the game. Provides double resources.
 */
public interface CityInterface extends BuildingInterface
{
	/**
	 * @param corner The HexCorner that this City will be placed on. Must not be currently occupied by another game piece.
	 * @see HexCorner 
	 */
	public abstract void setLocation(HexCorner corner);
	
	/**
	 * @return The HexCorner where this City is placed on the game board. Null if this City is not on the board.
	 * @see HexCorner
	 */
	public abstract HexCorner getLocation();
}
