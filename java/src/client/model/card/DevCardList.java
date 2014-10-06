package client.model.card;

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

}
