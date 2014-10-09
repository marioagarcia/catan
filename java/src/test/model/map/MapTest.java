package test.model.map;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.serialization.ModelSerializer;
import client.communication.server.ServerProxy;
import client.manager.GameData;
import client.manager.GameManager;
import client.model.GameInfo;
import client.model.card.ResourceList;
import client.model.map.BoardMap;
import client.model.player.Player;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;

public class MapTest {
	
	ModelSerializer ms;
	
	@Before
	public void setUp(){
		
		//manager = new GameManager(new ServerProxy("8081", "locolhost"));
		//System.out.println(manager.getServerProxy());
	}
	
	public GameData getGameData()
	{
		ms = new ModelSerializer();
		
		File file = new File("C:\\Users\\Casey\\Documents\\GitHub\\Catan\\java\\src\\test\\JSON\\getGameModel.txt");
		
		String content = "";
		try {
			content = new Scanner(file).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return ms.deserializeGameModel(content);
	}
		
	@Test
	public void testCanBuildRoad() {
		
		GameManager gameManager = new GameManager(null);
		
		GameData game = getGameData();
		BoardMap map = game.getBoardMap();
		TurnTracker tt = game.getTurnTracker();
		
		Player player = new Player();
		player = game.getPlayerList().get(0);
		
		for(int i = 0; i < game.getPlayerList().size(); i++){
			//System.out.println("Player " + i + ":\n" + player.getResourceList().toString());
		}
		
		int playerIndex = tt.getCurrentTurn();
		//Player Index -- RoadLocation:  0 -- EdgeLocation [hexLoc=HexLocation [x=2, y=0], dir=SouthWest]
		
		// AssertTrue when the road location is open, is connected to another road,
		// it's not on water, the player has 1 wood, brick, and road, it is the player's
		// turn, the game status is 'Playing'
		EdgeLocation location = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.South);
		player.setResourceList(new ResourceList(1, 0, 0, 0, 1));
		tt.setStatus(Status.PLAYING);
		
		//assertTrue(gameManager.canBuildRoad(location));
		assertTrue(map.canBuildRoad(location, playerIndex) && player.canBuildRoad());
		
		// AssertFalse when the road location is occupied
		EdgeLocation tempLocation = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.SouthWest);
		assertFalse(map.canBuildRoad(tempLocation, playerIndex) && player.canBuildRoad());
		
		// AssertFalse when the road is not connected to another road
		tempLocation = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.SouthEast);
		assertFalse(map.canBuildRoad(tempLocation, playerIndex));
		
		// AssertFalse when the road is on water
		tempLocation = new EdgeLocation(new HexLocation(3, 0), EdgeDirection.South);
		assertFalse(map.canBuildRoad(tempLocation, playerIndex));
		
		// AssertFalse when the player does not have 1 wood
		player.setResourceList(new ResourceList(1, 1, 1, 1, 0));
		assertFalse(map.canBuildRoad(location, playerIndex) && player.canBuildRoad());
		
		// AssertFalse when the player does not have 1 brick
		player.setResourceList(new ResourceList(0, 1, 1, 1, 1));
		assertFalse(map.canBuildRoad(location, playerIndex) && player.canBuildRoad()); 
		
		// AssertFalse if it isn't the player's turn
		tt.setCurrentTurn(1);
		player.setResourceList(new ResourceList(1, 0, 0, 0, 1));
		//assertFalse(map.canBuildRoad(location, playerIndex));
		
		// AssertFalse if the game status isn't 'Playing'
		tt.setCurrentTurn(0);
		tt.setStatus(Status.DISCARDING);
		//assertFalse(map.canBuildRoad(location, playerIndex));
	}
	
	@Test
	public void testCanBuildSettlement() {
		
		GameManager gameManager = new GameManager(null);
		
		GameData game = getGameData();
		BoardMap map = game.getBoardMap();
		TurnTracker tt = game.getTurnTracker();
		
		Player player = new Player();
		player = game.getPlayerList().get(0);
		
		//Player Index -- RoadLocation:  0 -- EdgeLocation [hexLoc=HexLocation [x=2, y=0], dir=SouthWest]
		
		// AssertTrue when the settlement location is open, not on water, connected to a road, 
		// the player has 1 wood, brick, wheat, sheep, settlement, it is the player's turn,
		// the game status is 'Playing'
		int playerIndex = 3;//tt.getCurrentTurn();System.out.println("Player Index: " + playerIndex + "\n");
		VertexLocation location = new VertexLocation(new HexLocation(-1, 1), VertexDirection.West);
		assertTrue(map.canBuildSettlement(location, playerIndex, false));
		
		//@TODO
		// AssertFalse when the settlement location is next to another settlement
		
		//@TODO
		// AssertFalse when the settlement location is occupied
		
		//@TODO 
		// AssertFalse when the settlement location is on water
		
		//@TODO
		// AssertFalse when the settlement location is not connected to one of the player's roads
		
		//@TODO
		// AssertFalse when the player doesn't have 1 wood, brick, wheat, sheep, and settlement
		
		//@TODO
		// AssertFalse if it isn't the player's turn
		
		//@TODO
		// AssertFalse if the game status isn't 'Playing'
		
	}
	
	@Test
	public void testCanBuildCity() {
		
		//@TODO
		// AssertTrue when the city location is currently occupied by one of the player's settlements,
		// the player has 2 wheat, 3 ore, 1 city, it is the player's turn, game status is 'Playing
		
		//@TODO
		// AssertFalse when the city location is not currently occupied by one of the player's
		// settlements
		
		//@TODO
		// AssertFalse when the player does not have 2 wheat
		
		//@TODO
		// AssertFalse when the player does not have 3 ore
		
		//@TODO
		// AssertFalse when the player does not have a city
		
		//@TODO
		// AssertFalse if it isn't the player's turn
		
		//@TODO
		// AssertFalse if the game status isn't 'Playing'
		
	}
	
	@Test
	public void testCanMaritimeTrade() {
		
		//@TODO
		// AssertTrue when the player has the 'input resources' (the resources being given), it is the
		// player's turn, and the game status is 'Playing'
		
		//@TODO
		// AssertFalse when the player does not have the resources being given
		
		//@TODO
		// AssertFalse if it isn't the player's turn
		
		//@TODO
		// AssertFalse if the game status isn't 'Playing'
		
	}
	
	@Test
	public void testCanPlayRoadBuild() {
		
		//@TODO
		// AssertTrue if the first road location is connected to one of the player's roads, the second
		// location is connected to one of the player's roads, first and second locations are not on
		// water, the player has 2 roads, the player has a RoadBuild card, the player hasn't played
		// RoadBuild this turn yet, it is the player's turn, the Game status is "playing"
		
		//@TODO
		// AsserTrue if the first road location is connected to one of the player's roads, the second
		// location is connected to the first road location, both locations are not on water, the player
		// has 2 roads
		
		//@TODO
		// AssertTrue if the first road location is connected to the second road location, the second
		// road location is connected to one of the player's roads, both locations are not on water, the
		// player has 2 roads
		
		//@TODO
		// AssertFalse if the first road location is not connected to one of the player's roads or the
		// second road location
		
		//@TODO
		// AssertFalse if the second road location is not connected to one of the player's roads or the 
		// first road location
		
		//@TODO
		// AssertFalse if the first road location is on water
		
		//@TODO
		// AssertFalse if the second road location is on water
		
		//@TODO
		// AssertFalse if the player does not have 2 roads
		
		//@TODO
		// AssertFalse if the player does not have a RoadBuild card
		
		//@TODO
		// AssertFalse if the player has played a RoadBuild this turn
		
		//@TODO
		// AssertFalse if it is not the player's turn
		
		//@TODO
		// AssertFalse if the game status is not 'Playing'
		
	}
	
	@Test
	public void testCanPlaySoldier() {
		
		//@TODO
		// AssertTrue if the robber is being moved to a new location, the player to rob has at least 1
		// resource card, the player has a soldier card, the player hasn't played the soldier card yet
		// this turn, it is the player's turn, the game status is 'Playing'
		
		//@TODO
		// AssertFalse if the robber is not being moved (i.e. being moved to
		// the same location
		
		//@TODO
		// AssertFalse if the player to rob doesn't have any resource cards
		
		//@TODO
		// AssertFalse if the player doesn't have a soldier card
		
		//@TODO
		// AssertFalse if the player has already played the soldier card this turn
		
		//@TODO
		// AssertFalse if it isn't the player's turn
		
		//@TODO
		// AssertFalse if the game status is not 'Playing'
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
