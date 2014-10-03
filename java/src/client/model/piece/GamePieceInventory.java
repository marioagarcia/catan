package client.model.piece;

import java.util.Collection;

public class GamePieceInventory implements GamePieceInventoryInterface {
	
	private Collection<GamePieceInterface> pieces;
	
	public GamePieceInventory(){
		
	}

	@Override
	public void addPiece(GamePieceInterface piece) {
		pieces.add(piece);
	}

	@Override
	public GamePieceInterface removePiece(GamePieceInterface.GamePieceType type) throws PieceNotFoundException {
		for(GamePieceInterface piece : pieces)
			if(piece.getType() == type){
				pieces.remove(piece);
				return piece;
			}

		throw new PieceNotFoundException();
	}

	@Override
	public int getPieceCount(GamePieceInterface piece) {
		return pieces.size();
	}

}
