package server.handler.facade.real;

public interface GamesHandlerFacadeInterface extends HandlerFacadeInterface{
	
	//public GamesList getGamesList();
	
	public Boolean createGame();
	
	public Boolean joinGame();
	
	public Boolean saveGame();
	
	public Boolean loadGame();
	
}
