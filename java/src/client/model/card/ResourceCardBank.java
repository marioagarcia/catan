package client.model.card;

import java.util.HashMap;
import java.util.Map;

import client.model.card.ResourceCardInterface.ResourceCardType;

public class ResourceCardBank implements ResourceCardBankInterface {
	
	private Map<ResourceCardType, Integer> cards;
	
	public ResourceCardBank()
	{
		this.cards = new HashMap<ResourceCardType, Integer>();
	}

	@Override
	public void addCard(ResourceCardInterface card) {
		if(this.cards.containsKey(card.getResourceCardType()))
			cards.put(card.getResourceCardType(), cards.get(card.getResourceCardType()) + 1);
		else {
			cards.put(card.getResourceCardType(), 1);
		}
		return;
	}

	@Override
	public ResourceCardInterface removeCard(ResourceCardType type) throws NoSuchCardException {
		if(!this.cards.containsKey(type)){
			throw new NoSuchCardException();
		}
		cards.put(type, cards.get(type) - 1);
		
		return new ResourceCard(type);
	}

	@Override
	public boolean containsCard(ResourceCardInterface.ResourceCardType type) {
		return this.cards.containsKey(type);
	}


}
