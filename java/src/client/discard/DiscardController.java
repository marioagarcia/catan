package client.discard;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.*;
import client.base.*;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;
import client.communication.facade.ModelFacade;
import client.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

	private IWaitView waitView;
	private Map<ResourceType, Integer> amounts;
	
	private class DiscardObserver implements Observer{
		
		@Override
		public void update(Observable o, Object arg){
			TurnTracker turn_tracker = (TurnTracker)o;
			
			if(turn_tracker.getStatus() != Status.DISCARDING){
				return;
			}
			
			for(ResourceType resource : ResourceType.values()){
				int number_of_resource = ModelFacade.getInstance(null).getManager().getResCardBank().getNumberOfResourcesByType(resource);
				
				getDiscardView().setResourceMaxAmount(resource, number_of_resource);
				getDiscardView().setResourceDiscardAmount(resource, 0);
				amounts.put(resource,  0);
			}

			updateDiscardView();
			getDiscardView().showModal();
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
		amounts = new HashMap<ResourceType, Integer>();
		
		this.waitView = waitView;
		ModelFacade.getInstance(null).getManager().getTurnTracker().addObserver(new DiscardObserver());
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		this.amounts.put(resource, this.amounts.get(resource) + 1);
		updateDiscardView();
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		this.amounts.put(resource, this.amounts.get(resource) - 1);
		updateDiscardView();
	}

	@Override
	public void discard() {
		getDiscardView().closeModal();
	}
	
	private void updateDiscardView(){
		int total_cards = 0;
		int total_discard_amount = 0;
		
		for(ResourceType resource : ResourceType.values()){
			int number_of_resource = ModelFacade.getInstance(null).getManager().getResCardBank().getNumberOfResourcesByType(resource);
			int number_of_resource_to_discard = this.amounts.get(resource);
			
			total_cards += number_of_resource;
			total_discard_amount += number_of_resource_to_discard;
			getDiscardView().setResourceDiscardAmount(resource, number_of_resource_to_discard);
			
			boolean increase = this.amounts.get(resource) < ModelFacade.getInstance(null).getManager().getResCardBank().getNumberOfResourcesByType(resource);
			boolean decrease = this.amounts.get(resource) > 0;
			getDiscardView().setResourceAmountChangeEnabled(resource, increase, decrease);
		}
		
		getDiscardView().setStateMessage(total_discard_amount + "/" + (total_cards - 7));
	}

}

