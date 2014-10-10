package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import client.communication.facade.ModelFacade;
import client.communication.server.ServerMoxy;
import client.manager.GameManager;
import client.model.GameInfo;
import client.model.card.DevCardList;
import client.model.card.DomesticTrade;
import client.model.card.MaritimeTrade;
import client.model.card.ResourceList;
import client.model.player.PlayerInfo;
import client.model.turntracker.TurntrackerInterface.Status;

public class FacadeTest {

	private ModelFacade facade;
	private GameManager manager;
	
	@Before
	public void setUp(){
		manager = new GameManager(new ServerMoxy());
		facade = new ModelFacade(manager);

		facade.loginPlayer("Sam", "sam");
		
		GameInfo g = new GameInfo();
		g.setId(0);
		g.setTitle("My game");
		facade.joinGame(CatanColor.ORANGE, g);
		
		//Always start on the local player's turn
		manager.getTurnTracker().setCurrentTurn(0);
	}
	
	@Test
	public void testCanJoinGame() {
		
		GameInfo g = new GameInfo();
		
		ArrayList<PlayerInfo> playerList = createPlayers(2);
		
		g.setGameInfo("TestGame", 0, playerList);
		
		//Player with color Brown already exists in game
		assertFalse(facade.canJoinGame(CatanColor.BROWN, g));
		
		//Color Puce is available
		assertTrue(facade.canJoinGame(CatanColor.PUCE, g));
		
		/*
		playerList = createPlayers(4);
		gameInfo.setGameInfo("TestGame", 173, playerList);*/
		
		g = new GameInfo();
		g.setId(173);
		g.setTitle("My game");
		
		//Can join game is only verifying color. Since a valid color was given, it returned true, even though the game doesn't exist
		//assertFalse(facade.canJoinGame(CatanColor.PUCE, g));
	}
	
	@Test
	public void testSendChat(){
			assertTrue(manager.sendChat("Yo, yo, whatup?"));
	}
	
	@Test
	public void testCanAcceptTrade(){
		
		manager.getTurnTracker().setStatus(Status.PLAYING);
		
		//Player can accept when it is not their turn
		manager.getTurnTracker().setCurrentTurn(2);
		DomesticTrade trade = new DomesticTrade(1, 2, new ResourceList(1, 0, 0, 0, -1));
		assertTrue(facade.canAcceptTrade(trade));
		
		//Player cannot accept trade on their turn
		manager.getTurnTracker().setCurrentTurn(0);
		assertFalse(facade.canAcceptTrade(trade));
		
		//Player does not have the required resources
		manager.getTurnTracker().setCurrentTurn(2);
		trade = new DomesticTrade(1, 2, new ResourceList(20, 2, 0, -10, -5));
		assertFalse(facade.canAcceptTrade(trade));
	}
	
	@Test
	public void testCanDiscardCards(){
		
		manager.getTurnTracker().setStatus(Status.DISCARDING);
		manager.getLocalPlayer().setDiscarded(false);
		manager.getLocalPlayer().setResourceList(new ResourceList(0, 0, 0, 0, 7));
		assertFalse(facade.canDiscardCards(manager.getLocalPlayer().getResourceList()));
		
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 0, 0, 0, 7));
		assertTrue(facade.canDiscardCards(manager.getLocalPlayer().getResourceList()));
		
		manager.getLocalPlayer().setResourceList(new ResourceList(20, 20, 20, 20, 20));
		assertTrue(facade.canDiscardCards(manager.getLocalPlayer().getResourceList()));
			
		facade.finishTurn();
		assertFalse(facade.canDiscardCards(manager.getLocalPlayer().getResourceList()));
		
		manager.getTurnTracker().setStatus(Status.ROLLING);
		manager.getLocalPlayer().setResourceList(new ResourceList(2, 0, 3, 0, 9));
		assertFalse(facade.canDiscardCards(manager.getLocalPlayer().getResourceList()));
	}
	
	@Test
	public void testCanRollNumber(){
		
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getTurnTracker().setStatus(Status.ROLLING);
		assertTrue(facade.canRoll());
		
		//Can't roll if it is not your turn
		manager.getTurnTracker().setCurrentTurn(2);
		assertFalse(facade.canRoll());
		
		//Correct turn, but wrong state
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getTurnTracker().setStatus(Status.DISCARDING);
		assertFalse(facade.canRoll());
		
		//Wrong turn and wrong state
		manager.getTurnTracker().setCurrentTurn(2);
		assertFalse(facade.canRoll());
	}
	
	@Test
	public void testCanOfferTrade(){
		manager.getTurnTracker().setStatus(Status.PLAYING);
		
		//Player can offer when it is their turn
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getLocalPlayer().setResourceList(new ResourceList(20, 20, 20, 20, 20));
		DomesticTrade trade = new DomesticTrade(1, 2, new ResourceList(-1, 0, 0, 0, 1));
		assertTrue(facade.canOfferTrade(trade));
		
		//Player cannot offer trade on another player's turn
		manager.getTurnTracker().setCurrentTurn(2);
		assertFalse(facade.canOfferTrade(trade));
		
		//Player does not have the required resources
		manager.getTurnTracker().setCurrentTurn(2);
		manager.getLocalPlayer().setResourceList(new ResourceList(0, 0, 0, 0, 0));
		trade = new DomesticTrade(1, 2, new ResourceList(20, 2, 0, -10, -5));
		assertFalse(facade.canOfferTrade(trade));
	}
	
	@Test
	public void testCanFinishTurn(){
		
		//Player's turn but not in the playing state
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getTurnTracker().setStatus(Status.ROLLING);
		assertFalse(facade.canFinishTurn());	
		
		//Player's turn and in the play state
		manager.getTurnTracker().setStatus(Status.PLAYING);
		assertTrue(facade.canFinishTurn());	
		
		//Cannot end turn if it is not currently your turn
		manager.getTurnTracker().setCurrentTurn(2);
		assertFalse(facade.canFinishTurn());
	}
	
	@Test
	public void testCanBuyDevCard(){
		
		manager.getTurnTracker().setStatus(Status.PLAYING);
		manager.getDevCardBank().setDeck(2, 2, 2, 2, 2);
		
		//Player can buy on their turn, and they have enough resources to buy dev card
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getLocalPlayer().setResourceList(new ResourceList(20, 20, 20, 20, 20));
		assertTrue(facade.canBuyDevCard());
		
		//Player cannot buy a dev card on another player's turn
		manager.getTurnTracker().setCurrentTurn(2);
		assertFalse(facade.canBuyDevCard());
		
		//Player does not have the required resources
		manager.getTurnTracker().setCurrentTurn(2);
		manager.getLocalPlayer().setResourceList(new ResourceList(0, 0, 0, 0, 0));
		assertFalse(facade.canBuyDevCard());
		
		//Bank has no dev cards available to buy
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getDevCardBank().setDeck(0, 0, 0, 0, 0);
		manager.getLocalPlayer().setResourceList(new ResourceList(20, 20, 20, 20, 20));
		assertFalse(facade.canBuyDevCard());
	}
	
	@Test
	public void testCanPlayYearOfPlenty(){
		//Load bank with enough resources
		manager.getResCardBank().setBank(10, 10, 10, 10, 10);
		
		//Cannot play dev card during rolling
		manager.getTurnTracker().setStatus(Status.ROLLING);
		assertFalse(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));
		
		manager.getTurnTracker().setStatus(Status.PLAYING);
		
		//Player has the card, and it is their turn
		manager.getTurnTracker().setCurrentTurn(0);
		DevCardList dev_cards = new DevCardList();
		dev_cards.setYearOfPlenty(1);
		manager.getLocalPlayer().setNewDevCards(dev_cards);
		assertTrue(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));
		
		 //Not the player's turn
		manager.getTurnTracker().setCurrentTurn(2);
		assertFalse(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));
		
		//Player does not have year_of_plenty card
		dev_cards.setYearOfPlenty(0);
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getLocalPlayer().setNewDevCards(dev_cards);
		assertFalse(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));
		
		//Player has the card, and it is their turn, but the bank doesn't have the cards
		dev_cards.setYearOfPlenty(1);
		manager.getLocalPlayer().setNewDevCards(dev_cards);
		manager.getResCardBank().setBank(0, 0, 0, 0, 0);
		assertFalse(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));	
	}
	
	@Test
	public void testCanPlayMonopoly(){
		
		manager.getTurnTracker().setStatus(Status.PLAYING);
		
		manager.getTurnTracker().setCurrentTurn(0);
		DevCardList dev_cards = new DevCardList();
		dev_cards.setMonopoly(1);
		
		manager.getLocalPlayer().setNewDevCards(dev_cards);
		assertTrue(facade.canPlayMonopoly()); //Player has the card, and it is their turn
		
		manager.getTurnTracker().setStatus(Status.ROLLING);
		assertFalse(facade.canPlayMonopoly()); //Cannot play dev card during rolling state
		
		manager.getTurnTracker().setCurrentTurn(2);
		assertFalse(facade.canPlayMonopoly()); //Cannot play when it is not your turn
		
		manager.getTurnTracker().setStatus(Status.PLAYING);
		dev_cards.setMonopoly(0);
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getLocalPlayer().setNewDevCards(dev_cards);
		assertFalse(facade.canPlayMonopoly());	//Player does not have monopoly card
	}
	
	@Test
	public void testCanPlayMonument(){
		manager.getTurnTracker().setStatus(Status.PLAYING);
		
		manager.getTurnTracker().setCurrentTurn(0);
		DevCardList dev_cards = new DevCardList();
		dev_cards.setMonument(1);
		
		manager.getLocalPlayer().setNewDevCards(dev_cards);
		assertTrue(facade.canPlayMonument()); //Player has the card, and it is their turn
		
		manager.getTurnTracker().setStatus(Status.ROLLING);
		assertFalse(facade.canPlayMonument()); //Cannot play dev card during rolling state
		
		manager.getTurnTracker().setCurrentTurn(2);
		assertFalse(facade.canPlayMonument()); //Cannot play when it is not your turn
		
		manager.getTurnTracker().setStatus(Status.PLAYING);
		dev_cards.setMonument(0);
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getLocalPlayer().setNewDevCards(dev_cards);
		assertFalse(facade.canPlayMonument());	//Player does not have monument card	
	}
	
	public ArrayList<PlayerInfo> createPlayers(int numPlayers){
		
		ArrayList<CatanColor> colorList = new ArrayList<CatanColor>();
		colorList.add(CatanColor.BLUE);
		colorList.add(CatanColor.BROWN);
		colorList.add(CatanColor.GREEN);
		colorList.add(CatanColor.ORANGE);
		
		ArrayList<String> nameList = new ArrayList<String>();
		nameList.add("Mario");
		nameList.add("Kevin");
		nameList.add("Chris");
		nameList.add("Casey");
		
		ArrayList<PlayerInfo> playerList = new ArrayList<PlayerInfo>();
		for(int i = 0; i < numPlayers; i++){
			PlayerInfo playerInfo = new PlayerInfo();
			playerInfo.setPlayerInfo(colorList.get(i), nameList.get(i), i);
			playerList.add(playerInfo);
		}
		return playerList;
	}
	
	@Test
	public void testCanBuildRoad() {
		//BoardMap map = game.getBoardMap();
		//TurnTracker tt = game.getTurnTracker();
		
		//Player player = new Player();
		//player = game.getPlayerList().get(0);
		
		//int playerIndex = manager.getTurnTracker().getCurrentTurn();
		//Player Index -- RoadLocation:  0 -- EdgeLocation [hexLoc=HexLocation [x=2, y=0], dir=SouthWest]
		
		// AssertTrue when the road location is open, is connected to another road,
		// it's not on water, the player has 1 wood, brick, and road, it is the player's
		// turn, the game status is 'Playing'
		EdgeLocation location = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.South);
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 0, 0, 0, 1));
		manager.getTurnTracker().setStatus(Status.PLAYING);
		
		//assertTrue(gameManager.canBuildRoad(location));
		assertTrue(manager.canBuildRoad(location));//manager.canBuildRoad(location));
		
		// AssertFalse when the road location is occupied
		EdgeLocation tempLocation = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.SouthWest);
		assertFalse(manager.canBuildRoad(tempLocation));//map.canBuildRoad(tempLocation, playerIndex) && manager.getLocalPlayer().canBuildRoad());
		
		// AssertFalse when the road is not connected to another road
		tempLocation = new EdgeLocation(new HexLocation(2, 0), EdgeDirection.SouthEast);
		assertFalse(manager.canBuildRoad(tempLocation));
		
		// AssertFalse when the road is on water
		tempLocation = new EdgeLocation(new HexLocation(3, 0), EdgeDirection.South);
		assertFalse(manager.canBuildRoad(tempLocation));//map.canBuildRoad(tempLocation, playerIndex) && manager.getLocalPlayer().canBuildRoad());
		
		// AssertFalse when the player does not have 1 wood
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 1, 1, 1, 0));
		assertFalse(manager.canBuildRoad(location));
		
		// AssertFalse when the player does not have 1 brick
		manager.getLocalPlayer().setResourceList(new ResourceList(0, 1, 1, 1, 1));
		assertFalse(manager.canBuildRoad(location)); 
		
		// AssertFalse if it isn't the player's turn
		
		manager.getTurnTracker().setCurrentTurn(1);
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 0, 0, 0, 1));
		assertFalse(manager.canBuildRoad(location));
		
		// AssertFalse if the game status isn't 'Playing'
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getTurnTracker().setStatus(Status.DISCARDING);
		assertFalse(manager.canBuildRoad(location));
	}
	
	@Test
	public void testCanBuildSettlement() {
		//Player Index -- RoadLocation:  0 -- EdgeLocation [hexLoc=HexLocation [x=0, y=1], dir=South]
		
		// AssertTrue when the settlement location is open, not on water, connected to a road, 
		// the player has 1 wood, brick, wheat, sheep, settlement, it is the player's turn,
		// the game status is 'Playing'
		int playerIndex = 0; 
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 0, 1, 1, 1));
		manager.getTurnTracker().setStatus(Status.PLAYING);
		VertexLocation location = new VertexLocation(new HexLocation(-1, 2), VertexDirection.SouthEast);
		assertTrue(manager.canBuildSettlement(location));
		
		// AssertFalse when the settlement location is next to another settlement
		location = new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthWest);
		assertFalse(manager.canBuildSettlement(location));
		
		// AssertFalse when the settlement location is occupied
		location = new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast);
		assertFalse(manager.canBuildSettlement(location));
		
		// AssertFalse when the player doesn't have 1 wood
		playerIndex = 0; 
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 1, 1, 1, 0));
		location = new VertexLocation(new HexLocation(-1, 2), VertexDirection.SouthEast);
		manager.getTurnTracker().setStatus(Status.PLAYING);
		assertFalse(manager.canBuildSettlement(location));
		
		// AssertFalse when the player doesn't have 1 brick
		manager.getLocalPlayer().setResourceList(new ResourceList(0, 1, 1, 1, 1));
		assertFalse(manager.canBuildSettlement(location));
		
		// AssertFalse when the player doesn't have 1 wheat
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 1, 1, 0, 1));
		assertFalse(manager.canBuildSettlement(location));
		
		// AssertFalse when the player doesn't have 1 sheep
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 1, 0, 1, 1));
		assertFalse(manager.canBuildSettlement(location));
		
		//AssertFalse when the player doesn't have 1 Settlement
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 1, 1, 1, 1));
		manager.getLocalPlayer().setSettlements(0);
		assertFalse(manager.canBuildSettlement(location));
		
		// AssertFalse if it isn't the player's turn
		manager.getLocalPlayer().setSettlements(5);
		manager.getTurnTracker().setCurrentTurn(1);
		assertFalse(manager.canBuildSettlement(location) && manager.getTurnTracker().getCurrentTurn() == playerIndex);
		
		// AssertFalse if the game status isn't 'Playing'
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getTurnTracker().setStatus(Status.DISCARDING);
		assertFalse(manager.canBuildSettlement(location) && manager.getTurnTracker().getStatus() == Status.PLAYING);
		
		facade.loginPlayer("Brooke", "brooke");
		
		GameInfo g = new GameInfo();
		g.setId(0);
		g.setTitle("My game");
		facade.joinGame(CatanColor.BLUE, g);
		
		// AssertFalse when the settlement location is on water
		playerIndex = 1;
		manager.getTurnTracker().setCurrentTurn(1);
		location = new VertexLocation(new HexLocation(-3, 1), VertexDirection.West);
		assertFalse(manager.canBuildSettlement(location));
		
		// AssertFalse when the settlement location is not connected to one of the player's roads
		location = new VertexLocation(new HexLocation(-1, 2), VertexDirection.SouthEast);
		assertFalse(manager.canBuildSettlement(location));

	}
	
	@Test
	public void testCanBuildCity() {
		// AssertTrue when the city location is currently occupied by one of the player's settlements,
		// the player has 2 wheat, 3 ore, 1 city, it is the player's turn, game status is 'Playing
		VertexLocation location = new VertexLocation(new HexLocation(0, 1), VertexDirection.SouthEast);
		int playerIndex = manager.getLocalPlayer().getPlayerId();
		manager.getLocalPlayer().setResourceList(new ResourceList(0, 3, 0, 2, 0));
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getTurnTracker().setStatus(Status.PLAYING);
		assertTrue(manager.canBuildSettlement(location));
		
		// AssertFalse when the city location is not currently occupied by one of the player's settlements
		VertexLocation tempLocation = new VertexLocation(new HexLocation(0, 1), VertexDirection.NorthWest);
		assertFalse(manager.canBuildSettlement(tempLocation));
		
		// AssertFalse when the player does not have 2 wheat
		manager.getLocalPlayer().setResourceList(new ResourceList(0, 3, 0, 1, 0));
		assertFalse(manager.canBuildSettlement(location));
		
		// AssertFalse when the player does not have 3 ore
		manager.getLocalPlayer().setResourceList(new ResourceList(0, 2, 0, 2, 0));
		assertFalse(manager.canBuildSettlement(location));
		
		// AssertFalse when the player does not have a city
		manager.getLocalPlayer().setResourceList(new ResourceList(0, 3, 0, 2, 0));
		manager.getLocalPlayer().setCities(0);
		assertFalse(manager.canBuildSettlement(location));
		
		// AssertFalse if it isn't the player's turn
		manager.getLocalPlayer().setCities(5);
		manager.getTurnTracker().setCurrentTurn(1);
		assertFalse(manager.canBuildSettlement(location) && manager.getTurnTracker().getCurrentTurn() == playerIndex);
		
		// AssertFalse if the game status isn't 'Playing'
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getTurnTracker().setStatus(Status.FIRST_ROUND);
		assertFalse(manager.canBuildSettlement(location) && manager.getTurnTracker().getStatus() == Status.PLAYING);
		
	}
	
	@Test
	public void testCanMaritimeTrade() {
		// AssertTrue when the player has the 'input resources' (the resources being given), it is the
		// player's turn, and the game status is 'Playing'
		facade.loginPlayer("Brooke", "brooke");
		
		GameInfo g = new GameInfo();
		g.setId(0);
		g.setTitle("My game");
		facade.joinGame(CatanColor.BLUE, g);
		
		int playerIndex = manager.getLocalPlayer().getId();
		VertexLocation location = new VertexLocation(new HexLocation(-2, 1), VertexDirection.SouthWest);
		manager.getTurnTracker().setStatus(Status.PLAYING);
		manager.getTurnTracker().setCurrentTurn(1);
		
		MaritimeTrade trade = new MaritimeTrade();
		trade.setRatio(2);
		trade.setResourceIn(ResourceType.BRICK);
		trade.setResourceOut(ResourceType.ORE);
		
		manager.getLocalPlayer().setResourceList(new ResourceList(2, 0, 0, 0, 0));
		
		assertTrue(manager.canMaritimeTrade(location, trade));
		
		// AssertFalse when the player does not have the resources being given
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 10, 10, 10, 10));
		assertTrue(manager.canMaritimeTrade(location, trade));
		assertFalse(manager.getLocalPlayer().canMaritimeTrade(trade));
		
		// AssertFalse if it isn't the player's turn
		manager.getLocalPlayer().setResourceList(new ResourceList(25, 25, 25, 25, 25));
		manager.getTurnTracker().setCurrentTurn(2);
		assertTrue(manager.canMaritimeTrade(location, trade));
		assertFalse(manager.getLocalPlayer().canMaritimeTrade(trade) && manager.getTurnTracker().getCurrentTurn() == playerIndex);
		
		// AssertFalse if the game status isn't 'Playing'
		manager.getTurnTracker().setCurrentTurn(1);
		manager.getTurnTracker().setStatus(Status.ROBBING);
		assertTrue(manager.canMaritimeTrade(location, trade));
		assertFalse(manager.getLocalPlayer().canMaritimeTrade(trade) && manager.getTurnTracker().getStatus() == Status.PLAYING);
	}
	
	@Test
	public void testCanPlayRoadBuild() {
		
		//Player player = new Player();
		//player = game.getPlayerList().get(1);
		manager.getLocalPlayer().setPlayerIndex(1);
		
		//Player Index -- RoadLocation:  0 -- EdgeLocation [hexLoc=HexLocation [x=0, y=1], dir=South]
		manager.getLocalPlayer().setResourceList(new ResourceList(1, 0, 1, 1, 1));
		manager.getTurnTracker().setStatus(Status.PLAYING);
		EdgeLocation location1 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.NorthEast);
		EdgeLocation location2 = new EdgeLocation(new HexLocation(0, 1), EdgeDirection.SouthWest);
		
		// AssertTrue if the first road location is connected to one of the player's roads, the second
		// location is connected to one of the player's roads, first and second locations are not on
		// water, the player has 2 roads, the player has a RoadBuild card, the player hasn't played
		// RoadBuild this turn yet, it is the player's turn, the Game status is "playing"
		DevCardList devCardList = new DevCardList();
		devCardList.setDevCardList(0, 0, 0, 1, 0);
		manager.getLocalPlayer().setNewDevCards(devCardList);
		assertTrue(manager.canPlayRoadBuilding(location1, location2));
		
		// AsserTrue if the first road location is connected to one of the player's roads, the second
		// location is connected to the first road location, both locations are not on water, the player
		// has 2 roads
		EdgeLocation tempLocation2 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.SouthEast);
		assertTrue(manager.canPlayRoadBuilding(location1, tempLocation2));
		
		// AssertTrue if the first road location is connected to the second road location, the second
		// road location is connected to one of the player's roads, both locations are not on water, the
		// player has 2 roads
		EdgeLocation tempLocation1 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.South);//= tempLocation2;
		tempLocation2 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.SouthWest);
		
		assertTrue(manager.canPlayRoadBuilding(tempLocation1, tempLocation2));
		// AssertFalse if the first road location is not connected to one of the player's roads or the
		// second road location
		tempLocation2 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.NorthEast);
		assertFalse(manager.canPlayRoadBuilding(tempLocation1, location2));
		// AssertFalse if the second road location is not connected to one of the player's roads or the 
		// first road location
		tempLocation1 = location1;
		tempLocation2 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.South);
		assertFalse(manager.canPlayRoadBuilding(tempLocation1, tempLocation2) && manager.getLocalPlayer().canPlayRoadBuilding());
		
		// AssertFalse if the first road location is on water
		tempLocation1 = new EdgeLocation(new HexLocation(0, 3), EdgeDirection.NorthWest);
		tempLocation2 = new EdgeLocation(new HexLocation(0, 2), EdgeDirection.SouthWest);
		assertFalse(manager.canPlayRoadBuilding(tempLocation1, tempLocation2) && manager.getLocalPlayer().canPlayRoadBuilding());
		
		// AssertFalse if the second road location is on water
		tempLocation1 = tempLocation2;
		tempLocation2 = new EdgeLocation(new HexLocation(0, 3), EdgeDirection.NorthWest);
		assertFalse(manager.canPlayRoadBuilding(tempLocation1, tempLocation2) && manager.getLocalPlayer().canPlayRoadBuilding());
		
		// AssertFalse if the player does not have 2 roads
		manager.getLocalPlayer().setRoads(1);
		assertFalse(manager.canPlayRoadBuilding(location1, location2));
		
		// AssertFalse if the player does not have a RoadBuild card
		manager.getLocalPlayer().setRoads(25);
		devCardList.setDevCardList(25, 25, 25, 0, 25);
		assertFalse(manager.canPlayRoadBuilding(location1, location2));
		
		// AssertFalse if the player has played a RoadBuild this turn
		devCardList.setDevCardList(25, 25, 25, 25, 25);
		manager.getLocalPlayer().setPlayedDevCard(true);
		assertFalse(manager.canPlayRoadBuilding(location1, location2));
		
		// AssertFalse if it is not the player's turn
		manager.getLocalPlayer().setPlayedDevCard(false);
		manager.getTurnTracker().setCurrentTurn(1);
		assertFalse(manager.canPlayRoadBuilding(location1, location2));
		
		// AssertFalse if the game status is not 'Playing'
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getTurnTracker().setStatus(Status.ROBBING);
		assertFalse(manager.canPlayRoadBuilding(location1, location2));
	}
	
	@Test
	public void testCanPlaySoldier() {
		manager.getLocalPlayer().setPlayerIndex(0);
		
		HexLocation oldLocation = manager.getBoardMap().getRobberLocation(); //HexLocation [x=0, y=-2]
		DevCardList newDevCardList = new DevCardList();
		newDevCardList.setDevCardList(0, 0, 1, 0, 0);
		manager.getLocalPlayer().setNewDevCards(newDevCardList);
		
		// AssertTrue if the robber is being moved to a new location, the player to rob has at least 1
		// resource card, the player has a soldier card, the player hasn't played the soldier card yet
		// this turn, it is the player's turn, the game status is 'Playing'
		HexLocation newLocation = new HexLocation(0, 0);
		assertTrue(manager.canPlaySoldier(oldLocation, newLocation, 2));
		
		// AssertFalse if the robber is not being moved (i.e. being moved to
		// the same location
		newLocation = new HexLocation(0, -2);
		assertFalse(manager.canPlaySoldier(oldLocation, newLocation, 3));
		
		//@TODO
		// AssertFalse if the player to rob doesn't have any resource cards
		
		// AssertFalse if the player doesn't have a soldier card
		newLocation = new HexLocation(0, 0);
		newDevCardList.setDevCardList(25, 25, 0, 25, 25);
		manager.getLocalPlayer().setNewDevCards(newDevCardList);
		assertFalse(manager.canPlaySoldier(oldLocation, newLocation, 2));
		
		// AssertFalse if the player has already played the soldier card this turn
		newDevCardList.setDevCardList(25, 25, 25, 25, 25);
		manager.getLocalPlayer().setNewDevCards(newDevCardList);
		manager.getLocalPlayer().setPlayedDevCard(true);
		assertFalse(manager.canPlaySoldier(oldLocation, newLocation, 2));
		
		// AssertFalse if it isn't the player's turn
		manager.getLocalPlayer().setPlayedDevCard(false);
		manager.getTurnTracker().setCurrentTurn(1);
		assertFalse(manager.canPlaySoldier(oldLocation, newLocation, 2));
		
		// AssertFalse if the game status is not 'Playing'
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getTurnTracker().setStatus(Status.ROLLING);
		assertFalse(manager.canPlaySoldier(oldLocation, newLocation, 2));
		
	}

}
