package client.model.piece;

import client.model.map.HexInterface;

public class Robber implements RobberInterface {
	
	GamePieceType type;
	int playerIndex;
	HexInterface location;
	
	public Robber()
	{
		this.type = GamePieceType.ROBBER;
	}

	@Override
	public GamePieceType getType() {
		return type;
	}

	@Override
	public void setPlayerIndex(int index) {
		this.playerIndex = index;
	}

	@Override
	public int getPlayerIndex() {
		return this.playerIndex;
	}

	@Override
	public void moveTo(HexInterface location) {
		this.location = location;
	}

	@Override
	public HexInterface getLocation() {
		return location;
	}

}
