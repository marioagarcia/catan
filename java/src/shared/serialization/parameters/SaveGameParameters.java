package shared.serialization.parameters;

public class SaveGameParameters extends MasterParameterInterface{

	private int id;
	private String name;
	
	public SaveGameParameters(int ID, String nme){
		id = ID;
		name = nme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
