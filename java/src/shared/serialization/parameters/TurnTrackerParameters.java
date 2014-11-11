package shared.serialization.parameters;

public class TurnTrackerParameters {
	
	private String status;
	private int currentTurn;
	private int longestRoad;
	private int largestArmy;
	
	public TurnTrackerParameters(String status, int currentTurn, int longestRoad, int largestArmy){
		this.status = status;
		this.currentTurn = currentTurn;
		this.longestRoad = longestRoad;
		this.largestArmy = largestArmy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public int getLongestRoad() {
		return longestRoad;
	}

	public void setLongestRoad(int longestRoad) {
		this.longestRoad = longestRoad;
	}

	public int getLargestArmy() {
		return largestArmy;
	}

	public void setLargestArmy(int largestArmy) {
		this.largestArmy = largestArmy;
	}
}
