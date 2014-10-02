package client.communication.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class ServerProxy implements ServerProxyInterface
{

	private String serverPortNumber;
	private String host;
	private String link;
	private String methodUrl;
	
	/**
	 * Initializes the ServerProxy object with the given port and host name.
	 * @param port The port number the server is listening on for requests from the ServerProxy.
	 * @param host The name of the machine running the server.
	 */
	public ServerProxy(String port, String host){
		this.serverPortNumber = port;
		this.host = host;
		link = "http://" + this.host + ":" + serverPortNumber;
	}
	
	private String doGet(String url_path, String json_post_data){
		String json_line;
		String json_result = null;
		HttpURLConnection connection = null;
		
		try { 
			 URL url = new URL(link + url_path);
			 
			 connection = (HttpURLConnection)url.openConnection(); 
			 
			 connection.setRequestMethod("GET"); 
			  
			 connection.setDoInput(true);
			 connection.setDoOutput(true);
			 
			 connection.connect(); 
			 
			 if (json_post_data != null){
				 OutputStream request_body = connection.getOutputStream(); 
				 
				 request_body.write(json_post_data.getBytes());
				 request_body.close(); 
			 }
			 
			 StringBuilder server_response = new StringBuilder();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			 
			 while ((json_line = reader.readLine()) != null){
				 server_response.append(json_line + '\n');
			 }
			 
			 json_result = server_response.toString();
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
		
		return json_result;
	}
	
	@Override
	public String login(String JSONString){
		methodUrl = "/user/login";	
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String register(String JSONString){
		methodUrl = "/user/register";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String listGames(){
		methodUrl = "/games/list";
		return doGet(methodUrl, null);
	}

	@Override
	public String createGame(){
		methodUrl = "/games/create";
		return doGet(methodUrl, null);
	}

	@Override
	public String joinGame(String JSONString){
		methodUrl = "/games/join";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String getGameModel(String JSONString, String latest_model){
		methodUrl = "/games/model?version=" + latest_model;
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String resetGame(String JSONString){
		methodUrl = "/game/reset";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String getGameActionList(String JSONString){
		methodUrl = "/game/commands";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String postGameActionList(String JSONString){
		methodUrl = "/game/commands";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String getAIList(){
		methodUrl = "/game/listAI";
		return doGet(methodUrl, null);
	}

	@Override
	public String postNewAI(String JSONString){
		methodUrl = "/game/addAI";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String utilChangeLogLevel(String JSONString){
		methodUrl = "/util/changeLogLevel";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String sendChat(String JSONString){
		methodUrl = "/moves/sendChat";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String acceptTrade(String JSONString){
		methodUrl = "/moves/acceptTrade";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String discardCards(String JSONString){
		methodUrl = "/moves/discardCards";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String rollNumber(String JSONString){
		methodUrl = "/moves/rollNumber";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String buildRoad(String JSONString){
		methodUrl = "/moves/buildRoad";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String buildSettlement(String JSONString){
		methodUrl = "/moves/buildSettlement";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String buildCity(String JSONString){
		methodUrl = "/moves/buildCity";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String offerTrade(String JSONString){
		methodUrl = "/moves/offerTrade";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String maritimeTrade(String JSONString){
		methodUrl = "/moves/maritimeTrade";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String finishTurn(String JSONString){
		methodUrl = "/moves/finishTurn";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String buyDevCard(String JSONString){
		methodUrl = "/moves/buyDevCard";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String playYearOfPlenty(String JSONString){
		methodUrl = "/moves/Year_of_Plenty";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String playRoadBuilding(String JSONString){
		methodUrl = "/moves/Road_Building";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String playSoldier(String JSONString){
		methodUrl = "/moves/Soldier";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String playMonopoly(String JSONString){
		methodUrl = "/moves/Monopoly";
		return doGet(methodUrl, JSONString);
	}

	@Override
	public String playMonument(String JSONString){
		methodUrl = "/moves/Monument";
		return doGet(methodUrl, JSONString);
	}


}
