package client.model.piece;

import shared.locations.EdgeLocation;
import client.model.map.HexBorder;

/**
 * Represents a Road on the board. Roads provide connections between a Player's City and Settlement pieces. 
 */
public interface RoadInterface extends GamePieceInterface
{
	/**
	 * @param border The HexBorder that this Road will be placed on. 
	 * Must not be currently occupied by another GamePiece, 
	 * and must be adjacent to another Road or Settlement/City owned by the Player.
	 * 
	 * @see HexBorder 
	 */
	public abstract void setLocation(EdgeLocation location);
	
	/**
	 * @return The HexBorder where this Road is placed on the game board. 
	 * Null if this Road is not on the board.
	 * 
	 * @see HexBorder
	 */
	public abstract EdgeLocation getLocation();
}
