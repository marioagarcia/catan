package shared.serialization.parameters;

public class YearOfPlentyParameters {

	private String type;
	private int playerIndex;
	private String resource1;
	private String resource2;
	
	public YearOfPlentyParameters(int index, String resourceOne, String resourceTwo){
		type = "Year_of_Plenty";
		playerIndex = index;
		resource1 = resourceOne;
		resource2 = resourceTwo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public String getResource1() {
		return resource1;
	}

	public void setResource1(String resource1) {
		this.resource1 = resource1;
	}

	public String getResource2() {
		return resource2;
	}

	public void setResource2(String resource2) {
		this.resource2 = resource2;
	}
}
