package shared.model.piece;

import shared.locations.VertexLocation;

/**
 * Represents a Settlement in the game. Provides resources to the player who owns it.
 */
public interface SettlementInterface extends BuildingInterface
{
	/**
	 * @param corner The VertexLocation that this Settlement will be placed on. Must not be currently occupied by another City or Settlement.
	 */
	public abstract void setLocation(VertexLocation corner);
	
	/**
	 * @return The VertexLocation where this Settlement is placed on the game board
	 */
	public abstract VertexLocation getLocation();
}
