package shared.model.card;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import shared.definitions.DevCardType;
import shared.model.manager.interfaces.GMDevCardBankInterface;
import shared.serialization.interfaces.SerializerDeckInterface;


public class DevCardBank implements DevCardBankInterface, SerializerDeckInterface, GMDevCardBankInterface, Serializable {

	private static final long serialVersionUID = 6326060884633687447L;

	private final int CARD_TYPES = 5;

	private Map<DevCardType, Integer> cards;
	
	public DevCardBank()
	{
		this.cards = new HashMap<DevCardType, Integer>();
	}

	public static DevCardBank createBankForNewGame(){
		DevCardBank bank = new DevCardBank();
		bank.setDeck(2, 2, 14, 2, 5);
		return bank;
	}
	
	public DevCardBank(Map<DevCardType, Integer> cards){
		this.cards = cards;
	}

	public Map<DevCardType, Integer> getCards() {
		return cards;
	}

	public void setCards(Map<DevCardType, Integer> cards) {
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

	@Override
	public DevCardType buyDevCard() {
		if (!containsAnyCard()) throw new AssertionError();

        Random random = new Random();
        int index = random.nextInt(getSize()) + 1;

        for(DevCardType type : DevCardType.values()){
            if(this.cards.get(type) == 0){
                continue;
            }
            else if(index <= this.cards.get(type)){
                this.cards.put(type, this.cards.get(type) - 1);
                return type;
            }
            index -= this.cards.get(type);
        }
		return null;
	}

	public boolean containsCard(DevCardType type) {
		return this.cards.containsKey(type) && this.cards.get(type) > 0;
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
		DevCardBank other = (DevCardBank) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}
	
	public int numberOfType(DevCardType type){
		if(this.cards.containsKey(type)){
			return this.cards.get(type);
		}
		return 0;
	}
	
	public int getSize(){
		int size = 0;
		
        for(DevCardType type : DevCardType.values()){
        	size += numberOfType(type);
        }
		
		return size;
	}
}
