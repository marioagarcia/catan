package test.model.map;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.card.DevCardList;
import shared.model.card.MaritimeTrade;
import shared.model.card.ResourceList;
import shared.model.map.BoardMap;
import shared.model.player.Player;
import shared.model.turntracker.TurnTracker;
import shared.model.turntracker.TurntrackerInterface.Status;
import shared.serialization.ModelSerializer;
import client.manager.GameData;

public class MapTest {
	
	ModelSerializer ms;

	public GameData getGameData() {
		ms = new ModelSerializer();
	
		
		
		File file = new File("JSON\\getGameModel.txt");
		
		String content = "";
		try {
			content = new Scanner(file).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return ms.deserializeGameModel(content);
	}

	@Test
	public void testCanBuildRoad() {
		GameData game = getGameData();
		BoardMap map = game.getBoardMap();
		TurnTracker tt = game.getTurnTracker();
		Player player = new Player();
		player = game.getPlayerList().get(0);
		for (int i = 0; i < game.getPlayerList().size(); i++) {
			// System.out.println("Player " + i + ":\n" +
			// player.getResourceList().toString());
		}
		int playerIndex = tt.getCurrentTurn();
		// Player Index -- RoadLocation: 0 -- EdgeLocation [hexLoc=HexLocation
		// [x=2, y=0], dir=SouthWest]
		// AssertTrue when the road location is open, is connected to another
		// road,
		// it's not on water, the player has 1 wood, brick, and road, it is the
		// player's
		// turn, the game status is 'Playing'
		EdgeLocation location = new EdgeLocation(new HexLocation(2, 0),
				EdgeDirection.South);
		player.setResourceList(new ResourceList(1, 0, 0, 0, 1));
		tt.setStatus(Status.PLAYING);
		// assertTrue(gameManager.canBuildRoad(location));
		assertTrue(map.canBuildRoad(location, playerIndex, null)
				&& player.canBuildRoad());
		// AssertFalse when the road location is occupied
		EdgeLocation tempLocation = new EdgeLocation(new HexLocation(2, 0),
				EdgeDirection.SouthWest);
		assertFalse(map.canBuildRoad(tempLocation, playerIndex, null)
				&& player.canBuildRoad());
		// AssertFalse when the road is not connected to another road
		tempLocation = new EdgeLocation(new HexLocation(2, 0),
				EdgeDirection.SouthEast);
		assertFalse(map.canBuildRoad(tempLocation, playerIndex, null));
		// AssertFalse when the road is on water
		tempLocation = new EdgeLocation(new HexLocation(3, 0),
				EdgeDirection.South);
		assertFalse(map.canBuildRoad(tempLocation, playerIndex, null)
				&& player.canBuildRoad());
		// AssertFalse when the player does not have 1 wood
		player.setResourceList(new ResourceList(1, 1, 1, 1, 0));
		assertFalse(map.canBuildRoad(location, playerIndex, null)
				&& player.canBuildRoad());
		// AssertFalse when the player does not have 1 brick
		player.setResourceList(new ResourceList(0, 1, 1, 1, 1));
		assertFalse(map.canBuildRoad(location, playerIndex, null)
				&& player.canBuildRoad());
		// AssertFalse if it isn't the player's turn
		tt.setCurrentTurn(1);
		player.setResourceList(new ResourceList(1, 0, 0, 0, 1));
		assertFalse(map.canBuildRoad(location, playerIndex, null)
				&& player.canBuildRoad() && tt.getCurrentTurn() == playerIndex
				&& tt.getStatus() == Status.PLAYING);
		// AssertFalse if the game status isn't 'Playing'
		tt.setCurrentTurn(0);
		tt.setStatus(Status.DISCARDING);
		assertFalse(map.canBuildRoad(location, playerIndex, null)
				&& player.canBuildRoad() && tt.getCurrentTurn() == playerIndex
				&& tt.getStatus() == Status.PLAYING);
	}

	@Test
	public void testCanBuildSettlement() {
		GameData game = getGameData();
		BoardMap map = game.getBoardMap();
		TurnTracker tt = game.getTurnTracker();
		Player player = new Player();
		player = game.getPlayerList().get(0);
		// Player Index -- RoadLocation: 0 -- EdgeLocation [hexLoc=HexLocation
		// [x=0, y=1], dir=South]
		// AssertTrue when the settlement location is open, not on water,
		// connected to a road,
		// the player has 1 wood, brick, wheat, sheep, settlement, it is the
		// player's turn,
		// the game status is 'Playing'
		int playerIndex = 0;
		player.setResourceList(new ResourceList(1, 0, 1, 1, 1));
		tt.setStatus(Status.PLAYING);
		VertexLocation location = new VertexLocation(new HexLocation(-1, 2),
				VertexDirection.SouthEast);
		assertTrue(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement());
		// AssertFalse when the settlement location is next to another
		// settlement
		location = new VertexLocation(new HexLocation(0, 1),
				VertexDirection.SouthWest);
		assertFalse(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement());
		// AssertFalse when the settlement location is occupied
		location = new VertexLocation(new HexLocation(0, 1),
				VertexDirection.SouthEast);
		assertFalse(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement());
		// AssertFalse when the settlement location is on water
		playerIndex = 1;
		tt.setCurrentTurn(1);
		location = new VertexLocation(new HexLocation(-3, 1),
				VertexDirection.West);// (-2, 1) also broken)
		assertFalse(map.canBuildSettlement(location, playerIndex, false));// &&
																			// player.canBuildSettlement());
		// AssertFalse when the settlement location is not connected to one of
		// the player's roads
		location = new VertexLocation(new HexLocation(-1, 2),
				VertexDirection.SouthEast);// (-2, 1)
		assertFalse(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement());
		// AssertFalse when the player doesn't have 1 wood
		playerIndex = 0;
		player.setResourceList(new ResourceList(1, 1, 1, 1, 0));
		location = new VertexLocation(new HexLocation(-1, 2),
				VertexDirection.SouthEast);
		tt.setStatus(Status.PLAYING);
		assertFalse(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement());
		// AssertFalse when the player doesn't have 1 brick
		player.setResourceList(new ResourceList(0, 1, 1, 1, 1));
		assertFalse(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement());
		// AssertFalse when the player doesn't have 1 wheat
		player.setResourceList(new ResourceList(1, 1, 1, 0, 1));
		assertFalse(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement());
		// AssertFalse when the player doesn't have 1 sheep
		player.setResourceList(new ResourceList(1, 1, 0, 1, 1));
		assertFalse(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement());
		// AssertFalse when the player doesn't have 1 Settlement
		player.setResourceList(new ResourceList(1, 1, 1, 1, 1));
		player.setSettlements(0);
		assertFalse(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement());
		// AssertFalse if it isn't the player's turn
		player.setSettlements(5);
		tt.setCurrentTurn(1);
		assertFalse(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement()
				&& tt.getCurrentTurn() == playerIndex);
		// AssertFalse if the game status isn't 'Playing'
		tt.setCurrentTurn(0);
		tt.setStatus(Status.DISCARDING);
		assertFalse(map.canBuildSettlement(location, playerIndex, false)
				&& player.canBuildSettlement()
				&& tt.getStatus() == Status.PLAYING);
	}

	@Test
	public void testCanBuildCity() {
		GameData game = getGameData();
		BoardMap map = game.getBoardMap();
		TurnTracker tt = game.getTurnTracker();
		Player player = new Player();
		player = game.getPlayerList().get(0);
		// AssertTrue when the city location is currently occupied by one of the
		// player's settlements,
		// the player has 2 wheat, 3 ore, 1 city, it is the player's turn, game
		// status is 'Playing
		VertexLocation location = new VertexLocation(new HexLocation(0, 1),
				VertexDirection.SouthEast);
		int playerIndex = player.getPlayerId();
		player.setResourceList(new ResourceList(0, 3, 0, 2, 0));
		tt.setCurrentTurn(0);
		tt.setStatus(Status.PLAYING);
		assertTrue(map.canBuildCity(location, playerIndex)
				&& player.canBuildCity());
		// AssertFalse when the city location is not currently occupied by one
		// of the player's settlements
		VertexLocation tempLocation = new VertexLocation(new HexLocation(0, 1),
				VertexDirection.NorthWest);
		assertFalse(map.canBuildCity(tempLocation, playerIndex)
				&& player.canBuildCity());
		// AssertFalse when the player does not have 2 wheat
		player.setResourceList(new ResourceList(0, 3, 0, 1, 0));
		assertFalse(map.canBuildCity(location, playerIndex)
				&& player.canBuildCity());
		// AssertFalse when the player does not have 3 ore
		player.setResourceList(new ResourceList(0, 2, 0, 2, 0));
		assertFalse(map.canBuildCity(location, playerIndex)
				&& player.canBuildCity());
		// AssertFalse when the player does not have a city
		player.setResourceList(new ResourceList(0, 3, 0, 2, 0));
		player.setCities(0);
		assertFalse(map.canBuildCity(location, playerIndex)
				&& player.canBuildCity());
		// AssertFalse if it isn't the player's turn
		player.setCities(5);
		tt.setCurrentTurn(1);
		assertFalse(map.canBuildCity(location, playerIndex)
				&& player.canBuildCity() && tt.getCurrentTurn() == playerIndex);
		// AssertFalse if the game status isn't 'Playing'
		tt.setCurrentTurn(0);
		tt.setStatus(Status.FIRST_ROUND);
		assertFalse(map.canBuildCity(location, playerIndex)
				&& player.canBuildCity() && tt.getStatus() == Status.PLAYING);
	}

	@Test
	public void testCanMaritimeTrade() {
		GameData game = getGameData();
		BoardMap map = game.getBoardMap();
		TurnTracker tt = game.getTurnTracker();
		Player player = new Player();
		player = game.getPlayerList().get(1);
		// @TODO
		// AssertTrue when the player has the 'input resources' (the resources
		// being given), it is the
		// player's turn, and the game status is 'Playing'
		int playerIndex = player.getId();
		VertexLocation location = new VertexLocation(new HexLocation(-2, 1),
				VertexDirection.SouthWest);
		tt.setStatus(Status.PLAYING);
		tt.setCurrentTurn(1);
		MaritimeTrade trade = new MaritimeTrade();
		trade.setRatio(2);
		trade.setResourceIn(ResourceType.BRICK);
		trade.setResourceOut(ResourceType.ORE);
		player.setResourceList(new ResourceList(2, 0, 0, 0, 0));
		//TODO
//		assertTrue(map.canMaritimeTrade(location, playerIndex));
//		assertTrue(player.canMaritimeTrade(trade));
//		// AssertFalse when the player does not have the resources being given
//		player.setResourceList(new ResourceList(1, 10, 10, 10, 10));
//		assertTrue(map.canMaritimeTrade(location, playerIndex));
//		assertFalse(player.canMaritimeTrade(trade));
//		// AssertFalse if it isn't the player's turn
//		player.setResourceList(new ResourceList(25, 25, 25, 25, 25));
//		tt.setCurrentTurn(2);
//		assertTrue(map.canMaritimeTrade(location, playerIndex));
//		assertFalse(player.canMaritimeTrade(trade)
//				&& tt.getCurrentTurn() == playerIndex);
//		// AssertFalse if the game status isn't 'Playing'
//		tt.setCurrentTurn(1);
//		tt.setStatus(Status.ROBBING);
//		assertTrue(map.canMaritimeTrade(location, playerIndex));
//		assertFalse(player.canMaritimeTrade(trade)
//				&& tt.getStatus() == Status.PLAYING);
	}

	@Test
	public void testCanPlayRoadBuild() {
		GameData game = getGameData();
		BoardMap map = game.getBoardMap();
		TurnTracker tt = game.getTurnTracker();
		Player player = new Player();
		player = game.getPlayerList().get(1);
		// Player Index -- RoadLocation: 0 -- EdgeLocation [hexLoc=HexLocation
		// [x=0, y=1], dir=South]
		int playerIndex = 0;
		player.setResourceList(new ResourceList(1, 0, 1, 1, 1));
		tt.setStatus(Status.PLAYING);
		EdgeLocation location1 = new EdgeLocation(new HexLocation(0, 2),
				EdgeDirection.NorthEast);
		EdgeLocation location2 = new EdgeLocation(new HexLocation(0, 1),
				EdgeDirection.SouthWest);
		// AssertTrue if the first road location is connected to one of the
		// player's roads, the second
		// location is connected to one of the player's roads, first and second
		// locations are not on
		// water, the player has 2 roads, the player has a RoadBuild card, the
		// player hasn't played
		// RoadBuild this turn yet, it is the player's turn, the Game status is
		// "playing"
		DevCardList devCardList = new DevCardList();
		devCardList.setDevCardList(0, 0, 0, 1, 0);
		player.setNewDevCards(devCardList);
		assertTrue(map.canPlayRoadBuilding(location1, location2, playerIndex)
				&& player.canPlayRoadBuilding());
		// AsserTrue if the first road location is connected to one of the
		// player's roads, the second
		// location is connected to the first road location, both locations are
		// not on water, the player
		// has 2 roads
		EdgeLocation tempLocation2 = new EdgeLocation(new HexLocation(0, 2),
				EdgeDirection.SouthEast);
		assertTrue(map.canPlayRoadBuilding(location1, tempLocation2,
				playerIndex) && player.canPlayRoadBuilding());
		// AssertTrue if the first road location is connected to the second road
		// location, the second
		// road location is connected to one of the player's roads, both
		// locations are not on water, the
		// player has 2 roads
		EdgeLocation tempLocation1 = new EdgeLocation(new HexLocation(0, 2),
				EdgeDirection.South);// = tempLocation2;
		tempLocation2 = new EdgeLocation(new HexLocation(0, 2),
				EdgeDirection.SouthWest);
		assertTrue(map.canPlayRoadBuilding(tempLocation1, tempLocation2,
				playerIndex));
		assertTrue(player.canPlayRoadBuilding());
		// AssertFalse if the first road location is not connected to one of the
		// player's roads or the
		// second road location
		tempLocation2 = new EdgeLocation(new HexLocation(0, 2),
				EdgeDirection.NorthEast);
		assertFalse(map.canPlayRoadBuilding(tempLocation1, location2,
				playerIndex) && player.canPlayRoadBuilding());
		// AssertFalse if the second road location is not connected to one of
		// the player's roads or the
		// first road location
		tempLocation1 = location1;
		tempLocation2 = new EdgeLocation(new HexLocation(0, 2),
				EdgeDirection.South);
		assertFalse(map.canPlayRoadBuilding(tempLocation1, tempLocation2,
				playerIndex) && player.canPlayRoadBuilding());
		// AssertFalse if the first road location is on water
		tempLocation1 = new EdgeLocation(new HexLocation(0, 3),
				EdgeDirection.NorthWest);
		tempLocation2 = new EdgeLocation(new HexLocation(0, 2),
				EdgeDirection.SouthWest);
		assertFalse(map.canPlayRoadBuilding(tempLocation1, tempLocation2,
				playerIndex) && player.canPlayRoadBuilding());
		// AssertFalse if the second road location is on water
		tempLocation1 = tempLocation2;
		tempLocation2 = new EdgeLocation(new HexLocation(0, 3),
				EdgeDirection.NorthWest);
		assertFalse(map.canPlayRoadBuilding(tempLocation1, tempLocation2,
				playerIndex) && player.canPlayRoadBuilding());
		// AssertFalse if the player does not have 2 roads
		player.setRoads(1);
		assertTrue(map.canPlayRoadBuilding(location1, location2, playerIndex));
		assertFalse(player.canPlayRoadBuilding());
		// AssertFalse if the player does not have a RoadBuild card
		player.setRoads(25);
		devCardList.setDevCardList(25, 25, 25, 0, 25);
		assertTrue(map.canPlayRoadBuilding(location1, location2, playerIndex));
		assertFalse(player.canPlayRoadBuilding());
		// AssertFalse if the player has played a RoadBuild this turn
		devCardList.setDevCardList(25, 25, 25, 25, 25);
		player.setPlayedDevCard(true);
		assertTrue(map.canPlayRoadBuilding(location1, location2, playerIndex));
		assertFalse(player.canPlayRoadBuilding());
		// AssertFalse if it is not the player's turn
		player.setPlayedDevCard(false);
		tt.setCurrentTurn(1);
		assertTrue(map.canPlayRoadBuilding(location1, location2, playerIndex));
		assertTrue(player.canPlayRoadBuilding());
		assertFalse(tt.getCurrentTurn() == playerIndex);
		// AssertFalse if the game status is not 'Playing'
		tt.setCurrentTurn(0);
		tt.setStatus(Status.ROBBING);
		assertTrue(map.canPlayRoadBuilding(location1, location2, playerIndex));
		assertTrue(player.canPlayRoadBuilding());
		assertFalse(tt.getStatus() == Status.PLAYING);
	}

	@Test
	public void testCanPlaySoldier() {
		GameData game = getGameData();
		BoardMap map = game.getBoardMap();
		TurnTracker tt = game.getTurnTracker();
		Player player = new Player();
		player = game.getPlayerList().get(0);
		HexLocation oldLocation = map.getRobberLocation(); // HexLocation [x=0,
															// y=-2]
		DevCardList newDevCardList = new DevCardList();
		newDevCardList.setDevCardList(0, 0, 1, 0, 0);
		player.setNewDevCards(newDevCardList);
		// AssertTrue if the robber is being moved to a new location, the player
		// to rob has at least 1
		// resource card, the player has a soldier card, the player hasn't
		// played the soldier card yet
		// this turn, it is the player's turn, the game status is 'Playing'
		HexLocation newLocation = new HexLocation(0, 0);
		assertTrue(map.canPlaySoldier(oldLocation, newLocation, 2)
				&& player.canPlaySoldier());
		// AssertFalse if the robber is not being moved (i.e. being moved to
		// the same location
		newLocation = new HexLocation(0, -2);
		assertFalse(map.canPlaySoldier(oldLocation, newLocation, 3)
				&& player.canPlaySoldier());
		// @TODO
		// AssertFalse if the player to rob doesn't have any resource cards
		// AssertFalse if the player doesn't have a soldier card
		newLocation = new HexLocation(0, 0);
		newDevCardList.setDevCardList(25, 25, 0, 25, 25);
		player.setNewDevCards(newDevCardList);
		assertTrue(map.canPlaySoldier(oldLocation, newLocation, 2));
		assertFalse(player.canPlaySoldier());
		// int yearOfPlenty, int monopoly, int soldier, int roadBuild, int
		// monument
		// AssertFalse if the player has already played the soldier card this
		// turn
		newDevCardList.setDevCardList(25, 25, 25, 25, 25);
		player.setNewDevCards(newDevCardList);
		player.setPlayedDevCard(true);
		assertTrue(map.canPlaySoldier(oldLocation, newLocation, 2));
		assertFalse(player.canPlaySoldier());
		// AssertFalse if it isn't the player's turn
		player.setPlayedDevCard(false);
		tt.setCurrentTurn(1);
		assertTrue(map.canPlaySoldier(oldLocation, newLocation, 2)
				&& player.canPlaySoldier());
		assertFalse(tt.getCurrentTurn() == player.getPlayerId());
		// AssertFalse if the game status is not 'Playing'
		tt.setCurrentTurn(0);
		tt.setStatus(Status.ROLLING);
		assertTrue(map.canPlaySoldier(oldLocation, newLocation, 2)
				&& player.canPlaySoldier());
		assertTrue(tt.getCurrentTurn() == player.getPlayerId());
		assertFalse(tt.getStatus() == Status.PLAYING);
	}
}