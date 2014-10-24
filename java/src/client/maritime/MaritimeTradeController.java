package client.maritime;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import shared.definitions.*;
import shared.locations.VertexLocation;
import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.card.MaritimeTrade;
import client.model.map.Port;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

	private IMaritimeTradeOverlay tradeOverlay;
	private MaritimeTrade trade;
	private VertexLocation location;
	private final String stateMessageSelectGive = "Please select resources to give up in trade";
	private final String stateMessageSelectGet = "Please select resources to receive in trade";
	private final String stateMessageTrade = "Trade!!";
	
	private class MaritimeTradeObserver implements Observer
	{

		@Override
		public void update(Observable o, Object arg) {
			TurnTracker tt = (TurnTracker)o;
			if(tt.getStatus() == Status.PLAYING) {
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
		ModelFacade.getInstance(null).getManager().getTurnTracker().addObserver(new MaritimeTradeObserver());
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
		ModelFacade.getInstance(null).getManager().maritimeTrade(location, trade);
		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		this.trade.setResourceOut(resource);
		this.trade.setRatio(this.determineRatio(resource));
		if(ModelFacade.getInstance(null).getManager().canMaritimeTrade(location, trade)){
			this.getTradeOverlay().setTradeEnabled(true);
		}
		getTradeOverlay().selectGetOption(resource, 1);
		getTradeOverlay().setStateMessage(this.stateMessageTrade);
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		this.trade.setResourceIn(resource);
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
		for(Port port : ModelFacade.getInstance(null).getManager().getBoardMap().getPortsByPlayer(ModelFacade.getInstance(null).getLocalPlayer())){
			if((port.getResource() == PortType.THREE || port.getResource().toString().equals(type.toString())) && port.getRatio() < ratio){
				ratio = port.getRatio();
			}
		}
		return ratio;
	}

	private ResourceType[] getTradableTypes(){
		Set<ResourceType> enabled_types = new HashSet<ResourceType>();
		for(ResourceType type : ResourceType.values()){
			if(this.determineRatio(type) <= ModelFacade.getInstance(null).getManager().getLocalPlayer().getResourceList().getResourceByType(type)){
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

