package shared.serialization.parameters;

import shared.model.card.ResourceList;
import shared.serialization.interfaces.SerializerResourceListInterface;

public class DiscardCardsParameters extends MasterParameterInterface{

	private String type;
	private int playerIndex;
	private SerializerResourceListInterface discardedCards;
	
	public DiscardCardsParameters(int index, SerializerResourceListInterface discards){
		type = "discardCards";
		playerIndex = index;
		discardedCards = discards;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public SerializerResourceListInterface getDiscardedCards() {
		return discardedCards;
	}

	public void setDiscardedCards(ResourceList discardedCards) {
		this.discardedCards = discardedCards;
	}
}
