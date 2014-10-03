package shared.serialization.parameters;

public class MaritimeTradeParameters {

	private String type;
	private int playerIndex;
	private int ratio;
	private String inputResource;
	private String outputResource;
	
	public MaritimeTradeParameters(int index, int rat, String inputRsrc, String outputRsrc){
		type = "maritimeTrade";
		playerIndex = index;
		ratio = rat;
		inputResource = inputRsrc;
		outputResource = outputRsrc;
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

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public String getInputResource() {
		return inputResource;
	}

	public void setInputResource(String inputResource) {
		this.inputResource = inputResource;
	}

	public String getOutputResource() {
		return outputResource;
	}

	public void setOutputResource(String outputResource) {
		this.outputResource = outputResource;
	}
}
