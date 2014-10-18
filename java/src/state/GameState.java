package state;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.serialization.interfaces.SerializerResourceListInterface;
import client.communication.facade.ModelFacade;
import client.manager.interfaces.GMDomesticTradeInterface;
import client.map.MapController;
import client.model.card.MaritimeTrade;
import client.model.card.TradeInterface;

public class GameState
{
	protected ModelFacade facade = null;
	protected MapController controller = null;
	
	public GameState(){
		facade = ModelFacade.getInstance(null);
	}
	
	public boolean resetGame(){ return false; }
	
	
	public boolean saveGame(){ return false; }
	
	
	public boolean getGameCommands(){ return false; }
	
	
	public boolean postGameCommands(){ return false; }
	
	
	public boolean canSendChat(){ 
		return true; 
	}
	
	public boolean sendChat(String chatMessage){ 
		return facade.sendChat(chatMessage);
	}
	
	
	public boolean canAcceptTrade(TradeInterface trade){ return false; }
	public boolean acceptTrade(TradeInterface trade, boolean accept){ return false; }
	
	
	public boolean canDiscardCards(SerializerResourceListInterface list){ return false; }
	public boolean discardCards(SerializerResourceListInterface list){ return false; }
	
	
	public boolean canRoll(){ return false; }
	public boolean roll(){ return false; }
	
	
	public boolean canBuildRoad(EdgeLocation location){ return false; }
	public boolean buildRoad(EdgeLocation location){ return false; }
	
	
	public boolean canBuildSettlement(VertexLocation location){ return false; }
	public boolean buildSettlement(VertexLocation location){ return false; }
	
	public boolean canBuildCity(VertexLocation location){ return false; }
	public boolean buildCity(VertexLocation location){ return false; }
	
	
	public boolean canOfferTrade(TradeInterface trade){ return false; }
	public boolean offerTrade(GMDomesticTradeInterface trade, int otherPlayerIndex){ return false; }
	
	
	public boolean canMaritimeTrade(VertexLocation location, MaritimeTrade trade){ return false; }
	public boolean maritimeTrade(VertexLocation location, MaritimeTrade trade){ return false; }
	
	
	public boolean canFinishTurn(){ return false; }
	public boolean finishTurn(){ return false; }
	
	
	public boolean canBuyDevCard(){ return false; }
	public boolean buyDevCard(){ return false; }
	

	public boolean canPlayYearOfPlenty(ResourceType type1, ResourceType type2){ return false; }
	public boolean playYearOfPlenty(ResourceType type1, ResourceType type2){ return false; }
	
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2){ return false; }
	public boolean playRoadBuilding(EdgeLocation location1, EdgeLocation location2){ return false; }
	
	
	public boolean canPlaySoldier(HexLocation oldLocation, HexLocation newLocation, int victimIndex){ return false; }
	public boolean playSoldier(HexLocation newLocation, int victimIndex){ return false; }
	
	
	public boolean canPlayMonopoly(){ return false; }
	public boolean playMonopoly(ResourceType resourceType){ return false; }
	
	
	public boolean canPlayMonument(){ return false; }
	public boolean playMonument(){ return false; }
}
