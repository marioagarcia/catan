package server.command;


/**
 * Updates the server log to record more or less detail
 * @author Kevin
 *
 */
public class ChangeLogLevel extends CatanCommand {

	/**
	 * Initializes the ChangeLogLevel object with the new log level, and the game the change applies to
	 * @param parameters A string representing the new log level. Must be on of the following options: 
	 * ALL, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, OFF
	 * @param game_id The integer ID of the game the operation is to be performed on. Must be an ID for an existing game
	 */
	public ChangeLogLevel(String parameters, int game_id){
		
	}
	
	/**
	 * Updates the logger to use the provided level of detail
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
