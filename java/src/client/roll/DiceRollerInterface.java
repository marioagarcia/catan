package client.roll;

import client.model.player.PlayerInterface;

public interface DiceRollerInterface 
{
	
	/**
	 * perform the roll action
	 * @param player the player who is rolling the dice
	 * @return int with value 2-12 if player can roll dice
	 * @throws CannotRollException if it is not the players turn or if he has already rolled
	 */
	public int roll(PlayerInterface player) throws CannotRollException;

}
