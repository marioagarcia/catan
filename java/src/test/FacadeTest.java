//package test;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import shared.definitions.CatanColor;
//import shared.definitions.ResourceType;
//import client.facade.ModelFacade;
//import client.communication.server.ServerMoxy;
//import shared.model.manager.GameManager;
//import shared.model.GameInfo;
//import shared.model.card.DevCardList;
//import shared.model.card.DomesticTrade;
//import shared.model.card.ResourceList;
//import shared.model.player.PlayerInfo;
//import shared.model.turntracker.TurntrackerInterface.Status;
//
//public class FacadeTest {
//
//	private ClientModelFacade facade;
//	private ClientGameManager manager;
//	
//	@Before
//	public void setUp(){
//		facade = ClientModelFacade.getInstance(new ServerMoxy());
//		manager = facade.getManager();
//
//		facade.loginPlayer("Sam", "sam");
//		
//		GameInfo g = new GameInfo();
//		g.setId(0);
//		g.setTitle("My game");
//		facade.joinGame(CatanColor.ORANGE, g);
//		
//		//Always start on the local player's turn
//		manager.getTurnTracker().setCurrentTurn(0);
//	}
//	
//	@Test
//	public void testCanJoinGame() {
//		
//		GameInfo g = new GameInfo();
//		
//		ArrayList<PlayerInfo> playerList = createPlayers(2);
//		
//		g.setGameInfo("TestGame", 0, playerList);
//		
//		//Player with color Bronze already exists in game
//		assertFalse(facade.canJoinGame(CatanColor.BRONZE, g));
//		
//		//Color Puce is available
//		assertTrue(facade.canJoinGame(CatanColor.PUCE, g));
//		
//		/*
//		playerList = createPlayers(4);
//		gameInfo.setGameInfo("TestGame", 173, playerList);*/
//		
//		g = new GameInfo();
//		g.setId(173);
//		g.setTitle("My game");
//		
//		//Can join game is only verifying color. Since a valid color was given, it returned true, even though the game doesn't exist
//		//assertFalse(facade.canJoinGame(CatanColor.PUCE, g));
//	}
//	
//	@Test
//	public void testCanAcceptTrade(){
//		
//		manager.getTurnTracker().setStatus(Status.PLAYING);
//		
//		//Player can accept when it is not their turn
//		manager.getTurnTracker().setCurrentTurn(2);
//		DomesticTrade trade = new DomesticTrade(1, 2, new ResourceList(1, 0, 0, 0, -1));
//		assertTrue(facade.canAcceptTrade(trade));
//		
//		//Player cannot accept trade on their turn
//		manager.getTurnTracker().setCurrentTurn(0);
//		assertFalse(facade.canAcceptTrade(trade));
//		
//		//Player does not have the required resources
//		manager.getTurnTracker().setCurrentTurn(2);
//		trade = new DomesticTrade(1, 2, new ResourceList(20, 2, 0, -10, -5));
//		assertFalse(facade.canAcceptTrade(trade));
//	}
//	
//	@Test
//	public void testCanDiscardCards(){
//		
//		//Player does not have over 7 cards
//		manager.getTurnTracker().setStatus(Status.DISCARDING);
//		manager.getLocalPlayer().setDiscarded(false);
//		manager.getLocalPlayer().setResourceList(new ResourceList(0, 0, 0, 0, 7));
//		assertFalse(facade.canDiscardCards(manager.getLocalPlayer().getResourceList()));
//		
//		//Player has more than 7 cards and is in the discarding phase
//		manager.getLocalPlayer().setResourceList(new ResourceList(1, 0, 0, 0, 7));
//		assertTrue(facade.canDiscardCards(manager.getLocalPlayer().getResourceList()));
//		
//		//Definitely more than 7 cards
//		manager.getLocalPlayer().setResourceList(new ResourceList(20, 20, 20, 20, 20));
//		assertTrue(facade.canDiscardCards(manager.getLocalPlayer().getResourceList()));
//			
//		//Not the player's turn, and in the rolling state
//		manager.getTurnTracker().setCurrentTurn(1);
//		manager.getTurnTracker().setStatus(Status.ROLLING);
//		manager.getLocalPlayer().setResourceList(new ResourceList(2, 0, 3, 0, 9));
//		assertFalse(facade.canDiscardCards(manager.getLocalPlayer().getResourceList()));
//	}
//	
//	@Test
//	public void testCanRollNumber(){
//		
//		//Correct turn and game state
//		manager.getTurnTracker().setCurrentTurn(0);
//		manager.getTurnTracker().setStatus(Status.ROLLING);
//		assertTrue(facade.canRoll());
//		
//		//Can't roll if it is not your turn
//		manager.getTurnTracker().setCurrentTurn(2);
//		assertFalse(facade.canRoll());
//		
//		//Correct turn, but wrong state
//		manager.getTurnTracker().setCurrentTurn(0);
//		manager.getTurnTracker().setStatus(Status.DISCARDING);
//		assertFalse(facade.canRoll());
//		
//		//Wrong turn and wrong state
//		manager.getTurnTracker().setCurrentTurn(2);
//		assertFalse(facade.canRoll());
//	}
//	
//	@Test
//	public void testCanOfferTrade(){
//		manager.getTurnTracker().setStatus(Status.PLAYING);
//		
//		//Player can offer when it is their turn, and they have enough resources
//		manager.getTurnTracker().setCurrentTurn(0);
//		manager.getLocalPlayer().setResourceList(new ResourceList(20, 20, 20, 20, 20));
//		DomesticTrade trade = new DomesticTrade(1, 2, new ResourceList(-1, 0, 0, 0, 1));
//		assertTrue(facade.canOfferTrade(trade));
//		
//		//Player cannot offer trade on another player's turn
//		manager.getTurnTracker().setCurrentTurn(2);
//		assertFalse(facade.canOfferTrade(trade));
//		
//		//Player does not have the required resources
//		manager.getTurnTracker().setCurrentTurn(2);
//		manager.getLocalPlayer().setResourceList(new ResourceList(0, 0, 0, 0, 0));
//		trade = new DomesticTrade(1, 2, new ResourceList(20, 2, 0, -10, -5));
//		assertFalse(facade.canOfferTrade(trade));
//	}
//	
//	@Test
//	public void testCanFinishTurn(){
//		
//		//Player's turn but not in the playing state
//		manager.getTurnTracker().setCurrentTurn(0);
//		manager.getTurnTracker().setStatus(Status.ROLLING);
//		assertFalse(facade.canFinishTurn());	
//		
//		//Player's turn and in the play state
//		manager.getTurnTracker().setStatus(Status.PLAYING);
//		assertTrue(facade.canFinishTurn());	
//		
//		//Cannot end turn if it is not currently your turn
//		manager.getTurnTracker().setCurrentTurn(2);
//		assertFalse(facade.canFinishTurn());
//	}
//	
//	@Test
//	public void testCanBuyDevCard(){
//		
//		manager.getTurnTracker().setStatus(Status.PLAYING);
//		manager.getDevCardBank().setDeck(2, 2, 2, 2, 2);
//		
//		//Player can buy on their turn, and they have enough resources to buy dev card
//		manager.getTurnTracker().setCurrentTurn(0);
//		manager.getLocalPlayer().setResourceList(new ResourceList(20, 20, 20, 20, 20));
//		assertTrue(facade.canBuyDevCard());
//		
//		//Player cannot buy a dev card on another player's turn
//		manager.getTurnTracker().setCurrentTurn(2);
//		assertFalse(facade.canBuyDevCard());
//		
//		//Player does not have the required resources
//		manager.getTurnTracker().setCurrentTurn(2);
//		manager.getLocalPlayer().setResourceList(new ResourceList(0, 0, 0, 0, 0));
//		assertFalse(facade.canBuyDevCard());
//		
//		//Bank has no dev cards available to buy
//		manager.getTurnTracker().setCurrentTurn(0);
//		manager.getDevCardBank().setDeck(0, 0, 0, 0, 0);
//		manager.getLocalPlayer().setResourceList(new ResourceList(20, 20, 20, 20, 20));
//		assertFalse(facade.canBuyDevCard());
//	}
//	
//	@Test
//	public void testCanPlayYearOfPlenty(){
//		
//		//Load bank with enough resources
//		manager.getResCardBank().setBank(10, 10, 10, 10, 10);
//		
//		//Cannot play dev card during rolling
//		manager.getTurnTracker().setStatus(Status.ROLLING);
//		assertFalse(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));
//		
//		manager.getTurnTracker().setStatus(Status.PLAYING);
//		
//		//Player has the card, and it is their turn
//		manager.getTurnTracker().setCurrentTurn(0);
//		DevCardList dev_cards = new DevCardList();
//		dev_cards.setYearOfPlenty(1);
//		manager.getLocalPlayer().setNewDevCards(dev_cards);
//		assertTrue(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));
//		
//		 //Not the player's turn
//		manager.getTurnTracker().setCurrentTurn(2);
//		assertFalse(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));
//		
//		//Player does not have year_of_plenty card
//		dev_cards.setYearOfPlenty(0);
//		manager.getTurnTracker().setCurrentTurn(0);
//		manager.getLocalPlayer().setNewDevCards(dev_cards);
//		assertFalse(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));
//		
//		//Player has the card, and it is their turn, but the bank doesn't have the cards
//		dev_cards.setYearOfPlenty(1);
//		manager.getLocalPlayer().setNewDevCards(dev_cards);
//		manager.getResCardBank().setBank(0, 0, 0, 0, 0);
//		assertFalse(facade.canPlayYearOfPlenty(ResourceType.BRICK, ResourceType.WOOD));	
//	}
//	
//	@Test
//	public void testCanPlayMonopoly(){
//		
//		manager.getTurnTracker().setStatus(Status.PLAYING);
//		
//		manager.getTurnTracker().setCurrentTurn(0);
//		DevCardList dev_cards = new DevCardList();
//		dev_cards.setMonopoly(1);
//		
//		//Player has the card, and it is their turn
//		manager.getLocalPlayer().setNewDevCards(dev_cards);
//		assertTrue(facade.canPlayMonopoly());
//		
//		 //Cannot play dev card during rolling state
//		manager.getTurnTracker().setStatus(Status.ROLLING);
//		assertFalse(facade.canPlayMonopoly());
//		
//		 //Cannot play when it is not your turn
//		manager.getTurnTracker().setCurrentTurn(2);
//		assertFalse(facade.canPlayMonopoly());
//		
//		//Player does not have monopoly card
//		manager.getTurnTracker().setStatus(Status.PLAYING);
//		dev_cards.setMonopoly(0);
//		manager.getTurnTracker().setCurrentTurn(0);
//		manager.getLocalPlayer().setNewDevCards(dev_cards);
//		assertFalse(facade.canPlayMonopoly());
//	}
//	
//	@Test
//	public void testCanPlayMonument(){
//		manager.getTurnTracker().setStatus(Status.PLAYING);
//		
//		manager.getTurnTracker().setCurrentTurn(0);
//		DevCardList dev_cards = new DevCardList();
//		dev_cards.setMonument(1);
//		
//		//Player has the card, and it is their turn
//		manager.getLocalPlayer().setNewDevCards(dev_cards);
//		assertTrue(facade.canPlayMonument());
//		
//		 //Cannot play dev card during rolling state
//		manager.getTurnTracker().setStatus(Status.ROLLING);
//		assertFalse(facade.canPlayMonument());
//		
//		 //Cannot play when it is not your turn
//		manager.getTurnTracker().setCurrentTurn(2);
//		assertFalse(facade.canPlayMonument());
//		
//		//Player does not have monument card
//		manager.getTurnTracker().setStatus(Status.PLAYING);
//		dev_cards.setMonument(0);
//		manager.getTurnTracker().setCurrentTurn(0);
//		manager.getLocalPlayer().setNewDevCards(dev_cards);
//		assertFalse(facade.canPlayMonument());	
//	}
//	
//	public ArrayList<PlayerInfo> createPlayers(int numPlayers){
//		
//		ArrayList<CatanColor> colorList = new ArrayList<CatanColor>();
//		colorList.add(CatanColor.BLUE);
//		colorList.add(CatanColor.BRONZE);
//		colorList.add(CatanColor.GREEN);
//		colorList.add(CatanColor.ORANGE);
//		
//		ArrayList<String> nameList = new ArrayList<String>();
//		nameList.add("Mario");
//		nameList.add("Kevin");
//		nameList.add("Chris");
//		nameList.add("Casey");
//		
//		ArrayList<PlayerInfo> playerList = new ArrayList<PlayerInfo>();
//		for(int i = 0; i < numPlayers; i++){
//			PlayerInfo playerInfo = new PlayerInfo();
//			playerInfo.setPlayerInfo(colorList.get(i), nameList.get(i), i);
//			playerList.add(playerInfo);
//		}
//		return playerList;
//	}
//}
