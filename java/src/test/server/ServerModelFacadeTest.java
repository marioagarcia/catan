package test.server;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import server.facade.ServerModelFacade;
import server.persistence.PersistenceInterface;
import shared.definitions.CatanColor;

public class ServerModelFacadeTest extends TestCase {

    ServerModelFacade serverModelFacade;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        serverModelFacade = ServerModelFacade.getInstance();
        
        
        File pluginFile = new File("RegisteredPlugins" + File.separator + "RDBMSPlugin.jar");
		
		//If the file (based on the name provided) doesn't exist, tell the user and get the rock out of there
		if(!pluginFile.exists()){
			System.out.println("does not exist.");
			System.exit(0);
		}
		
		PersistenceInterface persistor = null;
		
		try {
			//Create a URL from the file we just created
			URL pluginURL = pluginFile.toURI().toURL();
			
			//Create a class loader from the plugin url
			URLClassLoader classLoader = new URLClassLoader(new URL[] { pluginURL });
			//Load the class represented by pluginName (the parameter passed in)
			classLoader.loadClass("server.persistence.RDBMSPlugin.RDBMSPersistence");
			
			//Load the class (that implements the PeristenceInterface) and create a PersistenceInterface with it
			persistor = (PersistenceInterface)Class.forName("server.persistence.RDBMSPlugin.RDBMSPersistence", true, classLoader).newInstance();
			
			persistor.startTransaction();
			persistor.resetAllPersistence();
			persistor.endTransaction();
			
			serverModelFacade.configorPersistor(persistor, 5);
			
			serverModelFacade.createNewGame("Blah", true, true, true);
			
		} catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    	
    	serverModelFacade.registerPlayer("Sam", "sam");
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

    	assertFalse(serverModelFacade.verifyUser("Sam", "badPassword", 0));
    	assertFalse(serverModelFacade.verifyUser("Sam", "sam", 15));

    }

    @Test
    public void testVerifyGame() throws Exception {
    	
    	assertFalse(serverModelFacade.verifyGame(19, 0));
    	assertFalse(serverModelFacade.verifyGame(0, 23));
    }
}