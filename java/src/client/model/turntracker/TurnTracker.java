package client.model.turntracker;

public class TurnTracker implements TurntrackerInterface {
	
	private Status status;
	private int currentTurnNumber;
	private SpecialStatus longestRoad;
	private SpecialStatus largestArmy;
	
	public TurnTracker(Status status, int currentTurnNumber, SpecialStatus longestRoad, SpecialStatus largestArmy){
		this.status = status;
		this.currentTurnNumber = currentTurnNumber;
		this.longestRoad = longestRoad;
		this.largestArmy = largestArmy;
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
		return this.currentTurnNumber;
	}
	
	@Override
	public void incrementTurn(){
		this.currentTurnNumber++;
		//TODO change the turn status to reflect the new turn
	}

	@Override
	public SpecialStatus getLongestRoadStatus() {
		return this.longestRoad;
	}

	@Override
	public SpecialStatus getLargestArmy() {
		return this.largestArmy;
	}

}
