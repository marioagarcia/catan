package client.model.map;

import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;

public interface HexInterface {
	
	/**
	 * returns the location object with the location of the Hex
	 * @return HexLocation
	 */
	public abstract HexLocation getLocation();
	
	/**
	 * this returns the HexType of the hex
	 * @return HexType
	 */
	public abstract HexType getType();
	/**
	 * sets the type of hex
	 * @param type HexType representing the kind of hex that it is
	 */
	public abstract void setType(HexType type);
	
	/**
	 * gets the dice number which activates this hex
	 * @return int
	 */
	public abstract int getNumber();

	/**
	 * sets the dice roll number which will activate this hex
	 * @param number
	 */
	public abstract void setNumber(int number);
	
	/**
	 * gets the given corner of the HexInterface object
	 * @param type the corner position (top right, etc)
	 * @return HexCorner
	 */
	public abstract HexCornerInterface getCorner(VertexDirection direction);
	
	/**
	 * returns the border given border of the Hex
	 * @param type HexBorderInterface.HexBorderType
	 * @return HexBorderInterface
	 */
	public abstract HexBorderInterface getBorder(EdgeDirection location);
	
	public enum HexType{
		BRICK,
		WHEAT,
		SHEEP,
		ORE,
		WOOD
	}
}
