package client.model.card;

/**
 * This class enumerates the different types of development cards
 */
public interface DevCardInterface
{
	/**
	 * The different types of development cards
	 */
	public enum DevCardType
	{
		//VICTORYPOINTCARD,
		//PROGRESSCARD,
		//KNIGHTCARD
		
		//Theirs
		SOLDIER, 
		YEAR_OF_PLENTY, 
		MONOPOLY, 
		ROAD_BUILD, 
		MONUMENT
	}
	
	public DevCardType getType();
}
