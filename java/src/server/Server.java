package server;

import java.io.*;
import java.net.*;

import server.command.facade.GameCommandFacadeInterface;
import server.command.facade.GamesCommandFacadeInterface;
import server.command.facade.MovesCommandFacadeInterface;
import server.command.facade.UserCommandFacadeInterface;
import server.command.facade.UtilCommandFacadeInterface;
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
import server.handler.GameHandler;
import server.handler.GamesHandler;
import server.handler.MovesHandler;
import server.handler.UserHandler;
import server.handler.UtilHandler;

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
		//userCommandFacade = new UserCommandFacade();
		//gamesCommandFacade = new GamesCommandFacade();
		//gameCommandFacade = new GameCommandFacade();
		//movesCommandFacade = new MovesCommandFacade();
		//utilCommandFacade = new UtilCommandFacade();
	}
	
	public static void main(String[] args) {
		Server s = new Server();
		if(args.length == 0){
			s.setCommandFacade();
			s.run();
		}else if (args[0].equals("mock")){
			s.setMockCommandFacade();
			s.run();
		}else{
			System.out.println("The only valid argument is \"mock\".");
		}
	}

}
