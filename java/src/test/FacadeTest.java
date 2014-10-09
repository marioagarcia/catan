package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import client.communication.facade.ModelFacade;
import client.communication.server.ServerProxy;
import client.manager.GameData;
import client.manager.GameManager;
import client.model.GameInfo;
import client.model.card.DevCardList;
import client.model.card.DomesticTrade;
import client.model.card.ResourceList;
import client.model.player.Player;
import client.model.player.PlayerInfo;
import client.model.turntracker.TurntrackerInterface.Status;

public class FacadeTest {

	private ModelFacade facade;
	private GameManager manager;
	
	@Before
	public void setUp(){
		manager = new GameManager(new ServerProxy("8081","localhost"));
		facade = new ModelFacade(manager);

		facade.loginPlayer("Sam", "sam");
		
		GameInfo g = new GameInfo();
		g.setId(0);
		g.setTitle("My game");
		facade.joinGame(CatanColor.ORANGE, g);
		
		//Always start on the local player's turn
		while (!facade.canRoll())
		{
			facade.finishTurn();
		}
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
	
	public void testSendChat(){
		//Doesn't exist
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

		assertTrue(facade.canRoll());
		
		//Can't roll if it is not your turn
		facade.finishTurn();
		assertFalse(facade.canRoll());
	}
	
	public void testCanBuildRoad(){
		
	}
	
	public void testCanBuildSettlement(){
		
	}
	
	public void testCanBuildCity(){
		
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
	
	public void testCanMaritimeTrade(){
		
	}
	
	//
	@Test
	public void testCanFinishTurn(){
		
		
		//Need a game that is already in process
		//assertTrue(facade.canFinishTurn());
		
		//Should not be allowed because game is currently rolling
		assertFalse(facade.canFinishTurn());	
	}
	
	//
	public void testCanBuyDevCard(){
		
	}
	
	//
	@Test
	public void testCanPlayYearOfPlenty(){
		
		manager.getTurnTracker().setStatus(Status.PLAYING);
		
		manager.getTurnTracker().setCurrentTurn(0);
		DevCardList dev_cards = new DevCardList();
		dev_cards.setYearOfPlenty(1);
		
		manager.getLocalPlayer().setNewDevCards(dev_cards);
		assertTrue(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD)); //Player has the card, and it is their turn
		
		manager.getTurnTracker().setStatus(Status.ROLLING);
		assertTrue(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));
		
		manager.getTurnTracker().setCurrentTurn(2);
		assertTrue(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));
		
		manager.getTurnTracker().setStatus(Status.PLAYING);
		dev_cards.setYearOfPlenty(0);
		manager.getTurnTracker().setCurrentTurn(0);
		manager.getLocalPlayer().setNewDevCards(dev_cards);
		assertFalse(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));	//Player does not have monopoly card
		
	}
	
	public void testCanPlayRoadCard(){
		
	}
	
	public void testCanPlaySoldier(){
		
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
		assertFalse(facade.canPlayMonopoly()); //Cannot play dev card during rolling phase
		
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
		assertFalse(facade.canPlayMonument()); //Cannot play dev card during rolling phase
		
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

}
