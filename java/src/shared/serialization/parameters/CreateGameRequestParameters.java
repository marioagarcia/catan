package shared.serialization.parameters;

public class CreateGameRequestParameters extends MasterParameterInterface{

	private boolean randomTiles;
	private boolean randomNumbers;
	private boolean randomPorts;
	private String name;
	
	public CreateGameRequestParameters(boolean randTiles, boolean randNums, boolean randPorts, String nme){
		randomTiles = randTiles;
		randomNumbers = randNums;
		randomPorts = randPorts;
		name = nme;
	}

	public boolean isRandomTiles() {
		return randomTiles;
	}

	public void setRandomTiles(boolean randomTiles) {
		this.randomTiles = randomTiles;
	}

	public boolean isRandomNumbers() {
		return randomNumbers;
	}

	public void setRandomNumbers(boolean randomNumbers) {
		this.randomNumbers = randomNumbers;
	}

	public boolean isRandomPorts() {
		return randomPorts;
	}

	public void setRandomPorts(boolean randomPorts) {
		this.randomPorts = randomPorts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
