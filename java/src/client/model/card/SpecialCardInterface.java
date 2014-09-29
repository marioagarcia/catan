package client.model.card;

public interface SpecialCardInterface extends CardInterface 
{
	/**
	 * The types of special cards available
	 */
	public enum SpecialCardType
	{
		LongestRoadCard,
		LargestArmyCard
	}
}