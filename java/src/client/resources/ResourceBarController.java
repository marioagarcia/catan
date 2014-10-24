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
			boolean settlement_enabled = false;
			boolean city_enabled = false;
			boolean road_enabled = false;
			boolean play_dev_card_enabled = false;
			boolean buy_dev_card_enabled = false;
			
			if (ModelFacade.getInstance(null).getManager().isLocalPlayersTurn()){
				
				//This needs to be more robust. Player can only play one dev card per turn. 
				//Should probably disable settlements in first round when one has been placed

				switch (tracker.getStatus()){
					case FIRST_ROUND:
						settlement_enabled = true;
						road_enabled = true;
						break;
					case PLAYING:
						play_dev_card_enabled = true;
						buy_dev_card_enabled = true;
						settlement_enabled = true;
						city_enabled = true;
						road_enabled = true;
						break;
					default:
						break;
				}
			}
			
			getView().setElementEnabled(ResourceBarElement.ROAD, road_enabled);
			getView().setElementEnabled(ResourceBarElement.SETTLEMENT, settlement_enabled);
			getView().setElementEnabled(ResourceBarElement.CITY, city_enabled);
			getView().setElementEnabled(ResourceBarElement.BUY_CARD, buy_dev_card_enabled);
			getView().setElementEnabled(ResourceBarElement.PLAY_CARD, play_dev_card_enabled);
		}	
	}
	
	private class ResourceObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			localPlayer = (Player)o;
			
			getView().setElementAmount(ResourceBarElement.BRICK, localPlayer.getResourceList().getBrick());
			getView().setElementAmount(ResourceBarElement.ORE, localPlayer.getResourceList().getOre());
			getView().setElementAmount(ResourceBarElement.SHEEP, localPlayer.getResourceList().getSheep());
			getView().setElementAmount(ResourceBarElement.WHEAT, localPlayer.getResourceList().getWheat());
			getView().setElementAmount(ResourceBarElement.WOOD, localPlayer.getResourceList().getWood());			
			
			getView().setElementAmount(ResourceBarElement.CITY, localPlayer.getCities());
			getView().setElementAmount(ResourceBarElement.SETTLEMENT, localPlayer.getSettlements());
			getView().setElementAmount(ResourceBarElement.ROAD, localPlayer.getRoads());
			
			//getView().setElementAmount(ResourceBarElement.PLAY_CARD, localPlayer.getResourceList().getResourceByType("brick")); Not sure what these are supposed to be
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

