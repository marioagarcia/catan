package client.maritime;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import shared.definitions.*;
import shared.locations.EdgeLocation;
import shared.model.GameModel;
import shared.model.card.MaritimeTrade;
import shared.model.map.BoardMap;
import shared.model.map.Port;
import shared.model.player.Player;
import shared.model.turntracker.TurnTracker;
import shared.model.turntracker.TurntrackerInterface.Status;
import client.base.*;
import client.facade.ClientModelFacade;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

	private IMaritimeTradeOverlay tradeOverlay;
	private MaritimeTrade trade;
	private final String stateMessageSelectGive = "Please select resources to give up in trade";
	private final String stateMessageSelectGet = "Please select resources to receive in trade";
	private final String stateMessageTrade = "Trade!!";
	BoardMap boardMap;
	Player localPlayer;
	
	
	private class MaritimeTradeObserver implements Observer
	{

		@Override
		public void update(Observable o, Object arg) {
			GameModel game_model = (GameModel)o;
			
			boardMap = game_model.getBoardMap();
			localPlayer = game_model.getLocalPlayer();
			
			TurnTracker tt = game_model.getTurnTracker();
			if(tt.getStatus() == Status.PLAYING && tt.getCurrentTurn() == game_model.getLocalPlayer().getPlayerIndex()) {
				getTradeView().enableMaritimeTrade(true);
			} else {
				getTradeView().enableMaritimeTrade(false);
			}
		}
	}
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		
		super(tradeView);

		setTradeOverlay(tradeOverlay);
		this.trade = new MaritimeTrade();
		getTradeView().enableMaritimeTrade(false);
		ClientModelFacade.getInstance(null).addObserver(new MaritimeTradeObserver());
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	@Override
	public void startTrade() {
		getTradeOverlay().reset();
		
		getTradeOverlay().setTradeEnabled(false);
		getTradeOverlay().setStateMessage(this.stateMessageSelectGive);
		getTradeOverlay().showGiveOptions(getTradableTypes());
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {
		Port port = this.determineTradeLocation(this.trade.getResourceOut());
		EdgeLocation location = null;
		if(port != null){
			location = port.getLocation();
		}
		ClientModelFacade.getInstance(null).getManager().maritimeTrade(location, trade);
		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		this.trade.setResourceOut(resource);
		
		Port port = this.determineTradeLocation(this.trade.getResourceIn());
		this.getTradeOverlay().setTradeEnabled(ClientModelFacade.getInstance(null).canMaritimeTrade((port == null) ? null : port.getLocation(), trade));

		getTradeOverlay().selectGetOption(resource, 1);
		getTradeOverlay().setStateMessage(this.stateMessageTrade);
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		this.trade.setResourceIn(resource);
		this.trade.setRatio(this.determineRatio(resource));
		getTradeOverlay().selectGiveOption(resource, this.determineRatio(resource));
		getTradeOverlay().showGetOptions(this.getAllTypesExcept(resource));
		getTradeOverlay().setStateMessage(this.stateMessageSelectGet);
	}

	@Override
	public void unsetGetValue() {
		getTradeOverlay().showGetOptions(this.getAllTypesExcept(this.trade.getResourceOut()));
		getTradeOverlay().setStateMessage(this.stateMessageSelectGet);
	}

	@Override
	public void unsetGiveValue() {
		getTradeOverlay().showGiveOptions(this.getTradableTypes());
		getTradeOverlay().setStateMessage(this.stateMessageSelectGive);
	}
	
	
	private int determineRatio(ResourceType type){
		int ratio = 4;
		for(Port port : boardMap.getPortsByPlayer(ClientModelFacade.getInstance(null).getLocalPlayer())){
			if(port.getResource() == PortType.THREE && ratio > 3){
				ratio = 3;
			}
			else if(port.getResource().toString().equals(type.toString())){
				return 2;
			}
		}
		System.out.println("resource " + type + " ratio " + ratio);
		return ratio;
	}
	
	private Port determineTradeLocation(ResourceType type){
		Port best_port = null;
		for( Port port : boardMap.getPortsByPlayer(ClientModelFacade.getInstance(null).getLocalPlayer())){
			if(port.getResource().toString().equals(type.toString())){
				return port;
			}
			else if(port.getResource() == PortType.THREE){
				best_port = port;
			}
		}
		return best_port;
	}

	private ResourceType[] getTradableTypes(){
		Set<ResourceType> enabled_types = new HashSet<ResourceType>();
		for(ResourceType type : ResourceType.values()){
			if(this.determineRatio(type) <= localPlayer.getResourceList().getResourceByType(type)){
				enabled_types.add(type);
			}
		}
		
		return enabled_types.toArray(new ResourceType[1]);
	}
	
	private ResourceType[] getAllTypesExcept(ResourceType to_give){
		Set<ResourceType> types = new HashSet<ResourceType>();
		
		for(ResourceType type : ResourceType.values()){
			if(!type.equals(to_give)){
				types.add(type);
			}
		}
		return types.toArray(new ResourceType[1]);
	}
}

