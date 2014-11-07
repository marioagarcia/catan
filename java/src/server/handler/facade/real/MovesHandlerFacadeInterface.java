package server.handler.facade.real;

public interface MovesHandlerFacadeInterface extends HandlerFacadeInterface{
	
	public Boolean sendChat();
	
	public Boolean rollNumber();
	
	public Boolean robPlayer();
	
	public Boolean finishTurn();
	
	public Boolean buyDevCard();
	
	public Boolean playYearOfPlenty();
	
	public Boolean playRoadBuilding();
	
	public Boolean playSoldier();
	
	public Boolean playMonopoly();
	
	public Boolean playMonument();
	
	public Boolean buildRoad();
	
	public Boolean buildSettlement();
	
	public Boolean buildCity();
	
	public Boolean offerTrade();
	
	public Boolean acceptTrade();
	
	public Boolean maritimeTrade();
	
	public Boolean discardCards();
}
