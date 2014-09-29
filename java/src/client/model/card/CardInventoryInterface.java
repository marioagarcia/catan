package client.model.card;

/**
 * Stores all of the cards owned by a Player
 */
public interface CardInventoryInterface
{
	/**
	 * Counts all instance of a single type of Card in the Player's hand
	 * @param card An instance of the type of Card to count
	 * @return The number of the given type of Card that the Player currently has
	 */
	public abstract int getCount(CardInterface card);
	
	/**
	 * Inserts a Card into the Player's inventory
	 * @param card The Card to add to the Player's inventory
	 */
	public abstract void addCard(CardInterface card);
	
	/**
	 * Removes a single Card of the given type from the Player's inventory. 
	 * @param card The Card to remove from the Player. The Player must have at least
	 * one instance of the card to be removed.
	 */
	public abstract void removeCard(CardInterface card);
}
