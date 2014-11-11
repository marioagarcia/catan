package shared.serialization.parameters;

public class JoinGameParameters extends MasterParameterInterface{
	
	private int id;
	private String color;
	
	public JoinGameParameters(int ID, String clr){
		id = ID;
		color = clr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
