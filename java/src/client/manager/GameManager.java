package client.manager;

import java.util.ArrayList;

import shared.definitions.CatanColor;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.serialization.ModelSerializer;
import client.communication.server.ServerProxy;
import client.model.GameInfo;
import client.model.card.MaritimeTrade;
import client.model.card.ResourceList;
import client.model.card.TradeInterface;
import client.model.map.HexInterface;
import client.model.player.PlayerInfo;
import client.model.player.PlayerInterface;

public class GameManager implements GameManagerInterface {
	
	ServerProxy serverProxy;
	ArrayList<GameInfo> gameList;
	ModelSerializer modelSerializer;
	PlayerInfo localPlayer;
	public GameManager() {
		serverProxy = null; //serverProxy.getInstance();
		//serverPoller = serverPoller.getInstance();
		modelSerializer = new ModelSerializer(); //modelSerializer.getInstance();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void populateGameList() {
		String gameListStr = serverProxy.listGames();
		gameList = (ArrayList<GameInfo>)modelSerializer.deserialize(gameListStr, ModelSerializer.ObjectType.GAME_LIST);
	}
	
	@Override
	public PlayerInterface loginPlayer(String username, String password) {
		
		
		return null;
	}
	
	private boolean validatePlayer() {
		return serverProxy.validatePlayer(localPlayer);
	}

	@Override
	public boolean canJoinGame(CatanColor color, GameInfo game) {
		if(validatePlayer() && game.validateColor(color)) {
				return true;
		}		
		return false;
	}
	
	@Override
	public void joinGame(CatanColor color, GameInfo game) {
		String JSONString = modelSerializer.serializeJoinGameRequest(color, game);
		serverProxy.joinGame(JSONString);		
	}

	@Override
	public boolean getGameModel() {
		//TODO make data holding class and merge method
		return false;
	}
	
	private boolean merge() {
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
		if(serverProxy.resetGame() == "success") {
			resetModel();
			return true;
		}
		return false;
	}
	
	private void resetModel() {
		//reset all the model classes 
	}

	@Override
	public boolean getGameCommands() {
		
		return false;
	}

	@Override
	public boolean postGameCommands() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAcceptTrade(TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptTrade(TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canDiscardCards(ResourceList list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean discardCards() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRoll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int roll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canBuildRoad(EdgeLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildRoad(EdgeLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildSettlement(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildSettlement(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildCity(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildCity(VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canOfferTrade(TradeInterface trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerTrade(TradeInterface trade, int otherPlayerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMaritimeTrade(HexInterface location, MaritimeTrade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean maritimeTrade(HexInterface location, MaritimeTrade trade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canFinishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playYearOfPlenty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1,
			EdgeLocation location2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playRoadBuilding(EdgeLocation location1,
			EdgeLocation location2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaySoldier(HexInterface oldLocation,
			HexInterface newLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playSoldier(HexInterface oldLocation,
			HexInterface newLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonopoly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playMonopoly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonument() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playMonument() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PlayerInterface registerPlayer(PlayerInfo playerInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logoutPlayer(int playerId) {
		// TODO Auto-generated method stub
		
	}

}
