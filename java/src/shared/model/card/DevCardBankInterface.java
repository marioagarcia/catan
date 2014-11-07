package shared.model.card;

import shared.definitions.DevCardType;

public interface DevCardBankInterface {
	
	/**
	 * Adds a card to the CardBank 
	 * @param card The card being added
	 */
	public abstract void addCard(DevCardType card);
	
	/**
	 * checks if the bank contains a card of the specified type
	 * @param type ResourceCardType to check for
	 * @return boolean true if contains the card/else false
	 */
	public abstract boolean containsCard(DevCardType type);
	
	/**
	 * Removes a card from the Player's CardInventory and places it in the CardBank.
	 * The Player must have at least one of the given Card objects when this method is called. 
	 * @param type The Card type to be removed
	 * @return the type of card it is removing
	 * @throws NoSuchCardException if the card is not there
	 */
	public abstract DevCardType removeCard(DevCardType type) throws NoSuchCardException;
}
