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
	 * @param id The id number of the Player who owns this piece
	 */
	public abstract void setPlayerId(int id);
	
	/**
	 * @return the id number of the Player who owns this piece
	 */
	public abstract int getPlayerId();
	
	public enum GamePieceType{
		CITY,
		SETTLEMENT,
		ROAD,
		ROBBER		
	}
}
