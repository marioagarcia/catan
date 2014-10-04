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
	
	
	public enum Status{
		FIRST_ROUND,
		PLAYING,
		ROBBING,
		DISCARDING
	}
}
