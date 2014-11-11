package server;

import java.io.*;
import java.net.*;

import server.handler.GameHandler;
import server.handler.GamesHandler;
import server.handler.MovesHandler;
import server.handler.UserHandler;
import server.handler.UtilHandler;
import server.handler.facade.GameCommandFacadeInterface;
import server.handler.facade.GamesCommandFacadeInterface;
import server.handler.facade.MovesCommandFacadeInterface;
import server.handler.facade.UserCommandFacadeInterface;
import server.handler.facade.UtilCommandFacadeInterface;
import server.handler.facade.mock.MockGameCommandFacade;
import server.handler.facade.mock.MockGamesCommandFacade;
import server.handler.facade.mock.MockMovesCommandFacade;
import server.handler.facade.mock.MockUserCommandFacade;
import server.handler.facade.mock.MockUtilCommandFacade;
import server.handler.facade.real.GameCommandFacade;
import server.handler.facade.real.GamesCommandFacade;
import server.handler.facade.real.MovesCommandFacade;
import server.handler.facade.real.UserCommandFacade;
import server.handler.facade.real.UtilCommandFacade;

import com.sun.net.httpserver.*;

public class Server {

	private static final int SERVER_PORT_NUMBER = 8081;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private HttpServer server;
	private UserCommandFacadeInterface userCommandFacade;
	private GamesCommandFacadeInterface gamesCommandFacade;
	private GameCommandFacadeInterface gameCommandFacade;
	private MovesCommandFacadeInterface movesCommandFacade;
	private UtilCommandFacadeInterface utilCommandFacade;
	
	
	private Server() {
		return;
	}
	
	private void setMockCommandFacade(){
		userCommandFacade = new MockUserCommandFacade();
		gamesCommandFacade = new MockGamesCommandFacade();
		gameCommandFacade = new MockGameCommandFacade();
		movesCommandFacade = new MockMovesCommandFacade();
		utilCommandFacade = new MockUtilCommandFacade();
	}
	
	private void setCommandFacade(){
		userCommandFacade = new UserCommandFacade();
		gamesCommandFacade = new GamesCommandFacade();
		gameCommandFacade = new GameCommandFacade();
		movesCommandFacade = new MovesCommandFacade();
		utilCommandFacade = new UtilCommandFacade();
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
		
		server.createContext("/User", userHandler);
		server.createContext("/Games", gamesHandler);
		server.createContext("/Game", gameHandler);
		server.createContext("/Moves", movesHandler);
		server.createContext("/Util", utilHandler);

		server.start();
	}

	private HttpHandler userHandler = new UserHandler(userCommandFacade);
	private HttpHandler gamesHandler = new GamesHandler(gamesCommandFacade);
	private HttpHandler gameHandler = new GameHandler(gameCommandFacade);
	private HttpHandler movesHandler = new MovesHandler(movesCommandFacade);
	private HttpHandler utilHandler = new UtilHandler(utilCommandFacade);
	
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
