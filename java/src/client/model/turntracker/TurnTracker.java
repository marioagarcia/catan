package client.model.turntracker;

import shared.serialization.interfaces.SerializerTurnTrackerInterface;
import client.manager.interfaces.GMTurnTrackerInterface;

public class TurnTracker implements TurntrackerInterface, GMTurnTrackerInterface, SerializerTurnTrackerInterface {
	
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
	public void setCurrentTurn(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
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
		return (player_index == currentPlayerIndex && status == Status.PLAYING);
	}

	@Override
	public boolean canPlayDevCard(int player_index) {
		return (player_index == currentPlayerIndex && status == Status.PLAYING);
	}
	
	@Override
	public void setTurnTracker(String status, int currentTurn, int longestRoad,
			int largestArmy){
		
		this.status = statusFromString(status);
		this.currentPlayerIndex = currentTurn;
		this.playerWithLongestRoad = longestRoad;
		this.playerWithLargestArmy = largestArmy;	
	}
	
	private static Status statusFromString(String text) {
	    if (text != null) {
	    	for (Status s : Status.values()) {
	    		if (text.equalsIgnoreCase(s.toString())) {
	    			return s;
	    		}
	    	}
	    }
	    return null;
	  }

}
