package shared.model.card;

import java.util.HashMap;
import java.util.Map;
import shared.definitions.ResourceType;
import shared.serialization.interfaces.SerializerBankInterface;

public class ResourceCardBank implements ResourceCardBankInterface, SerializerBankInterface {
	
	private Map<ResourceType, Integer> cards;
    private static final int totalCardsPerType = 19;
	
	public ResourceCardBank()
	{
		this.cards = new HashMap<ResourceType, Integer>();
	}

    public static ResourceCardBank createResourceCardBankForNewGame(){
        ResourceCardBank bank = new ResourceCardBank();

        Map<ResourceType, Integer> cards = new HashMap<ResourceType, Integer>();
        for(ResourceType resource : ResourceType.values()){
            cards.put(resource, ResourceCardBank.totalCardsPerType);
        }

        bank.setCards(cards);
        return bank;
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

    public boolean canMakeMaritimeTrade(MaritimeTrade trade){
        return this.cards.get(trade.getResourceOut()) > 0;
    }

    public void makeMaritimeTrade(MaritimeTrade trade){
        assert(this.canMakeMaritimeTrade(trade));

        this.cards.put(trade.getResourceIn(), this.cards.get(trade.getResourceIn()) + trade.getRatio());
        this.cards.put(trade.getResourceOut(), this.cards.get(trade.getResourceOut()) - 1);
    }

	@Override
	public void addCard(ResourceType card) {
		if(this.cards.containsKey(card))
			cards.put(card, cards.get(card) + 1);
		else {
			cards.put(card, 1);
		}
	}

	@Override
	public void removeCard(ResourceType card) {

		if(this.cards.containsKey(card))
			cards.put(card, cards.get(card) - 1);
		else {
			//throw and exception?
		}
	}

	@Override
	public void yearOfPlentyPlayed(ResourceType type1, ResourceType type2) {

		removeCard(type1);
		removeCard(type2);

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
