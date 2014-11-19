package shared.model.player;

import java.util.ArrayList;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.model.card.DevCardList;
import shared.model.card.MaritimeTrade;
import shared.model.card.ResourceList;
import shared.model.card.TradeInterface;
import shared.model.manager.interfaces.GMPlayerInterface;
import shared.serialization.interfaces.SerializerPlayerInterface;

public class Player implements PlayerInterface, GMPlayerInterface, SerializerPlayerInterface {

    int cities;
    CatanColor color;
    int monuments;
    String name;
    DevCardList newDevCards;
    DevCardList oldDevCards;
    int playerIndex;
    int playerId;
    ResourceList resourceList;
    int roads;
    int settlements;
    int soldiers;
    int victoryPoints;
    boolean discarded;
    boolean playedDevCard;
    boolean placedFreeSettlement;
    boolean placedFreeRoad;

    public Player() {
        newDevCards = new DevCardList();
        playerId = -1;
        playerIndex = -1;
        placedFreeSettlement = false;
        placedFreeRoad = false;
        monuments = 0;
        roads = 15;
        settlements = 5;
        cities = 4;
        soldiers = 0;
        victoryPoints = 7;
        resourceList = new ResourceList(0, 0, 0, 0, 0);
        newDevCards = new DevCardList();
        newDevCards.setDevCardList(0, 0, 0, 0, 0);
        oldDevCards = new DevCardList();
        oldDevCards.setDevCardList(0, 0, 0, 0, 0);
    }

    public boolean getPlayedDevCard() {
        return playedDevCard;
    }

    public void addResourceCard(ResourceType type) {
        this.resourceList.setResourceByType(type, this.resourceList.getResourceByType(type) + 1);
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

    public boolean hasDiscarded() {
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

    public boolean hasPlayedDevCard() {
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
    public boolean canBuildRoad() {
        boolean canBuildRoad = false;
        if (resourceList.getWood() >= 1 &&
                resourceList.getBrick() >= 1 &&
                roads >= 1) {
            canBuildRoad = true;
        }

        return canBuildRoad;
    }

    public void endTurn(){
        this.playedDevCard = false;
        this.placedFreeSettlement = false;
        this.placedFreeRoad = false;

        for(DevCardType type : DevCardType.values()){
            this.oldDevCards.setCardsByType(type, this.newDevCards.getCardsByType(type) + this.oldDevCards.getCardsByType(type));
            this.newDevCards.setCardsByType(type, 0);
        }
    }

    @Override
    public boolean canBuildSettlement() {

        boolean canBuildSettlement = false;

        if (resourceList.getWood() >= 1 && resourceList.getBrick() >= 1 &&
                resourceList.getWheat() >= 1 && resourceList.getSheep() >= 1 &&
                settlements >= 1) {
            canBuildSettlement = true;
        }

        return canBuildSettlement;
    }

    @Override
    public boolean canBuildCity() {
        boolean canBuildCity = false;
        //the player has 2 wheat, 3 ore, 1 city
        if (resourceList.getWheat() >= 2 && resourceList.getOre() >= 3 && cities >= 1) {
            canBuildCity = true;
        }

        return canBuildCity;
    }

    @Override
    public boolean canMaritimeTrade(MaritimeTrade trade) {
        return this.resourceList.getResourceByType(trade.getResourceIn()) >= trade.getRatio();
    }

    @Override
    public boolean canOfferTrade(TradeInterface trade) {
        for(ResourceType type : ResourceType.values()){
            if (trade.getTradeCard(type) > resourceList.getResourceByType(type)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canBuyDevCard() {

        return (resourceList.getOre() >= 1 &&
                resourceList.getSheep() >= 1 &&
                resourceList.getWheat() >= 1);
    }

    @Override
    public boolean canPlayYearOfPlenty() {
        return (!playedDevCard && oldDevCards.getYearOfPlenty() >= 1);
    }

    @Override
    public boolean canPlayRoadBuilding() {
        return (!playedDevCard && oldDevCards.getRoadBuild() >= 1 && roads >= 2);
    }

    @Override
    public boolean canPlaySoldier() {
        return (!playedDevCard && oldDevCards.getSoldier() >= 1);
    }

    @Override
    public int getId() {
        return playerId;
    }

    @Override
    public int getPoints() {
        return victoryPoints;
    }

    @Override
    public boolean canAcceptTrade(TradeInterface trade) {
        for(ResourceType type : ResourceType.values()){
            if(-1 * trade.getTradeCard(type) > (resourceList.getResourceByType(type))){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canDiscardCards(ResourceList list) {
        for(ResourceType type : ResourceType.values()){
            if(this.resourceList.getResourceByType(type) <  list.getResourceByType(type)){
                return false;
            }
        }

        return (!discarded && resourceList.totalNumberCards() > 7 && (resourceList.totalNumberCards() / 2) == list.totalNumberCards()) ||
                (list.totalNumberCards() == 0 && this.resourceList.totalNumberCards() <= 7);
    }

    @Override
    public boolean canPlayMonument() {
        return (!playedDevCard && (oldDevCards.getMonument() >= 1 || newDevCards.getMonument() >= 1));
    }

    @Override
    public boolean canPlayMonopoly() {
        return (!playedDevCard && oldDevCards.getMonopoly() >= 1);
    }

    @Override
    public int getNumberOfCards() {
        return resourceList.totalNumberCards();
    }

    @Override
    public boolean canBeRobbed() {
        return (resourceList.totalNumberCards() >= 1);
    }

    public boolean hasPlacedFreeSettlement() {
        return placedFreeSettlement;
    }

    public void setPlacedFreeSettlement(boolean placedFreeSettlement) {
        this.placedFreeSettlement = placedFreeSettlement;
    }

    public boolean hasPlacedFreeRoad() {
        return placedFreeRoad;
    }

    public void setPlacedFreeRoad(boolean placedFreeRoad) {
        this.placedFreeRoad = placedFreeRoad;
    }

    @Override
    public void acceptTrade(TradeInterface trade) {
        assert (this.canAcceptTrade(trade));

        for (ResourceType type : ResourceType.values()) {
            this.resourceList.setResourceByType(type, this.resourceList.getResourceByType(type) + trade.getTradeCard(type));
        }
    }

    @Override
    public void discardCards(ResourceList list) {
        assert (this.canDiscardCards(list));

        for (ResourceType type : ResourceType.values()) {
            this.resourceList.setResourceByType(type, this.resourceList.getResourceByType(type) - list.getResourceByType(type));
        }
        this.discarded = true;
    }

    @Override
    public void makeMaritimeTrade(MaritimeTrade trade) {
        assert (this.canMaritimeTrade(trade));

        this.resourceList.setResourceByType(trade.getResourceIn(), this.resourceList.getResourceByType(trade.getResourceIn()) - trade.getRatio());
        this.resourceList.setResourceByType(trade.getResourceOut(), this.resourceList.getResourceByType(trade.getResourceOut()) - 1);
    }

    @Override
    public void makeDomesticTrade(TradeInterface trade) {
        assert (this.canOfferTrade(trade));

        for (ResourceType type : ResourceType.values()) {

            this.resourceList.setResourceByType(type, this.resourceList.getResourceByType(type) - trade.getTradeCard(type));

        }
    }

    @Override
    public void buyDevCard(DevCardType dev_card_type) {
        assert (this.canBuyDevCard());

        ResourceList price = new ResourceList(0, 1, 1, 1, 0);

        for (ResourceType type : ResourceType.values()) {
            this.resourceList.setResourceByType(type, this.resourceList.getResourceByType(type) - price.getResourceByType(type));
        }

        addNewDevCard(dev_card_type);
    }

    private void addNewDevCard(DevCardType type) {
        this.newDevCards.setCardsByType(type, this.newDevCards.getCardsByType(type) + 1);
    }

    @Override
    public void playYearOfPlenty(ResourceType type1, ResourceType type2) {
        assert (this.canPlayYearOfPlenty());

        this.oldDevCards.setYearOfPlenty(this.oldDevCards.getYearOfPlenty() - 1);
        this.playedDevCard = true;

        this.resourceList.setResourceByType(type1, this.resourceList.getResourceByType(type1) + 1);
        this.resourceList.setResourceByType(type2, this.resourceList.getResourceByType(type2) + 1);
    }

    @Override
    public void playRoadBuilding() {
        assert (this.canPlayRoadBuilding());

        this.oldDevCards.setRoadBuild(this.oldDevCards.getRoadBuild() - 1);
        this.playedDevCard = true;

        this.roads -= 2;
    }

    @Override
    public void playSoldier(ResourceType stolen_resource) {
        assert (this.canPlaySoldier());

        this.oldDevCards.setSoldier(this.oldDevCards.getSoldier() - 1);
        this.playedDevCard = true;

        if (stolen_resource != null) {
            this.resourceList.setResourceByType(stolen_resource, this.resourceList.getResourceByType(stolen_resource) + 1);
        }
    }

    @Override
    public void playMonument() {
        assert (this.canPlayMonument());

        if (this.oldDevCards.getMonument() > 0) {
            this.oldDevCards.setMonument(this.oldDevCards.getMonument() - 1);
        } else {
            this.newDevCards.setMonument(this.newDevCards.getMonument() - 1);
        }

        this.playedDevCard = true;
        this.victoryPoints++;
    }

    @Override
    public void playMonopoly(ResourceType type, int number_of_resource_taken) {
        assert (this.canPlayMonopoly());

        this.oldDevCards.setMonopoly(this.oldDevCards.getMonopoly() - 1);
        this.playedDevCard = true;

        this.resourceList.setResourceByType(type, this.resourceList.getResourceByType(type) + number_of_resource_taken);
    }

    @Override
    public void buildRoad(boolean free) {
        assert (this.canBuildRoad());

        if (free) {
            this.placedFreeRoad = true;
        } else {
            this.resourceList.setBrick(this.resourceList.getBrick() - 1);
            this.resourceList.setWood(this.resourceList.getWood() - 1);
        }

        this.roads--;
    }

    @Override
    public void buildSettlement(boolean free) {
        assert (this.canBuildSettlement());

        if (free) {
            this.placedFreeSettlement = true;
        } else {
            this.resourceList.setBrick(this.resourceList.getBrick() - 1);
            this.resourceList.setWood(this.resourceList.getWood() - 1);
            this.resourceList.setWheat(this.resourceList.getWheat() - 1);
            this.resourceList.setSheep(this.resourceList.getSheep() - 1);
        }

        this.settlements--;
        this.victoryPoints++;
    }

    @Override
    public void buildCity() {
        assert (this.canBuildCity());

        this.resourceList.setWheat(this.resourceList.getWheat() - 2);
        this.resourceList.setOre(this.resourceList.getOre() - 3);
        this.cities--;
        this.victoryPoints++;
    }

    public ResourceType rob() {
        ArrayList<ResourceType> potential_types = new ArrayList<ResourceType>();

        for (ResourceType type : ResourceType.values()) {
            if (this.resourceList.getResourceByType(type) > 0) {
                potential_types.add(type);
            }
        }

        int resource;
        do {
            resource = (int) ((Math.random() * 31)) % potential_types.size();
        } while(resource < 0);

        this.resourceList.setResourceByType(potential_types.get(resource), this.resourceList.getResourceByType(potential_types.get(resource)) - 1);
        return potential_types.get(resource);
    }

    @Override
    public void addRollResources(ResourceList resource_list) {
        for (ResourceType resource : ResourceType.values()) {
            this.resourceList.setResourceByType(resource, this.resourceList.getResourceByType(resource) + resource_list.getResourceByType(resource));
        }
    }

    @Override
    public int giveUpCards(ResourceType resource_type) {
        int number_of_card = this.resourceList.getResourceByType(resource_type);
        this.resourceList.setResourceByType(resource_type, 0);
        return number_of_card;
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

        return roads == other.roads
                && settlements == other.settlements
                && soldiers == other.soldiers
                && victoryPoints == other.victoryPoints;
    }

    @Override
    public String toString() {
        String playerString = "";
        playerString += "Name: " + name + "\n" +
                "PlayerIndex: " + playerIndex + "\n" +
                "Player ID: " + playerId + "\n" +
                "Color: " + color + "\n" +
                "Roads: " + roads + "\n" +
                "Cities: " + cities + "\n" +
                "Settlements: " + settlements + "\n" +
                "Monuments: " + monuments + "\n" +
                "Soldiers: " + soldiers + "\n" +
                "Victory Points: " + victoryPoints + "\n" +
                "Discarded: " + discarded + "\n" +
                "Played Dev Card: " + playedDevCard + "\n" +
                "ResourceList: " + "\n" + resourceList + "\n";

        return playerString;
    }
}
