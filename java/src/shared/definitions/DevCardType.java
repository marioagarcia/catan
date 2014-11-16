package shared.definitions;

public enum DevCardType
{
	SOLDIER, YEAR_OF_PLENTY, MONOPOLY, ROAD_BUILD, MONUMENT;

	public static DevCardType getRandomType() {

		DevCardType[] types = values();

		return types[(int)(Math.random() * 100) % types.length];
	}
}

