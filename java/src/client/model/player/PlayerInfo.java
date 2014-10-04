package client.model.player;

import client.model.card.DevCardList;
import client.model.card.ResourceList;
import client.model.card.TradeInterface;
import client.model.card.ResourceCardInterface.ResourceCardType;
import shared.definitions.CatanColor;

/**
 * Used to pass player information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique player ID</li>
 * <li>PlayerIndex: Player's order in the game [0-3]</li>
 * <li>Name: Player's name (non-empty string)</li>
 * <li>Color: Player's color (cannot be null)</li>
 * </ul>
 * 
 */
public class PlayerInfo implements PlayerInterface
{
	
	private int id;
	private int playerIndex;
	private String name;
	private CatanColor color;
	private int cities;
	private boolean discarded;
	private int monuments;
	private DevCardList newDevCards;
	private DevCardList oldDevCards;
	private boolean playedDevCard;
	private int playerId;
	private ResourceList resourceList;
	private int roads;
	private int settlements;
	private int soldiers;
	private int victoryPoints;
	
	public int getCities() {
		return cities;
	}

	public void setCities(int cities) {
		this.cities = cities;
	}

	public boolean isDiscarded() {
		return discarded;
	}

	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}

	public int getMonuments() {
		return monuments;
	}

	public void setMonuments(int monuments) {
		this.monuments = monuments;
	}

	public DevCardList getNewDevCards() {
		return newDevCards;
	}

	public void setNewDevCards(DevCardList newDevCards) {
		this.newDevCards = newDevCards;
	}

	public DevCardList getOldDevCards() {
		return oldDevCards;
	}

	public void setOldDevCards(DevCardList oldDevCards) {
		this.oldDevCards = oldDevCards;
	}

	public boolean isPlayedDevCard() {
		return playedDevCard;
	}

	public void setPlayedDevCard(boolean playedDevCard) {
		this.playedDevCard = playedDevCard;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public ResourceList getResourceList() {
		return resourceList;
	}

	public void setResourceList(ResourceList resourceList) {
		this.resourceList = resourceList;
	}

	public int getRoads() {
		return roads;
	}

	public void setRoads(int roads) {
		this.roads = roads;
	}

	public int getSettlements() {
		return settlements;
	}

	public void setSettlements(int settlements) {
		this.settlements = settlements;
	}

	public int getSoldiers() {
		return soldiers;
	}

	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}

	public int getVictoryPoints() {
		return victoryPoints;
	}

	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}

	public PlayerInfo()
	{
		setId(-1);
		setPlayerIndex(-1);
		setName("");
		setColor(CatanColor.WHITE);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getPlayerIndex()
	{
		return playerIndex;
	}
	
	public void setPlayerIndex(int playerIndex)
	{
		this.playerIndex = playerIndex;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public CatanColor getColor()
	{
		return this.color;
	}
	
	public void setColor(CatanColor color)
	{
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + playerIndex;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerInfo other = (PlayerInfo) obj;
		if (color != other.color)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (playerIndex != other.playerIndex)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		String str = "Player Name: " + name + "\n";
		str += "Player ID: " + id + "\n";
		str += "Player Color: " + color.toString() + "\n";
		return str;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canDiscardCards(ResourceList list) {
		if(this.resourceList == list){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param trade
	 * @return
	 */
	@Override
	public boolean canAcceptTrade(TradeInterface trade){
		if( (-1 * trade.getTradeCard(ResourceCardType.ORE) )  < this.resourceList.getOre() ||
			(-1 * trade.getTradeCard(ResourceCardType.BRICK) ) < this.resourceList.getBrick() ||
			(-1 * trade.getTradeCard(ResourceCardType.SHEEP) ) < this.resourceList.getSheep() ||
			(-1 * trade.getTradeCard(ResourceCardType.WHEAT) ) < this.resourceList.getWheat() ||
			(-1 * trade.getTradeCard(ResourceCardType.WOOD) ) < this.resourceList.getWood() ){
			return false;
		}
		
		return true;
	}
}

