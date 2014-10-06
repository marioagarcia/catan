package client.model.piece;

import shared.locations.VertexLocation;
import client.model.map.HexCornerInterface;

/**
 * Represents an upgraded Settlement in the game. Provides double resources.
 */
public interface CityInterface extends BuildingInterface
{
	/**
	 * @param corner The HexCorner that this City will be placed on. Must not be currently occupied by another game piece.
	 * @see HexCornerInterface 
	 */
	public abstract void setLocation(VertexLocation corner);
	
	/**
	 * @return The HexCorner where this City is placed on the game board. Null if this City is not on the board.
	 * @see HexCornerInterface
	 */
	public abstract VertexLocation getLocation();
}
