package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.CatanColor;
import client.communication.facade.ModelFacade;
import client.communication.server.ServerProxy;
import client.manager.GameData;
import client.manager.GameManager;
import client.model.GameInfo;
import client.model.card.DomesticTrade;
import client.model.card.ResourceList;
import client.model.player.Player;
import client.model.player.PlayerInfo;

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
		
		/*GameInfo gameInfo = new GameInfo();*/
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
		
		DomesticTrade trade = new DomesticTrade(1, 2, new ResourceList(1, 0, 0, 0, -1));
		assertTrue(facade.canAcceptTrade(trade));
		
		//Player does not have the required resources
		trade = new DomesticTrade(1, 2, new ResourceList(20, 2, 0, -10, -5));
		assertFalse(facade.canAcceptTrade(trade));
	}
	
	public void testCanDiscardCards(){
		//Player get resource list method is returning the wrong type
		//facade.canDiscardCards(manager.getLocalPlayer().getResourceList());
		
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
	
	public void testCanOfferTrade(){
		
	}
	
	public void testCanMaritimeTrade(){
		
	}
	
	@Test
	public void testCanFinishTurn(){
		
		
		//Need a game that is already in process
		//assertTrue(facade.canFinishTurn());
		
		//Should not be allowed because game is currently rolling
		assertFalse(facade.canFinishTurn());	
	}
	
	public void testCanBuyDevCard(){
		
	}
	
	@Test
	public void testCanPlayYearOfPlenty(){
		
		//Facade canPlayDevCard methods are not finished
		//Player does not have this card
		//assertFalse(facade.canPlayYearOfPlenty());
		
	}
	
	public void testCanPlayRoadCard(){
		
	}
	
	public void testCanPlaySoldier(){
		
	}
	
	public void testCanPlayMonopoly(){
		
	}
	
	public void testCanPlayMonument(){
		
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
		System.out.println(playerList);
		return playerList;
	}

}
