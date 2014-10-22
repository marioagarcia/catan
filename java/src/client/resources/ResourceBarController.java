package client.resources;

import java.util.*;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.player.Player;
import client.model.turntracker.TurnTracker;

/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController {

	private Map<ResourceBarElement, IAction> elementActions;
	private TurnTracker tracker = null;
	private Player localPlayer = null;
	
	public ResourceBarController(IResourceBarView view) {

		super(view);
		
		elementActions = new HashMap<ResourceBarElement, IAction>();
		
		tracker = ModelFacade.getInstance(null).getManager().getTurnTracker();
		tracker.addObserver(new TrackerObserver());
		
		localPlayer = ModelFacade.getInstance(null).getLocalPlayer();
		localPlayer.addObserver(new ResourceObserver());
	}
	
	private class TrackerObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			tracker = (TurnTracker)o;
			boolean enabled = false;
			
			System.out.println("Resource controller received tracker update");
			if (ModelFacade.getInstance(null).getManager().isLocalPlayersTurn()){
				enabled = true;
			}
			
			for (Map.Entry<ResourceBarElement, IAction> resource_element : elementActions.entrySet()){
				getView().setElementEnabled(resource_element.getKey(), enabled);
			}
			
		}
		
	}
	
	private class ResourceObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			localPlayer = (Player)o;
			
			System.out.println("Resource controller received player update");
			//localPlayer.setResourceList(new ResourceList(5, 5, 5, 5, 5));
			
			getView().setElementAmount(ResourceBarElement.BRICK, localPlayer.getResourceList().getBrick());
			getView().setElementAmount(ResourceBarElement.ORE, localPlayer.getResourceList().getOre());
			getView().setElementAmount(ResourceBarElement.SHEEP, localPlayer.getResourceList().getSheep());
			getView().setElementAmount(ResourceBarElement.WHEAT, localPlayer.getResourceList().getWheat());
			getView().setElementAmount(ResourceBarElement.WOOD, localPlayer.getResourceList().getWood());			
			
			getView().setElementAmount(ResourceBarElement.CITY, localPlayer.getCities());
			getView().setElementAmount(ResourceBarElement.SETTLEMENT, localPlayer.getSettlements());
			
			//getView().setElementAmount(ResourceBarElement.PLAY_CARD, localPlayer.getResourceList().getResourceByType("brick"));
			//getView().setElementAmount(ResourceBarElement.BUY_CARD, localPlayer.getResourceList().getResourceByType("brick"));
			
			getView().setElementAmount(ResourceBarElement.SOLDIERS, localPlayer.getSoldiers());
			
		}
		
	}

	@Override
	public IResourceBarView getView() {
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) {

		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() {
		executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
		executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {
		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
		executeElementAction(ResourceBarElement.BUY_CARD);
	}

	@Override
	public void playCard() {
		executeElementAction(ResourceBarElement.PLAY_CARD);
	}
	
	private void executeElementAction(ResourceBarElement element) {
		
		if (elementActions.containsKey(element)) {
			
			IAction action = elementActions.get(element);
			action.execute();
		}
	}

}

