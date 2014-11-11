package server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import server.command.facade.GamesCommandFacadeInterface;
import server.serialization.ServerModelSerializer;
import shared.model.GameInfo;
import shared.model.manager.GameList;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.JoinGameParameters;
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
		String response;
		int responseCode;
		Boolean successful;
		String jsonString = getJsonString(exchange.getRequestBody());
		
		String uri = exchange.getRequestURI().toString();
		if(uri.equals("/games/list")){
			GameList gameList = facade.getGamesList();
			if(gameList != null){
				response = serializer.serializeGamesList(gameList.getGameList());
				responseCode = 200;
			}else{
				response = "Failed to get the game list.";
				responseCode = 400;
			}
		}else if(uri.equals("/games/create")){
			CreateGameRequestParameters params = serializer.deserializeCreateGameRequest(jsonString);
			GameInfo gameInfo = facade.createGame(params);
			if(gameInfo != null){
				response = serializer.serializeGameInfo(gameInfo);
				responseCode = 200;
			}else{
				response = "Failed to create game.";
				responseCode = 400;
			}
		}else if(uri.equals("/games/join")){
			JoinGameParameters params = serializer.deserializeJoinGameRequest(jsonString);
			successful = facade.joinGame(params);
			if(successful){
				response = "Success";
				responseCode = 200;
			}else{
				response = "Failed to join game.";
				responseCode = 400;
			}
		}else if(uri.equals("/games/save")){
			SaveGameParameters params = serializer.deserializeSaveGameRequest(jsonString);
			successful = facade.saveGame(params);
			if(successful){
				response = "Success";
				responseCode = 200;
			}else{
				response = "Failed to save game.";
				responseCode = 400;
			}
		}else if(uri.equals("/games/load")){
			// @ TODO implement this
			response = "";
			responseCode = 99999;
			
		}else{
			response = "Games URI not recognized.";
			responseCode = 400;
		}
		
		exchange.sendResponseHeaders(responseCode, response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
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
