package shared.serialization.parameters;

public class LoadGameRequestParameters {

	private String name;
	
	public LoadGameRequestParameters(String nme){
		name = nme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
