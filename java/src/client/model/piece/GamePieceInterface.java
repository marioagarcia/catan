package client.model.piece;

/**
 * Represents a Piece in the Player's inventory.
 */
public interface GamePieceInterface
{
	/**
	 * gets the type of the game piece
	 * @return GamePieceType representing the type of the piece in question
	 */
	public abstract GamePieceType getType();
	
	/**
	 * @param index The index of the Player who owns this piece
	 */
	public abstract void setPlayerIndex(int index);
	
	/**
	 * @return the index of the Player who owns this piece
	 */
	public abstract int getPlayerIndex();
	
	public enum GamePieceType{
		CITY,
		SETTLEMENT,
		ROAD,
		ROBBER		
	}
}
