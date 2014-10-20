package client.join;

import client.base.*;
import client.communication.facade.ModelFacade;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		ModelFacade facade = ModelFacade.getInstance(null);
		
		String[] listAI = {"Butthole", "Buttface", "Butthead", "Buttwad"};
		getView().setAIChoices(listAI);
		
		getView().showModal();
	}

	@Override
	public void addAI() {

		// TEMPORARY
		getView().closeModal();
	}

}

