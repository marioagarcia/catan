package shared.model.card;

import shared.definitions.ResourceType;

/**
 * An object representing all the cards currently not owned by a Player
 */
public interface ResourceCardBankInterface
{
	/**
	 * Adds a card to the CardBank 
	 * @param card. The card being added
	 */
	public abstract void addCard(ResourceCardInterface card);
	
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
	 * @return the card that was removed
	 * @throws NoSuchCardException if the card is not there
	 */
	public abstract ResourceCardInterface removeCard(ResourceType type) throws NoSuchCardException;
	
}
