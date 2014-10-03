package client.model.piece;

import client.model.map.HexCorner;

public class Settlement implements SettlementInterface {
	
	GamePieceType type;
	int playerId;
	HexCorner location;
	
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
	public void setLocation(HexCorner corner) {
		this.location = corner;
	}

	@Override
	public HexCorner getLocation() {
		return this.location;
	}

}
