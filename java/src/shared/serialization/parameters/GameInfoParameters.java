package shared.serialization.parameters;

public class GameInfoParameters {

	private String title;
	private int id;
	private PlayerInfoParameters[] players;
	
	public GameInfoParameters(String title, int id, PlayerInfoParameters[] players){
		this.title = title;
		this.id = id;
		this.players = players;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PlayerInfoParameters[] getPlayers() {
		return players;
	}

	public void setPlayers(PlayerInfoParameters[] players) {
		this.players = players;
	}
	
}
