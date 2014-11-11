package server.handler;

import java.io.IOException;

import server.handler.facade.GamesCommandFacadeInterface;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GamesHandler implements HttpHandler{

	private GamesCommandFacadeInterface facade;
	
	public GamesHandler(){
		
	}
	
	public void setGamesCommandFacadeInterface(GamesCommandFacadeInterface facade){
		this.facade = facade;
	}
	
	@Override
	/**
	 * Parses the HttpExchange object to determine where to re-route and 
	 * extracts the parameters object and has it deserialized by the serializer; then 
	 * calls the method on the GamesHandlerFacadeInterface that corresponds to the 
	 * re-route and passes the parameters object into that method
	 */
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
