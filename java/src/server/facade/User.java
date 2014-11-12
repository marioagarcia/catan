package server.facade;

public class User {

	private String username;
	private String password;
	private int ID;
	
	public User(String name, String password, int id){
		
		this.username = name;
		this.password = password;
		this.ID = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
}
