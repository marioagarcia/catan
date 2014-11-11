package server.handler;

import java.io.IOException;

import server.command.facade.GameCommandFacadeInterface;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GameHandler implements HttpHandler{
	
	private GameCommandFacadeInterface facade;
	
	public GameHandler(){
		
	}
	
	public void setGameCommandFacadeInterface(GameCommandFacadeInterface facade){
		this.facade = facade;
	}

	@Override
	/**
	 * Parses the HttpExchange object to determine where to re-route and 
	 * extracts the parameters object and has it deserialized by the serializer; then 
	 * calls the method on the GameHandlerFacadeInterface that corresponds to the 
	 * re-route and passes the parameters object into that method
	 */
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
