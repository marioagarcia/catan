package client.model.piece;

import shared.locations.VertexLocation;

/**
 * Represents a Settlement in the game. Provides resources to the player who owns it.
 */
public interface SettlementInterface extends BuildingInterface
{
	/**
	 * @param corner The HexCorner that this Settlement will be placed on. Must not be currently occupied by another City or Settlement.
	 * @see HexCornerInterface 
	 */
	public abstract void setLocation(VertexLocation corner);
	
	/**
	 * @return The HexCorner where this Settlement is placed on the game board. Null if this Settlement is not on the board.
	 * @see HexCornerInterface
	 */
	public abstract VertexLocation getLocation();
}
