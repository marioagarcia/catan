package client.model.card;

public class DomesticTrade implements TradeInterface {

	//TODO make private
	private int brick;
	private int sheep;
	private int ore;
	private int wood;
	private int wheat;
	
	@Override
	public void setTradeCard(ResourceCardInterface.ResourceCardType type, int count) {
		switch( type ) {
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
	public int getTradeCard(ResourceCardInterface.ResourceCardType type) {
		switch( type ) {
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
