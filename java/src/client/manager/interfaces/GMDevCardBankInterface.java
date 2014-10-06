package client.manager.interfaces;

public interface GMDevCardBankInterface {
	
	/**
	 * Determines whether there are any dev cards left in the deck for a player to draw one
	 * @return true if there is at least one card left
	 */
	public boolean containsAnyCard();

}
