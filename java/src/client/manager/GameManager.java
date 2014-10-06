package client.manager;

import java.util.ArrayList;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.serialization.ModelSerializer;
import shared.serialization.parameters.*;
import client.communication.server.ServerProxy;
import client.logging.GameLog;
import client.manager.interfaces.GMBoardMapInterface;
import client.manager.interfaces.GMPlayerInterface;
import client.manager.interfaces.GMDomesticTradeInterface;
import client.model.GameInfo;
import client.model.card.DevCardBank;
import client.model.card.DevCardInterface.DevCardType;
import client.model.card.MaritimeTrade;
import client.model.card.ResourceCardBank;
import client.model.card.ResourceList;
import client.model.card.TradeInterface;
import client.model.map.HexInterface;
import client.model.player.Player;
import client.model.player.PlayerInfo;
import client.model.player.PlayerInterface;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;
import client.roll.DiceRoller;

public class GameManager implements GameManagerInterface {
	
	ServerProxy serverProxy;
	ArrayList<GameInfo> gameList;
	ModelSerializer modelSerializer;
	Player localPlayer;
	GameInfo currentGame;
	GameLog gameLog;
	TurnTracker turnTracker;
	DiceRoller diceRoller;
	GMBoardMapInterface boardMap;
	DevCardBank devCardBank;
	ResourceCardBank resCardBank;
	
	public GameManager() {
		serverProxy = null; //serverProxy.getInstance();
		//serverPoller = serverPoller.getInstance();
		modelSerializer = new ModelSerializer(); //modelSerializer.getInstance();
	}

	@Override
	public void populateGameList() {
		String gameListStr = serverProxy.listGames();
		//gameList = modelSerializer.deserializeGamesList(gameListStr);
	}
	
	private boolean validatePlayer() {
		return serverProxy.validatePlayer(localPlayer);
	}

	@Override
	public boolean canJoinGame(CatanColor color, GameInfo game) {
		if(validatePlayer() /*&& game.validateColor(color)*/) {
				return true;
		}		
		return false;
	}
	
	@Override
	public void joinGame(CatanColor color, GameInfo game) {
		JoinGameRequestParameters param = new JoinGameRequestParameters(game.getId(), color.toString().toLowerCase());
		String JSONString = modelSerializer.serializeJoinGameRequest(param);
		String result = serverProxy.joinGame(JSONString);
		if(result == "Success") {
			currentGame = game;
		}
		else {
			//figure out how to respond with the bad response that 			
		}
	}

	@Override
	public boolean getGameModel() {
		//TODO
		return false;
	}

	//TODO
	private boolean resetFromGameModel(GameData gameModel) {
		//reset model classes
		/*
		 * ClientModel {
bank (ResourceList): The cards available to be distributed to the players.,
chat (MessageList): All the chat messages.,
log (MessageList): All the log messages.,
map (Map),
players (array[Player]),
tradeOffer (TradeOffer, optional): The current trade offer, if there is one.,
turnTracker (TurnTracker): This tracks who's turn it is and what action's being done.,
version (index): The version of the model. This is incremented whenever anyone makes a move.,
winner (index): This is -1 when nobody's won yet. When they have, it's their order index [0-3]
}
ResourceList {
brick (integer),
ore (integer),
sheep (integer),
wheat (integer),
wood (integer)
}
MessageList {
lines (array[MessageLine])
}
MessageLine {
message (string),
source (string)
}
Map {
hexes (array[Hex]): A list of all the hexes on the grid - it's only land tiles,
ports (array[Port]),
roads (array[Road]),
settlements (array[VertexObject]),cities (array[VertexObject]),
radius (integer): The radius of the map (it includes the center hex, and the ocean hexes; pass
this into the hexgrid constructor),
robber (HexLocation): The current location of the robber
}
Hex {
location (HexLocation),
resource (string, optional) = ['Wood' or 'Brick' or 'Sheep' or 'Wheat' or 'Ore']: What resource this
tile gives - it's only here if the tile is not desert.,
number (integer, optional): What number is on this tile. It's omitted if this is a desert hex.
}
HexLocation {
x (integer),
y (integer)
}
Port {
resource (string, optional) = ['Wood' or 'Brick' or 'Sheep' or 'Wheat' or 'Ore']: What type
resource this port trades for. If it's omitted, then it's for any resource.,
location (HexLocation): Which hex this port is on. This shows the (ocean/non-existent) hex to
draw the port on.,
direction (string) = ['NW' or 'N' or 'NE' or 'E' or 'SE' or 'SW']: Which edge this port is on.,
ratio (integer): The ratio for trade in (ie, if this is 2, then it's a 2:1 port.
}
EdgeValue {
owner (index): The index (not id) of the player who owns this piece (0-3),
location (EdgeLocation): The location of this road.
}
EdgeLocation {
x (integer),
y (integer),
direction (string) = ['NW' or 'N' or 'NE' or 'SW' or 'S' or 'SE']
}
VertexObject {
owner (index): The index (not id) of the player who owns thie piece (0-3),
location (EdgeLocation): The location of this road.
}
Player {
cities (number): How many cities this player has left to play,
color (string): The color of this player.,discarded (boolean): Whether this player has discarded or not already this discard phase.,
monuments (number): How many monuments this player has played.,
name (string),
newDevCards (DevCardList): The dev cards the player bought this turn.,
oldDevCards (DevCardList): The dev cards the player had when the turn started.,
playerIndex (index): What place in the array is this player? 0-3. It determines their turn order.
This is used often everywhere.,
playedDevCard (boolean): Whether the player has played a dev card this turn.,
playerID (integer): The unique playerID. This is used to pick the client player apart from the
others. This is only used here and in your cookie.,
resources (ResourceList): The resource cards this player has.,
roads (number),
settlements (integer),
soldiers (integer),
victoryPoints (integer)
}
DevCardList {
monopoly (number),
monument (number),
roadBuilding (number),
soldier (number),
yearOfPlenty (number)
}
TradeOffer {
sender (integer): The index of the person offering the trade,
receiver (integer): The index of the person the trade was offered to.,
offer (ResourceList): Positive numbers are resources being offered. Negative are resources
being asked for.
}
TurnTracker {
currentTurn (index): Who's turn it is (0-3),
status (string) = ['Rolling' or 'Robbing' or 'Playing' or 'Discarding' or 'FirstRound' or
'SecondRound']: What's happening now,
longestRoad (index, optional): The index of who has the longest road,
largestArmy (index, optional): The index of who has the biggest army (3 or more)
}ASampleJ
		 */
		return false;
	}

	@Override
	public boolean resetGame() { 
		String JSONString = serverProxy.resetGame();
		GameData reset_game_data = modelSerializer.deserializeGameModel(JSONString);
		if(resetFromGameModel(reset_game_data))
			return true;
		else
			return false;
	}

	@Override
	public boolean getGameCommands() {
		String JSONString = serverProxy.getGameCommands();
		modelSerializer.deserializeGetGameCommands(JSONString);
		return true;
	}

	@Override
	public boolean postGameCommands() {
		//String JSONString = modelSerializer
		//serverProxy.postGameCommands(JSONString);
		return false;
	}

	@Override
	public boolean canAcceptTrade(TradeInterface trade) {
		boolean player_condition_met = localPlayer.canAcceptTrade(trade);
		boolean turn_condition_met = (turnTracker.getCurrentTurn() == localPlayer.getPlayerIndex());
		return (player_condition_met && turn_condition_met);
	}

	@Override
	public boolean acceptTrade(TradeInterface trade, boolean accept) {
		int player_index = localPlayer.getPlayerIndex();
		String JSONString = modelSerializer.serializeAcceptTrade(new AcceptTradeParameters(player_index, accept));
		serverProxy.acceptTrade(JSONString);
		return false;
	}

	@Override
	public boolean canDiscardCards(ResourceList list) {
		return localPlayer.canDiscardCards(list);
	}

	@Override
	public boolean discardCards(ResourceList list) {
		int player_index = localPlayer.getPlayerIndex();
		String JSONString = modelSerializer.serializeDiscardCards(new DiscardCardsParameters(player_index, list));
		serverProxy.discardCards(JSONString); //this should throw an exception	
		return true;
	}

	@Override
	public boolean canRoll() {
		int player_index = localPlayer.getPlayerIndex();
		return turnTracker.canRoll(player_index);
	}

	@Override
	public int roll() {
		int player_index = localPlayer.getPlayerIndex();
		int number = diceRoller.roll();
		String JSONString = modelSerializer.serializeRollNumber(new RollNumberParameters(player_index, number));
		serverProxy.rollNumber(JSONString);
		return 0;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		return boardMap.canBuildRoad(location, player_index);
	}

	@Override
	public boolean buildRoad(EdgeLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		boolean isFree = (TurnTracker.Status.FIRST_ROUND == turnTracker.getStatus());
		BuildRoadParameters param = new BuildRoadParameters(player_index, new EdgeLocationParameters(location), isFree);
		String JSONString = modelSerializer.serializeBuildRoad(param);
		serverProxy.rollNumber(JSONString);
		return true;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		return boardMap.canBuildSettlement(location, player_index);
	}

	@Override
	public boolean buildSettlement(VertexLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		boolean isFree = (TurnTracker.Status.FIRST_ROUND == turnTracker.getStatus());
		
		BuildSettlementParameters param = new BuildSettlementParameters(player_index, new VertexLocationParameters(location), isFree);
		
		String JSONString = modelSerializer.serializeBuildSettlement(param);
		serverProxy.rollNumber(JSONString);
		return true;
	}

	@Override
	public boolean canBuildCity(VertexLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		
		return boardMap.canBuildCity(location, player_index);
	}

	@Override
	public boolean buildCity(VertexLocation location) {
		int player_index = localPlayer.getPlayerIndex();
		
		BuildCityParameters param = new BuildCityParameters(player_index, new VertexLocationParameters(location));
		
		String JSONString = modelSerializer.serializeBuildCity(param);
		serverProxy.rollNumber(JSONString);
		
		return true;
	}

	@Override
	public boolean canOfferTrade(TradeInterface trade) {
		return localPlayer.canOfferTrade(trade);
	}

	//TODO
	@Override
	public boolean offerTrade(TradeInterface trade, int otherPlayerIndex) {
		GMDomesticTradeInterface trade_i = null;
		int local_player_index = localPlayer.getPlayerIndex();
		
		ResourceList resource_list = new ResourceList(trade_i.getBrickCount(), trade_i.getOreCount(), trade_i.getSheepCount(), trade_i.getWheatCount(), trade_i.getWoodCount());
		
		String JSONString = modelSerializer.serializeOfferTrade(new OfferTradeParameters(local_player_index, resource_list, otherPlayerIndex));
		serverProxy.offerTrade(JSONString);
		
		return true;
	}

	@Override
	public boolean canMaritimeTrade(HexInterface location, MaritimeTrade trade) {
		return localPlayer.canOfferTrade(trade);
	}

	@Override
	public boolean maritimeTrade(HexInterface location, MaritimeTrade trade) {
		int player_index = localPlayer.getPlayerIndex();
		String resource_in = trade.getResourceIn().toString().toLowerCase();
		String resource_out = trade.getResourceOut().toString().toLowerCase();
		
		MaritimeTradeParameters param = new MaritimeTradeParameters(player_index, trade.getRatio(), resource_in, resource_out);
		
		String JSONString = modelSerializer.serializeMaritimeTrade(param);
		serverProxy.offerTrade(JSONString);
		
		return true;
	}

	@Override
	public boolean canFinishTurn() {
		return (turnTracker.getStatus() == Status.PLAYING);
	}

	@Override
	public boolean finishTurn() {
		int player_index = localPlayer.getPlayerIndex();
		
		FinishTurnParameters param = new FinishTurnParameters(player_index);
		
		String JSONString = modelSerializer.serializeFinishTurn(param);
		
		serverProxy.finishTurn(JSONString);		
		
		return true;
	}

	@Override
	public boolean canBuyDevCard() {
		boolean player_condition_met = localPlayer.canBuyDevCard();
		boolean turn_condition_met = turnTracker.canBuyDevCard(localPlayer.getPlayerIndex());
		boolean deck_condition_met = true; //devCardBank.containsAnyCard();
		
		return (player_condition_met && turn_condition_met && deck_condition_met );
	}

	@Override
	public boolean buyDevCard() {
		int player_index = localPlayer.getPlayerIndex();
		
		BuyDevCardParameters param = new BuyDevCardParameters(player_index);
		
		String JSONString = modelSerializer.serializeBuyDevCard(param);
		serverProxy.buyDevCard(JSONString);
		
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty(ResourceType type1, ResourceType type2) {
		boolean player_condition_met = localPlayer.canPlayYearOfPlenty();
		boolean resource_bank_condition_met = (resCardBank.containsCard(type1) && resCardBank.containsCard(type2));
		boolean turn_condition_met = turnTracker.canPlayDevCard(localPlayer.getPlayerIndex());
 
		return (player_condition_met && turn_condition_met && resource_bank_condition_met);
	}

	@Override
	public boolean playYearOfPlenty(ResourceType type1, ResourceType type2) {
		int player_index = localPlayer.getPlayerIndex();
		String resourceOne = type1.toString().toLowerCase();
		String resourceTwo = type2.toString().toLowerCase();
		
		YearOfPlentyParameters param = new YearOfPlentyParameters(player_index, resourceOne, resourceTwo);
		
		String JSONString = modelSerializer.serializeYearOfPlenty(param);
		serverProxy.playYearOfPlenty(JSONString);
		
		return false;
	}

	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2) {
		boolean player_condition_met = localPlayer.canPlayRoadBuilding();
		boolean turn_condition_met = turnTracker.canPlayDevCard(localPlayer.getPlayerIndex());
		boolean map_condition_met = true; //boardMap.canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2, localPlayer.getPlayerIndex());
		
		return (player_condition_met && turn_condition_met && map_condition_met);
	}

	@Override
	public boolean playRoadBuilding(EdgeLocation location1,	EdgeLocation location2) {
		int player_index = localPlayer.getPlayerIndex();
		EdgeLocationParameters edge_location1_param = new EdgeLocationParameters(location1);
		EdgeLocationParameters edge_location2_param = new EdgeLocationParameters(location2);
		
		RoadBuildingParameters param = new RoadBuildingParameters(player_index, edge_location1_param, edge_location2_param);
		
		String JSONString = modelSerializer.serializeRoadBuilding(param);
		serverProxy.playRoadBuilding(JSONString);
		
		return true;
	}

	@Override
	public boolean canPlaySoldier(HexLocation oldLocation,	HexLocation newLocation, int victimIndex) {
		boolean player_condition_met = localPlayer.canPlaySoldier();
		boolean turn_condition_met = turnTracker.canPlayDevCard(localPlayer.getPlayerIndex());
		boolean map_condition_met = true; //boardMap.canPlaySoldier(HexInterface oldLocation, HexInterface newLocation);
		boolean robbed_player_condition_met = true; //currentGame.playerCanBeRobbed(victimIndex);
		
		return (player_condition_met && turn_condition_met && map_condition_met && robbed_player_condition_met);
	}

	@Override
	public boolean playSoldier(HexLocation newLocation, int victimIndex) {
		int player_index = localPlayer.getPlayerIndex();
		
		SoldierParameters param = new SoldierParameters(player_index, victimIndex, newLocation);
		
		String JSONString = modelSerializer.serializeSoldier(param);
		
		serverProxy.playSoldier(JSONString);
		
		return true;
	}

	@Override
	public boolean canPlayMonopoly() {
		boolean player_condition_met = true;//localPlayer.canPlayDevCard(devCardType);
		boolean turn_condition_met = turnTracker.canPlayDevCard(localPlayer.getPlayerIndex());
		
		return (player_condition_met && turn_condition_met);
	}

	@Override
	public boolean playMonopoly(ResourceType resourceType) {
		int player_index = localPlayer.getPlayerIndex();
		String resource = resourceType.toString().toLowerCase();
		
		String JSONString = modelSerializer.serializeMonopoly(new MonopolyParameters(resource, player_index));
		
		serverProxy.playMonopoly(JSONString);
		
		return true;
	}

	@Override
	public boolean canPlayMonument() {
		boolean player_condition_met = localPlayer.canPlayMonument();
		boolean turn_condition_met = turnTracker.canPlayDevCard(localPlayer.getPlayerIndex());
		
		return (player_condition_met && turn_condition_met);
	}

	@Override
	public boolean playMonument() {
		int player_index = localPlayer.getPlayerIndex();
		
		String JSONString = modelSerializer.serializeMonument(new MonumentParameters(player_index));
		
		serverProxy.playMonopoly(JSONString);
		
		return true;
	}

	@Override
	public boolean registerPlayer(String username, String password) {
		CredentialsParameters credentials = new CredentialsParameters(username, password);
		
		String JSONString = modelSerializer.serializeCredentials(credentials);
		
		serverProxy.register(JSONString);
		
		return true;
	}

	@Override
	public boolean loginPlayer(String username, String password) {
		CredentialsParameters credentials = new CredentialsParameters(username, password);
		
		String JSONString = modelSerializer.serializeCredentials(credentials);
		
		serverProxy.login(JSONString);
		
		return true;
	}

}
