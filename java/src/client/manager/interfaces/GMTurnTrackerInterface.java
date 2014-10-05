package client.manager.interfaces;

public interface GMTurnTrackerInterface {

	/**
	 * Verifies that a given player can roll the dice.
	 * @param player_index The index of the current player in the player array
	 * @return true if if is the player's turn and the model state is "rolling", otherwise returns false
	 */
	public boolean canRoll(int player_index);
	
	/**
	 * Verifies that the given player can buy a dev card
	 * @param player_index
	 * @return true if it is the player's turn and the status of the game is playing
	 */
	public boolean canBuyDevCard(int player_index);
	
	/**
	 * Verifies that the given player can play a dev card
	 * @param player_index
	 * @return true if it is the player's turn and the status of the game is playing
	 */
	public boolean canPlayDevCard(int player_index);
}
