package shared.serialization.interfaces;

public interface SerializerTurnTrackerInterface {

	public abstract void setTurnTracker(String status, int currentTurn, int longestRoad, int largestArmy);
	
}
