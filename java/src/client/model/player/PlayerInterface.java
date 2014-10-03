package client.model.player;

import java.awt.Color;

/**
 * Represents a Player in the game
 */
public interface PlayerInterface
{
	/**
	 * Gets the full name of this Player
	 * @return the name of this Player
	 */
	public String getName();
	
	/**
	 * Gets the Color representing this Player in the current game
	 * @return the Color representing this Player
	 */
	public CatanColor getColor();
	
	/**
	 * Gets the id of this Player
	 * @return the id number of this Player
	 */
	public int getId();
	
	/**
	 * TODO
	 */
	public String toString();
	
	/**
	 * Gets the total number of victory points earned by this Player
	 * @return the number of victory points this Player has earned
	 */
	public int getPoints();
}
