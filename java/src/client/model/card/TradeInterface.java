package client.model.card;

import client.model.card.ResourceCardInterface;

public interface TradeInterface {
	
	public void setTradeCard(ResourceCardInterface card, int count);
	
	public int getTradeCard(ResourceCardInterface card);
	
}
