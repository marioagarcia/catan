package client.model.piece;

/**
 * Contains all the pieces not currently played on the board owned by a Player
 */
public interface GamePieceInventoryInterface
{
	/**
	 * Adds a piece into the Player's current inventory of GamePieces
	 * @param piece The GamePiece to be added.
	 */
	public abstract void addPiece(GamePieceInterface piece);
	
	/**
	 * Removes a single GamePiece from the Player's inventory. 
	 * @param piece The piece to be removed. An instance of this piece must exist in the Player's current inventory. 
	 */
	public abstract void removePiece(GamePieceInterface piece);
	
	/**
	 * Counts all instances of a single type of GamePiece in the Player's inventory
	 * @param piece The type of GamePiece to count
	 * @return The current count of a single type of GamePiece contained in the Player's inventory.
	 */
	public abstract int getPieceCount(GamePieceInterface piece);
	
}
