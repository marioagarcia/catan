package client.model.piece;

import client.model.map.HexInterface;

public class Robber implements RobberInterface {
	
	GamePieceType type;
	int playerId;
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
	public void setPlayerId(int id) {
		this.playerId = id;
	}

	@Override
	public int getPlayerId() {
		return this.playerId;
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
