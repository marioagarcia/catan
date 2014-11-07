package server;

import java.io.*;
import java.net.*;
import java.rmi.ServerException;

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
/*		try {
			ServerFacade.initialize();		
		}
		catch (ServerException e) {
			System.out.println(e.getMessage());
			return;
		}*/
		
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

	private HttpHandler userHandler = new UserHandler();
	private HttpHandler gamesHandler = new GamesHandler();
	private HttpHandler gameHandler = new GameHandler();
	private HttpHandler movesHandler = new MovesHandler();
	private HttpHandler utilHandler = new UtilHandler();
	
	public static void main(String[] args) {
		new Server().run();
	}

}
