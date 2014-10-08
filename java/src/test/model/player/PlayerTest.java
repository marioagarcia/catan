/**
 * 
 */
package test.model.player;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.definitions.CatanColor;
import client.model.card.DevCardList;
import client.model.card.ResourceList;
import client.model.card.Trade;
import client.model.card.TradeInterface;
import client.model.player.Player;

/**
 * @author mario
 *
 */
public class PlayerTest {

	Player player;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		player = new Player();
		
		ResourceList resource_list = new ResourceList(6, 6, 6, 6, 6);
		
		DevCardList new_dev_card_list = new DevCardList();
		new_dev_card_list.setDevCardList(1, 1, 1, 1, 1);
		
		DevCardList old_dev_card_list = new DevCardList();
		old_dev_card_list.setDevCardList(1, 1, 1, 1, 1);
		
		int player_id = 0;
		int roads = 15;
		int cities = 4;
		int settlements = 5;
		int soldiers = 1;
		int victory_points = 1;
		int monuments = 1;
		int player_index = 0;
		
		String player_name = "Sam";
		CatanColor player_color = CatanColor.BLUE;
		
		boolean played_dev_card = false;
		boolean discarded = false;
				
		player.setPlayer(resource_list, 
				old_dev_card_list, 
				new_dev_card_list, 
				roads, 
				cities, 
				settlements, 
				soldiers, 
				victory_points, 
				monuments, 
				played_dev_card, 
				discarded, 
				player_id, 
				player_index, 
				player_name, 
				player_color);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link client.model.player.Player#canOfferTrade(client.model.card.TradeInterface)}.
	 */
	@Test
	public void testCanOfferTrade() {
		
		Trade trade_one = new Trade(0, 0, 3, 0, 0);
		
		Trade trade_two = new Trade(0, 0, -17, 0, 0);
		
		assertTrue(player.canOfferTrade(trade_one));
		
		assertFalse(player.canOfferTrade(trade_two));
		
	}

	/**
	 * Test method for {@link client.model.player.Player#canBuyDevCard()}.
	 */
	@Test
	public void testCanBuyDevCard() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link client.model.player.Player#canPlayYearOfPlenty()}.
	 */
	@Test
	public void testCanPlayYearOfPlenty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link client.model.player.Player#canPlayRoadBuilding()}.
	 */
	@Test
	public void testCanPlayRoadBuilding() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link client.model.player.Player#canPlaySoldier()}.
	 */
	@Test
	public void testCanPlaySoldier() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link client.model.player.Player#canAcceptTrade(client.model.card.TradeInterface)}.
	 */
	@Test
	public void testCanAcceptTrade() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link client.model.player.Player#canDiscardCards(client.model.card.ResourceList)}.
	 */
	@Test
	public void testCanDiscardCards() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link client.model.player.Player#canPlayMonument()}.
	 */
	@Test
	public void testCanPlayMonument() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link client.model.player.Player#canPlayMonopoly()}.
	 */
	@Test
	public void testCanPlayMonopoly() {
		fail("Not yet implemented");
	}

}
