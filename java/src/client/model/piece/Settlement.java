package client.model.piece;

import client.model.map.HexCornerInterface;

public class Settlement implements SettlementInterface {
	
	GamePieceType type;
	int playerId;
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
	public void setPlayerId(int id) {
		this.playerId = id;
	}

	@Override
	public int getPlayerId() {
		return this.playerId;
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
