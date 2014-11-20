package shared.model.turntracker;

import java.io.Serializable;

import shared.model.manager.interfaces.GMTurnTrackerInterface;
import shared.serialization.interfaces.SerializerTurnTrackerInterface;

@SuppressWarnings("serial")
public class TurnTracker implements TurntrackerInterface, GMTurnTrackerInterface, SerializerTurnTrackerInterface, Serializable {
	
	private Status status;
	private int localPlayerIndex;
	private int currentPlayerIndex;
	private int playerWithLongestRoad;
	private int playerWithLargestArmy;
	
	public TurnTracker(Status status, int currentTurnNumber, int longestRoad, int largestArmy){
		this.status = status;
		this.currentPlayerIndex = currentTurnNumber;
		this.playerWithLongestRoad = longestRoad;
		this.playerWithLargestArmy = largestArmy;
	}
	
	public TurnTracker() {

		//this.status = Status.FIRST_ROUND;

		this.setCurrentTurn(0);

		this.setPlayerWithLargestArmy(-1);

		this.setPlayerWithLongestRoad(-1);
		
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
	public void setLocalPlayerIndex(int local_player_index) {
		localPlayerIndex = local_player_index;		
	}

	@Override
	public boolean isLocalPlayerTurn() {
		return localPlayerIndex == getCurrentTurn();
	}

	@Override
	public int getLongestRoadStatus() {
		return this.playerWithLongestRoad;
	}

	public int getPlayerWithLongestRoad() {
		return playerWithLongestRoad;
	}

	public void setPlayerWithLongestRoad(int playerWithLongestRoad) {
		this.playerWithLongestRoad = playerWithLongestRoad;
	}

	public int getPlayerWithLargestArmy() {
		return playerWithLargestArmy;
	}

	public void setPlayerWithLargestArmy(int playerWithLargestArmy) {
		this.playerWithLargestArmy = playerWithLargestArmy;
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
	
	public boolean canMakeMove(int player_index){
		return (currentPlayerIndex == player_index && status == Status.PLAYING);
	}

	@Override
	public void setTurnTracker(String status, int currentTurn, int longestRoad,
			int largestArmy) {
		
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

	public String statusToString(Status status){
		String returnValue = "";
		switch(status){
			case FIRST_ROUND:
				returnValue = "FirstRound";
				break;
			case SECOND_ROUND:
				returnValue = "SecondRound";
				break;
			case PLAYING:
				returnValue = "Playing";
				break;
			case ROBBING:
				returnValue = "Robbing";
				break;
			case DISCARDING:
				returnValue = "Discarding";
				break;
			case ROLLING:
				returnValue = "Rolling";
				break;
		}
		return returnValue;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPlayerIndex;
		result = prime * result + playerWithLargestArmy;
		result = prime * result + playerWithLongestRoad;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurnTracker other = (TurnTracker) obj;
		if (currentPlayerIndex != other.currentPlayerIndex)
			return false;
		if (playerWithLargestArmy != other.playerWithLargestArmy)
			return false;
		if (playerWithLongestRoad != other.playerWithLongestRoad)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
