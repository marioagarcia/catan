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
	 * @param type DevCardType to check for
	 * @return boolean true if contains the card/else false
	 */
	public abstract boolean containsCard(DevCardType type);
	
	/**
	 * Removes a card from the Player's CardInventory
	 * The Player must have at least one of the given Card objects when this method is called. 
	 * @param type The DevCardType to be removed
	 * @return the type of card it is removing
	 * @throws NoSuchCardException if the card is not there
	 */
	public abstract DevCardType removeCard(DevCardType type) throws NoSuchCardException;

	/**
	 * returns a random card from the list of cards
	 * @return the type of the random card
	 */
	DevCardType buyDevCard();
}
