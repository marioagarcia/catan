package client.model;

import java.util.*;

import shared.definitions.CatanColor;
import shared.serialization.interfaces.SerializerGameInfoInterface;
import client.model.player.Player;
import client.model.player.PlayerInfo;

/**
 * Used to pass game information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique game ID</li>
 * <li>Title: Game title (non-empty string)</li>
 * <li>Players: List of players who have joined the game (can be empty)</li>
 * </ul>
 * 
 */
public class GameInfo extends Observable implements SerializerGameInfoInterface
{
	private int id;
	private String title;
	private List<PlayerInfo> players;
	
	public GameInfo()
	{
		setId(-1);
		setTitle("");
		players = new ArrayList<PlayerInfo>();
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void addPlayer(PlayerInfo newPlayer)
	{
		players.add(newPlayer);
	}
	
	public List<PlayerInfo> getPlayers()
	{
		return Collections.unmodifiableList(players);
	}
	
	public boolean playerCanJoin(Player localPlayer) {
		PlayerInfo player = new PlayerInfo();
		player.setPlayerInfo(localPlayer.getColor(), localPlayer.getName(), localPlayer.getId());
		
		boolean color_available = true;
		
		for (PlayerInfo p : players)
		{	
			if (p.getColor() == player.getColor())
			{
				if(!p.equals(player))
					color_available = false;
			}
		}
		
		return ((players.size() < 4 || players.contains(player)) && color_available);
	}
	
	public String toString(){
		String str = "Game Title: " + title + "\n";
		str += "Game ID: " + id + "\n";
		for(int i = 0; i < players.size(); i++){
			str += players.get(i).toString();
		}
		return str;
	}
	
	public boolean validateColor(CatanColor color) {
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).getColor() == color)
				return false;
		}
		return true;
	}

	@Override
	public void setGameInfo(String gameTitle, int gameId,
			ArrayList<PlayerInfo> playerList) {
		title = gameTitle;
		id = gameId;
		players = playerList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameInfo other = (GameInfo) obj;
		if (id != other.id)
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}

