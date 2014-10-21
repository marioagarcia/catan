package client.devcards;

import java.util.Observable;
import java.util.Observer;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import client.base.*;
import client.communication.facade.ModelFacade;
import client.communication.server.ServerMoxy;
import client.model.card.DevCardBank;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	
	private class DevCardBankObserver implements Observer{

		@Override
		public void update(Observable o, Object arg) {
			DevCardBank bank = (DevCardBank) o;

			for(DevCardType type : DevCardType.values()){
				getPlayCardView().setCardAmount(type, bank.numberOfType(type));
			}
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
		
		ModelFacade.getInstance(null).getManager().getDevCardBank().addObserver(new DevCardBankObserver());
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

