package client.model.player;

import java.util.Observable;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.serialization.interfaces.SerializerPlayerInterface;
import shared.serialization.interfaces.SerializerResourceListInterface;
import client.manager.interfaces.GMPlayerInterface;
import client.model.card.DevCardList;
import client.model.card.MaritimeTrade;
import client.model.card.ResourceList;
import client.model.card.TradeInterface;

public class Player extends Observable implements PlayerInterface, GMPlayerInterface, SerializerPlayerInterface {
	int cities;
	CatanColor color;
	boolean discarded;
	int monuments;
	String name;
	DevCardList newDevCards;
	DevCardList oldDevCards;
	int playerIndex;
	boolean playedDevCard;
	int playerId;
	ResourceList resourceList;
	int roads;
	int settlements;
	int soldiers;
	int victoryPoints;
	
	public Player(){
		newDevCards = new DevCardList();
	}

	public int getCities() {
		return cities;
	}

	public void setCities(int cities) {
		this.cities = cities;
	}

	public CatanColor getColor() {
		return color;
	}

	public void setColor(CatanColor color) {
		this.color = color;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
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

	public SerializerResourceListInterface getResourceList() {
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

	@Override
	public void setPlayer(ResourceList resourceList, DevCardList oldDevCards,
			DevCardList newDevCards, int roads, int cities, int settlements,
			int soldiers, int victoryPoints, int monuments,
			boolean playedDevCard, boolean discarded, int playerID,
			int playerIndex, String playerName, CatanColor playerColor) {
		
		this.resourceList = resourceList;
		this.oldDevCards = oldDevCards;
		this.newDevCards = newDevCards;
		this.roads = roads;
		this.cities = cities;
		this.settlements = settlements;
		this.soldiers = soldiers;
		this.victoryPoints = victoryPoints;
		this.monuments = monuments;
		this.playedDevCard = playedDevCard;
		this.discarded = discarded;
		this.playerId = playerID;
		this.playerIndex = playerIndex;
		this.name = playerName;
		this.color = playerColor;	
	}
	
	public boolean canBuildRoad(){
		boolean canBuildRoad = false;
		if(resourceList.getWood() >= 1 &&
		   resourceList.getBrick() >=1 &&
		   roads >= 1){
			canBuildRoad = true;
		}
		
		return canBuildRoad;
	}
	
	public boolean canBuildSettlement(){
		boolean canBuildSettlement = false;
		
		if(resourceList.getWood() >= 1 && resourceList.getBrick() >= 1 &&
		   resourceList.getWheat() >= 1 && resourceList.getSheep() >= 1 && 
		   settlements >= 1){
			canBuildSettlement = true;
		}
		
		return canBuildSettlement;
	}
	
	public boolean canBuildCity(){
		boolean canBuildCity = false;
		//the player has 2 wheat, 3 ore, 1 city
		if(resourceList.getWheat() >= 2 && resourceList.getOre() >= 3 && cities >= 1){
			canBuildCity = true;
		}
		
		return canBuildCity;
	}
	
	public boolean canMaritimeTrade(MaritimeTrade trade){
		boolean result = false;
		int resourceAmount = 0;
		
		switch(trade.getResourceIn()){
			case BRICK:
				resourceAmount = resourceList.getBrick();
				break;
			case ORE:
				resourceAmount = resourceList.getOre();
				break;
			case SHEEP:
				resourceAmount = resourceList.getSheep();
				break;
			case WHEAT:
				resourceAmount = resourceList.getWheat();
				break;
			case WOOD:
				resourceAmount = resourceList.getWood();
				break;
		}
		if(trade.getRatio() <= resourceAmount){
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean canOfferTrade(TradeInterface trade){
		
		int brick = trade.getTradeCard(ResourceType.BRICK);
		int wheat = trade.getTradeCard(ResourceType.WHEAT);
		int wood = trade.getTradeCard(ResourceType.WOOD);
		int sheep = trade.getTradeCard(ResourceType.SHEEP);
		int ore = trade.getTradeCard(ResourceType.ORE);
		
		boolean can_offer = true;
		
		if (brick > 0)
		{
			if (resourceList.getBrick() < brick)
			{
				can_offer = false;
			}
		}
		
		if (wheat > 0)
		{
			if (resourceList.getWheat() < wheat)
			{
				can_offer = false;
			}
		}
		
		if (wood > 0)
		{
			if (resourceList.getWood() < wood)
			{
				can_offer = false;
			}
		}
		
		if (sheep > 0)
		{
			if (resourceList.getSheep() < sheep)
			{
				can_offer = false;
			}
		}
		
		if (ore > 0)
		{
			if (resourceList.getOre() < ore)
			{
				can_offer = false;
			}
		}
		
		return can_offer;
	}

	@Override
	public boolean canBuyDevCard(){
		
		return (resourceList.getOre() >=1 && 
				resourceList.getSheep() >= 1 &&
				resourceList.getWheat() >= 1);
	}

	@Override
	public boolean canPlayYearOfPlenty(){
		return (!playedDevCard && newDevCards.getYearOfPlenty() >=1);
	}

	@Override
	public boolean canPlayRoadBuilding(){
		return (!playedDevCard && newDevCards.getRoadBuild() >=1 && roads >= 2);
	}

	@Override
	public boolean canPlaySoldier(){
		return (!playedDevCard && newDevCards.getSoldier() >=1);
	}

	@Override
	public int getId(){
		return playerId;
	}

	@Override
	public int getPoints(){
		return victoryPoints;
	}

	@Override
	public boolean canAcceptTrade(TradeInterface trade){
		int brick = trade.getTradeCard(ResourceType.BRICK);
		int wheat = trade.getTradeCard(ResourceType.WHEAT);
		int wood = trade.getTradeCard(ResourceType.WOOD);
		int sheep = trade.getTradeCard(ResourceType.SHEEP);
		int ore = trade.getTradeCard(ResourceType.ORE);
		
		boolean can_accept = true;
		
		if (brick < 0){
			if (resourceList.getBrick() < (-1 * brick)){
				can_accept = false;
			}
		}
		
		if (wheat < 0){
			if (resourceList.getWheat() < (-1 * wheat)){
				can_accept = false;
			}
		}
		
		if (wood < 0){
			if (resourceList.getWood() < (-1 * wood)){
				can_accept = false;
			}
		}
		
		if (sheep < 0){
			if (resourceList.getSheep() < (-1 * sheep)){
				can_accept = false;
			}
		}
		
		if (ore < 0){
			if (resourceList.getOre() < (-1 * ore)){
				can_accept = false;
			}
		}
		
		return can_accept;
	}

	@Override
	public boolean canDiscardCards(SerializerResourceListInterface list){
		return (!discarded && resourceList.totalNumberCards() > 7);
	}
	
	@Override
	public boolean canPlayMonument() {
		return (!playedDevCard && newDevCards.getMonument() >=1);
	}

	@Override
	public boolean canPlayMonopoly() {
		return (!playedDevCard && newDevCards.getMonopoly() >=1);
	}

	@Override
	public int getNumberOfCards() {
		return resourceList.totalNumberCards();
	}

	@Override
	public boolean canBeRobbed() {
		return (resourceList.totalNumberCards() >= 1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cities;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + (discarded ? 1231 : 1237);
		result = prime * result + monuments;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((newDevCards == null) ? 0 : newDevCards.hashCode());
		result = prime * result
				+ ((oldDevCards == null) ? 0 : oldDevCards.hashCode());
		result = prime * result + (playedDevCard ? 1231 : 1237);
		result = prime * result + playerId;
		result = prime * result + playerIndex;
		result = prime * result
				+ ((resourceList == null) ? 0 : resourceList.hashCode());
		result = prime * result + roads;
		result = prime * result + settlements;
		result = prime * result + soldiers;
		result = prime * result + victoryPoints;
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
		Player other = (Player) obj;
		if (cities != other.cities)
			return false;
		if (color != other.color)
			return false;
		if (discarded != other.discarded)
			return false;
		if (monuments != other.monuments)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (newDevCards == null) {
			if (other.newDevCards != null)
				return false;
		} else if (!newDevCards.equals(other.newDevCards))
			return false;
		if (oldDevCards == null) {
			if (other.oldDevCards != null)
				return false;
		} else if (!oldDevCards.equals(other.oldDevCards))
			return false;
		if (playedDevCard != other.playedDevCard)
			return false;
		if (playerId != other.playerId)
			return false;
		if (playerIndex != other.playerIndex)
			return false;
		if (resourceList == null) {
			if (other.resourceList != null)
				return false;
		} else if (!resourceList.equals(other.resourceList))
			return false;
		if (roads != other.roads)
			return false;
		if (settlements != other.settlements)
			return false;
		if (soldiers != other.soldiers)
			return false;
		if (victoryPoints != other.victoryPoints)
			return false;
		return true;
	}
	
}
