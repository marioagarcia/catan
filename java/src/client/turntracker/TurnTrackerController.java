package client.turntracker;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.player.Player;
import client.model.player.Players;
import client.model.turntracker.TurnTracker;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {
	
	ModelFacade facade;
	boolean playersInitialized;

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
		facade  = ModelFacade.getInstance(null);
		
		facade.getManager().getAllPlayers().addObserver(playersObserver);
		facade.getManager().getTurnTracker().addObserver(turnTrackerObserver);
		
		playersInitialized = false;
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		
		facade.finishTurn();
	}
	
	private Observer playersObserver = new Observer() {
		
		private final int TOTAL_PLAYERS = 4;
		
		@Override
		public void update(Observable o, Object arg) {
			Players players = (Players)o;
			TurnTracker turnTracker = (TurnTracker)arg;
			
			if(!playersInitialized) {
				
				if(players.getPlayerList().size() == TOTAL_PLAYERS) {
					playersInitialized = true;
				}
				
				for (Player player : players.getPlayerList()) {
					
					getView().initializePlayer(player.getPlayerIndex(), player.getName(), player.getColor());
					
					getView().setLocalPlayerColor(players.getPlayer(players.getLocalPlayerIndex()).getColor());
				}
			}
			else {
				for (Player player : players.getPlayerList()) {
					
					int player_index = player.getPlayerIndex();
					
					getView().updatePlayer( player.getPlayerIndex(), 
											player.getPoints(), 
											(turnTracker.getCurrentTurn() == player_index), 
											(turnTracker.getPlayerWithLargestArmy() == player_index), 
											(turnTracker.getPlayerWithLongestRoad() == player_index));
				}
			}
			
			boolean enable = facade.canFinishTurn() && turnTracker.isLocalPlayerTurn();
			getView().updateGameState(turnTracker.getStatus().toString(), enable);
		}
	};
	
	private Observer turnTrackerObserver = new Observer() {
		
		@Override
		public void update(Observable o, Object arg) {
			TurnTracker turnTracker = (TurnTracker)o;
			boolean enable = facade.canFinishTurn() && turnTracker.isLocalPlayerTurn();
			getView().updateGameState(turnTracker.getStatus().toString(), enable);
		}
	};

}

