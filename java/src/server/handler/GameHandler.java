package server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import server.command.facade.GameCommandFacadeInterface;
import server.serialization.ServerModelSerializer;
import shared.model.manager.GameData;
import shared.serialization.parameters.AIRequestParameters;
import shared.serialization.parameters.CredentialsParameters;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GameHandler implements HttpHandler{
	
	private GameCommandFacadeInterface facade;
	private ServerModelSerializer serializer;
	
	public GameHandler(){
		serializer = new ServerModelSerializer();
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
	public void handle(HttpExchange exchange) throws IOException {
		String response;
		int responseCode;
		
		String uri = exchange.getRequestURI().toString();
		if(uri.equals("/game/model")){
			// @ TODO Must retrieve the game model
			//	But in the meantime...
			GameData gameData = facade.getModel();
			if(gameData != null){
				response = serializer.serializeGameModel(gameData);
				responseCode = 200;
			}else{
				response = "Failed to get game model.";
				responseCode = 400;
			}
		}else if(uri.equals("/game/reset")){
			 GameData gameData = facade.reset();
			 if(gameData != null){
				 response = serializer.serializeGameModel(gameData);
				 responseCode = 200;
			 }else{
				 response = "Failed to reset game.";
				 responseCode = 400;
			 }			 
		}else if(uri.equals("/game/commands")){
			response = "";
			responseCode = -1000;
		}else if(uri.equals("game/listAI")){
			String[] aiList = facade.getListAI();
			if(aiList != null){
				response = serializer.serializeGetListAI(aiList);
				responseCode = 200;
			}else{
				response = "Failed to get AI list.";
				responseCode = 400;
			}
		}else if(uri.equals("game/addAI")){
			BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
			String jsonString = getJsonString(reader);
			AIRequestParameters params = serializer.deserializeAIRequest(jsonString);
			Boolean successful = facade.AddAI(params.getAIType());
			if(successful){
				response = "Success";
				responseCode = 200;
			}else{
				response = "Failed to add AI.";
				responseCode = 400;
			}
		}else{
			System.out.println("URI not recognized");
			response = "Game URI not recognized.";
			responseCode = 400;
		}
		
		exchange.sendResponseHeaders(responseCode, response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
		
	}
	
	private String getJsonString(BufferedReader reader) throws IOException{
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
