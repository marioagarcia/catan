package shared.model.card;

import shared.definitions.ResourceType;

/**
 * An object representing all the cards currently not owned by a Player
 */
public interface ResourceCardBankInterface
{
	/**
	 * Adds a card to the CardBank 
	 * @param card The card being added
	 */
	public void addCard(ResourceType card);
	
	/**
	 * checks if the bank contains a card of the specified type
	 * @param type1 ResourceCardType to check for
	 * @param type2 ResourceCardType to check for
	 * @return boolean. true if contains the cards/else false
	 */
	public boolean containsCards(ResourceType type1, ResourceType type2);
	
	/**
	 * Removes a card from the Player's CardInventory
	 * The Player must have at least one of the given Card objects when this method is called. 
	 * @param type The Card type to be removed
	 */
	public abstract void removeCard(ResourceType type);

	/**
	 * subtracts the two resource cards that were given to the player
	 * @param type1 first resource card
	 * @param type2 second resource card
	 */
	void yearOfPlentyPlayed(ResourceType type1, ResourceType type2);
}
