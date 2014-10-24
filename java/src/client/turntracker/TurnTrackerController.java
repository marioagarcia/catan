package client.turntracker;

import client.base.*;
import client.communication.facade.ModelFacade;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {
	
	ModelFacade facade;
	boolean playersInitialized;

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
		facade  = ModelFacade.getInstance(null);
		
		playersInitialized = false;
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		
		facade.finishTurn();
	}

}

