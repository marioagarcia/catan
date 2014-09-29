package client.model.card;

import client.model.player.PlayerInterface;

/**
 * An object representing all the cards currently not owned by a Player
 */
public interface CardBankInterface
{
	/**
	 * Removes a card from the CardBank and places it in the given Player's CardInventory.
	 * There must be at least one of the given card type that exists in the CardBank when this method is called. 
	 * @param recipient The Player receiving the Card from the CardBank
	 * @param card The Card to be given
	 */
	public abstract void giveCard(PlayerInterface recipient, CardInterface card);
	
	/**
	 * Removes a card from the Player's CardInventory and places it in the CardBank.
	 * The Player must have at least one of the given Card objects when this method is called. 
	 * @param giver The Player giving the Card to the CardBank
	 * @param card The Card to be taken
	 */
	public abstract void takeCard(PlayerInterface giver, CardInterface card);
}
