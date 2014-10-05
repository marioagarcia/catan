package client.model.turntracker;

import client.manager.interfaces.GMTurnTrackerInterface;

public class TurnTracker implements TurntrackerInterface, GMTurnTrackerInterface {
	
	private Status status;
	private int currentPlayerIndex;
	private int playerWithLongestRoad;
	private int playerWithLargestArmy;
	
	public TurnTracker(Status status, int currentTurnNumber, int longestRoad, int largestArmy){
		this.status = status;
		this.currentPlayerIndex = currentTurnNumber;
		this.playerWithLongestRoad = longestRoad;
		this.playerWithLargestArmy = largestArmy;
	}

	@Override
	public Status getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int getCurrentTurn() {
		return this.currentPlayerIndex;
	}

	@Override
	public int getLongestRoadStatus() {
		return this.playerWithLongestRoad;
	}

	@Override
	public int getLargestArmy() {
		return this.playerWithLargestArmy;
	}

	@Override
	public boolean canRoll(int player_index){
		return (currentPlayerIndex == player_index && this.status == Status.ROLLING);
	}

	@Override
	public boolean canBuyDevCard(int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayDevCard(int player_index) {
		// TODO Auto-generated method stub
		return false;
	}

}
