package client.model.piece;

import shared.locations.VertexLocation;
import shared.serialization.interfaces.SerializerSettlementInterface;


public class Settlement implements SettlementInterface, SerializerSettlementInterface {
	
	GamePieceType type;
	int playerIndex;
	VertexLocation location;
	
	public Settlement(){
		this.type = GamePieceType.SETTLEMENT;
		//TODO this should start out in the desert
	}
	
	public Settlement(int playerIndex, VertexLocation location){
		this.playerIndex = playerIndex;
		this.location = location;
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
	public void setLocation(VertexLocation corner) {
		this.location = corner;
	}

	@Override
	public VertexLocation getLocation() {
		return this.location;
	}

	@Override
	public void setCity(int playerIndex, VertexLocation vertexLocation) {
		this.playerIndex = playerIndex;
		this.location = vertexLocation;
	}

	@Override
	public String toString(){
		String returnString = "";
		
		returnString += playerIndex + " -- " + location.toString() + "\n";
		
		return returnString;
	}
}
