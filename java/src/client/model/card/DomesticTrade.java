package client.model.card;

import client.manager.interfaces.GMDomesticTradeInterface;
import shared.definitions.ResourceType;

public class DomesticTrade implements TradeInterface, GMDomesticTradeInterface {

	private int sender;
	private int receiver;
	private ResourceList resourceList;
	
	public DomesticTrade(){
		sender = -1;
		receiver = -1;
		resourceList = null;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + receiver;
		result = prime * result
				+ ((resourceList == null) ? 0 : resourceList.hashCode());
		result = prime * result + sender;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomesticTrade other = (DomesticTrade) obj;
		if (receiver != other.receiver)
			return false;
		if (resourceList == null) {
			if (other.resourceList != null)
				return false;
		} else if (!resourceList.equals(other.resourceList))
			return false;
		if (sender != other.sender)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		String str = "";
		str += "Sender Index: " + sender + "\n";
		str += "Receiver Index: " + receiver + "\n";
		str += resourceList.toString();
		
		return str;
	}
}
