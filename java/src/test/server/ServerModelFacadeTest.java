package test.server;

import junit.framework.TestCase;
import server.facade.ServerModelFacade;

public class ServerModelFacadeTest extends TestCase {

    ServerModelFacade serverModelFacade;

    public void setUp() throws Exception {
        super.setUp();

        serverModelFacade = ServerModelFacade.getInstance();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        serverModelFacade = null;
    }

    public void testLoginPlayer() throws Exception {

        assertFalse(serverModelFacade.loginPlayer("Mario", "mario"));

        assertTrue(serverModelFacade.registerPlayer("Mario", "mario"));

        assertTrue(serverModelFacade.loginPlayer("Mario", "mario"));

    }

    public void testRegisterPlayer() throws Exception {

        assertTrue(serverModelFacade.registerPlayer("Mario0", "mario"));

        assertFalse(serverModelFacade.registerPlayer("Mario0", "mario"));

        assertTrue(serverModelFacade.registerPlayer("Mario1", "mario"));
        assertTrue(serverModelFacade.registerPlayer("Mario2", "mario"));
        assertTrue(serverModelFacade.registerPlayer("Mario3", "mario"));

    }

    public void testCreateNewGame() throws Exception {

        assertTrue(serverModelFacade.createNewGame("test1", true, true, true));

        assertTrue(serverModelFacade.createNewGame("test2", false, true, true));

        assertTrue(serverModelFacade.createNewGame("test3", true, false, true));

        assertTrue(serverModelFacade.createNewGame("test4", true, true, false));

        assertEquals(4, serverModelFacade.getGameList().size());

        assertFalse(serverModelFacade.createNewGame("test1", true, true,true));

    }

    public void testSaveGame() throws Exception {

        assertTrue(serverModelFacade.createNewGame("test1", true, true, true));

        assertTrue(serverModelFacade.createNewGame("test2", false, true, true));

        assertTrue(serverModelFacade.createNewGame("test3", true, false, true));

        //save game
        assertTrue(serverModelFacade.saveGame(2));

        serverModelFacade.loadGames();

        assertEquals(serverModelFacade.getGameList().size(), 5);

    }

    public void testLoadGame() throws Exception {

    }

    public void testCanJoinGame() throws Exception {

    }

    public void testJoinGame() throws Exception {


    }

    public void testVerifyUser() throws Exception {



    }

    public void testVerifyGame() throws Exception {

    }
}