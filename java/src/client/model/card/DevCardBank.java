package client.model.card;

import java.util.HashMap;
import java.util.Map;
import shared.serialization.interfaces.SerializerDeckInterface;

public class DevCardBank implements DevCardBankInterface, SerializerDeckInterface {

	private Map<DevCardInterface.DevCardType, Integer> cards;
	
	public DevCardBank()
	{
		this.cards = new HashMap<DevCardInterface.DevCardType, Integer>();
	}
	
	public DevCardBank(Map<DevCardInterface.DevCardType, Integer> cards){
		this.cards = cards;
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

	@Override
	public void setDeck(int yearOfPlenty, int monopoly, int soldier,
			int roadBuild, int monument) {
		
		cards.put(DevCardInterface.DevCardType.YEAR_OF_PLENTY, yearOfPlenty);
		cards.put(DevCardInterface.DevCardType.MONOPOLY, monopoly);
		cards.put(DevCardInterface.DevCardType.SOLDIER, soldier);
		cards.put(DevCardInterface.DevCardType.ROAD_BUILD, roadBuild);
		cards.put(DevCardInterface.DevCardType.MONUMENT, monument);
	}

}
