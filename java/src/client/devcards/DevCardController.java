package client.devcards;

import java.util.Observable;
import java.util.Observer;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.model.GameModel;
import shared.model.facade.ModelFacade;
import shared.model.player.Player;
import client.base.*;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	private Player localPlayer = null;
	
	private class GameModelObserver implements Observer{

		@Override
		public void update(Observable o, Object arg) {
			GameModel new_model = (GameModel) o;
			
			localPlayer = new_model.getLocalPlayer();

			int monopoly_cards = localPlayer.getNewDevCards().getMonopoly() + localPlayer.getOldDevCards().getMonopoly();
			int monument_cards = localPlayer.getNewDevCards().getMonument() + localPlayer.getOldDevCards().getMonument();
			int road_building_cards = localPlayer.getNewDevCards().getRoadBuild() + localPlayer.getOldDevCards().getRoadBuild();
			int soldier_cards = localPlayer.getNewDevCards().getSoldier() + localPlayer.getOldDevCards().getSoldier();
			int year_of_plenty_cards = localPlayer.getNewDevCards().getYearOfPlenty() + localPlayer.getOldDevCards().getYearOfPlenty();
					
			getPlayCardView().setCardAmount(DevCardType.MONOPOLY, monopoly_cards);
			getPlayCardView().setCardAmount(DevCardType.MONUMENT, monument_cards);
			getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, road_building_cards);
			getPlayCardView().setCardAmount(DevCardType.SOLDIER, soldier_cards);
			getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, year_of_plenty_cards);
			
			getPlayCardView().setCardEnabled(DevCardType.MONUMENT, localPlayer.getOldDevCards().getMonument() > 0);
			getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, localPlayer.getOldDevCards().getMonopoly() > 0);
			getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, localPlayer.getOldDevCards().getRoadBuild() > 0);
			getPlayCardView().setCardEnabled(DevCardType.SOLDIER, localPlayer.getOldDevCards().getSoldier() > 0);
			getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, localPlayer.getOldDevCards().getYearOfPlenty() > 0);
		}
		
	}
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction) {

		super(view);
		
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
		
		ModelFacade.getInstance(null).addObserver(new GameModelObserver());
	}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		
		getBuyCardView().showModal();
	}

	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		
		getBuyCardView().closeModal();
		if(!ModelFacade.getInstance(null).canBuyDevCard()){
			return;
		}
		
		ModelFacade.getInstance(null).buyDevCard();
	}

	@Override
	public void startPlayCard() {
		
		getPlayCardView().showModal();
	}

	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		if(!ModelFacade.getInstance(null).canPlayMonopoly()){
			return;
		}
		ModelFacade.getInstance(null).playMonopoly(resource);
	}

	@Override
	public void playMonumentCard() {
		if(!ModelFacade.getInstance(null).canPlayMonument()){
			return;
		}
		ModelFacade.getInstance(null).playMonument();
	}

	@Override
	public void playRoadBuildCard() {
		
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		if(!ModelFacade.getInstance(null).canPlayYearOfPlenty(resource1, resource2)){
			return;
		}
		ModelFacade.getInstance(null).playYearOfPlenty(resource1, resource2);
	}

}

