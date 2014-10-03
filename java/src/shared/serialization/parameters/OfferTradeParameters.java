package shared.serialization.parameters;

import client.model.card.ResourceList;

public class OfferTradeParameters extends MasterParameterInterface{

	private String type;
	private int playerIndex;
	private ResourceList offer;
	private int receiver;
	
	public OfferTradeParameters(int giverIndex, ResourceList offering, int receiverIndex){
		type = "offerTrade";
		playerIndex = giverIndex;
		offer = offering;
		receiver = receiverIndex;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public ResourceList getOffer() {
		return offer;
	}

	public void setOffer(ResourceList offer) {
		this.offer = offer;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
}
