package server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import server.command.facade.GamesCommandFacadeInterface;
import server.serialization.ServerModelSerializer;
import shared.model.GameInfo;
import shared.model.manager.GameData;
import shared.model.manager.GameList;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.JoinGameParameters;
import shared.serialization.parameters.LoadGameRequestParameters;
import shared.serialization.parameters.SaveGameParameters;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GamesHandler implements HttpHandler{

	private GamesCommandFacadeInterface facade;
	private ServerModelSerializer serializer;
	
	public GamesHandler(){
		serializer = new ServerModelSerializer();
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
	public void handle(HttpExchange exchange) throws IOException {
//System.out.println("Entering Games handler");
		String response;
		int responseCode;
		Boolean successful;
		
		int gameId = -1;
		String jsonString = getJsonString(exchange.getRequestBody());
		
		
		String uri = exchange.getRequestURI().toString();
		if(uri.equals("/games/list")){
			GameList gameList = facade.getGamesList();
			if(gameList != null){
				response = serializer.serializeGamesList(gameList.getGameList());
				responseCode = 200;
			}else{
System.out.println("\tGamesList failure");
				response = "Failed to get the game list.";
				responseCode = 400;
			}
		}else if(uri.equals("/games/create")){
System.out.println("\tCreate Game URI");
			CreateGameRequestParameters params = serializer.deserializeCreateGameRequest(jsonString);
			GameInfo gameInfo = facade.createGame(params);
			if(gameInfo != null){
System.out.println("\tCreate Game success");
				response = serializer.serializeGameInfo(gameInfo);
				responseCode = 200;
			}else{
System.out.println("\tCreate Game failure");
				response = "Failed to create game.";
				responseCode = 400;
			}
		}else if(uri.equals("/games/join")){
System.out.println("\tJoin Game URI");
			//Get the cookie from the request
			String cookie = (String)exchange.getRequestHeaders().values().toArray()[0].toString();

			CookieParser cookieParser = new CookieParser(cookie);		
System.out.println("\tJoin Game deserialization");
			//Deserialize the json string into a JoinGameParameters object
			JoinGameParameters params = serializer.deserializeJoinGameRequest(jsonString);
			successful = facade.joinGame(params, cookieParser.getPlayerID());
			if(successful){
System.out.println("\tJoin Game success");
				//If join game was successful, set the gameId
				gameId = params.getId();
				response = "Success";
				responseCode = 200;
			}else{
System.out.println("\tJoin Game failure");				
				response = "Failed to join game.";
				responseCode = 400;
			}
		}else if(uri.equals("/games/save")){
System.out.println("\tSave Game URI");
System.out.println("\tSave Game deserialization");
			SaveGameParameters params = serializer.deserializeSaveGameRequest(jsonString);
			successful = facade.saveGame(params);
			if(successful){
System.out.println("\tSave Game success");
				response = "Success";
				responseCode = 200;
			}else{
System.out.println("\tSave Game failure");
				response = "Failed to save game.";
				responseCode = 400;
			}
		}else if(uri.equals("/games/load")){
System.out.println("\tLoad Game URI");
System.out.println("\tLoad Game deserialization");
			LoadGameRequestParameters params = serializer.deserializeLoadGameRequest(jsonString);
			GameData gameData = facade.loadGame(params);
			if(gameData != null){
System.out.println("\tLoad Game success");
System.out.println("\tLoad Game serialization");
				response = serializer.serializeGameModel(gameData);
				responseCode = 200;
			}else{
System.out.println("\tLoad Game failure");
				response = "Failed to load game.";
				responseCode = 400;
			}
		}else{
System.out.println("\tGames URI not recognized: " + uri);
			response = "Games URI not recognized.";
			responseCode = 400;
		}
		
		if(gameId != -1){
			//If gameID is not -1 then it was a join game request
System.out.println("\tJoin Game cookie generation");
			//Set the cookie based on the id of the game that was joined
			String cookie = CookieParser.generateJoinCookie(gameId);

			ArrayList<String> cookieList = new ArrayList<String>();
			cookieList.add(cookie);
		
			//Put the cookie in the response headers and send the response headers with the response and response code
			exchange.getResponseHeaders().put("Set-Cookie", cookieList);
		}
			
		exchange.sendResponseHeaders(responseCode, response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
//System.out.println("Exiting Games handler");
	}
	
	private String getJsonString(InputStream inputStream) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		StringBuilder request = new StringBuilder();
		//Transfer the stuff from the BufferedReader into a stringBuilder
		String line;
		while((line = reader.readLine()) != null){
			request.append(line);
		}
		//The StringBuilder is now the json from the exchange, so return it's string
		return request.toString();
	}

}
