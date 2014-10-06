package shared.serialization.interfaces;

public interface SerializerGameCommandInterface {
	
	/**
	 * Sets a game command
	 * 
	 * @param content The content/message of the command
	 * @param type The type of command, i.e. "sendChat"
	 * @param playerIndex The index of the player who made the command
	 */
	public abstract void setGameCommand(String content, String type, int playerIndex);

}
