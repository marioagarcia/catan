package shared.serialization.parameters;

public class JoinGameRequestParameters {
	
	private int id;
	private String color;
	
	public JoinGameRequestParameters(int ID, String clr){
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
