package client.model.piece;

import client.model.map.HexInterface;

/**
 * Represents the Robber. The Robber can only exist on one Hex at a time.
 */
public interface RobberInterface extends GamePieceInterface
{
	/**
	 * Relocates the Robber to a different location on the board.
	 * @param location The new Hex where the robber will be located. Cannot be the same Hex that the Robber is already on.
	 */
	public abstract void moveTo(HexInterface location);
	
	/**
	 * @return The current Hex where the Robber is located
	 */
	public abstract HexInterface getLocation();
}
