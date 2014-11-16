package server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import server.command.Login;
import server.command.Register;
import server.command.facade.UserCommandFacadeInterface;
import server.command.facade.real.UserCommandFacade;
import server.facade.ServerModelFacade;
import server.serialization.ServerModelSerializer;
import shared.serialization.parameters.CredentialsParameters;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserHandler implements HttpHandler{

	private UserCommandFacadeInterface facade;
	private ServerModelSerializer serializer;
	
	public UserHandler(){
		serializer = new ServerModelSerializer();
	}
	
	public void setUserCommandFacadeInterface(UserCommandFacadeInterface facade){
		this.facade = facade;
	}
	
	@Override
	/**
	 * Parses the HttpExchange object to determine where to re-route and 
	 * extracts the parameters object and has it deserialized by the serializer; then 
	 * calls the method on the UserHandlerFacadeInterface that corresponds to the 
	 * re-route and passes the parameters object into that method
	 */
	public void handle(HttpExchange exchange) throws IOException {
System.out.println("Entering User handler");
		StringBuilder request = new StringBuilder();
		//Read the request into a buffered reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
		
		//Transfer what was read into the buffered reader to a stringbuilder
		String line;
		while((line = reader.readLine()) != null){
			request.append(line);
		}
		//Put the stringbuilder into a string
		String jsonString = request.toString();
System.out.println("User handler deserialization");
		//Send the json to the deserializer and get the credentials back
		CredentialsParameters credentials = serializer.deserializeCredentials(jsonString);

		String uri = exchange.getRequestURI().toString();
		Boolean successful = false;
		if(uri.equals("/user/login")){
System.out.println("\tLogin URI");
			successful = facade.login(credentials);
		}else if(uri.equals("/user/register")){
System.out.println("\tRegister URI");
			successful = facade.register(credentials);
		}else{
			System.out.println("\tUser URI not recognized: " + uri);
		}
		
		String response;
		int responseCode;

		if(successful){
System.out.println("UserHandler success");
			response = "Success";
			responseCode = 200;
		}else{
System.out.println("Userhandler failure");
			response = "Failed to login - bad username or password.";
			responseCode = 400;	
		}

		//Get the id of the player who registered/logged in
		int id = ServerModelFacade.getInstance().getPlayerId(credentials.getUsername());
System.out.println("Userhandler cookie generation");
		//Create a cookie with the user's name, password, and id
		String cookie = CookieParser.generateLoginCookie(credentials.getUsername(), credentials.getPassword(), id);
		ArrayList<String> cookieList = new ArrayList<String>();
		cookieList.add(cookie);
		
		//Put the cookie in the response headers and send the response headers with the response and response code
		exchange.getResponseHeaders().put("Set-Cookie", cookieList);
		exchange.sendResponseHeaders(responseCode, response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
System.out.println("Exiting user handler\n");
	}
}
