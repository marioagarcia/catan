package test.server;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.command.AcceptTrade;
import server.command.BuildCity;
import server.command.BuildRoad;
import server.command.BuildSettlement;
import server.command.DiscardCards;
import server.command.MaritimeTrade;
import server.command.OfferTrade;
import server.command.SendChat;
import server.facade.ServerModelFacade;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.card.ResourceList;
import shared.serialization.parameters.AcceptTradeParameters;
import shared.serialization.parameters.BuildCityParameters;
import shared.serialization.parameters.BuildRoadParameters;
import shared.serialization.parameters.BuildSettlementParameters;
import shared.serialization.parameters.DiscardCardsParameters;
import shared.serialization.parameters.EdgeLocationParameters;
import shared.serialization.parameters.MaritimeTradeParameters;
import shared.serialization.parameters.OfferTradeParameters;
import shared.serialization.parameters.SendChatParameters;
import shared.serialization.parameters.VertexLocationParameters;

public class CommandsTest2 {

	private ServerModelFacade facade = ServerModelFacade.getInstance();
	
	@Before
	public void load() {
		
		facade.loadTestGames();
	}
	
	private boolean buildRoadCommand(int player_index, int game_id, int x, int y, EdgeDirection direction){
		
		HexLocation h = new HexLocation(x, y);
		EdgeLocation l = new EdgeLocation(h, direction);
		EdgeLocationParameters location = new EdgeLocationParameters(l);
		
		BuildRoad road_command = new BuildRoad(new BuildRoadParameters(player_index, location, false), game_id);
		
		road_command.execute();
		
		return road_command.wasSuccessful();	
	}
	
	private boolean buildSettlementCommand(int player_index, int game_id, int x, int y, VertexDirection direction){
		
		HexLocation h = new HexLocation(x, y);
		VertexLocation v = new VertexLocation(h, direction);
		VertexLocationParameters location = new VertexLocationParameters(v);
		
		BuildSettlement settlement_command = new BuildSettlement(new BuildSettlementParameters(player_index, location, false), game_id);
		
		settlement_command.execute();
		
		return settlement_command.wasSuccessful();	
	}
	
	private boolean buildCityCommand(int player_index, int game_id, int x, int y, VertexDirection direction){
		
		HexLocation h = new HexLocation(x, y);
		VertexLocation v = new VertexLocation(h, direction);
		VertexLocationParameters location = new VertexLocationParameters(v);
		
		BuildCity city_command = new BuildCity(new BuildCityParameters(player_index, location), game_id);
		
		city_command.execute();
		
		return city_command.wasSuccessful();	
	}
	
	private boolean sendChatCommand(int player_index, int game_id, String message){
		
		SendChatParameters p = new SendChatParameters(player_index, message);
		SendChat chat_command = new SendChat(p, game_id);
		
		chat_command.execute();
		
		return chat_command.wasSuccessful();
	}
	
	private boolean offerTradeCommand(int player_index, int game_id, int receiver_index, ResourceList list){
		
		OfferTradeParameters p = new OfferTradeParameters(player_index, list, receiver_index);
		OfferTrade trade_command = new OfferTrade(p, game_id);
		
		trade_command.execute();
		return trade_command.wasSuccessful();
	}
	
	private boolean acceptTradeCommand(int player_index, int game_id, boolean accept){
		
		AcceptTradeParameters p = new AcceptTradeParameters(player_index, accept);
		AcceptTrade trade_command = new AcceptTrade(p, game_id);
		
		trade_command.execute();
		return trade_command.wasSuccessful();
	}
	
	private boolean maritimeTradeCommand(int player_index, int game_id, int ratio, String input, String output){
		
		MaritimeTradeParameters p = new MaritimeTradeParameters(player_index, ratio, input, output);
		MaritimeTrade trade_command = new MaritimeTrade(p, game_id);
		
		trade_command.execute();
		return trade_command.wasSuccessful();
	}
	
	private boolean discardCommand(int player_index, int game_id, ResourceList list){
		
		DiscardCardsParameters p = new DiscardCardsParameters(player_index, list);
		DiscardCards discard_command = new DiscardCards(p, game_id);
		
		discard_command.execute();
		
		return discard_command.wasSuccessful();
	}
	
	@Test
	public void testRoads(){
		
		assertFalse(buildRoadCommand(0, 1, 1, -2, EdgeDirection.SouthEast));
		assertTrue(buildRoadCommand(0, 1, 1, -1, EdgeDirection.SouthEast));
	}
		/*assertTrue(buildRoadCommand(3, 2, 2, -2, EdgeDirection.South));
		assertFalse(buildRoadCommand(1, 5, -1, 2, EdgeDirection.North));
	}
	
	@Test
	public void testSettlements(){
		
		assertTrue(buildSettlementCommand(3, 3, 2, -2, VertexDirection.SouthEast));
		assertFalse(buildSettlementCommand(3, 3, 2, -2, VertexDirection.SouthEast));
		assertFalse(buildSettlementCommand(0, 4, 2, -2, VertexDirection.SouthEast));
	}
	
	@Test
	public void testCities(){
		
		assertTrue(buildCityCommand(3, 10, 2, -2, VertexDirection.SouthEast));
		assertFalse(buildCityCommand(3, 10, 2, -2, VertexDirection.SouthEast));
		
		assertFalse(buildCityCommand(2, 10, 2, -2, VertexDirection.SouthEast));
		assertFalse(buildCityCommand(2, 10, 0, 0, VertexDirection.SouthEast));
	}
	
	@Test
	public void testChat(){
		
		assertTrue(sendChatCommand(3, 2, "Suckas!"));
		assertTrue(sendChatCommand(1, 2, "Grow up, Joe."));
	}
	
	@Test
	public void testTrade(){
		
		//Has enough resources, is player's turn
		assertTrue(offerTradeCommand(1, 7, 3, new ResourceList(0, 0, 0, 1, -1)));
		assertTrue(acceptTradeCommand(3, 7, true));
		
		//Doesn't have resources
		assertFalse(offerTradeCommand(2, 9, 0, new ResourceList(1, 0, 0, -1, 0)));
		
		//Has a wood port
		assertTrue(maritimeTradeCommand(1, 6, 2, "wood", "wheat"));
		
		//Doesn't have enough to use wood port
		assertFalse(maritimeTradeCommand(1, 6, 2, "wood", "wheat"));
		
		//Doesn't have a port or enough resources
		assertFalse(maritimeTradeCommand(3, 12, 4, "ore", "brick"));
	}
	
	@Test
	public void testDiscard(){
		
		assertTrue(discardCommand(3, 11, new ResourceList(0, 0, 0, 4, 0)));
		
		assertTrue(discardCommand(2, 11, new ResourceList(0, 0, 0, 0, 4)));
	}
*/
}
