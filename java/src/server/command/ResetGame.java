package server.command;

/**
 * A Command object that returns the game to its initial state after all four players joined the game
 * @author Kevin
 */
public class ResetGame extends CatanCommand {

	public ResetGame(){
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		success = true;
	}
}
