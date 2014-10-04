package client.model.turntracker;

public interface TurntrackerInterface {
	
	/**
	 * returns the TurntrackerInterface.Status representing where you are in the turn cycle
	 * @return
	 */
	public Status getStatus();
	
	/**
	 * set currentTurnStatus.
	 * @param status the new Status
	 */
	public void setStatus(Status status);
	
	/**
	 * @return the integer number of the current turn, 0 being the setup phase
	 */
	public int getCurrentTurn();
	
	/**
	 * player's status with regards to the longestRoad card
	 * @return status
	 */
	public int getLongestRoadStatus();
	
	/**
	 * player's status with regards to the largestArmy card
	 * @return status
	 */
	public int getLargestArmy();
	
	/**
	 * Verifies that a given player can roll the dice.
	 * @param player_index The index of the current player in the player array
	 * @return True if if is the player's turn and the model state is "rolling", otherwise returns false
	 */
	public boolean canRoll(int player_index);
	
	
	public enum Status{
		FIRST_ROUND,
		PLAYING,
		ROBBING,
		DISCARDING,
		ROLLING
	}
}
