package shared.model.card;

import java.io.Serializable;

import shared.serialization.interfaces.SerializerDevCardListInterface;
import shared.definitions.DevCardType;

@SuppressWarnings("serial")
public class DevCardList implements SerializerDevCardListInterface, Serializable {
	private int yearOfPlenty;
	private int monopoly;
	private int soldier;
	private int roadBuild;
	private int monument;
	
	public DevCardList(){
		
	}

	public int getMonopoly() {
		return monopoly;
	}

	public void setMonopoly(int monopoly) {
		this.monopoly = monopoly;
	}

	public int getMonument() {
		return monument;
	}

	public void setMonument(int monument) {
		this.monument = monument;
	}

	public int getRoadBuild() {
		return roadBuild;
	}

	public void setRoadBuild(int roadBuilding) {
		this.roadBuild = roadBuilding;
	}

	public int getSoldier() {
		return soldier;
	}

	public void setSoldier(int soldier) {
		this.soldier = soldier;
	}

	public int getYearOfPlenty() {
		return yearOfPlenty;
	}

	public void setYearOfPlenty(int yearOfPlenty) {
		this.yearOfPlenty = yearOfPlenty;
	}

    public void setCardsByType(DevCardType type, int value){
        switch(type){
            case SOLDIER:
                this.setSoldier(value);
                return;
            case YEAR_OF_PLENTY:
                this.setYearOfPlenty(value);
                return;
            case MONOPOLY:
                this.setMonopoly(value);
                return;
            case ROAD_BUILD:
                this.setRoadBuild(value);
                return;
            case MONUMENT:
                this.setMonument(value);
                return;
            default:
                System.out.println("Illegal value of " + type + " passed to setCardsByType()");
        }
    }

    public int getCardsByType(DevCardType type){
        switch(type){
            case SOLDIER:
                return this.getSoldier();
            case YEAR_OF_PLENTY:
                return this.getYearOfPlenty();
            case MONOPOLY:
                return this.getMonopoly();
            case ROAD_BUILD:
                return this.getRoadBuild();
            case MONUMENT:
                return this.getMonument();
            default:
                System.out.println("Illegal value of " + type + " passed to setCardsByType()");
                return -1;
        }
    }

	@Override
	public void setDevCardList(int yearOfPlenty, int monopoly, int soldier,
			int roadBuild, int monument)
	{
		setYearOfPlenty(yearOfPlenty);
		setMonopoly(monopoly);
		setSoldier(soldier);
		setRoadBuild(roadBuild);
		setMonument(monument);
	}

	@Override
	public String toString(){
		String returnString = "";
		
		returnString += "\tYear of Plenty: " + yearOfPlenty + "\n";
		returnString += "\tMonopoly: " + monopoly + "\n";
		returnString += "\tSoldier: " + soldier + "\n";
		returnString += "\tRoadBuilding: " + roadBuild + "\n";
		returnString += "\tMonument: " + monument;
		
		return returnString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + monopoly;
		result = prime * result + monument;
		result = prime * result + roadBuild;
		result = prime * result + soldier;
		result = prime * result + yearOfPlenty;
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
		DevCardList other = (DevCardList) obj;
		if (monopoly != other.monopoly)
			return false;
		if (monument != other.monument)
			return false;
		if (roadBuild != other.roadBuild)
			return false;
		if (soldier != other.soldier)
			return false;
		if (yearOfPlenty != other.yearOfPlenty)
			return false;
		return true;
	}

}
