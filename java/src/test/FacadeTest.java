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
		facade.registerPlayer("a", "a");
		facade.loginPlayer("a", "a");
	}
	
	@Test
	public void testCanJoinGame() {
		GameInfo gameInfo = new GameInfo();
		ArrayList<PlayerInfo> playerList = createPlayers(2);
		gameInfo.setGameInfo("TestGame", 0, playerList);
		
		assertTrue(facade.canJoinGame(CatanColor.PUCE, gameInfo));
		
		playerList = createPlayers(4);
		gameInfo.setGameInfo("TestGame", 173, playerList);
		
		//assertFalse(facade.canJoinGame(CatanColor.PUCE, gameInfo));
	}
	
	public void testSendChat(){
		//Doesn't exist
	}
	
	public void testCanAcceptTrade(){
		
		GameData gameData = new GameData();
		gameData.setDomesticTrade(null);
		System.out.println(gameData);
		
		
		DomesticTrade trade = new DomesticTrade(1, 2, new ResourceList(0, 0, 0, 0, 0));
		facade.canAcceptTrade(trade);
	}
	
	public void testCanDiscardCards(){
		
	}
	
	public void testCanRollNumber(){
		
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
	
	public void testCanFinishTurn(){
		
	}
	
	public void testCanBuyDevCard(){
		
	}
	
	public void testCanPlayYearOfPlenty(){
		
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
