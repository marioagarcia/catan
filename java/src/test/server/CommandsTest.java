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
    }

    private boolean finishTurn(int player_index, int game_id) {
        FinishTurnParameters finish_turn_parameters = new FinishTurnParameters(player_index);
        FinishTurn finish_turn = new FinishTurn(finish_turn_parameters, game_id);
        finish_turn.execute();
        return finish_turn.wasSuccessful();
    }

    private boolean buildRoad(int x, int y, EdgeDirection direction, boolean free, int player_index, int game_id) {
        EdgeLocation location = new EdgeLocation(new HexLocation(x, y), direction);
        BuildRoadParameters build_road_parameters = new BuildRoadParameters(player_index, new EdgeLocationParameters(location), free);
        BuildRoad build_road = new BuildRoad(build_road_parameters, game_id);
        build_road.execute();
        return build_road.wasSuccessful();
    }

    private boolean buildSettlement(int x, int y, VertexDirection direction, int player_index, boolean free, int game_id) {
        VertexLocation vertex_location = new VertexLocation(new HexLocation(x, y), direction);
        BuildSettlementParameters build_settlement_parameters = new BuildSettlementParameters(player_index, new VertexLocationParameters(vertex_location), free);
        BuildSettlement build_settlement = new BuildSettlement(build_settlement_parameters, game_id);
            build_settlement.execute();
        return build_settlement.wasSuccessful();
    }

    private boolean registerPlayer(String username, String password) {
        Register register = new Register(new CredentialsParameters(username, password));
        register.execute();
        return register.wasSuccessful();
    }

    private boolean loginPlayer(String username, String password) {
        Login login = new Login(new CredentialsParameters(username, password));
        login.execute();
        return login.wasSuccessful();
    }

    private boolean createGame(boolean random_ports, boolean random_resources, boolean random_hexes, String name) {
        CreateGameRequestParameters create_game_request_parameters = new CreateGameRequestParameters(random_resources, random_hexes, random_ports, name);
        CreateGame create_game = new CreateGame(create_game_request_parameters);
        create_game.execute();
        return create_game.wasSuccessful();
    }

    private boolean joinGame(int game_id, String color, int player_id) {
        JoinGameParameters join_game_parameters = new JoinGameParameters(game_id, color);
        JoinGame join_game = new JoinGame(join_game_parameters, player_id);
        join_game.execute();
        return join_game.wasSuccessful();
    }

    private void addPlayerResources(int player_index, int game_id, ResourceType type, int amount) {
        this.setPlayerResources(player_index, game_id, type, amount + this.getPlayerResources(player_index, game_id, type));
    }

    private int getPlayerResources(int player_index, int game_id, ResourceType type) {
        return serverModelFacade.getGameModel(game_id).getPlayers().getPlayer(player_index).getResourceList().getResourceByType(type);
    }

    private void setPlayerResources(int player_index, int game_id, ResourceType type, int amount){
        serverModelFacade.getGameModel(game_id).getPlayers().getPlayer(player_index).getResourceList().setResourceByType(type, amount);
    }

    public void testCommands() {
        //try to login a player that doesn't  exist
        assertEquals(false, this.loginPlayer("username", "password"));
        //register a player
        assertEquals(true, this.registerPlayer("username", "password"));
        //log in a valid player
        assertEquals(true, this.loginPlayer("username", "password"));
        //make sure that there is only one game currently (the default one)
        assertEquals(1, serverModelFacade.getGameList().size());
        //create a new game
        assertEquals(true, this.createGame(false, false, false, "name"));
        //check to make sure that the new game is in the game list
        assertEquals(2, serverModelFacade.getGameList().size());
        //join the game
        assertEquals(true, this.joinGame(serverModelFacade.getGameList().getGameList().get(1).getId(), CatanColor.RED.toString(), 0));
        //join the game again (for instance, if you restarted the client)
        assertEquals(true, this.joinGame(serverModelFacade.getGameList().getGameList().get(1).getId(), CatanColor.RED.toString(), 0));
        //register more players
        this.registerPlayer("one", "asdfasdf");
        this.registerPlayer("two", "asdfasdf");
        this.registerPlayer("three", "asdfasdf");
        //have the new players join the game as well
        //the first one tries to use a bad color the first time
        assertEquals(false, this.joinGame(serverModelFacade.getGameList().getGameList().get(1).getId(), CatanColor.RED.toString(), 1));
        assertEquals(true, this.joinGame(serverModelFacade.getGameList().getGameList().get(1).getId(), CatanColor.BLUE.toString(), 1));
        assertEquals(true, this.joinGame(serverModelFacade.getGameList().getGameList().get(1).getId(), CatanColor.GREEN.toString(), 2));
        assertEquals(true, this.joinGame(serverModelFacade.getGameList().getGameList().get(1).getId(), CatanColor.PUCE.toString(), 3));
        //have player one build a free road and settlement
        assertEquals(true, this.buildSettlement(0, 0, VertexDirection.NorthEast, 0, true,serverModelFacade.getGameList().getGameList().get(1).getId()));
        assertEquals(true, this.buildRoad(0, 0, EdgeDirection.North, true, 0,serverModelFacade.getGameList().getGameList().get(1).getId()));

        //try to let a player play out of turn
        assertEquals(false, this.buildSettlement(1,1,VertexDirection.NorthEast, 1, true,serverModelFacade.getGameList().getGameList().get(1).getId()));

        for(ResourceType type : ResourceType.values()){
            this.addPlayerResources(0,1,type, 5);
        }
        assertEquals(false, this.buildRoad(0,0,EdgeDirection.NorthWest, false, 0,serverModelFacade.getGameList().getGameList().get(1).getId()));

        for(ResourceType type : ResourceType.values()){
            this.setPlayerResources(0,1,type, 0);
        }

        //have another player try to end a turn
        assertEquals(false, this.finishTurn(1,serverModelFacade.getGameList().getGameList().get(1).getId()));

        //have the player finish his turn
        assertEquals(true, this.finishTurn(0,serverModelFacade.getGameList().getGameList().get(1).getId()));

        //have all of the players finish their turns
        assertEquals(true, this.buildSettlement(-2, 2, VertexDirection.NorthEast, 1, true,serverModelFacade.getGameList().getGameList().get(1).getId()));
        assertEquals(true, this.buildRoad(-2, 2, EdgeDirection.North, true, 1,serverModelFacade.getGameList().getGameList().get(1).getId()));
        assertEquals(true, this.finishTurn(1,serverModelFacade.getGameList().getGameList().get(1).getId()));

        assertEquals(true, this.buildSettlement(-2, 0, VertexDirection.NorthEast, 2, true,serverModelFacade.getGameList().getGameList().get(1).getId()));
        assertEquals(true, this.buildRoad(-2, 0, EdgeDirection.North, true, 2,serverModelFacade.getGameList().getGameList().get(1).getId()));
        assertEquals(true, this.finishTurn(2,serverModelFacade.getGameList().getGameList().get(1).getId()));

        assertEquals(true, this.buildSettlement(2, 0, VertexDirection.NorthEast, 3, true,serverModelFacade.getGameList().getGameList().get(1).getId()));
        assertEquals(true, this.buildRoad(2, 0, EdgeDirection.North, true, 3,serverModelFacade.getGameList().getGameList().get(1).getId()));
        assertEquals(true, this.finishTurn(3,serverModelFacade.getGameList().getGameList().get(1).getId()));







        assertEquals(true, this.buildSettlement(2, 0, VertexDirection.SouthWest, 3, true,serverModelFacade.getGameList().getGameList().get(1).getId()));
//        assertEquals(true, this.buildRoad(2, 0, EdgeDirection.South, true, 3,serverModelFacade.getGameList().getGameList().get(1).getId()));
//        assertEquals(true, this.finishTurn(3,serverModelFacade.getGameList().getGameList().get(1).getId()));
//
//        assertEquals(true, this.buildSettlement(-2, 0, VertexDirection.SouthWest, 2, true,serverModelFacade.getGameList().getGameList().get(1).getId()));
//        assertEquals(true, this.buildRoad(-2, 0, EdgeDirection.South, true, 2,serverModelFacade.getGameList().getGameList().get(1).getId()));
//        assertEquals(true, this.finishTurn(2,serverModelFacade.getGameList().getGameList().get(1).getId()));
//
//        assertEquals(true, this.buildSettlement(-2, 2, VertexDirection.SouthWest, 1, true,serverModelFacade.getGameList().getGameList().get(1).getId()));
//        assertEquals(true, this.buildRoad(-2, 2, EdgeDirection.South, true, 1,serverModelFacade.getGameList().getGameList().get(1).getId()));
//        assertEquals(true, this.finishTurn(1,serverModelFacade.getGameList().getGameList().get(1).getId()));
//
//        assertEquals(true, this.buildSettlement(0, 0, VertexDirection.SouthEast, 0, true,serverModelFacade.getGameList().getGameList().get(1).getId()));
//        assertEquals(true, this.buildRoad(0, 0, EdgeDirection.South, true, 0,serverModelFacade.getGameList().getGameList().get(1).getId()));
//        assertEquals(true, this.finishTurn(1,serverModelFacade.getGameList().getGameList().get(1).getId()));
    }
}

//accepttrade
//buildcity
//buildRoad         ----------------------------------------------------
//buildSettlement   ----------------------------------------------------
//buyDevCard
//changeLogLevel
//createGame
//DiscardCards
//finishTurn
//joinGame          ----------------------------------------------------
//loadGame
//login             ----------------------------------------------------
//maritimeTrade
//offerTrade
//playMonopoly
//playMonument
//playRoadBuilding
//playSoldier
//playYearOfPlenty
//Register          ----------------------------------------------------
//ResetGame
//RobPlayer
//RollNumber
//SaveGame
//SendChat
