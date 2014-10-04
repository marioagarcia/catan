package client.roll;

public interface DiceRollerInterface 
{
	
	/**
	 * perform the roll action
	 * @param player the player who is rolling the dice
	 * @return int with value 2-12 if player can roll dice
	 */
	public int roll();

}
