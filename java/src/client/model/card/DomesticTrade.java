package client.model.card;

public class DomesticTrade implements TradeInterface {

	//TODO make private
	int brick;
	int sheep;
	int ore;
	int wood;
	int wheat;
	
	@Override
	public void setTradeCard(ResourceCardInterface card, int count) {
		switch(card.getResourceCardType()) {
		case BRICK:
			brick = count;
			break;
		case WHEAT:
			wheat = count;
			break;
		case WOOD:
			wood = count;
			break;
		case ORE:
			ore = count;
			break;
		case SHEEP:
			sheep = count;
			break;
		}
	}

	@Override
	public int getTradeCard(ResourceCardInterface card) {
		switch(card.getResourceCardType()) {
		case BRICK:
			return brick;
		case WHEAT:
			return wheat;
		case WOOD:
			return wood;
		case ORE:
			return ore;
		case SHEEP:
			return sheep;
		default:
			return Integer.MAX_VALUE;
		}
	}

}
