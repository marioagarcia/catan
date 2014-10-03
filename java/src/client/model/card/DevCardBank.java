package client.model.card;

import java.util.HashMap;
import java.util.Map;

public class DevCardBank implements DevCardBankInterface {

	private Map<DevCardInterface.DevCardType, Integer> cards;
	
	public DevCardBank()
	{
		this.cards = new HashMap<DevCardInterface.DevCardType, Integer>();
	}

	@Override
	public void addCard(DevCardInterface card) {
		if(this.cards.containsKey(card.getType()))
			cards.put(card.getType(), cards.get(card.getType()) + 1);
		else {
			cards.put(card.getType(), 1);
		}
		return;
	}

	@Override
	public DevCardInterface removeCard(DevCardInterface.DevCardType type) throws NoSuchCardException {
		if(!this.cards.containsKey(type)){
			throw new NoSuchCardException();
		}
		cards.put(type, cards.get(type) - 1);
		
		return new DevCard(type);
	}

	@Override
	public boolean containsCard(DevCardInterface.DevCardType type) {
		return this.cards.containsKey(type);
	}

}
