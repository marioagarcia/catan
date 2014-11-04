package client.resources;

import java.util.*;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.GameModel;
import client.model.map.BoardMap;
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
		
		ModelFacade.getInstance(null).addObserver(new GameModelObserver());
	}
	
	private class GameModelObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			GameModel latest_model = (GameModel) o;
			
			tracker = latest_model.getTurnTracker();
			localPlayer = latest_model.getLocalPlayer();
			int local_index = localPlayer.getPlayerIndex();
			BoardMap latest_map = latest_model.getBoardMap();
			
			getView().setElementAmount(ResourceBarElement.BRICK, localPlayer.getResourceList().getBrick());
			getView().setElementAmount(ResourceBarElement.ORE, localPlayer.getResourceList().getOre());
			getView().setElementAmount(ResourceBarElement.SHEEP, localPlayer.getResourceList().getSheep());
			getView().setElementAmount(ResourceBarElement.WHEAT, localPlayer.getResourceList().getWheat());
			getView().setElementAmount(ResourceBarElement.WOOD, localPlayer.getResourceList().getWood());			
			
			getView().setElementAmount(ResourceBarElement.CITY, localPlayer.getCities());
			getView().setElementAmount(ResourceBarElement.SETTLEMENT, localPlayer.getSettlements());
			getView().setElementAmount(ResourceBarElement.ROAD, localPlayer.getRoads());
			getView().setElementAmount(ResourceBarElement.SOLDIERS, localPlayer.getSoldiers());
			
			getView().setElementEnabled(ResourceBarElement.ROAD, false);
			getView().setElementEnabled(ResourceBarElement.SETTLEMENT, false);
			getView().setElementEnabled(ResourceBarElement.CITY, false);
			getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
			getView().setElementEnabled(ResourceBarElement.PLAY_CARD, false);
			
			if (tracker.isLocalPlayerTurn()){
				switch (tracker.getStatus()){
					case SECOND_ROUND:
						boolean hasPlacedSecondRoad = (localPlayer.hasPlacedFreeRoad() || latest_map.getNumberOfRoadsByPlayerIndex(local_index) == 2);
						boolean hasPlacedSecondSettlement = (localPlayer.hasPlacedFreeSettlement() || latest_map.getNumberOfSettlementsByPlayerIndex(local_index) == 2);
						
						getView().setElementEnabled(ResourceBarElement.ROAD, (!hasPlacedSecondRoad && hasPlacedSecondSettlement));
						getView().setElementEnabled(ResourceBarElement.SETTLEMENT, !hasPlacedSecondSettlement);
						
						getView().setElementEnabled(ResourceBarElement.CITY, false);
						getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
						getView().setElementEnabled(ResourceBarElement.PLAY_CARD, false);
						break;
					case FIRST_ROUND:
						boolean hasPlacedFirstRoad = (localPlayer.hasPlacedFreeRoad() || latest_map.getNumberOfRoadsByPlayerIndex(local_index) == 1);
						boolean hasPlacedFirstSettlement = (localPlayer.hasPlacedFreeSettlement() || latest_map.getNumberOfSettlementsByPlayerIndex(local_index) == 1);
						
						getView().setElementEnabled(ResourceBarElement.ROAD, (!hasPlacedFirstRoad && hasPlacedFirstSettlement));
						getView().setElementEnabled(ResourceBarElement.SETTLEMENT, !hasPlacedFirstSettlement);
						
						getView().setElementEnabled(ResourceBarElement.CITY, false);
						getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
						getView().setElementEnabled(ResourceBarElement.PLAY_CARD, false);
						break;
					case PLAYING:
						getView().setElementEnabled(ResourceBarElement.ROAD, localPlayer.canBuildRoad());	
						getView().setElementEnabled(ResourceBarElement.SETTLEMENT, localPlayer.canBuildSettlement());
						getView().setElementEnabled(ResourceBarElement.CITY, localPlayer.canBuildCity());
						getView().setElementEnabled(ResourceBarElement.BUY_CARD, localPlayer.canBuyDevCard());
						getView().setElementEnabled(ResourceBarElement.PLAY_CARD, !localPlayer.isPlayedDevCard());
						break;
					default:
						getView().setElementEnabled(ResourceBarElement.ROAD, false);
						getView().setElementEnabled(ResourceBarElement.SETTLEMENT, false);
						getView().setElementEnabled(ResourceBarElement.CITY, false);
						getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
						getView().setElementEnabled(ResourceBarElement.PLAY_CARD, false);
						break;
				}
			}
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

