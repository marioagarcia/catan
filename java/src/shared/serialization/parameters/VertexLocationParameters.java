package shared.serialization.parameters;

import shared.locations.VertexLocation;

public class VertexLocationParameters extends MasterParameterInterface{

	private int x;
	private int y;
	private String direction;
	
	public VertexLocationParameters(VertexLocation location){
		x = location.getHexLoc().getX();
		y = location.getHexLoc().getY();
		String temp_direction = location.getDir().name();
		
		switch(temp_direction)
		{
			case "West":
				temp_direction = "W";
				break;
			case "NorthWest": 
				temp_direction = "NW";
				break;
			case "NorthEast":
				temp_direction = "NE";
				break;
			case "East":
				temp_direction = "E";
				break;
			case "SouthEast":
				temp_direction = "SE";
				break;
			case "SouthWest":
				temp_direction = "SW";
				break;
		}
		
		direction = temp_direction;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
