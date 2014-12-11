package test.server;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.command.AcceptTrade;
import server.command.BuildCity;
import server.command.BuildRoad;
import server.command.BuildSettlement;
import server.command.DiscardCards;
import server.command.FinishTurn;
import server.command.MaritimeTrade;
import server.command.OfferTrade;
import server.command.RollNumber;
import server.command.SendChat;
import server.facade.ServerModelFacade;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.card.ResourceList;
import shared.model.player.Player;
import shared.serialization.parameters.AcceptTradeParameters;
import shared.serialization.parameters.BuildCityParameters;
import shared.serialization.parameters.BuildRoadParameters;
import shared.serialization.parameters.BuildSettlementParameters;
import shared.serialization.parameters.DiscardCardsParameters;
import shared.serialization.parameters.EdgeLocationParameters;
import shared.serialization.parameters.FinishTurnParameters;
import shared.serialization.parameters.MaritimeTradeParameters;
import shared.serialization.parameters.OfferTradeParameters;
import shared.serialization.parameters.RollNumberParameters;
import shared.serialization.parameters.SendChatParameters;
import shared.serialization.parameters.VertexLocationParameters;

public class CommandsTest2 {

	private ServerModelFacade facade = ServerModelFacade.getInstance();
	
	@Before
	public void load() {
		
		//facade.loadTestGames();
	}
	/*
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
	
	private boolean rollCommand(int player_index, int game_id, int number)
	{
		RollNumberParameters p = new RollNumberParameters(player_index, number);
		RollNumber roll_command = new RollNumber(p, game_id);
		
		roll_command.execute();
		
		return roll_command.wasSuccessful();
	}
	
	private Player getPlayer(int index, int game_id){
		return facade.getGameModel(game_id).getPlayers().getPlayer(index);
	}
	*/
	@Test
	public void testRoads(){
		/*
		int roads_before = getPlayer(0, 1).getRoads();
		int wood_before = getPlayer(0, 1).getResourceList().getWood();
		int brick_before = getPlayer(0, 1).getResourceList().getBrick();
		
		//Has resources, but wrong location
		assertFalse(buildRoadCommand(0, 1, 1, -2, EdgeDirection.SouthEast));
		
		int roads_after = getPlayer(0, 1).getRoads();
		int wood_after = getPlayer(0, 1).getResourceList().getWood();
		int brick_after = getPlayer(0, 1).getResourceList().getBrick();
		
		assertTrue(roads_before == roads_after);
		assertTrue(wood_before == wood_after);
		assertTrue(brick_before == brick_after);
		
		//Has resources. Right location
		roads_before = getPlayer(0, 1).getRoads();
		wood_before = getPlayer(0, 1).getResourceList().getWood();
		brick_before = getPlayer(0, 1).getResourceList().getBrick();
		
		assertTrue(buildRoadCommand(0, 1, -1, -1, EdgeDirection.SouthEast));
		
		roads_after = getPlayer(0, 1).getRoads();
		wood_after = getPlayer(0, 1).getResourceList().getWood();
		brick_after = getPlayer(0, 1).getResourceList().getBrick();
		
		assertTrue(roads_before - 1 == roads_after);
		
		assertTrue(wood_before - 1 == wood_after);
		assertTrue(brick_before - 1 == brick_after);
		
		//Has resources, location not connected to other roads
		assertFalse(buildRoadCommand(3, 3, 0, -2, EdgeDirection.North));
		
		assertTrue(buildRoadCommand(3, 3, 0, -2, EdgeDirection.NorthEast));
		
		//Finish previous turn
		FinishTurnParameters p = new FinishTurnParameters(3);
		FinishTurn turn_command = new FinishTurn(p, 3);
		
		turn_command.execute();
		
		//It is not this person's turn to roll
		assertFalse(rollCommand(2, 3, 5));
		
		assertTrue(rollCommand(0, 3, 5));
		*/
	}
	
	@Test
	public void testSettlements(){
		/*
		//No connecting road
		assertFalse(buildSettlementCommand(0, 4, -1, -1, VertexDirection.NorthEast));
		
		//Too close to other settlements
		assertFalse(buildSettlementCommand(0, 4, -1, -1, VertexDirection.East));
		
		//Valid
		int settlements_before = getPlayer(3, 6).getSettlements();
		int wood_before = getPlayer(3, 6).getResourceList().getWood();
		int brick_before = getPlayer(3, 6).getResourceList().getBrick();
		int wheat_before = getPlayer(3, 6).getResourceList().getWheat();
		int sheep_before = getPlayer(3, 6).getResourceList().getSheep();
		
		assertTrue(buildSettlementCommand(3, 6, 0, -2, VertexDirection.NorthEast));
		
		int settlements_after = getPlayer(3, 6).getSettlements();
		int wood_after = getPlayer(3, 6).getResourceList().getWood();
		int brick_after = getPlayer(3, 6).getResourceList().getBrick();
		int wheat_after = getPlayer(3, 6).getResourceList().getWheat();
		int sheep_after = getPlayer(3, 6).getResourceList().getSheep();
		
		assertTrue(settlements_before -1 == settlements_after);
		assertTrue(wood_before - 1 == wood_after);
		assertTrue(brick_before - 1 == brick_after);
		assertTrue(wheat_before - 1 == wheat_after);
		assertTrue(sheep_before - 1 == sheep_after);
		*/
	}
	
	@Test
	public void testCities(){
		/*
		//Valid
		int cities_before = getPlayer(3, 7).getCities();
		int wheat_before = getPlayer(3, 7).getResourceList().getWheat();
		int ore_before = getPlayer(3, 7).getResourceList().getOre();
		
		assertTrue(buildCityCommand(3, 7, 0, -2, VertexDirection.NorthEast));
		int cities_after = getPlayer(3, 7).getCities();
		int wheat_after = getPlayer(3, 7).getResourceList().getWheat();
		int ore_after = getPlayer(3, 7).getResourceList().getOre();
		
		assertTrue(cities_before - 1 == cities_after);
		assertTrue(wheat_before - 2 == wheat_after);
		assertTrue(ore_before - 3 == ore_after);
		
		//Not their turn
		assertFalse(buildCityCommand(0, 7, 2, -2, VertexDirection.SouthEast));
		
		//Not enough resources, valid location
		assertFalse(buildCityCommand(0, 8, -1, -1, VertexDirection.SouthWest));
		*/
	}
	
	@Test
	public void testChat(){
		
		//assertTrue(sendChatCommand(3, 2, "Suckas!"));
		//assertTrue(sendChatCommand(1, 2, "Grow up, Joe."));
	}
	
	@Test
	public void testTrade(){
		/*
		//Has a wheat port, but not enough wheat to trade
		assertFalse(maritimeTradeCommand(2, 2, 3, "wheat", "brick"));
		
		//Doesn't have resource he is trying to send
		assertFalse(offerTradeCommand(2, 2, 0, new ResourceList(-1, 0, 0, 0, 1)));
		
		//Has proper resources
		assertTrue(offerTradeCommand(2, 2, 0, new ResourceList(1, 0, 0, 0, -1)));
		
		//Now has resources
		assertTrue(maritimeTradeCommand(2, 5, 3, "wheat", "ore"));
		
		//Meets all resource requirements
		assertTrue(acceptTradeCommand(1, 10, true));
		*/
	}
	
	@Test
	public void testDiscard(){
		/*
		//Not discarding half of his cards
		assertFalse(discardCommand(0, 9, new ResourceList(0, 0, 2, 0, 0)));
		
		//Valid discard
		assertTrue(discardCommand(0, 9, new ResourceList(0, 0, 3, 1, 0)));
		
		//Not over 7 cards
		assertFalse(discardCommand(1, 9, new ResourceList(0, 0, 0, 0, 3)));
		*/
	}

}
