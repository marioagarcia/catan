package client.roll;

public class DiceRoller implements DiceRollerInterface{

	@Override
	public int roll() {
		int die1 = (int)(6.0 * Math.random()) + 1;
		int die2 = (int)(6.0 * Math.random()) + 1;
		
		return die1 + die2;
	}
}
