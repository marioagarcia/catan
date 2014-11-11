package server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import server.handler.facade.UserCommandFacadeInterface;
import server.handler.facade.real.UserCommandFacade;
import shared.serialization.parameters.CredentialsParameters;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserHandler implements HttpHandler{

	private UserCommandFacadeInterface facade;
	
	public UserHandler(){
		
	}
	
	@Override
	/**
	 * Parses the HttpExchange object to determine where to re-route and 
	 * extracts the parameters object and has it deserialized by the serializer; then 
	 * calls the method on the UserHandlerFacadeInterface that corresponds to the 
	 * re-route and passes the parameters object into that method
	 */
	public void handle(HttpExchange exchange) throws IOException {
		StringBuilder request = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
		
		String line;
		while((line = reader.readLine()) != null){
			request.append(line);
		}
		String jsonString = request.toString();

		Gson gson = new Gson();
		CredentialsParameters credentials = gson.fromJson(jsonString, CredentialsParameters.class);

		String uri = exchange.getRequestURI().toString();
		Boolean successful = false;
		if(uri.equals("/user/login")){
			successful = facade.login(credentials);
		}else if(uri.equals("/user/register")){
			successful = facade.register(credentials);
		}else{
			System.out.println("URI not recognized");
		}
		
		if(successful){
			// @ TODO Send a successful response
		}else{
			// @ TODO Send an unsuccessful response
		}
		
	}

	public void setUserCommandFacadeInterface(UserCommandFacadeInterface facade){
		this.facade = facade;
	}
}
