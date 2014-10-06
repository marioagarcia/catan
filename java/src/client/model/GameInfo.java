package client.model;

import java.util.*;

import shared.serialization.interfaces.SerializerGameInfoInterface;
import shared.serialization.interfaces.SerializerPlayerInterface;
import client.manager.interfaces.GMGameInfoInterface;
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
public class GameInfo implements GMGameInfoInterface, SerializerGameInfoInterface
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
	
	public boolean playerCanJoin(PlayerInfo player) {
		//TODO implement this method
		return false;
	}
	
	public String toString(){
		String str = "Game Title: " + title + "\n";
		str += "Game ID: " + id + "\n";
		for(int i = 0; i < players.size(); i++){
			str += players.get(i).toString();
		}
		return str;
	}

	@Override
	public void setGameInfo(String gameTitle, int gameId,
			ArrayList<PlayerInfo> playerList) {
		title = gameTitle;
		id = gameId;
		players = playerList;
	}

}

