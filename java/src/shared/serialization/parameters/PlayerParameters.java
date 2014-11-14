package shared.serialization.parameters;

import shared.model.card.DevCardList;
import shared.model.card.ResourceList;

public class PlayerParameters {

		private ResourceList resources;
		private DevCardListParameters oldDevCards;
		private DevCardListParameters newDevCards;
		private int roads;
		private int cities;
		private int settlements;
		private int soldiers;
		private int victoryPoints;
		private int monuments;
		private boolean playedDevCard;
		private boolean discarded;
		private int playerID;
		private int playerIndex;
		private String name;
		private String color;
		
		public PlayerParameters(ResourceList resources, DevCardListParameters oldDevCards, DevCardListParameters newDevCards, int roads, 
								int cities, int settlements, int soldiers, int victoryPoints, int monuments, boolean playedDevCard, 
								boolean discarded, int playerID, int playerIndex, String name, String color){
			this.resources = resources;
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
			this.playerID = playerID;
			this.playerIndex = playerIndex;
			this.name = name;
			this.color = color;
		}

		public ResourceList getResources() {
			return resources;
		}

		public void setResources(ResourceList resources) {
			this.resources = resources;
		}

		public DevCardListParameters getOldDevCards() {
			return oldDevCards;
		}

		public void setOldDevCards(DevCardListParameters oldDevCards) {
			this.oldDevCards = oldDevCards;
		}

		public DevCardListParameters getNewDevCards() {
			return newDevCards;
		}

		public void setNewDevCards(DevCardListParameters newDevCards) {
			this.newDevCards = newDevCards;
		}

		public int getRoads() {
			return roads;
		}

		public void setRoads(int roads) {
			this.roads = roads;
		}

		public int getCities() {
			return cities;
		}

		public void setCities(int cities) {
			this.cities = cities;
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

		public int getMonuments() {
			return monuments;
		}

		public void setMonuments(int monuments) {
			this.monuments = monuments;
		}

		public boolean isPlayedDevCard() {
			return playedDevCard;
		}

		public void setPlayedDevCard(boolean playedDevCard) {
			this.playedDevCard = playedDevCard;
		}

		public boolean isDiscarded() {
			return discarded;
		}

		public void setDiscarded(boolean discarded) {
			this.discarded = discarded;
		}

		public int getPlayerID() {
			return playerID;
		}

		public void setPlayerID(int playerID) {
			this.playerID = playerID;
		}

		public int getPlayerIndex() {
			return playerIndex;
		}

		public void setPlayerIndex(int playerIndex) {
			this.playerIndex = playerIndex;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
}
