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
	 * transitions to the next turn
	 * this should also reset the status
	 * this should only be called at the end of your own turn,
	 */
	public void incrementTurn();
	
	/**
	 * player's status with regards to the longestRoad card
	 * @return status
	 */
	public SpecialStatus getLongestRoadStatus();
	
	/**
	 * player's status with regards to the largestArmy card
	 * @return status
	 */
	public SpecialStatus getLargestArmy();
	
	
	public enum Status{
		FIRST_ROUND
	}
	
	public enum SpecialStatus{
		DOES_NOT_HAVE_SPECIAL,
		HAS_SPECIAL,
		SPECIAL_NOT_ISSUED
		
	}

}
