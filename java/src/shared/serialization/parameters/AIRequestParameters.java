package shared.serialization.parameters;

public class AIRequestParameters extends MasterParameterInterface{

	private String AIType;
	
	public AIRequestParameters(String aiType){
		AIType = aiType;
	}

	public String getAIType() {
		return AIType;
	}

	public void setAIType(String aIType) {
		AIType = aIType;
	}
}
