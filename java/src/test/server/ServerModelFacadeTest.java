package test.server;

import junit.framework.TestCase;
import server.facade.ServerModelFacade;

public class ServerModelFacadeTest extends TestCase {

    ServerModelFacade serverModelFacade;

    public void setUp() throws Exception {
        super.setUp();

        serverModelFacade = ServerModelFacade.getInstance();
    }

    public void testLoginPlayer() throws Exception {

        assertFalse(serverModelFacade.loginPlayer("Mario", "mario"));

        assertTrue(serverModelFacade.registerPlayer("Mario", "mario"));

        assertTrue(serverModelFacade.loginPlayer("Mario", "mario"));

    }

    public void testRegisterPlayer() throws Exception {

    }

    public void testVerifyUser() throws Exception {

    }

    public void testVerifyGame() throws Exception {

    }

    public void testCreateNewGame() throws Exception {

    }

    public void testCanJoinGame() throws Exception {

    }

    public void testJoinGame() throws Exception {

    }

    public void testSaveGame() throws Exception {

    }

    public void testLoadGame() throws Exception {

    }
}