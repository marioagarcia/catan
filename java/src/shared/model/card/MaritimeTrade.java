package shared.model.card;

import shared.definitions.ResourceType;


public class MaritimeTrade implements TradeInterface {
	private int ratio;
	private ResourceType resourceIn;
	private ResourceType resourceOut;
	
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	
	public int getRatio() {
		return ratio;
	}

	public ResourceType getResourceIn() {
		return resourceIn;
	}

	public void setResourceIn(ResourceType resourceIn) {
		this.resourceIn = resourceIn;
	}

	public ResourceType getResourceOut() {
		return resourceOut;
	}

	public void setResourceOut(ResourceType resourceOut) {
		this.resourceOut = resourceOut;
	}

	@Override
	public void setTradeCard(ResourceType type, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTradeCard(ResourceType type) {
		// TODO Auto-generated method stub
		return 0;
	}

}
