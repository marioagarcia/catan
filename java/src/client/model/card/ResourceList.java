package client.model.card;

public class ResourceList {

	int brick;
	int ore;
	int sheep;
	int wheat;
	int wood;
	
	public ResourceList(int b, int o, int s, int wh, int wo){
		brick = b;
		ore = o;
		sheep = s;
		wheat = wh;
		wood = wo;
	}

	public int getBrick() {
		return brick;
	}

	public void setBrick(int brick) {
		this.brick = brick;
	}

	public int getOre() {
		return ore;
	}

	public void setOre(int ore) {
		this.ore = ore;
	}

	public int getSheep() {
		return sheep;
	}

	public void setSheep(int sheep) {
		this.sheep = sheep;
	}

	public int getWheat() {
		return wheat;
	}

	public void setWheat(int wheat) {
		this.wheat = wheat;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}
}
