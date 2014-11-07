package client.domestic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.*;
import shared.model.GameModel;
import shared.model.card.DomesticTrade;
import shared.model.card.ResourceList;
import shared.model.player.Player;
import shared.model.player.PlayerInfo;
import shared.model.player.Players;
import shared.model.turntracker.TurnTracker;
import shared.model.turntracker.TurntrackerInterface.Status;
import client.base.*;
import client.facade.ClientModelFacade;
import client.misc.*;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private DomesticTrade trade;
	private Map<ResourceType, Boolean> send;
	private GameModel gameModel;
	
	private class DomesticTradeControllerObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			gameModel = (GameModel)o;
			
			trade.setSender(gameModel.getLocalPlayer().getPlayerIndex());
			
			TurnTracker tt = gameModel.getTurnTracker();
			if(tt.getStatus().equals(Status.PLAYING) && tt.isLocalPlayerTurn()){
				getTradeView().enableDomesticTrade(true);
			} else{
				getTradeView().enableDomesticTrade(false);
			}
			
			getAcceptOverlay().reset();
			getAcceptOverlay().setAcceptEnabled(true);
			DomesticTrade trade_offer = gameModel.getDomesticTrade();

			if (trade_offer == null && getWaitOverlay().isModalShowing()){
				getWaitOverlay().closeModal();
				return;
			}
			
			if(trade_offer != null){
				
				if(trade_offer.getReceiver() == -1){
					return;
				}
				
				if(trade_offer != null && trade_offer.getReceiver() == gameModel.getLocalPlayer().getPlayerIndex()){
					for(ResourceType resource : ResourceType.values()){
						if(trade_offer.getResourceList().getResourceByType(resource) > 0){
							getAcceptOverlay().addGetResource(resource, trade_offer.getResourceList().getResourceByType(resource));
							}
						else{
							getAcceptOverlay().addGiveResource(resource, trade_offer.getResourceList().getResourceByType(resource) * -1);
							if(trade_offer.getResourceList().getResourceByType(resource) * -1 > gameModel.getLocalPlayer().getResourceList().getResourceByType(resource)){
								getAcceptOverlay().setAcceptEnabled(false);
							}
						}
					}
					getAcceptOverlay().setPlayerName(gameModel.getPlayers().getPlayer(trade_offer.getSender()).getName());
					getAcceptOverlay().showModal();
				}
			
				
				Players players = gameModel.getPlayers();
				
				ArrayList<PlayerInfo> player_info = new ArrayList<PlayerInfo>();
				
				for(Player player : players.getPlayerList()){
					if(player.getPlayerIndex() != gameModel.getLocalPlayer().getPlayerIndex());
				}
				
				
				getTradeOverlay().setPlayers(player_info.toArray(new PlayerInfo[0]));
				getTradeOverlay().reset();
			}
		}
		
		
	}

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		this.trade = new DomesticTrade();
		this.trade.setResourceList(new ResourceList(0,0,0,0,0));
//		this.trade.setSender(ClientModelFacade.getInstance(null).getManager().get);
		this.send = new HashMap<ResourceType, Boolean>();
		
		for(ResourceType type : ResourceType.values()){
			send.put(type, false);
		}
		
		this.getTradeView().enableDomesticTrade(false);
		this.getTradeOverlay().setTradeEnabled(false);
		ClientModelFacade.getInstance(null).getManager().addObserver(new DomesticTradeControllerObserver());
		
		
	}
	
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {	
		ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();

		for(Player player : gameModel.getPlayers().getPlayerList()){
			if(player.getPlayerIndex() != gameModel.getLocalPlayer().getPlayerIndex()){
				PlayerInfo new_info = new PlayerInfo(player.getId(), player.getName(), player.getPlayerIndex(), player.getColor());
				players.add(new_info);
			}
		}
		
		getTradeOverlay().setPlayers(players.toArray(new PlayerInfo[1]));
		
		for(ResourceType resource : ResourceType.values()){
			enableResourceSelectionButtons(resource, true);
		}
		getTradeOverlay().reset();
		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		if(this.send.get(resource)){
			ResourceList list = this.trade.getResourceList();
			list.setResourceByType(resource, list.getResourceByType(resource) - 1);
		} else {
			this.trade.getResourceList().setResourceByType(resource,  this.trade.getResourceList().getResourceByType(resource) + 1);
		}
		this.enableResourceSelectionButtons(resource, false);
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		if(this.send.get(resource)){
			ResourceList list = this.trade.getResourceList();
			list.setResourceByType(resource, list.getResourceByType(resource) + 1);
		} else {
			this.trade.getResourceList().setResourceByType(resource,  this.trade.getResourceList().getResourceByType(resource) - 1);
		}
		
		this.enableResourceSelectionButtons(resource, this.send.get(resource));
	}

	@Override
	public void sendTradeOffer() {
		ClientModelFacade.getInstance(null).offerTrade(trade, this.trade.getReceiver());
		getTradeOverlay().closeModal();
		getWaitOverlay().showModal();
		resetTradeObject();
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		this.trade.setReceiver(playerIndex);
		getTradeOverlay().setTradeEnabled(this.trade.getReceiver() != -1 && this.trade.getSender() != -1 && ClientModelFacade.getInstance(null).canOfferTrade(this.trade));
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		this.send.put(resource, false);
		this.trade.getResourceList().setResourceByType(resource, 0);
		this.getTradeOverlay().setResourceAmount(resource, "0");
		this.trade.getResourceList().setResourceByType(resource, Math.abs(this.trade.getResourceList().getResourceByType(resource)) * -1);
		this.enableResourceSelectionButtons(resource, this.send.get(resource));
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		this.send.put(resource, true);
		this.trade.getResourceList().setResourceByType(resource, 0);
		this.getTradeOverlay().setResourceAmount(resource, "0");
		this.trade.getResourceList().setResourceByType(resource, Math.abs(this.trade.getResourceList().getResourceByType(resource)));
		this.enableResourceSelectionButtons(resource, this.send.get(resource));
	}

	@Override
	public void unsetResource(ResourceType resource) {
		this.send.put(resource,false);
		this.trade.getResourceList().setResourceByType(resource, 0);
		this.getTradeOverlay().setResourceAmount(resource, "0");
		this.enableResourceSelectionButtons(resource, this.send.get(resource));
	}

	@Override
	public void cancelTrade() {
		getTradeOverlay().closeModal();
		resetTradeObject();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		getAcceptOverlay().reset();
		getAcceptOverlay().closeModal();
		ClientModelFacade.getInstance(null).acceptTrade(gameModel.getDomesticTrade(), willAccept);
	}
	
	private void resetTradeObject(){
		this.trade.setResourceList(new ResourceList(0,0,0,0,0));
	}
	
	private void enableResourceSelectionButtons(ResourceType resource, boolean sending){
		boolean can_go_down = Math.abs(this.trade.getResourceList().getResourceByType(resource)) > 0;
		boolean can_go_up = !sending || this.trade.getResourceList().getResourceByType(resource) < gameModel.getLocalPlayer().getResourceList().getResourceByType(resource);
	
		getTradeOverlay().setResourceAmountChangeEnabled(resource, can_go_up, can_go_down);
		//getTradeOverlay().setTradeEnabled(this.trade.getReceiver() != -1 && this.trade.getSender() != -1 && ClientModelFacade.getInstance(null).canOfferTrade(this.trade));
	}

}