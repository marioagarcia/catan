package client.model.card;

import client.manager.interfaces.GMDomesticTradeInterface;
import shared.definitions.ResourceType;

public class DomesticTrade implements TradeInterface, GMDomesticTradeInterface {

	private int sender;
	private int receiver;
	private ResourceList resourceList;
	
	public DomesticTrade(){
		
	}
	
	public DomesticTrade(int sender, int receiver, ResourceList resourceList){
		this.sender = sender;
		this.receiver = receiver;
		this.resourceList = resourceList;
	}
	
	public int getSender() {
		return sender;
	}


	public void setSender(int sender) {
		this.sender = sender;
	}


	public int getReceiver() {
		return receiver;
	}


	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}


	public ResourceList getResourceList() {
		return resourceList;
	}


	public void setResourceList(ResourceList resourceList) {
		this.resourceList = resourceList;
	}


	@Override
	public void setTradeCard(ResourceType type, int count) {
		switch( type ) {
		case BRICK:
			resourceList.setBrick(count);
			break;
		case WHEAT:
			resourceList.setWheat(count);
			break;
		case WOOD:
			resourceList.setWood(count);
			break;
		case ORE:
			resourceList.setOre(count);
			break;
		case SHEEP:
			resourceList.setSheep(count);
			break;
		}
	}

	@Override
	public int getTradeCard(ResourceType type) {
		switch( type ) {
		case BRICK:
			return resourceList.getBrick();
		case WHEAT:
			return resourceList.getWheat();
		case WOOD:
			return resourceList.getWood();
		case ORE:
			return resourceList.getOre();
		case SHEEP:
			return resourceList.getSheep();
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
