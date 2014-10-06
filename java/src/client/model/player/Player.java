package client.model.player;

import shared.definitions.CatanColor;
import shared.serialization.interfaces.SerializerResourceListInterface;
import client.manager.interfaces.GMPlayerInterface;
import client.model.card.DevCardList;
import client.model.card.ResourceList;
import client.model.card.TradeInterface;

public class Player implements PlayerInterface, GMPlayerInterface, shared.serialization.interfaces.SerializerPlayerInterface {
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

	@Override
	public boolean canOfferTrade(TradeInterface trade){
		// TODO Auto-generated method stub
		return false;
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
		return (!playedDevCard && newDevCards.getRoadBuilding() >=1);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canDiscardCards(ResourceList list){
		//Resourcelist should have a size method to verify this.
		return (!discarded /*&& resourceList.size() > 7*/);
	}

	
}
