package client.join;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.manager.GameList;
import client.model.GameInfo;
import client.model.GameModel;
import client.model.player.Player;
import client.model.player.PlayerInfo;
import client.model.player.Players;
import client.model.turntracker.TurnTracker;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {
	
	private GameList gameList;
	Boolean hasLoggedIn;
	
	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
		ModelFacade.getInstance(null).addGameListObserver(gameListObserver);
		hasLoggedIn = false;
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		hasLoggedIn = true;
		ModelFacade facade = ModelFacade.getInstance(null);
		
		String[] listAI = facade.getListAI(); //Retrieve AIList
		//PlayerInfo[] players = getPlayerArray(facade.getAllPlayers()); //Retrieve player array
		GameInfo localGameInfo = facade.getCurrentGame();
	
		//Find the updated current game from the game list and get the player list from that game
		//
		List<PlayerInfo> playerList = getPlayerList(localGameInfo); 
		//Convert the list of players into an array of players
		//
		PlayerInfo[] players = getPlayerArray(playerList);
for(int i = 0; i < players.length; i++){
	System.out.println(players[i].toString());
}
		getView().setAIChoices(listAI); //Set AIList
		getView().setPlayers(players); //Set player list
		if (!getView().isModalShowing()){
			getView().showModal();
		}
		
		if(((PlayerWaitingView)getView()).isReady() && getView().isModalShowing()){ //If there are 4 players
			getView().closeModal();
		}
	}
	
	//Gets the list of players from the game from the updated game list that corresponds to the current game
	public List<PlayerInfo> getPlayerList(GameInfo gi){
		ModelFacade facade = ModelFacade.getInstance(null);
		
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
		ModelFacade facade = ModelFacade.getInstance(null);
		
		String ai = getView().getSelectedAI();//Retrieve the selected AI
		
		if(facade.addAI(ai)){
			facade.updateGameModel();
		}
	}
	//Observes the Players object and updates the view when the Players object changes
	private Observer gameListObserver = new Observer(){

		@Override
		public void update(Observable o, Object arg) {
System.out.println("Updating");
			if(hasLoggedIn){
				if (getView().isModalShowing()){
					getView().closeModal();
				}
				start();
			}
/*			if(tt != null && tt.getStatus() != null){
				if (getView().isModalShowing()){
					getView().closeModal();
				}
					start();
			}	*/
		}
	};
}

