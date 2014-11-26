package test.server;


import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import server.command.*;
import server.facade.ServerModelFacade;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.locations.VertexDirection;
import shared.model.manager.GameList;
import shared.serialization.parameters.*;
import shared.model.player.Player;

public class CommandsTest extends TestCase {

    ServerModelFacade serverModelFacade;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        serverModelFacade = ServerModelFacade.getInstance();
        serverModelFacade.loadTestGames();
    }
    
    @Test
    public void testFinishTurn(){
    	//Assert false when it's not the player's turn
    	FinishTurn command = new FinishTurn(new FinishTurnParameters(1), 13);
    	command.execute();
    	assertFalse(command.wasSuccessful());
    	
    	//Assert false when it's the player's turn but the game state is not playing
    	command = new FinishTurn(new FinishTurnParameters(0), 15);
    	assertFalse(command.wasSuccessful());
    	
    	//Assert true when it's the player's turn and the game state is playing
    	command = new FinishTurn(new FinishTurnParameters(0), 11);
    	command.execute();
    	assertTrue(command.wasSuccessful());
    }
    
    @Test
    public void testBuyDevCard(){
    	//Assert false when it's not the player's turn
    	BuyDevCard command = new BuyDevCard(new BuyDevCardParameters(1), 11);
    	command.execute();
    	assertFalse(command.wasSuccessful());
    	
    	//Assert false when it's the player's turn but the player doesn't have 1 sheep, 1 wheat, 1 ore
    	command = new BuyDevCard(new BuyDevCardParameters(0), 12);
    	command.execute();
    	assertFalse(command.wasSuccessful());
    	
    	//Number of dev cards in the bank and in a player's hand before a dev card is bought
    	int prevBankDevCardCount = ServerModelFacade.getInstance().getGameModel(13).getDevCardBank().getSize();
    	int prevPlayerDevCardCount = ServerModelFacade.getInstance().getGameModel(13).getPlayerList().get(0).getNewDevCards().getDevCardListSize();
    	
    	//Assert true when it's the player's turn and the player has 1 sheep, 1 wheat, 1 ore
    	command = new BuyDevCard(new BuyDevCardParameters(0), 13);
    	command.execute();
    	
    	//Number of dev cards in the bank and in the player's hand after a dev card is bought
    	int postBankDevCardCount = ServerModelFacade.getInstance().getGameModel(13).getDevCardBank().getSize();
    	int postPlayerDevCardCount = ServerModelFacade.getInstance().getGameModel(13).getPlayers().getPlayer(0).getNewDevCards().getDevCardListSize();
    	
    	//Assert true that the bank has 1 less dev card after the player buys a dev card
    	assertTrue(prevBankDevCardCount - 1 == postBankDevCardCount);
    	
    	//Assert true that the player has one more dev card than before the player bought the dev card
    	assertTrue(prevPlayerDevCardCount + 1 == postPlayerDevCardCount);
    }
    
    
    @Test
    public void testPlayYearOfPlenty(){
    	//Assert false when it's not the player's turn
    	PlayYearOfPlenty command = new PlayYearOfPlenty(new YearOfPlentyParameters(1, "brick", "brick"), 11);
    	command.execute();
    	assertFalse(command.wasSuccessful());
    	
    	//Assert false when it's the player's turn but the player doesn't have a year of plenty card
    	command = new PlayYearOfPlenty(new YearOfPlentyParameters(0, "brick", "brick"), 12);
    	command.execute();
    	assertFalse(command.wasSuccessful());
    	
    	//Number of year of plentys before the card is played
    	int prevCount = ServerModelFacade.getInstance().getGameModel(11).getPlayers().getPlayer(0).getOldDevCards().getYearOfPlenty();
    	
    	//Assert true when it's the player's turn and the player has a year of plenty card
    	command = new PlayYearOfPlenty(new YearOfPlentyParameters(0, "brick", "brick"), 11);
    	command.execute();
    	
    	int postCount = ServerModelFacade.getInstance().getGameModel(11).getPlayers().getPlayer(0).getOldDevCards().getYearOfPlenty();   	
    	//Assert true that the player has one less year of plenty card after it is played
    	assertTrue(prevCount - 1 == --postCount);
    }

    @Test
    public void testPlaySoldier(){
    	//Assert false when it's not the player's turn
    	PlaySoldier command = new PlaySoldier(new SoldierParameters(1, 0, new HexLocation(0, 0)), 11);
    	command.execute();
    	assertFalse(command.wasSuccessful());
    	
    	//Assert false when it's the player's turn but the player doesn't have a soldier card
    	command = new PlaySoldier(new SoldierParameters(0, 1, new HexLocation(0, 0)), 11);
    	command.execute();
    	assertFalse(command.wasSuccessful());
    	
    	//Assert true when it's the player's turn and the player has a soldier card
    	command = new PlaySoldier(new SoldierParameters(0, 1, new HexLocation(0, 0)), 11);
    	command.execute();
    }
    
    @Test
    public void testPlayMonopoly(){
    	//Assert false when it's not the player's turn
    	PlayMonopoly command = new PlayMonopoly(new MonopolyParameters("sheep", 1), 11);
    	command.execute();
    	assertFalse(command.wasSuccessful());
    	
    	//Assert false when it's the player's turn but the player doesn't have a monopoly card
    	command = new PlayMonopoly(new MonopolyParameters("sheep", 0), 12);
    	command.execute();
    	assertFalse(command.wasSuccessful());
    	
    	//Assert true when it's the player's turn and the player has a monopoly card
    	command = new PlayMonopoly(new MonopolyParameters("sheep", 0), 11);
    	command.execute();
    }
    
    @Test
    public void testPlayMonument(){
    	//Assert false when it's not the player's turn
    	PlayMonument command = new PlayMonument(new MonumentParameters(1), 11);
    	command.execute();
    	assertFalse(command.wasSuccessful());
    	
    	//Assert false when it's the player's turn but the player doesn't have a monument card
    	command = new PlayMonument(new MonumentParameters(0), 12);
    	command.execute();
    	assertFalse(command.wasSuccessful());
   	
    	//Assert true when it's the player's turn and the player has a monument card
    	command = new PlayMonument(new MonumentParameters(0), 15);
    	command.execute();
    }
}


