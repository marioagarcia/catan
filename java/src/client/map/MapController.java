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
import client.model.GameModel;
import client.model.map.BoardMap;
import client.model.map.HexInterface;
import client.model.map.Port;
import client.model.piece.City;
import client.model.piece.Road;
import client.model.piece.Settlement;
import client.model.player.Player;
import client.model.player.Players;
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
	private ArrayList<HexLocation> waterHexes;
	
	private Player localPlayer = null;
	private Players listOfPlayers = null;
	
	private boolean playingSoldier = false;
	
	private boolean roadBuilding = false;
	private boolean roadBuildingOne = false;
	private boolean roadBuildingTwo = false;
	
	private EdgeLocation roadBuildingLocationOne = null;
	private EdgeLocation roadBuildingLocationTwo = null;
	
	private BoardMap temp = new BoardMap();
	private Map<EdgeLocation, Road> temp_roads = new HashMap<EdgeLocation, Road>();
	
	private boolean currentlyRobbing = false;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);

		ModelFacade.getInstance(null).addObserver(new GameModelObserver());
		
		waterHexes = new ArrayList<HexLocation>();
		waterHexes.add(new HexLocation(-3, 1));
		waterHexes.add(new HexLocation(-3, 0));
		waterHexes.add(new HexLocation(-2, -1));
		waterHexes.add(new HexLocation(-1, -2));
		waterHexes.add(new HexLocation(0, -3));
		waterHexes.add(new HexLocation(1, -3));
		waterHexes.add(new HexLocation(2, -3));
		waterHexes.add(new HexLocation(3, -3));
		waterHexes.add(new HexLocation(3, -2));
		waterHexes.add(new HexLocation(3, -1));
		waterHexes.add(new HexLocation(3, 0));
		waterHexes.add(new HexLocation(2, 1));
		waterHexes.add(new HexLocation(1, 2));
		waterHexes.add(new HexLocation(0, 3));
		waterHexes.add(new HexLocation(-1, 3));
		waterHexes.add(new HexLocation(-2, 3));
		waterHexes.add(new HexLocation(-3, 3));
		waterHexes.add(new HexLocation(-3, 2));
		
		
		setRobView(robView);
	}
	
	private class GameModelObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			GameModel latest_model = (GameModel) o;
			
			tracker = latest_model.getTurnTracker();
			listOfPlayers = latest_model.getPlayers();
			
			map = latest_model.getBoardMap();
			initFromModel(map);
			
			localPlayer = latest_model.getLocalPlayer();
			localPlayerColor = localPlayer.getColor();
			
			if (tracker.isLocalPlayerTurn()){
				
				Status state = tracker.getStatus();
				
				switch (state){
					case SECOND_ROUND:
					case FIRST_ROUND:
						currentState = new FirstRoundState(MapController.this);
						currentlyRobbing = false;
						break;
					case PLAYING:
						currentState = new PlayingState(MapController.this);
						currentlyRobbing = false;
						break;
					case ROBBING:
						if (!currentlyRobbing){
							currentState = new RobbingState(MapController.this);
							getView().startDrop(PieceType.ROBBER, localPlayerColor, false);
							currentlyRobbing = true;
						}
						break;
					default:
						currentState = new GameState(MapController.this);
						currentlyRobbing = false;
						break;		
				}
			}
			else{
				currentState = new GameState(MapController.this);
			}
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
		
		//Draw hexes
		for (Map.Entry<HexLocation, HexInterface> hex : m.getHexes().entrySet()){
			
			getView().addHex(hex.getKey(), hex.getValue().getType());
			
			int tile_number =  hex.getValue().getNumber();
			
			if (tile_number != -1){
				getView().addNumber(hex.getKey(), hex.getValue().getNumber());
			}
			
		}
		
		
		//Draw water hexes
		for (HexLocation location : waterHexes){
			
			getView().addHex(location, HexType.WATER);	
		}
		
		//Draw roads
		for (Map.Entry<EdgeLocation, Road> road : m.getRoads().entrySet()){
			
			CatanColor color = listOfPlayers.getPlayer(road.getValue().getPlayerIndex()).getColor();
			getView().placeRoad(road.getKey(), color);
		}
		
		//Draw cities
		for (Map.Entry<VertexLocation, City> city : m.getCities().entrySet()){
			
			CatanColor color = listOfPlayers.getPlayer(city.getValue().getPlayerIndex()).getColor();
			getView().placeCity(city.getKey(), color);
		}
		
		//Draw settlements
		for (Map.Entry<VertexLocation, Settlement> settlement : m.getSettlements().entrySet()){
			
			CatanColor color = listOfPlayers.getPlayer(settlement.getValue().getPlayerIndex()).getColor();
			getView().placeSettlement(settlement.getKey(), color);
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
		
		int num_roads = localPlayer.getRoads();
		int index = localPlayer.getPlayerIndex();
		Status status = tracker.getStatus();
		
		return (currentState.canBuildRoad(edgeLoc) || (roadBuilding && num_roads >= 1 && temp.canBuildRoad(edgeLoc, index, status)));
		
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		
		return currentState.canBuildSettlement(vertLoc);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		
		return currentState.canBuildCity(vertLoc);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		
		return (!map.getRobberLocation().equals(hexLoc) && !waterHexes.contains(hexLoc));
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		System.out.println("Map Controller placeRoad");
		int index = localPlayer.getPlayerIndex();
		getView().placeRoad(edgeLoc, localPlayerColor);
		
		if (!roadBuilding){
			currentState.buildRoad(edgeLoc);
		}
		else{
			if (!roadBuildingOne){
				roadBuildingOne = true;
				roadBuildingLocationOne = edgeLoc;
				
				temp_roads.put(edgeLoc, new Road(index, edgeLoc));
				
				temp.setRoads(temp_roads);
			}
			else{
				roadBuildingTwo = true;
				roadBuildingLocationTwo = edgeLoc;
				temp_roads.put(edgeLoc, new Road(index, edgeLoc));
				
				if (roadBuildingOne && roadBuildingTwo){
					if(currentState.playRoadBuilding(roadBuildingLocationOne, roadBuildingLocationTwo)){
						roadBuildingLocationOne = null;
						roadBuildingLocationTwo = null;
						roadBuilding = false;
						temp.setRoads(map.getRoads());
					}
				}
			}
		}
	}

	public void placeSettlement(VertexLocation vertLoc) {
		System.out.println("Map Controller placeSettlement");
		getView().placeSettlement(vertLoc, localPlayerColor);
		currentState.buildSettlement(vertLoc);
	}

	public void placeCity(VertexLocation vertLoc) {
		System.out.println("Map Controller placeCity");
		currentState.buildCity(vertLoc);
		getView().placeCity(vertLoc, localPlayerColor);
	}

	public void placeRobber(HexLocation hexLoc) {
		System.out.println("Map Controller placeRobber");
		
		map.setRobberLocation(hexLoc);
		getView().placeRobber(hexLoc);
		getRobView().setPlayers(ModelFacade.getInstance(null).getRobbablePlayers(hexLoc));
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
		playingSoldier = true;
		getView().startDrop(PieceType.ROBBER, CatanColor.WHITE, true);
	}
	
	public void playRoadBuildingCard() {

		System.out.println("Map Controller playRoadBuildingCard");
		
		roadBuilding = true;
		
		temp.getHexes().clear();
		temp.setHexes(map.getHexes());
		
		temp.getRoads().clear();
		temp_roads.clear();
		
		temp_roads.putAll(map.getRoads());
		temp.setRoads(temp_roads);
		
		getView().startDrop(PieceType.ROAD, localPlayerColor, true);
		getView().startDrop(PieceType.ROAD, localPlayerColor, true);		
	}
	
	public void robPlayer(RobPlayerInfo victim) {	
		System.out.println("Map Controller robPlayer " + victim);
		
		if (playingSoldier){
			currentState.playSoldier(map.getRobberLocation(), victim.getPlayerIndex());
			playingSoldier = false;
		}
		else{
			currentState.robPlayer(victim, map.getRobberLocation());
		}
	}
	
	public void setGameState(GameState new_state){
		this.currentState = new_state;
	}
	
}

