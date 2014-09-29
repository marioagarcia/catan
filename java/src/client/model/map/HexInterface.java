package client.model.map;

/**
 * Base Interface for all the different Hexes that constitute the map
 *
 */
public interface HexInterface {

	/**
	 * Queries the hex for it's type
	 * @return enum type
	 */
	public abstract int getType();
	
	public abstract int getX();
	
	public abstract int setX(int x);
	
	public abstract int getY();
	
	public abstract int setY(int y);
	
	//TODO This method should return a Location (not void) and take a Direction as a parameter
	public void getNeighborLocation(Object obj);
	
	@Override 
	public String toString();
	
	@Override
	public boolean equals(Object obj);
}
