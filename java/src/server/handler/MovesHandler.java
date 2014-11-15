package server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import server.command.facade.MovesCommandFacadeInterface;
import server.facade.ServerModelFacade;
import server.serialization.ServerModelSerializer;
import shared.model.manager.GameData;
import shared.serialization.parameters.AcceptTradeParameters;
import shared.serialization.parameters.BuildCityParameters;
import shared.serialization.parameters.BuildRoadParameters;
import shared.serialization.parameters.BuildSettlementParameters;
import shared.serialization.parameters.BuyDevCardParameters;
import shared.serialization.parameters.DiscardCardsParameters;
import shared.serialization.parameters.FinishTurnParameters;
import shared.serialization.parameters.MaritimeTradeParameters;
import shared.serialization.parameters.MonopolyParameters;
import shared.serialization.parameters.MonumentParameters;
import shared.serialization.parameters.OfferTradeParameters;
import shared.serialization.parameters.RoadBuildingParameters;
import shared.serialization.parameters.RobPlayerParameters;
import shared.serialization.parameters.RollNumberParameters;
import shared.serialization.parameters.SendChatParameters;
import shared.serialization.parameters.SoldierParameters;
import shared.serialization.parameters.YearOfPlentyParameters;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MovesHandler implements HttpHandler{

	private MovesCommandFacadeInterface facade;
	private ServerModelSerializer serializer;
	
	public MovesHandler(){
		serializer = new ServerModelSerializer();
	}
	
	public void setMovesCommandFacadeInterface(MovesCommandFacadeInterface facade){
		this.facade = facade;
	}
	
	@Override
	/**
	 * Parses the HttpExchange object to determine where to re-route and 
	 * extracts the parameters object and has it deserialized by the serializer; then 
	 * calls the method on the MovesHandlerFacadeInterface that corresponds to the 
	 * re-route and passes the parameters object into that method
	 */
	public void handle(HttpExchange exchange) throws IOException {

		//Get the cookie from the request headers
		String cookie = (String)exchange.getRequestHeaders().values().toArray()[0];
System.out.println(cookie);
		//Parse the cookie
		CookieParser cookieParser = new CookieParser(cookie);
System.out.println("Got to this point2");
		//Validate the user
		if(!cookieParser.isValidCookie()){
			//If the user is not valid, send an invalid user response
			sendInvalidUserResponse(exchange);
			return;
		}
		
		//Get the game id from the cookie parser; all of the moves require it
		int gameId = cookieParser.getGameID();
		//This boolean will represent the success or failure of the move
		boolean successful = false;
		
		//Get the request and put it in a string
		String jsonString = getJsonString(exchange.getRequestBody());
		
		//Get the URI from the request
		String uri = exchange.getRequestURI().toString();
		
		//On each switch: Deserialize the json string into the appropriate parameters object, then call the appropriate move
		//passing in the parameters object
		Gson gson = new Gson();
		switch(uri){
			case "/moves/sendChat":
				successful = facade.sendChat(gson.fromJson(jsonString, SendChatParameters.class), gameId);
				break;
			case "/moves/acceptTrade":
				successful = facade.acceptTrade(gson.fromJson(jsonString, AcceptTradeParameters.class), gameId);
				break;
			case "/moves/discardCards":
				successful = facade.discardCards(gson.fromJson(jsonString, DiscardCardsParameters.class), gameId);
				break;
			case "/moves/rollNumber":
				successful = facade.rollNumber(gson.fromJson(jsonString, RollNumberParameters.class), gameId);
				break;
			case "/moves/buildRoad":
				successful = facade.buildRoad(gson.fromJson(jsonString, BuildRoadParameters.class), gameId);
				break;
			case "/moves/buildSettlement":
				successful = facade.buildSettlement(gson.fromJson(jsonString, BuildSettlementParameters.class), gameId);
				break;
			case "/moves/buildCity":
				successful = facade.buildCity(gson.fromJson(jsonString, BuildCityParameters.class), gameId);
				break;
			case "/moves/offerTrade":
				successful = facade.offerTrade(gson.fromJson(jsonString, OfferTradeParameters.class), gameId);
				break;
			case "/moves/maritimeTrade":
				successful = facade.maritimeTrade(gson.fromJson(jsonString, MaritimeTradeParameters.class), gameId);
				break;
			case "/moves/finishTurn":
				successful = facade.finishTurn(gson.fromJson(jsonString, FinishTurnParameters.class), gameId);
				break;
			case "/moves/buyDevCard":
				successful = facade.buyDevCard(gson.fromJson(jsonString, BuyDevCardParameters.class), gameId);
				break;
			case "/moves/Year_of_Plenty":
				successful = facade.playYearOfPlenty(gson.fromJson(jsonString, YearOfPlentyParameters.class), gameId);
				break;
			case "/moves/Road_Building":
				successful = facade.playRoadBuilding(gson.fromJson(jsonString, RoadBuildingParameters.class), gameId);
				break;
			case "/moves/Soldier":
				successful = facade.playSoldier(gson.fromJson(jsonString, SoldierParameters.class), gameId);
				break;
			case "/moves/Monopoly":
				successful = facade.playMonopoly(gson.fromJson(jsonString, MonopolyParameters.class), gameId);
				break;
			case "/moves/Monument":
				successful = facade.playMonument(gson.fromJson(jsonString, MonumentParameters.class), gameId);
				break;
			case "/moves/robPlayer":
				successful = facade.robPlayer(gson.fromJson(jsonString, RobPlayerParameters.class), gameId);
				break;
		}
		
		String response;
		int responseCode;
		if(successful){
			//If the move was successful, get the updated game model
			GameData gameData = ServerModelFacade.getInstance().getGameModel(gameId);
			response = serializer.serializeGameModel(gameData);
			responseCode = 200;
		}else{
			response = "Failed to perform move.";
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
	
	private void sendInvalidUserResponse(HttpExchange exchange) throws IOException{
		String response = "Invalid user.";
		exchange.sendResponseHeaders(400, response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
}
