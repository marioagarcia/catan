package client.communication.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;

import com.google.gson.*;

import client.model.player.Player;

public class ServerProxy implements ServerProxyInterface{
	private String serverPortNumber;
	private String host;
	private String link;
	private String methodUrl;
	private int gameId;
	private String cookie;
	private String plainTextCookie;
	private int playerId;
	private int latestVersion;
	
	/**
	 * Initializes the ServerProxy object with the given port and host name.
	 * @param port The port number the server is listening on for requests from the ServerProxy.
	 * @param host The name of the machine running the server.
	 */
	public ServerProxy(String port, String host){
		if (port != null)
		{
			this.serverPortNumber = port;
		}
		else
		{
			this.serverPortNumber = "8081";
		}
		if (host != null)
		{
			this.host = host;
		}
		else
		{
			this.host = "localhost";
		}
		
		link = "http://" + this.host + ":" + serverPortNumber;
		gameId = Integer.MAX_VALUE;
		cookie = null;
		plainTextCookie = null;
		playerId = Integer.MAX_VALUE;
		latestVersion = -1;
	}
	
	private String doGet(String url_path, String json_post_data, boolean needs_id){
		String json_line;
		String response = null;
		HttpURLConnection connection = null;
		
		try { 
			 URL url = new URL(link + url_path);
			 
			 connection = (HttpURLConnection)url.openConnection(); 
			 
			 connection.setRequestMethod("GET"); 
			  
			 connection.setDoInput(true);
			 connection.setDoOutput(true);
			 
			 if (cookie != null){
				 String full_cookie = "catan.user=" + cookie;
				 if (gameId != Integer.MAX_VALUE && needs_id){
					 full_cookie += ("; catan.game=" + gameId);
				 }
				 connection.setRequestProperty("Cookie", full_cookie);
			 }
			 connection.connect(); 
			 
			 if (json_post_data != null){
				 OutputStream request_body = connection.getOutputStream(); 
				 request_body.write(json_post_data.getBytes());
				 request_body.close(); 
			 }
			 
			 if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
				 if (url_path.equals("/user/register") || url_path.equals("/user/login")){
					String local_cookie = connection.getHeaderField("Set-Cookie");
					String[] pieces = local_cookie.split(";");
					
					cookie = pieces[0].split("=")[1];
					plainTextCookie = URLDecoder.decode(cookie, "UTF-8");
					JsonParser parser = new JsonParser();
					JsonElement cookie_element = parser.parse(plainTextCookie);
					JsonObject cookie_object = cookie_element.getAsJsonObject();
					playerId = cookie_object.get("playerID").getAsInt();
					
				 }
				 if (url_path.equals("/games/join")){
					 String local_cookie = connection.getHeaderField("Set-Cookie");
					 String[] pieces = local_cookie.split(";");
					 gameId = Integer.parseInt(pieces[0].split("=")[1]);
				 }
				 
				 StringBuilder server_response = new StringBuilder();
				 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				 
				 while ((json_line = reader.readLine()) != null){
					 server_response.append(json_line);
				 }
				 
				 response = server_response.toString();
			 }
			 else{
				 response = Integer.toString(connection.getResponseCode());
			 }
		}
		catch (MalformedURLException m){
			m.printStackTrace();
	        return null;
	    } 
		catch (ProtocolException p){
	        p.printStackTrace();
	        return null;
	    }
		catch (IOException e){
			e.printStackTrace();
			return null;
		}
		finally{
			connection.disconnect();
		}
		
		return response;
	}
	
	@Override
	public String login(String JSONString){
		methodUrl = "/user/login";	
		return 	doGet(methodUrl, JSONString, false);
	}

	@Override
	public String register(String JSONString){
		methodUrl = "/user/register";
		return doGet(methodUrl, JSONString, false);
	}

	@Override
	public String listGames(){
		methodUrl = "/games/list";
		return doGet(methodUrl, null, false);
	}

	@Override
	public String createGame(String JSONString){
		methodUrl = "/games/create";
		return doGet(methodUrl, JSONString, false);
	}

	@Override
	public String joinGame(String JSONString){
		methodUrl = "/games/join";
		return doGet(methodUrl, JSONString, false);
	}

	@Override
	public String getGameModel(boolean forceUpdate){
		
		if(forceUpdate){
			methodUrl = "/game/model";
		}
		else{
			methodUrl = "/game/model?version=" + latestVersion;
		}
		
		String model_string = doGet(methodUrl, null, true);
		//pull out the latest version number for future calls
		
		if (!model_string.equals("400") && !model_string.equals("\"true\"") && !model_string.equals("\"Success\"")){
			JsonParser parser = new JsonParser();
			JsonElement model_element = parser.parse(model_string);
			JsonObject model_object = model_element.getAsJsonObject();
			latestVersion = model_object.get("version").getAsInt();
		}
		
		return model_string;
	}
	
	@Override
	public String getGameModel(){
		return getGameModel(true);
	}

	@Override
	public String resetGame(){
		methodUrl = "/game/reset";
		return doGet(methodUrl, null, true);
	}

	@Override
	public String getGameCommands(){
		methodUrl = "/game/commands";
		return doGet(methodUrl, null, true);
	}

	@Override
	public String postGameCommands(String JSONString){
		methodUrl = "/game/commands";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String getAIList(){
		methodUrl = "/game/listAI";
		return doGet(methodUrl, null, false);
	}

	@Override
	public String postNewAI(String JSONString){
		methodUrl = "/game/addAI";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String utilChangeLogLevel(String JSONString){
		methodUrl = "/util/changeLogLevel";
		return doGet(methodUrl, JSONString, false);
	}

	@Override
	public String sendChat(String JSONString){
		methodUrl = "/moves/sendChat";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String acceptTrade(String JSONString){
		methodUrl = "/moves/acceptTrade";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String discardCards(String JSONString){
		methodUrl = "/moves/discardCards";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String rollNumber(String JSONString){
		methodUrl = "/moves/rollNumber";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String buildRoad(String JSONString){
		methodUrl = "/moves/buildRoad";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String buildSettlement(String JSONString){
		methodUrl = "/moves/buildSettlement";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String buildCity(String JSONString){
		methodUrl = "/moves/buildCity";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String offerTrade(String JSONString){
		methodUrl = "/moves/offerTrade";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String maritimeTrade(String JSONString){
		methodUrl = "/moves/maritimeTrade";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String finishTurn(String JSONString){
		methodUrl = "/moves/finishTurn";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String buyDevCard(String JSONString){
		methodUrl = "/moves/buyDevCard";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String playYearOfPlenty(String JSONString){
		methodUrl = "/moves/Year_of_Plenty";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String playRoadBuilding(String JSONString){
		methodUrl = "/moves/Road_Building";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String playSoldier(String JSONString){
		methodUrl = "/moves/Soldier";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String playMonopoly(String JSONString){
		methodUrl = "/moves/Monopoly";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public String playMonument(String JSONString){
		methodUrl = "/moves/Monument";
		return doGet(methodUrl, JSONString, true);
	}

	@Override
	public int getGameId() {
		return gameId;
	}
	
	@Override
	public int getLatestVersionNumber() {
		return latestVersion;
	}

	@Override
	public boolean validatePlayer(Player player) {
		return (plainTextCookie != null && player.getId() == playerId);
	}

	@Override
	public int getPlayerId() {
		return playerId;
	}

	@Override
	public String robPlayer(String JSONString){
		methodUrl = "/moves/robPlayer";
		return doGet(methodUrl, JSONString, true);
	}
}
