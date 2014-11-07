package shared.model.piece;

import shared.definitions.PieceType;
import shared.model.map.HexInterface;

public class Robber implements RobberInterface {
	
	PieceType type;
	int playerIndex;
	HexInterface location;
	
	public Robber()
	{
		this.type = PieceType.ROBBER;
	}

	@Override
	public PieceType getType() {
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
