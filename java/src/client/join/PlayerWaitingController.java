package client.join;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.model.GameInfo;
import shared.model.manager.GameList;
import shared.model.player.PlayerInfo;
import client.base.*;
import client.manager.ClientModelFacade;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {
	
	private GameList gameList;
	Boolean hasLoggedIn;
	
	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
		ClientModelFacade.getInstance(null).addGameListObserver(gameListObserver);
		hasLoggedIn = false;
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		hasLoggedIn = true;
		ClientModelFacade facade = ClientModelFacade.getInstance(null);
		
		String[] listAI = facade.getListAI(); //Retrieve AIList
		GameInfo localGameInfo = facade.getCurrentGame();
	
		//Find the updated current game from the game list and get the player list from that game
		//
		List<PlayerInfo> playerList = getPlayerList(localGameInfo); 
		//Convert the list of players into an array of players
		//
		PlayerInfo[] players = getPlayerArray(playerList);
		
		getView().setAIChoices(listAI); //Set AIList
		getView().setPlayers(players); //Set player list
		if (!getView().isModalShowing()){
			getView().showModal();
		}
		
		if(((PlayerWaitingView)getView()).isReady() && getView().isModalShowing()){ //If there are 4 players
			getView().closeModal();
			facade.stopListPoller();
		}
	}
	
	//Gets the list of players from the game from the updated game list that corresponds to the current game
	public List<PlayerInfo> getPlayerList(GameInfo gi){
		ClientModelFacade facade = ClientModelFacade.getInstance(null);
		
		GameInfo[] gameList = facade.getGamesList();

		for(int i = 0; i < gameList.length; i++){
			if(gameList[i].getId() == gi.getId()){
				return gameList[i].getPlayers();
			}
		}
		return null;
	}
	
	//Turns the Players object into an array of players
	public PlayerInfo[] getPlayerArray(List<PlayerInfo> playerList){
		
		PlayerInfo[] players = new PlayerInfo[playerList.size()];
		
		for(int i = 0; i < playerList.size(); i++){
			PlayerInfo playerInfo = new PlayerInfo();
			playerInfo.setPlayerInfo(playerList.get(i).getColor(), playerList.get(i).getName(), playerList.get(i).getId());
			
			players[i] = playerInfo;
		}
		
		return players;
	}

	@Override
	public void addAI() {
		ClientModelFacade facade = ClientModelFacade.getInstance(null);
		
		String ai = getView().getSelectedAI();//Retrieve the selected AI
		
		if(facade.addAI(ai)){
			facade.updateGameModel();
		}
	}
	//Observes the Players object and updates the view when the Players object changes
	private Observer gameListObserver = new Observer(){

		@Override
		public void update(Observable o, Object arg) {
			if(hasLoggedIn){
				if (getView().isModalShowing()){
					getView().closeModal();
				}
				start();
			}
		}
	};
}

