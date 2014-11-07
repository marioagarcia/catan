package shared.model.card;

import shared.definitions.ResourceType;

public class Trade implements TradeInterface {

	ResourceList resourceList;

	public Trade(int brick, int ore, int sheep, int wheat, int wood) {
		resourceList = new ResourceList(brick, ore, sheep, wheat, wood);
	}

	@Override
	public void setTradeCard(ResourceType type, int count) {
		switch (type) {
		case ORE:
			resourceList.setOre(count);
			break;
		case BRICK:
			resourceList.setBrick(count);
			break;
		case SHEEP:
			resourceList.setSheep(count);
			break;
		case WHEAT:
			resourceList.setWheat(count);
			break;
		case WOOD:
			resourceList.setWood(count);
			break;
		}
	}

	@Override
	public int getTradeCard(ResourceType type) {

		switch (type) {
		case ORE:
			return resourceList.getOre();
		case BRICK:
			return resourceList.getBrick();
		case SHEEP:
			return resourceList.getSheep();
		case WHEAT:
			return resourceList.getWheat();
		case WOOD:
			return resourceList.getWood();
		default:
			return Integer.MAX_VALUE;
		}
	}

}
