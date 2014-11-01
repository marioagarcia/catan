package client.points;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.GameModel;
import client.model.Winner;
import client.model.player.Player;


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

		ModelFacade.getInstance(null).addObserver(gameModelObserver);

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
			
			Winner winner = ((GameModel)o).getWinner();

			if(winner != null){
				String name = winner.getName();
				boolean i_won = winner.isLocalPlayer();
				if(!hasWinner){
					updateWinner(name, i_won);
				}
			}
			
			Player player = ((GameModel)o).getLocalPlayer();
			PointsController.this.updatePoints(player.getPoints());
		}
	};

}

