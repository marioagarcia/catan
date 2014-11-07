package shared.model.map;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public interface HexInterface {
	
	/**
	 * returns the location object with the location of the Hex
	 * @return HexLocation the location of the hex
	 */
	public abstract HexLocation getLocation();
	
	/**
	 * this returns the HexType of the hex
	 * @return HexType the resouce type of the hex
	 */
	public abstract HexType getType();
	/**
	 * sets the type of hex
	 * @param type HexType representing the kind of hex that it is
	 */
	public abstract void setType(HexType type);
	
	/**
	 * gets the dice number which activates this hex
	 * @return int the number rolled
	 */
	public abstract int getNumber();

	/**
	 * sets the dice roll number which will activate this hex
	 * @param number the number rolled
	 */
	public abstract void setNumber(int number);

}
