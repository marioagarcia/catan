package client.join;

import java.lang.reflect.Array;
import java.util.List;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.player.Player;
import client.model.player.PlayerInfo;
import client.model.player.Players;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		ModelFacade facade = ModelFacade.getInstance(null);
		
		String[] listAI = {"Butthole", "Buttface", "Butthead", "Buttwad"};
		PlayerInfo[] players = getPlayerArray(facade.getPlayers());
		
		getView().setAIChoices(listAI);
		getView().setPlayers(players);
		getView().showModal();
	}
	
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
		
		String ai = getView().getSelectedAI();
		
		getView().closeModal();
	}

}

