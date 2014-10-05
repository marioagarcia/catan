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

	private ServerProxy proxy_test;
	String result = null;
	String param = null;
	
	@Before
	public void setUp(){
		proxy_test = new ServerProxy("8081", "localhost");
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
	
	private void loginAndJoinGame()
	{
		param = "{"
				 + "\"username\": \"Sam\","
				 + "\"password\": \"sam\""
				 + "}";
		proxy_test.login(param);
		
		param = "{"
				  + "\"id\": 0,"
				  + "\"color\": \"orange\""
				  + "}";
		proxy_test.joinGame(param);
	}
	
	@Test
	public void testLogin(){
		
		param = "{"
				 + "\"username\": \"Sam\","
				 + "\"password\": \"sam\""
				 + "}";
		result = proxy_test.login(param);
		System.out.println("Good login: |" + result + "|");
		assertTrue(result.equals("Success"));
		
		
		param = "{"
				 + "\"username\": \"Sam\","
				 + "\"password\": \"Mas\""
				 + "}";
		result = proxy_test.login(param);
		System.out.println("Bad login: |" + result + "|");
		assertTrue(!result.equals("Success"));
	}
	
	@Test
	public void testRegister(){
		
		String username = generateString();
		String password = generateString();
		
		System.out.println("Generated username: " + username);
		System.out.println("Generated password: " + password);
		
		param = "{"
				 + "\"username\": \"" + username + "\","
				 + "\"password\": \"" + password + "\""
				 + "}";
		result = proxy_test.register(param);
		System.out.println("Good register: |" + result + "|");
		assertTrue(result.equals("Success"));
		
		
		param = "{"
				 + "\"username\": \"Sam\","
				 + "\"password\": \"test\""
				 + "}";
		result = proxy_test.register(param);
		System.out.println("Bad register: |" + result + "|");
		assertTrue(!result.equals("Success"));
	}
	
	@Test
	public void testListGames(){
		result = proxy_test.listGames();
		System.out.println("Games List: |" + result + "|");
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


		result = proxy_test.createGame(param);
		System.out.println("Create Game: |" + result + "|");
		
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
		result = proxy_test.login(param);
		
		param = "{"
				  + "\"id\": 0,"
				  + "\"color\": \"orange\""
				  + "}";
		result = proxy_test.joinGame(param);
		System.out.println("Good Join Response: |" + result + "|");
		
		assertTrue(result.equals("Success"));
		
		param = "{"
				  + "\"id\": 24,"
				  + "\"color\": \"blue\""
				  + "}";
		result = proxy_test.joinGame(param);
		System.out.println("Bad Join Response: |" + result + "|");
		assertTrue(!result.equals("Success"));
	}
	
	@Test
	public void testGetGameModel()
	{
		// Trying to get a game model while not logged in/in a game should fail. Server should respond
		// with 400 code, and proxy should not have a latest model version
		result = proxy_test.getGameModel();
		System.out.println("Bad Game Model Result: |" + result + "|");
		assertTrue(result.equals("400"));
		assertTrue(proxy_test.getLatestVersionNumber() == -1);
		System.out.println("Proxy Model Version: " + proxy_test.getLatestVersionNumber());
		
		//Successfully login, join game, and get current model
		loginAndJoinGame();
		result = proxy_test.getGameModel();
		System.out.println("Game Model Result: |" + result + "|");
		assertTrue(proxy_test.getLatestVersionNumber() != Integer.MAX_VALUE);
	}

}
