package test;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.CatanColor;
import client.communication.server.ServerMoxy;
import client.communication.server.ServerProxyInterface;
import client.manager.GameManager;
import client.model.GameInfo;


public class GameManagerTest {

	@Test
	public void test() {
		ServerProxyInterface proxy = new ServerMoxy();
		
		GameManager manager = new GameManager(proxy);
		
		manager.loginPlayer("Sam", "sam");
		
		GameInfo g = new GameInfo();
		g.setId(0);
		g.setTitle("My game");
		manager.joinGame(CatanColor.ORANGE, g);
		
		manager.resetFromGameModel(proxy.getGameModel(true));
		
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
