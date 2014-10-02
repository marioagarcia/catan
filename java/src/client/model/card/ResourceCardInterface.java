package client.model.card;

public interface ResourceCardInterface 
{
	/**
	 * The types of resource cards available
	 */
	public enum ResourceCardType
	{
		BRICK,
		WOOD,
		SHEEP,
		WHEAT,
		ORE
	}
	
	public ResourceCardType getResourceCardType();
}
