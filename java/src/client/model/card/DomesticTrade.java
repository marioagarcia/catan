package client.model.card;

import client.manager.interfaces.GMDomesticTradeInterface;
import shared.definitions.ResourceType;

public class DomesticTrade implements TradeInterface, GMDomesticTradeInterface {

	private int brick;
	private int sheep;
	private int ore;
	private int wood;
	private int wheat;
	
	@Override
	public void setTradeCard(ResourceType type, int count) {
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
	public int getTradeCard(ResourceType type) {
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

	@Override
	public int getOreCount() {
		return getTradeCard(ResourceType.ORE);
	}

	@Override
	public int getWoodCount() {
		return getTradeCard(ResourceType.WOOD);
	}

	@Override
	public int getSheepCount() {
		return getTradeCard(ResourceType.SHEEP);
	}

	@Override
	public int getBrickCount() {
		return getTradeCard(ResourceType.BRICK);
	}

	@Override
	public int getWheatCount() {
		return getTradeCard(ResourceType.WHEAT);
	}

}
