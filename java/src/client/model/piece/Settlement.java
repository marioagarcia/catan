package client.model.piece;

import client.model.map.HexCornerInterface;

public class Settlement implements SettlementInterface {
	
	GamePieceType type;
	int playerIndex;
	HexCornerInterface location;
	
	public Settlement(){
		this.type = GamePieceType.SETTLEMENT;
		//TODO this should start out in the desert
	}

	@Override
	public GamePieceType getType() {
		return this.type;
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
	public void setLocation(HexCornerInterface corner) {
		this.location = corner;
	}

	@Override
	public HexCornerInterface getLocation() {
		return this.location;
	}

}
