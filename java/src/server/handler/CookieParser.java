package server.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CookieParser {

	private String user = null;
	private String password = null;
	
	private int gameId = -1;
	private int playerId = -1;
	
	public CookieParser(String cookie){
		parseCookie(cookie);
	}
	/**
	 * Breaks a cookie down into its separate elements
	 * @param cookie The encoded cookie to be parsed
	 */
	public void parseCookie(String cookie){
		
		String[] pieces = cookie.split(";");
		
		if (cookie.contains("catan.game")){
			gameId = Integer.parseInt(pieces[1].split("=")[1]);
		}
		
		cookie = pieces[0].split("=")[1];
		
		try {
			String plainTextCookie = URLDecoder.decode(cookie, "UTF-8");
			JsonParser parser = new JsonParser();
			JsonElement cookie_element = parser.parse(plainTextCookie);
			JsonObject cookie_object = cookie_element.getAsJsonObject();
			
			playerId = cookie_object.get("playerID").getAsInt();
			user = cookie_object.get("name").getAsString();
			password = cookie_object.get("password").getAsString();
		} 
		catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static String generateLoginCookie(String user, String password, int id){
		StringBuilder sb = new StringBuilder();
		
		sb.append("catan.user=%7B%22name%22%3A%22");
		sb.append(user);
		sb.append("%22%2C%22password%22%3A%22");
		sb.append(password);
		sb.append("%22%2C%22playerID%22%3A");
		sb.append(id);
		sb.append("%7D;Path=/;");
		
		return sb.toString();
	}
	
	public static String generateJoinCookie(int game_id){
		
		return "catan.game=" + game_id + ";Path=/;";
	}
	
	/**
	 * @return The username provided in the given cookie. Returns null if no cookie has been parsed yet, or a username was not included
	 */
	public String getUserName(){
		return user;
	}
	
	/**
	 * @return The password provided in the given cookie. Returns null if no cookie has been parsed, or a password was not included
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * @return The game ID provided in the given cookie. Returns -1 If no ID is provided
	 */
	public int getGameID(){
		return this.gameId;
	}
	
	/**
	 * @return The player ID provided in the given cookie. Returns -1 If no ID is provided
	 */
	public int getPlayerID(){
		return this.playerId;
	}
}
