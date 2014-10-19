package state;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.serialization.interfaces.SerializerResourceListInterface;
import client.manager.interfaces.GMDomesticTradeInterface;
import client.map.MapController;
import client.model.card.MaritimeTrade;
import client.model.card.TradeInterface;

public class PlayingState extends GameState{
	public PlayingState(MapController c){
		super(c);
	}

	@Override
	public boolean resetGame(){ return false; }
	
	@Override
	public boolean saveGame(){ return false; }
	
	@Override
	public boolean getGameCommands(){ return false; }
	
	@Override
	public boolean postGameCommands(){ return false; }
	
	@Override
	public boolean canAcceptTrade(TradeInterface trade){ return false; }
	
	@Override
	public boolean acceptTrade(TradeInterface trade, boolean accept){ return false; }
	
	@Override
	public boolean canDiscardCards(SerializerResourceListInterface list){ return false; }
	
	@Override
	public boolean discardCards(SerializerResourceListInterface list){ return false; }
	
	@Override
	public boolean canBuildRoad(EdgeLocation location){ return false; }
	
	@Override
	public boolean buildRoad(EdgeLocation location){ return false; }
	
	@Override
	public boolean canBuildSettlement(VertexLocation location){ return false; }
	
	@Override
	public boolean buildSettlement(VertexLocation location){ return false; }
	
	@Override
	public boolean canBuildCity(VertexLocation location){ return false; }
	
	@Override
	public boolean buildCity(VertexLocation location){ return false; }
	
	@Override
	public boolean canOfferTrade(TradeInterface trade){ return false; }
	
	@Override
	public boolean offerTrade(GMDomesticTradeInterface trade, int otherPlayerIndex){ return false; }
	
	@Override
	public boolean canMaritimeTrade(VertexLocation location, MaritimeTrade trade){ return false; }
	
	@Override
	public boolean maritimeTrade(VertexLocation location, MaritimeTrade trade){ return false; }
	
	@Override
	public boolean canFinishTurn(){ return false; }
	
	@Override
	public boolean finishTurn(){ return false; }
	
	@Override
	public boolean canBuyDevCard(){ return false; }
	
	@Override
	public boolean buyDevCard(){ return false; }
	
	@Override
	public boolean canPlayYearOfPlenty(ResourceType type1, ResourceType type2){ return false; }
	
	@Override
	public boolean playYearOfPlenty(ResourceType type1, ResourceType type2){ return false; }
	
	@Override
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2){ return false; }
	
	@Override
	public boolean playRoadBuilding(EdgeLocation location1, EdgeLocation location2){ return false; }
	
	@Override
	public boolean canPlaySoldier(HexLocation oldLocation, HexLocation newLocation, int victimIndex){ return false; }
	
	@Override
	public boolean playSoldier(HexLocation newLocation, int victimIndex){ return false; }
	
	@Override
	public boolean canPlayMonopoly(){ return false; }
	
	@Override
	public boolean playMonopoly(ResourceType resourceType){ return false; }
	
	@Override
	public boolean canPlayMonument(){ return false; }
	
	@Override
	public boolean playMonument(){ return false; }
}
