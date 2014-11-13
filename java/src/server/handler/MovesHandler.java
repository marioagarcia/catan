package server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import server.command.facade.MovesCommandFacadeInterface;
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
		
		
		Gson gson = new Gson();
		GameData gameData = null;
		String jsonString = getJsonString(exchange.getRequestBody());
		
		String uri = exchange.getRequestURI().toString();
		switch(uri){
			case "/moves/sendChat":
				gameData = facade.sendChat(gson.fromJson(jsonString, SendChatParameters.class));
				break;
			case "/moves/acceptTrade":
				gameData = facade.acceptTrade(gson.fromJson(jsonString, AcceptTradeParameters.class));
				break;
			case "/moves/discardCards":
				gameData = facade.discardCards(gson.fromJson(jsonString, DiscardCardsParameters.class));
				break;
			case "/moves/rollNumber":
				gameData = facade.rollNumber(gson.fromJson(jsonString, RollNumberParameters.class));
				break;
			case "/moves/buildRoad":
				gameData = facade.buildRoad(gson.fromJson(jsonString, BuildRoadParameters.class));
				break;
			case "/moves/buildSettlement":
				gameData = facade.buildSettlement(gson.fromJson(jsonString, BuildSettlementParameters.class));
				break;
			case "/moves/buildCity":
				gameData = facade.buildCity(gson.fromJson(jsonString, BuildCityParameters.class));
				break;
			case "/moves/offerTrade":
				gameData = facade.offerTrade(gson.fromJson(jsonString, OfferTradeParameters.class));
				break;
			case "/moves/maritimeTrade":
				gameData = facade.maritimeTrade(gson.fromJson(jsonString, MaritimeTradeParameters.class));
				break;
			case "/moves/finishTurn":
				gameData = facade.finishTurn(gson.fromJson(jsonString, FinishTurnParameters.class));
				break;
			case "/moves/buyDevCard":
				gameData = facade.buyDevCard(gson.fromJson(jsonString, BuyDevCardParameters.class));
				break;
			case "/moves/Year_of_Plenty":
				gameData = facade.playYearOfPlenty(gson.fromJson(jsonString, YearOfPlentyParameters.class));
				break;
			case "/moves/Road_Building":
				gameData = facade.playRoadBuilding(gson.fromJson(jsonString, RoadBuildingParameters.class));
				break;
			case "/moves/Soldier":
				gameData = facade.playSoldier(gson.fromJson(jsonString, SoldierParameters.class));
				break;
			case "/moves/Monopoly":
				gameData = facade.playMonopoly(gson.fromJson(jsonString, MonopolyParameters.class));
				break;
			case "/moves/Monument":
				gameData = facade.playMonument(gson.fromJson(jsonString, MonumentParameters.class));
				break;
			case "/moves/robPlayer":
				gameData = facade.robPlayer(gson.fromJson(jsonString, RobPlayerParameters.class));
				break;
		}
		
		
		String response;
		int responseCode;
		if(gameData != null){
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

}
