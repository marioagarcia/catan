package client.model.turntracker;

public interface TurntrackerInterface {
	
	/**
	 * returns the TurntrackerInterface.Status representing where you are in the turn cycle
	 * @return the state of the game
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
	public void setCurrentTurn(int currentPlayerIndex);
	
	/**
	 * Sets the localPlayer's index so that it can know if it is it's turn or not when the
	 * localPlayer's is not known outside of the TurnTracker 
	 * @param local_player_index
	 */
	public void setLocalPlayerIndex(int local_player_index);
	
	/**
	 * Provides a way to find out if it is the localPlayer's turn if the localPlayer's index 
	 * is not known outside of the TurnTracker 
	 * @return true if it is the localPlayer's turn
	 */
	public boolean isLocalPlayerTurn();
	
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
	
	
	
	public enum Status{
		FIRST_ROUND,
		PLAYING,
		ROBBING,
		DISCARDING,
		ROLLING
	}
}
