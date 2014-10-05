package client.manager.interfaces;

public interface GMGameInfoInterface {
	
	/**
	 * Determines whether a player can be robbed
	 * @param robbedPlayerIndex
	 * @return true if the player of the given index has at least one card 
	 */
	public boolean playerCanBeRobbed(int player_index);

}
