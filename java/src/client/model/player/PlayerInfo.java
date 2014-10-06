package client.model.player;

import shared.definitions.CatanColor;
import shared.serialization.interfaces.SerializerPlayerInfoInterface;

/**
 * Used to pass player information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique player ID</li>
 * <li>PlayerIndex: Player's order in the game [0-3]</li>
 * <li>Name: Player's name (non-empty string)</li>
 * <li>Color: Player's color (cannot be null)</li>
 * </ul>
 * 
 */
public class PlayerInfo implements SerializerPlayerInfoInterface
{
	
	private int id;
	private String name;
	private CatanColor color;
	private int playerIndex;

	public PlayerInfo()
	{
		setId(-1);
		setName("");
		setPlayerIndex(-1);
		setColor(CatanColor.WHITE);
	}
	
	private void setPlayerIndex(int i) {
		playerIndex = i;
	}
	
	public int getPlayerIndex(){
		return playerIndex;
	}

	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public CatanColor getColor()
	{
		return this.color;
	}
	
	public void setColor(CatanColor color)
	{
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		PlayerInfo other = (PlayerInfo) obj;
		if (color != other.color)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		String str = "Player Name: " + name + "\n";
		str += "Player ID: " + id + "\n";
		str += "Player Color: " + color.toString() + "\n";
		return str;
	}

	@Override
	public void setPlayerInfo(CatanColor playerColor, String playerName,
			int playerId) {
		this.color = playerColor;
		this.name = playerName;
		this.id = playerId;
	}
}

