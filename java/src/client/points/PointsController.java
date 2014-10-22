package client.points;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.communication.facade.ModelFacade;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController {

	private IGameFinishedView finishedView;
	
	private ModelFacade facade = ModelFacade.getInstance(null);
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView) {
		
		super(view);
		
		setFinishedView(finishedView);
		
		facade.getManager().getLocalPlayer().addObserver(playerObserver);
		//initFromModel();
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

	private void updatePoints() {
		
	}
	
	private Observer playerObserver = new Observer() {
		
		@Override
		public void update(Observable o, Object arg) {
			
			
		}
	};
	
}

