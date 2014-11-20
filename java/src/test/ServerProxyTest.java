package test;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import client.communication.server.ServerProxy;

public class ServerProxyTest {

	private ServerProxy proxyTest;
	String result = null;
	String param = null;
	
	@Before
	public void setUp(){
		proxyTest = new ServerProxy("8081", "localhost");
		result = null;
		param = null;
	}
	
	private String generateString(){
		String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*";
		String result = "";
		
		Random generator = new Random();
		
		for (int i = 0; i < 5; i++){
			result += letters.charAt(generator.nextInt(letters.length() - 1));
		}
		
		return result;
	}
	
	private void loginAndJoinGame(){
		param = "{"
				 + "\"username\": \"Sam\","
				 + "\"password\": \"sam\""
				 + "}";
		proxyTest.login(param);
		
		param = "{"
				  + "\"id\": 0,"
				  + "\"color\": \"orange\""
				  + "}";
		proxyTest.joinGame(param);
	}
	
	@Test
	public void testLogin(){
		
		param = "{"
				 + "\"username\": \"Sam\","
				 + "\"password\": \"sam\""
				 + "}";
		result = proxyTest.login(param);
		assertTrue(result.equals("Success"));
		
		
		param = "{"
				 + "\"username\": \"Sam\","
				 + "\"password\": \"Mas\""
				 + "}";
		result = proxyTest.login(param);
		assertTrue(!result.equals("Success"));
	}
	
	@Test
	public void testRegister(){
		
		String username = generateString();
		String password = generateString();
		
		param = "{"
				 + "\"username\": \"" + username + "\","
				 + "\"password\": \"" + password + "\""
				 + "}";
		result = proxyTest.register(param);
		assertTrue(result.equals("Success"));
		
		
		param = "{"
				 + "\"username\": \"Sam\","
				 + "\"password\": \"test\""
				 + "}";
		result = proxyTest.register(param);
		assertTrue(!result.equals("Success"));
	}
	
	@Test
	public void testListGames(){
		result = proxyTest.listGames();
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testCreateGame(){
		String game_name = generateString();
		
		param = "{" 
				+ "\"randomTiles\": true,"
				+ "\"randomNumbers\": true,"
				+ "\"randomPorts\": true,"
				+ "\"name\": \"" + game_name + "\""
				+ "}";


		result = proxyTest.createGame(param);
		
		JsonParser parser = new JsonParser();
		JsonElement game_element = parser.parse(result);
		JsonObject game_object = game_element.getAsJsonObject();
		
		String title = game_object.get("title").getAsString();
		
		assertTrue(title.equals(game_name));
	}
	
	@Test
	public void testJoinGame(){
		param = "{"
				 + "\"username\": \"Sam\","
				 + "\"password\": \"sam\""
				 + "}";
		result = proxyTest.login(param);
		
		param = "{"
				  + "\"id\": 0,"
				  + "\"color\": \"orange\""
				  + "}";
		result = proxyTest.joinGame(param);
		
		assertTrue(result.equals("Success"));
		
		param = "{"
				  + "\"id\": 24,"
				  + "\"color\": \"blue\""
				  + "}";
		result = proxyTest.joinGame(param);
		assertTrue(!result.equals("Success"));
	}
	
	@Test
	public void testGetGameModel(){
		
		//Successfully login, join game, and get current model
		loginAndJoinGame();
		result = proxyTest.getGameModel(true);
		assertTrue(proxyTest.getLatestVersionNumber() != Integer.MAX_VALUE);
	}
	
	@Test
	public void testSendChat(){
		loginAndJoinGame();
		
		param = "{"
				  + "\"message\": \"Adding content to model\","
				  + "\"source\": \"Kevin\""
				  + "}";
		result = proxyTest.sendChat(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testAcceptTrade(){
		loginAndJoinGame();
		testOfferTrade();
		
		param = "{"
			  + "\"type\": \"acceptTrade\","
			  + "\"playerIndex\": 0,"
			  + "\"willAccept\": true"
			  + "}";
		
		result = proxyTest.acceptTrade(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testDiscardCards(){
		loginAndJoinGame();
		
		param = "{"
			  + "\"type\": \"discardCards\","
			  + "\"playerIndex\": 0,"
			  + "\"discardedCards\": {"
			  + "\"brick\": 0,"
			  + "\"ore\": -1,"
			  + "\"sheep\": -1,"
			  + "\"wheat\": 0,"
			  +  "\"wood\": 0"
			  + "}"
			  + "}";
		result = proxyTest.discardCards(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testRollNumber(){
		loginAndJoinGame();
		
		param = "{"
			  + "\"type\": \"rollNumber\","
			  + "\"playerIndex\": 0,"
			  + "\"number\": 7"
			  + "}";
		
		result = proxyTest.rollNumber(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testBuildRoad(){
		loginAndJoinGame();
		
		param = "{"
			  + "\"type\": \"buildRoad\","
			  + "\"playerIndex\": 0,"
			  + "\"roadLocation\": {"
			  + "\"x\": 1,"
			  + "\"y\": 2,"
			  + "\"direction\": \"NE\""
			  + "},"
			  + "\"free\": true"
			  + "}";
		
		result = proxyTest.buildRoad(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testBuildSettlement(){
		loginAndJoinGame();	
		
		param = "{"
			  + "\"type\": \"buildSettlement\","
			  + "\"playerIndex\": 0,"
			  + "\"vertexLocation\": {"
			  + "\"x\": 3,"
			  + "\"y\": 4,"
			  + "\"direction\": \"SW\""
			  + "},"
			  + "\"free\": false"
			  + "}";
		
		result = proxyTest.buildSettlement(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testBuildCity(){
		loginAndJoinGame();
		
		param = "{"
				  + "\"type\": \"buildCity\","
				  + "\"playerIndex\": 0,"
				  + "\"vertexLocation\": {"
				  + "\"x\": 2,"
				  + "\"y\": 2,"
				  + "\"direction\": \"SW\""
				  + "},"
				  + "\"free\": false"
				  + "}";
			
			result = proxyTest.buildCity(param);
			assertTrue(!result.equals(null));
	}
	
	@Test
	public void testOfferTrade(){
		loginAndJoinGame();
		
		param = "{"
				  + "\"type\": \"offerTrade\","
				  + "\"playerIndex\": 0,"
				  + "\"offer\": {"
				  + "\"brick\": 2,"
				  + "\"ore\": -2,"
				  + "\"sheep\": 0,"
				  + "\"wheat\": 0,"
				  +  "\"wood\": 0"
				  + "},"
				  + "\"reciever\": 2"
				  + "}";
		
			result = proxyTest.offerTrade(param);
			assertTrue(!result.equals(null));
	}
	
	@Test
	public void testMaritimeTrade(){
		loginAndJoinGame();
		
		param = "{"
			  + "\"type\": \"maritimeTrade\","
			  + "\"playerIndex\": 0,"
			  + "\"ratio\": 3,"
			  + "\"inputResource\": \"sheep\","
			  + "\"outputResource\": \"wood\""
			  + "}";
		
		result = proxyTest.maritimeTrade(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testFinishTurn(){
		loginAndJoinGame();
		
		param = "{"
			  + "\"type\": \"finishTurn\","
			  +  "\"playerIndex\": 0"
			  + "}";
		
		result = proxyTest.finishTurn(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testBuyDevCard(){
		loginAndJoinGame();
		
		param = "{"
				  + "\"type\": \"buyDevCard\","
				  +  "\"playerIndex\": 0"
				  + "}";
			
			result = proxyTest.buyDevCard(param);
			assertTrue(!result.equals(null));
	}
	
	@Test
	public void testYearOfPlenty(){
		loginAndJoinGame();
		
		param ="{\n"+
				" \"type\": \"Year_of_Plenty\",\n"+
				" \"playerIndex\": 0,\n"+
				" \"resource1\": \"brick\",\n"+
				" \"resource2\": \"ore\"\n"+
				"}";
		
		result = proxyTest.playYearOfPlenty(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testRoadCard(){
		loginAndJoinGame();
		
		param = "{\n"+
				" \"type\": \"Road_Building\",\n"+
				" \"playerIndex\": 0,\n"+
				" \"spot1\": {\n"+
				" \"x\": 4,\n"+
				" \"y\": 5,\n"+
				" \"direction\": \"NW\"\n"+
				" },\n"+
				" \"spot2\": {\n"+
				" \"x\": 4,\n"+
				" \"y\": 5,\n"+
				" \"direction\": \"N\"\n"+
				" }\n"+
				"}";
		
		result = proxyTest.playRoadBuilding(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testSoldier(){
		loginAndJoinGame();
		
		param = "{\n"+
				" \"type\": \"Soldier\",\n"+
				" \"playerIndex\": 0,\n"+
				" \"victimIndex\": 1,\n"+
				" \"location\": {\n"+
				" \"x\": 3,\n"+
				" \"y\": 2\n"+
				" }\n"+
				"}";
		
		result = proxyTest.playSoldier(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testMonopoly(){
		loginAndJoinGame();
		
		param = "{\n"+
				" \"type\": \"Monopoly\",\n"+
				" \"resource\": \"wood\",\n"+
				" \"playerIndex\": 0\n"+
				"}";
		
		result = proxyTest.playMonopoly(param);
		assertTrue(!result.equals(null));
	}
	
	@Test
	public void testMonument(){
		loginAndJoinGame();
		
		param = "{\n"+
				" \"type\": \"Monument\",\n"+
				" \"playerIndex\": 0\n"+
				"}";
		
		result = proxyTest.playMonument(param);
		assertTrue(!result.equals(null));
	}
}
