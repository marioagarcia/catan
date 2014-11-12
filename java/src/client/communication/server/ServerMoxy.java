package client.communication.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import shared.model.player.Player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServerMoxy implements ServerProxyInterface{
	private int gameId;
	private String cookie;
	private int latestVersion;
	private int playerId;
	
	public ServerMoxy(){
		gameId = 1;
		cookie = "%7B%22authentication%22%3A%22-798137185%22%2C%22name%22%3A%22blah%22%2C%22password%22%3A%22string%22%2C%22playerID%22%3A12%7D";
		String plain_text_cookie = null;
		try {
			plain_text_cookie = URLDecoder.decode(cookie, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		JsonParser parser = new JsonParser();
		JsonElement cookie_element = parser.parse(plain_text_cookie);
		JsonObject cookie_object = cookie_element.getAsJsonObject();
		playerId = cookie_object.get("playerID").getAsInt();
		latestVersion = 1;
	}
	
	private String readFile(String filename){
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = null;
			while ((line = reader.readLine()) != null) {
			    builder.append(line);
			}		
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if (reader != null){
					reader.close();
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return builder.toString().replaceAll("\\s+", "");
	}

	@Override
	public String login(String JSONString) {
		return "Success";
	}

	@Override
	public String register(String JSONString) {
		return "Success";
	}

	@Override
	public String listGames() {
		return readFile("JSON" + File.separator + "listGames.txt");
	}

	@Override
	public String createGame(String JSONString) {
		return readFile("JSON" + File.separator + "createGame.txt");
	}

	@Override
	public String joinGame(String JSONString) {
		return "Success";
	}

	@Override
	public String getGameModel(boolean forceUpdate) {
		return readFile("JSON" + File.separator + "getGameModel.txt");
	}

	@Override
	public String resetGame() {
		return readFile("JSON" + File.separator + "resetGame.txt");
	}

	@Override
	public String getGameCommands() {
		return readFile("JSON" + File.separator + "postGameCommands.txt");
	}

	@Override
	public String postGameCommands(String JSONString) {
		return readFile("JSON" + File.separator + "postGameCommands.txt");
	}

	@Override
	public String getAIList() {
		return "[ \"LARGEST_ARMY\" ]";
	}

	@Override
	public String postNewAI(String JSONString) {
		return "Could not add AI to game.";
	}

	@Override
	public String utilChangeLogLevel(String JSONString) {
		return "Success";
	}

	@Override
	public String sendChat(String JSONString) {
		return readFile("JSON" + File.separator + "sendChat.txt");
	}

	@Override
	public String acceptTrade(String JSONString) {
		return readFile("JSON" + File.separator + "acceptTrade.txt");
	}

	@Override
	public String discardCards(String JSONString) {
		return readFile("JSON" + File.separator + "discardCards.txt");
	}

	@Override
	public String rollNumber(String JSONString) {
		return readFile("JSON" + File.separator + "rollNumber.txt");
	}

	@Override
	public String buildRoad(String JSONString) {
		return readFile("JSON" + File.separator + "buildRoad.txt");
	}

	@Override
	public String buildSettlement(String JSONString) {
		return readFile("JSON" + File.separator + "buildSettlement.txt");
	}

	@Override
	public String buildCity(String JSONString) {
		return readFile("JSON" + File.separator + "buildCity.txt");
	}

	@Override
	public String offerTrade(String JSONString) {
		return readFile("JSON" + File.separator + "offerTrade.txt");
	}

	@Override
	public String maritimeTrade(String JSONString) {
		return readFile("JSON" + File.separator + "maritimeTrade.txt");
	}

	@Override
	public String finishTurn(String JSONString) {
		return readFile("JSON" + File.separator + "finishTurn.txt");
	}

	@Override
	public String buyDevCard(String JSONString) {
		return readFile("JSON" + File.separator + "buyDevCard.txt");
	}

	@Override
	public String playYearOfPlenty(String JSONString) {
		return readFile("JSON" + File.separator + "yearOfPlenty.txt");
	}

	@Override
	public String playRoadBuilding(String JSONString) {
		return readFile("JSON" + File.separator + "roadBuilding.txt");
	}

	@Override
	public String playSoldier(String JSONString) {
		return readFile("JSON" + File.separator + "soldier.txt");
	}

	@Override
	public String playMonopoly(String JSONString) {
		return readFile("JSON" + File.separator + "playMonopoly.txt");
	}

	@Override
	public String playMonument(String JSONString) {
		return readFile("JSON" + File.separator + "playMonument.txt");
	}

	@Override
	public int getGameId() {
		return gameId;
	}
	
	public int getLatestVersionNumber(){
		return latestVersion;
	}

	@Override
	public boolean validatePlayer(Player player) {
		return player.getId() == playerId;
	}

	@Override
	public int getPlayerId() {
		return playerId;
	}

	@Override
	public String robPlayer(String JSONString){
		return "400";
	}

	@Override
	public String getGameModel(){
		return getGameModel(true);
	}

	@Override
	public void updateVersion(int version){
		
	}

	@Override
	public String LoadGame(String JSONString) {
		return readFile("JSON" + File.separator + "getGameModel.txt");
	}

	@Override
	public String SaveGame(String JSONString) {
		return "Success";
	}
}
