package shared.serialization.interfaces;

import shared.definitions.CatanColor;
import shared.model.card.DevCardList;
import shared.model.card.ResourceList;

public interface SerializerPlayerInterface {

	public abstract void setPlayer(ResourceList resourceList, DevCardList oldDevCards, DevCardList newDevCards,
								   int roads, int cities, int settlements, int soldiers, int victoryPoints, int monuments, 
								   boolean playedDevCard, boolean discarded, int playerID, int playerIndex, String playerName, 
								   CatanColor playerColor);
	
}
