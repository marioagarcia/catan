package shared.serialization.interfaces;

import shared.definitions.CatanColor;


/**
 * The distinction between PlayerInfoInterface and PlayerInterface is PlayerInfoInterface - this interface - is what is used to 
 * generate the list of all games.  PlayerInterface is the model for the player; all of the player's attributes
 * are stored there.
 * 
 *
 */
public interface SerializerPlayerInfoInterface {
	
	public abstract void setPlayerInfo(CatanColor playerColor, String playerName, int playerId);

}
