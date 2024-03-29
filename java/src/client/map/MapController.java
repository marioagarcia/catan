package client.map;

import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import shared.model.GameModel;
import shared.model.map.BoardMap;
import shared.model.map.Hex;
import shared.model.map.HexInterface;
import shared.model.map.Port;
import shared.model.piece.City;
import shared.model.piece.Road;
import shared.model.piece.Settlement;
import shared.model.player.Player;
import shared.model.player.Players;
import shared.model.player.RobPlayerInfo;
import shared.model.turntracker.TurnTracker;
import shared.model.turntracker.TurntrackerInterface.Status;
import state.FirstRoundState;
import state.GameState;
import state.PlayingState;
import state.RobbingState;
import client.base.*;
import client.facade.ClientModelFacade;


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
	
	private boolean currentlyRobbing = false;
	private boolean robbedPlayer = false;
	
	private HexLocation currentRobberPosition = null;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		ClientModelFacade.getInstance(null).addObserver(new GameModelObserver());
		
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
		
		map = new BoardMap();
		tracker = new TurnTracker(Status.FIRST_ROUND, -1, -1, -1);
		setRobView(robView);
	}
	
	private class GameModelObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			GameModel latest_model = (GameModel) o;
			
			listOfPlayers = latest_model.getPlayers();
			
			if (!map.equals(latest_model.getBoardMap())){
				map = latest_model.getBoardMap();
				initFromModel(map);	
			}
			
			localPlayer = latest_model.getLocalPlayer();
			localPlayerColor = localPlayer.getColor();
		
			if (!tracker.equals(latest_model.getTurnTracker())){
				tracker = latest_model.getTurnTracker();
			
				if (tracker.isLocalPlayerTurn()){

					switch (tracker.getStatus()){
						case SECOND_ROUND:
						case FIRST_ROUND:
							currentState = new FirstRoundState(MapController.this);
							currentlyRobbing = false;
							robbedPlayer = false;
							break;
						case PLAYING:
							currentState = new PlayingState(MapController.this);
							currentlyRobbing = false;
							robbedPlayer = false;
							break;
						case ROBBING:
							if (!currentlyRobbing && !robbedPlayer){
								currentState = new RobbingState(MapController.this);
								getView().startDrop(PieceType.ROBBER, localPlayerColor, false);
								currentlyRobbing = true;
							}
							break;
						default:
							currentState = new GameState(MapController.this);
							currentlyRobbing = false;
							robbedPlayer = false;
							break;		
					}
				}
				else{
					currentState = new GameState(MapController.this);
				}
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
		
		if (roadBuilding){
			return (num_roads >= 1 && temp.canBuildRoad(edgeLoc, index, status));
		}
		else{
			return currentState.canBuildRoad(edgeLoc);
		}
		
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
		int index = localPlayer.getPlayerIndex();
		getView().placeRoad(edgeLoc, localPlayerColor);
		
		if (!roadBuilding){
			currentState.buildRoad(edgeLoc);
		}
		else{
			if (!roadBuildingOne){
				roadBuildingOne = true;
				roadBuildingLocationOne = edgeLoc;
				temp.getRoads().put(edgeLoc, new Road(index, edgeLoc));
				
			}
			else{
				roadBuildingTwo = true;
				roadBuildingLocationTwo = edgeLoc;
				temp.getRoads().put(edgeLoc, new Road(index, edgeLoc));
				
				if (roadBuildingOne && roadBuildingTwo){
					if(currentState.playRoadBuilding(roadBuildingLocationOne, roadBuildingLocationTwo)){
						roadBuildingLocationOne = null;
						roadBuildingLocationTwo = null;
						roadBuilding = false;
						roadBuildingOne = false;
						roadBuildingTwo = false;
					}
				}
			}
		}
	}

	public void placeSettlement(VertexLocation vertLoc) {
		getView().placeSettlement(vertLoc, localPlayerColor);
		currentState.buildSettlement(vertLoc);
	}

	public void placeCity(VertexLocation vertLoc) {
		currentState.buildCity(vertLoc);
		getView().placeCity(vertLoc, localPlayerColor);
	}

	public void placeRobber(HexLocation hexLoc) {
		
		map.setRobberLocation(hexLoc);
		currentRobberPosition = hexLoc;
		getView().placeRobber(hexLoc);
		
		RobPlayerInfo[] rob_list = ClientModelFacade.getInstance(null).getRobbablePlayers(hexLoc);
		
		getRobView().setPlayers(rob_list);
		
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		getView().startDrop(pieceType, localPlayerColor, true);	
	}
	
	public void cancelMove() {
		
		if (roadBuilding){
			roadBuilding = false;
		}
		
		roadBuildingOne = false;
		roadBuildingTwo = false;
		
		if (roadBuildingLocationOne != null){
			((MapView)getView()).removeRoad(roadBuildingLocationOne);
		}
		
		if (roadBuildingLocationTwo != null){
			((MapView)getView()).removeRoad(roadBuildingLocationTwo);
		}
		
		roadBuildingLocationOne = null;
		roadBuildingLocationTwo = null;

		this.temp = copy_map();
		
	}
	
	public void playSoldierCard() {
		playingSoldier = true;
		getView().startDrop(PieceType.ROBBER, CatanColor.WHITE, true);
	}
	
	public void playRoadBuildingCard() {
		//Should they be allowed to play the card at all if they don't have 2 roads? Because cancelling the second road will not play the card
		
		roadBuilding = true;
		
		this.temp = copy_map();
		
		getView().startDrop(PieceType.ROAD, localPlayerColor, true);
		getView().startDrop(PieceType.ROAD, localPlayerColor, true);		
	}
	
	private BoardMap copy_map(){
		BoardMap temp = new BoardMap();
		
		Map<HexLocation, HexInterface> temp_hexes = new HashMap<HexLocation, HexInterface>();
		Map<EdgeLocation, Road> temp_roads = new HashMap<EdgeLocation, Road>();
		
		for (Map.Entry<HexLocation, HexInterface> hexes : map.getHexes().entrySet()){
			temp_hexes.put(new HexLocation(hexes.getKey().getX(), hexes.getKey().getY()), new Hex(hexes.getValue().getLocation(), hexes.getValue().getType(), hexes.getValue().getNumber()));
		}
		
		for (Map.Entry<EdgeLocation, Road> roads : map.getRoads().entrySet()){
			temp_roads.put(new EdgeLocation(roads.getKey().getHexLoc(), roads.getKey().getDir()), new Road(roads.getValue().getPlayerIndex(), roads.getValue().getLocation()));
		}
		
		temp.setHexes(temp_hexes);
		temp.setRoads(temp_roads);
		return temp;
	}
	
	public void robPlayer(RobPlayerInfo victim) {	
		
		if (playingSoldier){
			if (victim != null){
				currentState.playSoldier(currentRobberPosition, victim.getPlayerIndex());
			}
			else{
				currentState.playSoldier(currentRobberPosition, -1);
			}
			playingSoldier = false;
			robbedPlayer = true;
		}
		else{
			currentState.robPlayer(victim, currentRobberPosition);
		}
	}
	
	public void setGameState(GameState new_state){
		this.currentState = new_state;
	}
	
}

