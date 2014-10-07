package client.model.card;

import java.util.HashMap;
import java.util.Map;

import client.manager.interfaces.GMDevCardBankInterface;
import shared.definitions.DevCardType;
import shared.serialization.interfaces.SerializerDeckInterface;

public class DevCardBank implements DevCardBankInterface, SerializerDeckInterface, GMDevCardBankInterface {

	private Map<DevCardType, Integer> cards;
	
	public DevCardBank()
	{
		this.cards = new HashMap<DevCardType, Integer>();
	}
	
	public DevCardBank(Map<DevCardType, Integer> cards){
		this.cards = cards;
	}

	@Override
	public void addCard(DevCardType card) {
		if(this.cards.containsKey(card))
			cards.put(card, cards.get(card) + 1);
		else {
			cards.put(card, 1);
		}
		return;
	}

	@Override
	public DevCardType removeCard(DevCardType type) throws NoSuchCardException {
		if(!this.cards.containsKey(type)){
			throw new NoSuchCardException();
		}
		cards.put(type, cards.get(type) - 1);
		
		return DevCardType.valueOf(type.toString());
	}

	public boolean containsCard(DevCardType type) {
		return this.cards.containsKey(type);
	}

	@Override
	public void setDeck(int yearOfPlenty, int monopoly, int soldier,
			int roadBuild, int monument) {
		
		cards.put(DevCardType.YEAR_OF_PLENTY, yearOfPlenty);
		cards.put(DevCardType.MONOPOLY, monopoly);
		cards.put(DevCardType.SOLDIER, soldier);
		cards.put(DevCardType.ROAD_BUILD, roadBuild);
		cards.put(DevCardType.MONUMENT, monument);
	}

	@Override
	public boolean containsAnyCard() {
		for (Map.Entry<DevCardType, Integer> card_entry : cards.entrySet()){
			if (card_entry.getValue() > 0){
				return true;
			}
		}
		
		return false;
	}
}
