package shared.serialization.parameters;

import shared.locations.VertexLocation;

public class VertexLocationParameters {

	private int x;
	private int y;
	private String direction;
	
	public VertexLocationParameters(VertexLocation location){
		x = location.getHexLoc().getX();
		y = location.getHexLoc().getY();
		direction = location.getDir().name();
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
