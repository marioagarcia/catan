package client.model.piece;

/**
 * Represents a Piece in the Player's inventory.
 */
public interface GamePieceInterface
{
	/**
	 * @param id The id number of the Player who owns this piece
	 */
	public abstract void setPlayerId(int id);
	
	/**
	 * @return the id number of the Player who owns this piece
	 */
	public abstract int getPlayerId();
}
