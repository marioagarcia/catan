package shared.serialization.interfaces;

import shared.definitions.CatanColor;

public interface PlayerInterface {

	public abstract void setPlayer(ResourceListInterface resourceList, DevCardListInterface oldDevCards, DevCardListInterface newDevCards,
								   int roads, int cities, int settlements, int soldiers, int victoryPoints, int monuments, 
								   boolean playedDevCard, boolean discarded, int playerID, int playerIndex, String playerName, 
								   CatanColor playerColor);
	
}
