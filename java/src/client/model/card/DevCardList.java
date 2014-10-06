package client.model.card;

import shared.serialization.interfaces.DevCardListInterface;

public class DevCardList implements DevCardListInterface {
	private int monopoly;
	private int monument;
	private int roadBuilding;
	private int soldier;
	private int yearOfPlenty;
	
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

	public int getRoadBuilding() {
		return roadBuilding;
	}

	public void setRoadBuilding(int roadBuilding) {
		this.roadBuilding = roadBuilding;
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
		setRoadBuilding(roadBuild);
		setMonument(monument);
	}


}
