package client.communication.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

import client.model.player.PlayerInfo;

public class ServerMoxy implements ServerProxyInterface
{
	private int gameId;
	private String cookie;
	private int latestVersion;
	
	public ServerMoxy()
	{
		gameId = 1;
		// %7B - "{"
		// %22 - "\"" 
		// %3A - ":"
		// %2C - ","
		cookie = "%7B%22authentication%22%3A%22-798137185%22%2C%22name%22%3A%22blah%22%2C%22password%22%3A%22string%22%2C%22playerID%22%3A12%7D";
		
		try {
			String plain_text_cookie = URLDecoder.decode(cookie, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		latestVersion = 1;
	}
	
	private String readFile(String filename)
	{
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
				reader.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return builder.toString();
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
		return readFile("test/JSON/listGames.txt");
	}

	@Override
	public String createGame(String JSONString) {
		return readFile("test/JSON/createGame.txt");
	}

	@Override
	public String joinGame(String JSONString) {
		return "Success";
	}

	@Override
	public String getGameModel() {
		return readFile("test/JSON/getGameModel.txt");
	}

	@Override
	public String resetGame() {
		return readFile("test/JSON/resetGame.txt");
	}

	@Override
	public String getGameCommands() {
		return readFile("test/JSON/postGameCommands.txt");
	}

	@Override
	public String postGameCommands(String JSONString) {
		return readFile("test/JSON/postGameCommands.txt");
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
		return readFile("test/JSON/sendChat.txt");
	}

	@Override
	public String acceptTrade(String JSONString) {
		return readFile("test/JSON/acceptTrade.txt");
	}

	@Override
	public String discardCards(String JSONString) {
		return readFile("test/JSON/discardCards.txt");
	}

	@Override
	public String rollNumber(String JSONString) {
		return readFile("test/JSON/rollNumber.txt");
	}

	@Override
	public String buildRoad(String JSONString) {
		return readFile("test/JSON/buildRoad.txt");
	}

	@Override
	public String buildSettlement(String JSONString) {
		return readFile("test/JSON/buildSettlement.txt");
	}

	@Override
	public String buildCity(String JSONString) {
		return readFile("test/JSON/buildCity.txt");
	}

	@Override
	public String offerTrade(String JSONString) {
		return readFile("test/JSON/offerTrade.txt");
	}

	@Override
	public String maritimeTrade(String JSONString) {
		return readFile("test/JSON/maritimeTrade.txt");
	}

	@Override
	public String finishTurn(String JSONString) {
		return readFile("test/JSON/finishTurn.txt");
	}

	@Override
	public String buyDevCard(String JSONString) {
		return readFile("test/JSON/buyDevCard.txt");
	}

	@Override
	public String playYearOfPlenty(String JSONString) {
		return readFile("test/JSON/yearOfPlenty.txt");
	}

	@Override
	public String playRoadBuilding(String JSONString) {
		return readFile("test/JSON/roadBuilding.txt");
	}

	@Override
	public String playSoldier(String JSONString) {
		return readFile("test/JSON/soldier.txt");
	}

	@Override
	public String playMonopoly(String JSONString) {
		return readFile("test/JSON/playMonopoly.txt");
	}

	@Override
	public String playMonument(String JSONString) {
		return readFile("test/JSON/playMonument.txt");
	}

	@Override
	public int getGameId() {
		return gameId;
	}

	@Override
	public boolean validatePlayer(PlayerInfo player) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
