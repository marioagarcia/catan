package client.points;

import java.util.Observable;
import java.util.Observer;
import shared.model.GameModel;
import shared.model.player.Player;
import client.base.*;
import client.facade.ClientModelFacade;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController {

	private IGameFinishedView finishedView;
	
	Boolean hasWinner;

	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView) {

		super(view);

		setFinishedView(finishedView);

		ClientModelFacade.getInstance(null).addObserver(gameModelObserver);

		hasWinner = false;
	}

	public IPointsView getPointsView() {

		return (IPointsView)super.getView();
	}

	public IGameFinishedView getFinishedView() {
		return finishedView;
	}

	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	private void updatePoints(int points) {
		getPointsView().setPoints(points);
	}

	private void updateWinner(String name, boolean isLocalPlayer) {
		hasWinner = true;
		getFinishedView().setWinner(name, isLocalPlayer);
		getFinishedView().showModal();
	}

	private Observer gameModelObserver = new Observer() {

		@Override
		public void update(Observable o, Object arg) {
			GameModel game_model = (GameModel)o;
			
			Player player = ((GameModel)o).getLocalPlayer();
			PointsController.this.updatePoints(player.getPoints());
			
			int winner_player_index = game_model.getWinnerPlayerIndex();

			if(winner_player_index != -1) {

				int local_player_index = game_model.getLocalPlayer().getPlayerIndex();

				Player winner= game_model.getPlayers().getPlayer(winner_player_index);
				
				if(winner != null) {
					
					String name = winner.getName();
					
					boolean local_player_wins = (winner.getPlayerIndex() == local_player_index);
					
					if(!hasWinner){
						updateWinner(name, local_player_wins);
					}
				}
			}
		}
	};

}

