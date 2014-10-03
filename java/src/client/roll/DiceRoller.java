package client.roll;

import client.model.player.PlayerInterface;

public class DiceRoller implements DiceRollerInterface{

	@Override
	public int roll(PlayerInterface player) throws CannotRollException {
		int die1 = (int)(6.0 * Math.random()) + 1;
		int die2 = (int)(6.0 * Math.random()) + 1;
		
		return die1 + die2;
	}
}
