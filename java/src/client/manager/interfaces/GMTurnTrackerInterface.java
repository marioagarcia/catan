package client.manager.interfaces;

public interface GMTurnTrackerInterface {

	/**
	 * Verifies that a given player can roll the dice.
	 * @param player_index The index of the current player in the player array
	 * @return True if if is the player's turn and the model state is "rolling", otherwise returns false
	 */
	public boolean canRoll(int player_index);
}
