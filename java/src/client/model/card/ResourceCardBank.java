package client.model.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import shared.definitions.ResourceType;
import shared.serialization.interfaces.SerializerBankInterface;

public class ResourceCardBank extends Observable implements ResourceCardBankInterface, SerializerBankInterface {
	
	private Map<ResourceType, Integer> cards;
	
	public ResourceCardBank()
	{
		this.cards = new HashMap<ResourceType, Integer>();
	}
	
	public Map<ResourceType, Integer> getCards() {
		return cards;
	}

	public void setCards(Map<ResourceType, Integer> cards) {
		this.cards = cards;
	}

	public int getNumberOfResourcesByType(ResourceType type){
		if(this.cards.containsKey(type)){
			return this.cards.get(type);
		}
		else{
			return 0;
		}
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
	public ResourceCardInterface removeCard(ResourceType type) throws NoSuchCardException {
		if(!this.cards.containsKey(type)){
			throw new NoSuchCardException();
		}
		cards.put(type, cards.get(type) - 1);
		
		return new ResourceCard(type);
	}

	@Override
	public boolean containsCards(ResourceType type1, ResourceType type2) {
		if (type1.equals(type2))
		{
			return this.cards.get(type1) >= 2;
		}
		else
		{
			return (this.cards.get(type1) >= 1 && (this.cards.get(type2) >= 1));
		}
	}

	@Override
	public void setBank(int brick, int wood, int sheep, int wheat, int ore) {
		cards.put(ResourceType.BRICK, brick);
		cards.put(ResourceType.WOOD, wood);
		cards.put(ResourceType.SHEEP, sheep);
		cards.put(ResourceType.WHEAT, wheat);
		cards.put(ResourceType.ORE, ore);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceCardBank other = (ResourceCardBank) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}

}
