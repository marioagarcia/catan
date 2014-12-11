package server;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import server.command.facade.mock.MockGameCommandFacade;
import server.command.facade.mock.MockGamesCommandFacade;
import server.command.facade.mock.MockMovesCommandFacade;
import server.command.facade.mock.MockUserCommandFacade;
import server.command.facade.mock.MockUtilCommandFacade;
import server.command.facade.real.GameCommandFacade;
import server.command.facade.real.GamesCommandFacade;
import server.command.facade.real.MovesCommandFacade;
import server.command.facade.real.UserCommandFacade;
import server.command.facade.real.UtilCommandFacade;
import server.facade.ServerModelFacade;
import server.handler.GameHandler;
import server.handler.GamesHandler;
import server.handler.MovesHandler;
import server.handler.UserHandler;
import server.handler.UtilHandler;
import server.persistence.PersistenceInterface;

import com.sun.net.httpserver.*;

public class Server {

	private static final int SERVER_PORT_NUMBER = 8081;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private HttpServer server;
	
	private Server() {
		return;
	}
	
	private void run() {
		try {
			server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER),
											MAX_WAITING_CONNECTIONS);
		} 
		catch (IOException e) {
			System.out.println("Error creating the server: " + e.getMessage());
			return;
		}

		server.setExecutor(null); // use the default executor
		
		server.createContext("/user", userHandler);
		server.createContext("/games", gamesHandler);
		server.createContext("/game", gameHandler);
		server.createContext("/moves", movesHandler);
		server.createContext("/util", utilHandler);

		server.start();
	}

	private UserHandler userHandler = new UserHandler();
	private GamesHandler gamesHandler = new GamesHandler();
	private GameHandler gameHandler = new GameHandler();
	private MovesHandler movesHandler = new MovesHandler();
	private UtilHandler utilHandler = new UtilHandler();
	
	private void setMockCommandFacade(){
		userHandler.setUserCommandFacadeInterface(new MockUserCommandFacade());
		gamesHandler.setGamesCommandFacadeInterface(new MockGamesCommandFacade());
		gameHandler.setGameCommandFacadeInterface(new MockGameCommandFacade());
		movesHandler.setMovesCommandFacadeInterface(new MockMovesCommandFacade());
		utilHandler.setUtilCommandHandler(new MockUtilCommandFacade());
	}
	
	private void setCommandFacade(){
		userHandler.setUserCommandFacadeInterface(new UserCommandFacade());
		gamesHandler.setGamesCommandFacadeInterface(new GamesCommandFacade());
		gameHandler.setGameCommandFacadeInterface(new GameCommandFacade());
		movesHandler.setMovesCommandFacadeInterface(new MovesCommandFacade());
		utilHandler.setUtilCommandHandler(new UtilCommandFacade());
	}
	//plugin N clear//
	public static void main(String[] args) {
	
		Server s = new Server();
		if(args.length == 0){
			s.setCommandFacade();
			s.run();
		}else if (args[0].equals("mock")){
			s.setMockCommandFacade();
			s.run();
		}else if (args.length >= 2){

            Map<String, String> config_data = new HashMap<String, String> ();

            String relative_path = ".." + File.separator + ".." + File.separator;
            BufferedReader bufferedReader = null;
            try
            {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("RegisteredPlugins" + File.separator + "config.catan"), Charset.forName("UTF-8")));
                String line;
                while((line = bufferedReader.readLine()) != null)
                {
                    String[] data = line.split(" ");

                    if(data.length != 2)
                        throw new Exception("you suck");

                    config_data.put(data[0], data[1]);
                }
                
                bufferedReader.close();
            } 
            catch (Exception e ) { 
            	e.printStackTrace(); 
            	System.exit(0);
            }


            if(!config_data.containsKey(args[0])){
                System.out.println("invalid plugin name passed to Server. Verify that your plugin has been registered in the config.catan file");
                System.exit(0);
            }

			String pluginName = config_data.get(args[0]);
			
			//File pluginFile = new File("Plugins/" + "server.TestPlugin.jar");
			
			//Create a file from the name that was provided
			File pluginFile = new File("RegisteredPlugins" + File.separator + args[0]);
			
			//If the file (based on the name provided) doesn't exist, tell the user and get the rock out of there
			if(!pluginFile.exists()){
				System.out.println(args[0] + " does not exist.");
				System.exit(0);
			}
			
			PersistenceInterface persistor = null;
			
			try {
				//Create a URL from the file we just created
				URL pluginURL = pluginFile.toURI().toURL();
				
				//Create a class loader from the plugin url
				URLClassLoader classLoader = new URLClassLoader(new URL[] { pluginURL });
				//Load the class represented by pluginName (the parameter passed in)
				classLoader.loadClass(pluginName);
				
				//Load the class (that implements the PeristenceInterface) and create a PersistenceInterface with it
				persistor = (PersistenceInterface)Class.forName(pluginName, true, classLoader).newInstance();
				
				if(args.length == 3){
					persistor.resetAllPersistence();
				}
				
			} catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Parse args[1] into an int; Grab the N that was passed in as a parameter
			int deltaThreshold = Integer.parseInt(args[1]);
			
			//Call configorPersistor on the server facade and pass in the PersistenceInterface object and int
			ServerModelFacade.getInstance().configorPersistor(persistor, deltaThreshold);
			
			s.setCommandFacade();
			s.run();
			
		}else{
			System.out.println("Error:  Valid arguments are 'mock' or name of jar followed by a space and an integer.");
		}
	}
}
