package client.join;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.GameModel;
import client.model.player.Player;
import client.model.player.PlayerInfo;
import client.model.player.Players;
import client.model.turntracker.TurnTracker;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {
	
	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
		ModelFacade.getInstance(null).addObserver(new GameModelObserver());
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		ModelFacade facade = ModelFacade.getInstance(null);
		String[] listAI = facade.getListAI(); //Retrieve AIList
		PlayerInfo[] players = getPlayerArray(facade.getAllPlayers()); //Retrieve player array
		
		getView().setAIChoices(listAI); //Set AIList
		getView().setPlayers(players); //Set player list
		if (!getView().isModalShowing()){
			getView().showModal();
		}
		
		if(((PlayerWaitingView)getView()).isReady() && getView().isModalShowing()){ //If there are 4 players
			getView().closeModal();
		}
	}
	
	public void refresh(){
		if (getView().isModalShowing()){
			getView().closeModal();
		}
		ModelFacade facade = ModelFacade.getInstance(null);
		
		String[] listAI = facade.getListAI(); //Retrieve AIList
		PlayerInfo[] players = getPlayerArray(facade.getAllPlayers()); //Retrieve player array
		
		getView().setAIChoices(listAI);
		getView().setPlayers(players); //Set player list
		if (!getView().isModalShowing()){
			getView().showModal();
		}
		
		if(((PlayerWaitingView)getView()).isReady() && getView().isModalShowing()){ //If there are 4 players
			getView().closeModal();
		}
	}
	
	//Turns the Players object into an array of players
	public PlayerInfo[] getPlayerArray(Players playerObj){
		List<Player> playerList = playerObj.getPlayerList();
		
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
	private class GameModelObserver implements Observer{

		@Override
		public void update(Observable o, Object arg) {
			TurnTracker tt = ((GameModel)o).getTurnTracker();
			if(tt != null && tt.getStatus() != null){
				if (getView().isModalShowing()){
					getView().closeModal();
				}
				if (!((PlayerWaitingView)getView()).isReady()){
					start();
				}
			}	
		}
	}
}

