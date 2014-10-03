package shared.serialization.parameters;

import client.model.card.ResourceList;

public class DiscardCardsParameters {

	private String type;
	private int playerIndex;
	private ResourceList discardedCards;
	
	public DiscardCardsParameters(int index, ResourceList discards){
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

	public ResourceList getDiscardedCards() {
		return discardedCards;
	}

	public void setDiscardedCards(ResourceList discardedCards) {
		this.discardedCards = discardedCards;
	}
}
