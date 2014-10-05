package client.manager.interfaces;

public interface GMDomesticTradeInterface {
	/**
	 * returns the amount of Ore cards to be traded. Negative means they are being requested
	 * @return number of Ore cards
	 */
	public int getOreCount();
	
	/**
	 * returns the amount of Wood cards to be traded. Negative means they are being requested
	 * @return number of Wood cards
	 */
	public int getWoodCount();
	
	/**
	 * returns the amount of Sheep cards to be traded. Negative means they are being requested
	 * @return number of Sheep cards
	 */
	public int getSheepCount();
	
	/**
	 * returns the amount of Brick cards to be traded. Negative means they are being requested
	 * @return number of Brick cards
	 */
	public int getBrickCount();
	
	/**
	 * returns the amount of Wheat cards to be traded. Negative means they are being requested
	 * @return number of Wheat cards
	 */
	public int getWheatCount();

}
