package client.discard;

import java.util.Observable;
import java.util.Observer;
import shared.definitions.*;
import client.base.*;
import client.model.GameModel;
import client.model.card.ResourceList;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;
import client.communication.facade.ModelFacade;
import client.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

	private IWaitView waitView;
	private ResourceList resourceList;
//	private Boolean isDiscarding;
	private TurnTracker oldTurnTracker;
	
	private class DiscardModelObserver implements Observer{
		
		@Override
		public void update(Observable o, Object arg){
			
			GameModel latest_model = (GameModel) o;
			TurnTracker turn_tracker = latest_model.getTurnTracker();

			if(turn_tracker.getCurrentTurn() != oldTurnTracker.getCurrentTurn() || turn_tracker.getStatus() != oldTurnTracker.getStatus()){
				oldTurnTracker = turn_tracker;
				
				if(turn_tracker.getStatus() != Status.DISCARDING){
					return;
				}
				
				if(latest_model.getLocalPlayer().getNumberOfCards() <= 7){
					ModelFacade.getInstance(null).discardCards(new ResourceList(0,0,0,0,0));
					
					if(getDiscardView().isModalShowing()){
						getDiscardView().closeModal();
					}
					return;
				}
				
				for(ResourceType resource : ResourceType.values()){
					int number_of_resource = latest_model.getLocalPlayer().getResourceList().getResourceByType(resource);
					
					getDiscardView().setResourceMaxAmount(resource, number_of_resource);
					getDiscardView().setResourceDiscardAmount(resource, 0);
					resourceList.setResourceByType(resource, 0);
					getDiscardView().setDiscardButtonEnabled(false);
				}
	
				updateDiscardView();
				if(!getDiscardView().isModalShowing()){
					getDiscardView().showModal();
				}
			}
		}
	}
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		
		super(view);
		resourceList = new ResourceList(0,0,0,0,0);
		
		this.waitView = waitView;
		ModelFacade.getInstance(null).addObserver(new DiscardModelObserver());
		
		oldTurnTracker = new TurnTracker();
		oldTurnTracker.setStatus(null);
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		this.resourceList.setResourceByType(resource, this.resourceList.getResourceByType(resource) + 1);
		updateDiscardView();
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		this.resourceList.setResourceByType(resource, this.resourceList.getResourceByType(resource) - 1);
		updateDiscardView();
	}

	@Override
	public void discard() {
		if(getDiscardView().isModalShowing()){
			getDiscardView().closeModal();
		}
		ModelFacade.getInstance(null).discardCards(this.resourceList);
	}
	
	private void updateDiscardView(){
		int total_cards = 0;
		
		for(ResourceType resource : ResourceType.values()){
			total_cards += this.resourceList.getResourceByType(resource);
			getDiscardView().setResourceDiscardAmount(resource, this.resourceList.getResourceByType(resource));
		}
		
		int amount_to_discard = ModelFacade.getInstance(null).getLocalPlayer().getNumberOfCards()/2;
		
		getDiscardView().setDiscardButtonEnabled(total_cards == amount_to_discard);
		
		for(ResourceType resource : ResourceType.values()){
			boolean increase = this.resourceList.getResourceByType(resource) < ModelFacade.getInstance(null).getLocalPlayer().getResourceList().getResourceByType(resource);
			boolean decrease = this.resourceList.getResourceByType(resource) > 0;
			getDiscardView().setResourceAmountChangeEnabled(resource, increase, decrease);
		}
		
		getDiscardView().setStateMessage(total_cards + "/" + amount_to_discard);
	}

}

