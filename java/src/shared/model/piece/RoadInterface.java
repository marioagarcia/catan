package shared.model.piece;

import shared.locations.EdgeLocation;

/**
 * Represents a Road on the board. Roads provide connections between a Player's City and Settlement pieces. 
 */
public interface RoadInterface extends GamePieceInterface
{
	/**
	 * Creates a road at the given location
	 * Location must not already be occupied, and must be adjacent to another Road/Settlement/City owned by the Player
	 * @param location the location where the road will go
	 */
	public abstract void setLocation(EdgeLocation location);
	
	/**
	 * Gets the location of the road
	 * @return The EdgeLocation where this Road is placed on the game board. 
	 * Null if this Road is not on the board.
	 */
	public abstract EdgeLocation getLocation();
}
