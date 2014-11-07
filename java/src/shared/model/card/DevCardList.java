package shared.model.card;

import shared.serialization.interfaces.SerializerDevCardListInterface;

public class DevCardList implements SerializerDevCardListInterface {
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
