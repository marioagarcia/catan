package shared.serialization.interfaces;

import client.model.card.DevCardList;
import client.model.card.ResourceList;
import shared.definitions.CatanColor;

public interface PlayerInterface {

	public abstract void setPlayer(ResourceList resourceList, DevCardList oldDevCards, DevCardList newDevCards,
								   int roads, int cities, int settlements, int soldiers, int victoryPoints, int monuments, 
								   boolean playedDevCard, boolean discarded, int playerID, int playerIndex, String playerName, 
								   CatanColor playerColor);
	
}
