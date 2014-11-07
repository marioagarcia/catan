package shared.serialization.parameters;

public class LoadGameRequestParameters extends MasterParameterInterface{

	private String name;
	
	public LoadGameRequestParameters(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
