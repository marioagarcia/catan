package test;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.CatanColor;
import client.communication.server.ServerProxy;
import client.manager.GameManager;
import client.model.GameInfo;


public class GameManagerTest {

	@Test
	public void test() {
		ServerProxy proxy = new ServerProxy("8081", "localhost");
	//	String param = null;
		
		GameManager manager = new GameManager(proxy);
		
		manager.loginPlayer("Sam", "sam");
		
		/*param = "{"
				 + "\"username\": \"Sam\","
				 + "\"password\": \"sam\""
				 + "}";*/
		
		/*param = "{"
				  + "\"id\": 0,"
				  + "\"color\": \"orange\""
				  + "}";*/
		
		GameInfo g = new GameInfo();
		g.setId(0);
		g.setTitle("My game");
		manager.joinGame(CatanColor.ORANGE, g);
		
		manager.resetFromGameModel(proxy.getGameModel());
		
		assertTrue(manager.getGameList() != null);
		assertTrue(manager.getLocalPlayer() != null);
		assertTrue(manager.getCurrentGame() != null);
		assertTrue(manager.getGameLog() != null);
		assertTrue(manager.getTurnTracker() != null);
		assertTrue(manager.getDiceRoller() != null);
		assertTrue(manager.getBoardMap() != null);
		assertTrue(manager.getDevCardBank() != null);
		assertTrue(manager.getResCardBank() != null);
		assertTrue(manager.getAllPlayers() != null);
	}

}
