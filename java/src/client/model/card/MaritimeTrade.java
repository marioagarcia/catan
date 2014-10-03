package client.model.card;

public class MaritimeTrade extends DomesticTrade {
//TODO this should probably not extend DomesticTrade
	private int ratio;
	
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	
	public int getRatio() {
		return ratio;
	}

}
