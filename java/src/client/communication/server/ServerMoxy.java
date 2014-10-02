package client.communication.server;

public class ServerMoxy implements ServerProxyInterface
{
	
	public ServerMoxy()
	{

	}
	
	@Override
	public String login(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{ \"name\": \"Mario\", \"playerID\": 25 }");
		
		return builder.toString();
	}

	@Override
	public String register(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{ \"name\": \"Cookie-Monster\", \"playerID\": 10 }");
		return builder.toString();
	}

	@Override
	public String listGames()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("[\n");
		
		builder.append("{\n");
		builder.append("\"title\": \"Game 1\",\n");
		builder.append("\"id\": 1,\n");
		builder.append("\"players\": ");
		builder.append("[\n");
		builder.append("{ \"color\": \"puce\", \"name\": \"Chris\", \"id\": 19},\n");
		builder.append("{ \"color\": \"orange\", \"name\": \"Mario\", \"id\": 20},\n");
		builder.append("{ \"color\": \"white\", \"name\": \"Casey\", \"id\": 21},\n");
		builder.append("{ \"color\": \"green\", \"name\": \"Kevin\", \"id\": 22},\n");
		builder.append("]\n");
		builder.append("},\n");
		
		builder.append("\"title\": \"Game 2\",\n");
		builder.append("\"id\": 2,\n");
		builder.append("\"players\": ");
		builder.append("[\n");
		builder.append("{ \"color\": \"red\", \"name\": \"InYang\", \"id\": 123},\n");
		builder.append("{ \"color\": \"orange\", \"name\": \"Casey\", \"id\": 456},\n");
		builder.append("{ \"color\": \"white\", \"name\": \"Chris\", \"id\": 789},\n");
		builder.append("{},\n");
		builder.append("]\n");
		builder.append("}\n");
		
		builder.append("]");
		return builder.toString();
	}

	@Override
	public String createGame()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("{\n");
		builder.append("\"title\": \"My Game\",\n");
		builder.append("\"id\": 1,\n");
		builder.append("\"players\": ");
		builder.append("[\n");
		builder.append("{},\n");
		builder.append("{},\n");
		builder.append("{},\n");
		builder.append("{}\n");
		builder.append("]\n");
		builder.append("}");
		
		return builder.toString();
	}

	@Override
	public String joinGame(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String getGameModel(String JSONString, String latest_model)
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("{\n");
		
		builder.append(buildBank());
		builder.append(buildChat());
		builder.append(buildLog());
		builder.append(buildMap());
		builder.append(buildPlayers());
		builder.append(buildTradeOffer());
		builder.append(buildTurnTracker());
		builder.append("\"version\": 3,\n");
		builder.append("\"winner\": -1\n");
		
		builder.append("}");
		
		return builder.toString();
	}
	
	private String buildBank()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("\"bank\": ");
		builder.append("{\n");
		builder.append("\"brick\": 10,\n");
		builder.append("\"ore\": 8,\n");
		builder.append("\"sheep\": 13,\n");
		builder.append("\"wheat\": 21,\n");
		builder.append("\"wood\": 7,\n");
		builder.append("},\n");
		
		return builder.toString();	
	}
	
	private String buildChat()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"chat\": ");
		builder.append("{\n");
		builder.append(buildChatLines());
		builder.append("},\n");
		return builder.toString();	
	}
	
	private String buildLogLines()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"lines\": ");
		builder.append("[\n");
		builder.append("{\n");
		builder.append("\"message\": \"This is a log message\",\n");
		builder.append("\"source\": \"A player did something\"\n");
		builder.append("}\n");
		builder.append("]\n");
		return builder.toString();	
	}
	private String buildChatLines()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"lines\": ");
		builder.append("[\n");
		builder.append("{\n");
		builder.append("\"message\": \"This is a chat message\",\n");
		builder.append("\"source\": \"Player 3\"\n");
		builder.append("}\n");
		builder.append("]\n");
		return builder.toString();	
	}
	
	private String buildLog()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("\"log\": ");
		builder.append("{\n");
		builder.append(buildLogLines());
		builder.append("},\n");
		
		return builder.toString();	
	}
	
	private String buildMap()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"map\": ");
		builder.append("{\n");
		builder.append(buildHexes());
		builder.append(buildPorts());
		builder.append(buildRoads());
		builder.append(buildSettlements());
		builder.append(buildCities());
		builder.append("\"radius\": 6,\n");
		builder.append(buildRobber(3,3));
		builder.append("},\n");
		return builder.toString();	
	}
	
	private String buildHexes()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"hexes\": ");
		builder.append("[\n");
		builder.append("{\n");
		builder.append(buildLocation(2,3));
		builder.append("\"resource\": \"wood\",\n");
		builder.append("\"number\": 5,\n");
		builder.append("},\n");
		
		builder.append("{\n");
		builder.append(buildLocation(4,5));
		builder.append("\"resource\": \"sheep\",\n");
		builder.append("\"number\": 6,\n");
		builder.append("},\n");
		
		builder.append("],\n");
		return builder.toString();	
	}
	
	private String buildLocation(int x, int y)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"location\": ");
		builder.append("{\n");
		builder.append("\"x\": ");
		builder.append(x);
		builder.append(",\n");
		builder.append("\"y\": ");
		builder.append(y);
		builder.append("\n");
		builder.append("},\n");
		return builder.toString();	
	}
	
	private String buildLocationWithDirection(int x, int y, String direction)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"location\": ");
		builder.append("{\n");
		builder.append("\"x\": ");
		builder.append(x);
		builder.append(",\n");
		builder.append("\"y\": ");
		builder.append(y);
		builder.append(",\n");
		builder.append("\"direction\": \"");
		builder.append(direction);
		builder.append("\"\n");
		builder.append("},\n");
		return builder.toString();	
	}
	
	private String buildPorts()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"ports\": ");
		builder.append("[\n");
		builder.append("{\n");
		builder.append("\"resource\": \"wheat\",\n");
		builder.append(buildLocation(2,1));
		builder.append("\"direction\": \"North West\",\n");
		builder.append("\"ratio\": 3\n");
		builder.append("}\n");
		builder.append("],\n");
		return builder.toString();	
	}
	
	private String buildRoads()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"roads\": ");
		builder.append("[\n");
		builder.append("{\n");
		builder.append("\"owner\": 2,\n");
		builder.append(buildLocationWithDirection(2,1, "South East"));
		builder.append("}\n");
		builder.append("],\n");
		return builder.toString();	
	}
	
	private String buildSettlements()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"settlements\": ");
		builder.append("[\n");
		builder.append("{\n");
		builder.append("\"owner\": 3,\n");
		builder.append(buildLocationWithDirection(5,4, "West"));
		builder.append("}\n");
		builder.append("],\n");
		return builder.toString();	
	}
	
	private String buildCities()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"cities\": ");
		builder.append("[\n");
		builder.append("{\n");
		builder.append("\"owner\": 2,\n");
		builder.append(buildLocationWithDirection(2,2, "South West"));
		builder.append("}\n");
		builder.append("],\n");
		return builder.toString();
	}
	
	private String buildRobber(int x, int y)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"robber\": ");
		builder.append("{\n");
		builder.append("\"x\": ");
		builder.append(x);
		builder.append(",\n");
		builder.append("\"y\": ");
		builder.append(y);
		builder.append("\n");
		builder.append("}\n");
		return builder.toString();	
	}
	
	private String buildPlayers()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"players\": ");
		builder.append("[\n");
		builder.append("{\n");
		builder.append("\"cities\": 2,\n");
		builder.append("\"color\": \"red\",\n");
		builder.append("\"discarded\": \"true\",\n");
		builder.append("\"monuments\": 1,\n");
		builder.append("\"name\": \"George\",\n");
		builder.append(buildNewDevCards());
		builder.append(buildOldDevCards());
		builder.append("\"playerIndex\": 2,\n");
		builder.append("\"playedDevCard\": \"false\",\n");
		builder.append("\"playerID\": 5,\n");
		builder.append(buildResources());
		builder.append("\"roads\": 6,\n");
		builder.append("\"settlements\": 4,\n");
		builder.append("\"soldiers\": 1,\n");
		builder.append("\"victoryPoints\": 3\n");
		builder.append("}\n");
		builder.append("],\n");
		return builder.toString();	
	}
	
	private String buildNewDevCards()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"newDevCards\": ");
		builder.append("{\n");
		builder.append("\"monopoly\": 1,\n");
		builder.append("\"monument\": 2,\n");
		builder.append("\"roadBuilding\": 2,\n");
		builder.append("\"soldier\": 3,\n");
		builder.append("\"yearOfPlenty\": 1\n");
		builder.append("},\n");
		return builder.toString();	
	}
	
	private String buildOldDevCards()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"oldDevCards\": ");
		builder.append("{\n");
		builder.append("\"monopoly\": 0,\n");
		builder.append("\"monument\": 2,\n");
		builder.append("\"roadBuilding\": 2,\n");
		builder.append("\"soldier\": 3,\n");
		builder.append("\"yearOfPlenty\": 1\n");
		builder.append("},\n");
		return builder.toString();	
	}
	
	private String buildResources()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"resources\": ");
		builder.append("{\n");
		builder.append("\"brick\": 1,\n");
		builder.append("\"ore\": 3,\n");
		builder.append("\"sheep\": 2,\n");
		builder.append("\"wheat\": 0,\n");
		builder.append("\"wood\": 4,\n");
		builder.append("},\n");
		return builder.toString();	
	}
	
	private String buildTradeOffer()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"tradeOffer\": ");
		builder.append("{\n");
		builder.append("\"sender\": 1,\n");
		builder.append("\"receiver\": 3,\n");
		builder.append("\"offer\": ");
		builder.append("{\n");
		builder.append("\"brick\": 0,\n");
		builder.append("\"ore\": -1,\n");
		builder.append("\"sheep\": 2,\n");
		builder.append("\"wheat\": 0,\n");
		builder.append("\"wood\": 0\n");
		builder.append("}\n");
		builder.append("},\n");
		return builder.toString();	
	}
	
	private String buildTurnTracker()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\"turnTracker\": ");
		builder.append("{\n");
		builder.append("\"currentTurn\": 1,\n");
		builder.append("\"status\": \"playing\",\n");
		builder.append("\"longestRoad\": 1,\n");
		builder.append("\"largestArmy\": 0\n");
		builder.append("},\n");
		return builder.toString();	
	}

	@Override
	public String resetGame(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String getGameActionList(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String postGameActionList(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		//returns game model on success
		return builder.toString();
	}

	@Override
	public String getAIList()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("[\"EasyAI\", \"Medium AI\", \"Hard AI\"]");
		
		return builder.toString();
	}

	@Override
	public String postNewAI(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("");
		
		return builder.toString();
	}

	@Override
	public String utilChangeLogLevel(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String sendChat(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String acceptTrade(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String discardCards(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String rollNumber(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String buildRoad(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String buildSettlement(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String buildCity(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String offerTrade(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String maritimeTrade(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String finishTurn(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String buyDevCard(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String playYearOfPlenty(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String playRoadBuilding(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String playSoldier(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String playMonopoly(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	@Override
	public String playMonument(String JSONString)
	{
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

}
