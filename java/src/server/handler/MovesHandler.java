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
	public void handle(HttpExchange exchange){
System.out.println("Entering Moves Handler");
		//Get the cookie from the request headers
		String cookie = (String)exchange.getRequestHeaders().values().toArray()[0].toString();

		//Parse the cookie
		CookieParser cookieParser = new CookieParser(cookie);

		//Validate the user
		if(!cookieParser.isValidCookie()){
System.out.println("Invalid Moves Cookie");
			//If the user is not valid, send an invalid user response
			try {
				sendInvalidUserResponse(exchange);
			} catch (IOException e) {
				System.out.println("Moves Handler: sendInvalidUserResponse");
				e.printStackTrace();
			}
			return;
		}
		
		//Get the game id from the cookie parser; all of the moves require it
		int gameId = cookieParser.getGameID();
		//This boolean will represent the success or failure of the move
		boolean successful = false;
		
		//Get the request and put it in a string
		String jsonString = "";
		try {
			jsonString = getJsonString(exchange.getRequestBody());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Get the URI from the request
		String uri = exchange.getRequestURI().toString();
		
		//On each switch: Deserialize the json string into the appropriate parameters object, then call the appropriate move
		//passing in the parameters object
		Gson gson = new Gson();
		switch(uri){
			case "/moves/sendChat":
				System.out.println("\tSend Chat URI");
				successful = facade.sendChat(gson.fromJson(jsonString, SendChatParameters.class), gameId);
				break;
			case "/moves/acceptTrade":
				System.out.println("\tAccept Trade URI");
				successful = facade.acceptTrade(gson.fromJson(jsonString, AcceptTradeParameters.class), gameId);
				break;
			case "/moves/discardCards":
				System.out.println("\tDiscard Cards URI");
				successful = facade.discardCards(gson.fromJson(jsonString, DiscardCardsParameters.class), gameId);
				break;
			case "/moves/rollNumber":
				System.out.println("\tRoll Number URI");
				successful = facade.rollNumber(gson.fromJson(jsonString, RollNumberParameters.class), gameId);
				break;
			case "/moves/buildRoad":
				System.out.println("\tBuild Road URI");
				successful = facade.buildRoad(gson.fromJson(jsonString, BuildRoadParameters.class), gameId);
				break;
			case "/moves/buildSettlement":
				System.out.println("\tBuild Settlement URI");
				try{
				successful = facade.buildSettlement(gson.fromJson(jsonString, BuildSettlementParameters.class), gameId);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case "/moves/buildCity":
				System.out.println("\tBuild City URI");
				successful = facade.buildCity(gson.fromJson(jsonString, BuildCityParameters.class), gameId);
				break;
			case "/moves/offerTrade":
				System.out.println("\tOffer Trade URI");
				successful = facade.offerTrade(gson.fromJson(jsonString, OfferTradeParameters.class), gameId);
				break;
			case "/moves/maritimeTrade":
				System.out.println("\tMaritime Trade URI");
				successful = facade.maritimeTrade(gson.fromJson(jsonString, MaritimeTradeParameters.class), gameId);
				break;
			case "/moves/finishTurn":
				System.out.println("\tFinish Turn URI");
				successful = facade.finishTurn(gson.fromJson(jsonString, FinishTurnParameters.class), gameId);
				break;
			case "/moves/buyDevCard":
				System.out.println("\tBuy Dev Card URI");
				successful = facade.buyDevCard(gson.fromJson(jsonString, BuyDevCardParameters.class), gameId);
				break;
			case "/moves/Year_of_Plenty":
				System.out.println("\tYear Of Plenty URI");
				successful = facade.playYearOfPlenty(gson.fromJson(jsonString, YearOfPlentyParameters.class), gameId);
				break;
			case "/moves/Road_Building":
				System.out.println("\tRoad Building URI");
				successful = facade.playRoadBuilding(gson.fromJson(jsonString, RoadBuildingParameters.class), gameId);
				break;
			case "/moves/Soldier":
				System.out.println("\tSoldier URI");
				successful = facade.playSoldier(gson.fromJson(jsonString, SoldierParameters.class), gameId);
				break;
			case "/moves/Monopoly":
				System.out.println("\tMonopoly URI");
				successful = facade.playMonopoly(gson.fromJson(jsonString, MonopolyParameters.class), gameId);
				break;
			case "/moves/Monument":
				System.out.println("\tMonument URI");
				successful = facade.playMonument(gson.fromJson(jsonString, MonumentParameters.class), gameId);
				break;
			case "/moves/robPlayer":
				System.out.println("\tRob Player URI");
				successful = facade.robPlayer(gson.fromJson(jsonString, RobPlayerParameters.class), gameId);
				break;
			default: 
				System.out.println("\tMoves URI not recognized: " + uri);
				successful = false;
				break;
		}
		
		String response;
		int responseCode;
		if(successful){
System.out.println("Move successful");			
			//If the move was successful, get the updated game model
			GameData gameData = ServerModelFacade.getInstance().getGameModel(gameId);
System.out.println("Move serialization");
			response = serializer.serializeGameModel(gameData);
			responseCode = 200;
		}else{
System.out.println("Move unsuccessful");
			response = "Failed to perform move.";
			responseCode = 400;
		}
		
		try {
			exchange.sendResponseHeaders(responseCode, response.length());

			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
		} catch (IOException e) {
			System.out.println("Moves Handler: sendResponseHeaders");
			e.printStackTrace();
		}
System.out.println("Exiting Moves Handler");
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
