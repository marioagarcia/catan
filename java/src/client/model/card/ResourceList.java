package client.model.card;

import shared.definitions.ResourceType;
import shared.serialization.interfaces.SerializerResourceListInterface;

public class ResourceList implements SerializerResourceListInterface {


	private int brick;
	private int ore;
	private int sheep;
	private int wheat;
	private int wood;
		
	public ResourceList(int b, int o, int s, int wh, int wo){
		brick = b;
		ore = o;
		sheep = s;
		wheat = wh;
		wood = wo;
	}
	
	public int getResourceByType(String type){
		switch (type){
			case "brick": 
				return brick;
			case "ore":
				return ore;
			case "sheep":
				return sheep;
			case "wheat":
				return wheat;
			case "wood":
				return wood;
			default:
				return 0;
		}
	}
	
	public int getResourceByType(ResourceType type){
		switch(type){
		case BRICK:
			return brick;
		case ORE:
			return ore;
		case SHEEP:
			return sheep;
		case WHEAT:
			return wheat;
		case WOOD:
			return wood;
		default:
			return 0;
		}
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

	@Override
	public void setResourceList(int brick, int ore, int sheep, int wheat, int wood)
	{
		setBrick(brick);
		setOre(ore);
		setSheep(sheep);
		setWheat(wheat);
		setWood(wood);
	}
	
	public int totalNumberCards(){
		return this.brick +
				this.ore +
				this.sheep +
				this.wheat +
				this.wood;
	}
	
	@Override
	public String toString(){
		String returnString = "";
		
		returnString += "\tBrick: " + brick + "\n";
		returnString += "\tWood: " + wood + "\n";
		returnString += "\tSheep: " + sheep + "\n";
		returnString += "\tWheat: " + wheat + "\n";
		returnString += "\tOre: " + ore;
		return returnString;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brick;
		result = prime * result + ore;
		result = prime * result + sheep;
		result = prime * result + wheat;
		result = prime * result + wood;
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
		ResourceList other = (ResourceList) obj;
		if (brick != other.brick)
			return false;
		if (ore != other.ore)
			return false;
		if (sheep != other.sheep)
			return false;
		if (wheat != other.wheat)
			return false;
		if (wood != other.wood)
			return false;
		return true;
	}
	
}
