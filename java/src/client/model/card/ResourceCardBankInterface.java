package client.model.card;

import client.model.player.PlayerInterface;

/**
 * An object representing all the cards currently not owned by a Player
 */
public interface ResourceCardBankInterface
{
	/**
	 * Removes a card from the CardBank and places it in the given Player's CardInventory.
	 * There must be at least one of the given card type that exists in the CardBank when this method is called. 
	 * @param recipient The Player receiving the Card from the CardBank
	 * @param card The Card to be given
	 */
	public abstract void addCard(ResourceCardInterface card);
	
	/**
	 * checks if the bank contains a card of the specified type
	 * @param type ResourceCardType to check for
	 * @return boolean. true if contains the card/else false
	 */
	public abstract boolean containsCard(ResourceCardInterface.ResourceCardType type);
	
	/**
	 * Removes a card from the Player's CardInventory and places it in the CardBank.
	 * The Player must have at least one of the given Card objects when this method is called. 
	 * @param giver The Player giving the Card to the CardBank
	 * @param card The Card to be taken
	 */
	public abstract ResourceCardInterface removeCard(ResourceCardInterface.ResourceCardType type) throws NoSuchCardException;
	
}
