package shared.model.turntracker;

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
	
	/**
	 * Changes the index representing whose turn it is
	 * @param currentPlayerIndex The new current player's index
	 */
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
	
	/**
	 * Verifies that the given player can roll the dice
	 * @param player_index
	 * @return True if it is the player's turn, and the game is in the rolling phase. Otherwise, returns false.
	 */
	public boolean canRoll(int player_index);

	/**
	 * Checks whether the given player is allowed to buy a dev card
	 * @param player_index The index of a player in the current game
	 * @return True if it is the current player's turn and the game is in the playing phase. False otherwise
	 */
	public boolean canBuyDevCard(int player_index);

	/**
	 * Checks to see if the given player can play a development card
	 * @param player_index The index of the player in the current game
	 * @return True if it is the player's turn and if the game is in the playing phase. False otherwise.
	 */
	public boolean canPlayDevCard(int player_index);
	
	/**
	 * Verifies that the current player can make a move
	 * @param player_index The player busting a move
	 * @return True if it is the current player's turn and the game is in the playing phase
	 */
	public boolean canMakeMove(int player_index);
	
	
	
	public enum Status{
		FIRST_ROUND,
		SECOND_ROUND,
		PLAYING,
		ROBBING,
		DISCARDING,
		ROLLING
	}
}
