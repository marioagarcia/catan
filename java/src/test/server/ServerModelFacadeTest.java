package test.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import server.facade.ServerModelFacade;
import shared.definitions.CatanColor;

public class ServerModelFacadeTest extends TestCase {

    ServerModelFacade serverModelFacade;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        serverModelFacade = ServerModelFacade.getInstance();
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        serverModelFacade = null;
    }

    @Test
    public void testLoginPlayer() throws Exception {

        assertFalse(serverModelFacade.loginPlayer("Mario", "mario"));

        assertTrue(serverModelFacade.registerPlayer("Mario", "mario"));

        assertTrue(serverModelFacade.loginPlayer("Mario", "mario"));

    }

    @Test
    public void testRegisterPlayer() throws Exception {

        assertTrue(serverModelFacade.registerPlayer("Mario0", "mario"));

        assertFalse(serverModelFacade.registerPlayer("Mario0", "mario"));

        assertTrue(serverModelFacade.registerPlayer("Mario1", "mario"));
        assertTrue(serverModelFacade.registerPlayer("Mario2", "mario"));
        assertTrue(serverModelFacade.registerPlayer("Mario3", "mario"));

    }

    @Test
    public void testCreateNewGame() throws Exception {

    	int before_size = serverModelFacade.getGameList().size();
    	
        assertTrue(serverModelFacade.createNewGame("test1", true, true, true));

        assertTrue(serverModelFacade.createNewGame("test2", false, true, true));

        assertTrue(serverModelFacade.createNewGame("test3", true, false, true));

        assertTrue(serverModelFacade.createNewGame("test4", true, true, false));
        
        int after_size = serverModelFacade.getGameList().size();

        assertEquals(before_size + 4, after_size);

        assertFalse(serverModelFacade.createNewGame("test1", true, true,true));

    }

    @Test
    public void testCanJoinGame() throws Exception {
    	
    	assertTrue(serverModelFacade.loginPlayer("Sam", "sam"));
    	assertFalse(serverModelFacade.joinGame(300, 0, CatanColor.ORANGE));
    	assertTrue(serverModelFacade.joinGame(0, 0, CatanColor.ORANGE));
    }

    @Test
    public void testJoinGame() throws Exception {
    	
    	assertFalse(serverModelFacade.joinGame(300, 0, CatanColor.ORANGE));
    	assertTrue(serverModelFacade.joinGame(0, 0, CatanColor.ORANGE));

    }

    @Test
    public void testVerifyUser() throws Exception {

    	assertTrue(serverModelFacade.verifyUser("Sam", "sam", 0));
    	assertFalse(serverModelFacade.verifyUser("Sam", "badPassword", 0));
    	assertFalse(serverModelFacade.verifyUser("Sam", "sam", 15));

    }

    @Test
    public void testVerifyGame() throws Exception {
    	
    	assertTrue(serverModelFacade.verifyGame(0, 0));
    	assertFalse(serverModelFacade.verifyGame(19, 0));
    	assertFalse(serverModelFacade.verifyGame(0, 23));
    }
}