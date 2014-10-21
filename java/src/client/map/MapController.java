package client.map;

import java.util.*;
import shared.definitions.*;
import shared.locations.*;
import state.FirstRoundState;
import state.GameState;
import state.PlayingState;
import state.RobbingState;
import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.map.BoardMap;
import client.model.map.HexInterface;
import client.model.map.Port;
import client.model.piece.City;
import client.model.piece.Road;
import client.model.piece.Settlement;
import client.model.player.RobPlayerInfo;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController {
	
	private IRobView robView;
	private GameState currentState = new GameState(this);
	private TurnTracker tracker = null;
	private BoardMap map = null;
	private CatanColor localPlayerColor =  null;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);

		tracker = ModelFacade.getInstance(null).getManager().getTurnTracker();
		tracker.addObserver(new TrackerObserver());
		
		map = ModelFacade.getInstance(null).getManager().getBoardMap();
		MapObserver m = new MapObserver();
		map.addObserver(m);
		
		setRobView(robView);
	}
	
	private class TrackerObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			tracker = (TurnTracker)o;
			
			if (ModelFacade.getInstance(null).getManager().isLocalPlayersTurn()){
				
				Status state = tracker.getStatus();
				
				switch (state){
					case FIRST_ROUND:
						currentState = new FirstRoundState(MapController.this);
						break;
					case PLAYING:
						currentState = new PlayingState(MapController.this);
						break;
					case ROBBING:
						currentState = new RobbingState(MapController.this);
						break;
					default:
						currentState = new GameState(MapController.this);
						break;		
				}
			}
			else{
				currentState = new GameState(MapController.this);
			}
			
			localPlayerColor = ModelFacade.getInstance(null).getLocalPlayer().getColor();
		}
		
	}
	
	private class MapObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			map = (BoardMap)o;
			
			initFromModel(map);
		}
	}
	
	public IMapView getView() {
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	
	protected void initFromModel(BoardMap m) {
		
		//<temp>
		
		System.out.println("Drawing Map");
		
		//Draw hexes
		for (Map.Entry<HexLocation, HexInterface> hex : m.getHexes().entrySet()){
			
			getView().addHex(hex.getKey(), hex.getValue().getType());
		}
		
		//Draw roads
		for (Map.Entry<EdgeLocation, Road> road : m.getRoads().entrySet()){

			CatanColor color = ModelFacade.getInstance(null).getManager().getAllPlayers().getPlayer(road.getValue().getPlayerIndex()).getColor();
			getView().placeRoad(road.getKey(), color);
		}
		
		//Draw cities
		for (Map.Entry<VertexLocation, City> city : m.getCities().entrySet()){
			
			CatanColor color = ModelFacade.getInstance(null).getManager().getAllPlayers().getPlayer(city.getValue().getPlayerIndex()).getColor();
			getView().placeCity(city.getKey(), color);
		}
		
		//Draw settlements
		for (Map.Entry<VertexLocation, Settlement> settlement : m.getSettlements().entrySet()){
			
			CatanColor color = ModelFacade.getInstance(null).getManager().getAllPlayers().getPlayer(settlement.getValue().getPlayerIndex()).getColor();
			getView().placeCity(settlement.getKey(), color);
		}
		
		//Draw ports
		for (Map.Entry<EdgeLocation, Port> port : m.getPorts().entrySet()){

			if (port.getValue().getResource() != null){
				getView().addPort(port.getKey(), PortType.valueOf(port.getValue().getResource().toString()));
			}
			else
			{
				getView().addPort(port.getKey(), PortType.THREE);
			}
		}
		
		//Place robber
		getView().placeRobber(m.getRobberLocation());
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		
		return currentState.canBuildRoad(edgeLoc);
		
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		
		return currentState.canBuildSettlement(vertLoc);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		
		return currentState.canBuildCity(vertLoc);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		
		return currentState.canPlaceRobber(hexLoc);
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		System.out.println("Map Controller placeRoad");
		currentState.buildRoad(edgeLoc);
		getView().placeRoad(edgeLoc, localPlayerColor);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		System.out.println("Map Controller placeSettlement");
		currentState.buildSettlement(vertLoc);
		getView().placeSettlement(vertLoc, localPlayerColor);
	}

	public void placeCity(VertexLocation vertLoc) {
		System.out.println("Map Controller placeCity");
		currentState.buildCity(vertLoc);
		getView().placeCity(vertLoc, localPlayerColor);
	}

	public void placeRobber(HexLocation hexLoc) {
		System.out.println("Map Controller placeRobber");
		getView().placeRobber(hexLoc);
		
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		System.out.println("Map Controller startMove");
		getView().startDrop(pieceType, localPlayerColor, true);
	}
	
	public void cancelMove() {
		System.out.println("Map Controller cancelMove");
	}
	
	public void playSoldierCard() {	
		System.out.println("Map Controller playSoldierCard");
	}
	
	public void playRoadBuildingCard() {	
		System.out.println("Map Controller playRoadBuildingCard");
	}
	
	public void robPlayer(RobPlayerInfo victim) {	
		System.out.println("Map Controller robPlayer");
	}
	
	public void setGameState(GameState new_state){
		this.currentState = new_state;
	}
	
}

