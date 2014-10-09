package client.model.card;

import java.util.HashMap;
import java.util.Map;

import shared.definitions.ResourceType;
import shared.serialization.interfaces.SerializerBankInterface;

public class ResourceCardBank implements ResourceCardBankInterface, SerializerBankInterface {
	
	private Map<ResourceType, Integer> cards;
	
	public ResourceCardBank()
	{
		this.cards = new HashMap<ResourceType, Integer>();
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


}
