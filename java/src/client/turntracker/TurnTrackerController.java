package client.turntracker;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.player.Player;
import client.model.player.Players;
import client.model.turntracker.TurnTracker;
import client.model.turntracker.TurntrackerInterface.Status;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {

	private final int TOTAL_PLAYERS = 4;
	
	ModelFacade facade;
	boolean playersInitialized;

	public TurnTrackerController(ITurnTrackerView view) {

		super(view);

		facade  = ModelFacade.getInstance(null);

		facade.getManager().getAllPlayers().addObserver(playersObserver);
		facade.getManager().getTurnTracker().addObserver(turnTrackerObserver);

		playersInitialized = false;
	}

	public static String makeMessage(Status status, boolean is_local_turn, boolean can_finish_turn) {

		if(is_local_turn) {
			if(!can_finish_turn) {
				return status.toString();
			}
			else {
				return "Finish Turn";
			}
		}

		return "Waiting for other players";

	}
	
	public void initializePlayers(Players players) {
		
		if(players.getPlayerList().size() == TOTAL_PLAYERS) {
			playersInitialized = true;
		}

		for (Player player : players.getPlayerList()) {

			getView().initializePlayer(player.getPlayerIndex(), player.getName(), player.getColor());

			getView().setLocalPlayerColor(players.getPlayer(players.getLocalPlayerIndex()).getColor());
		}
		
	}
	
	public void updatePlayers(Players players, TurnTracker turnTracker) {
		
		for (Player player : players.getPlayerList()) {

			int player_index = player.getPlayerIndex();

			getView().updatePlayer( player.getPlayerIndex(), 
					player.getPoints(), 
					(turnTracker.getCurrentTurn() == player_index), 
					(turnTracker.getPlayerWithLargestArmy() == player_index), 
					(turnTracker.getPlayerWithLongestRoad() == player_index));
		}
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

		@Override
		public void update(Observable o, Object arg) {
			Players players = (Players)o;
			TurnTracker turnTracker = (TurnTracker)arg;

			if(!playersInitialized) {

				initializePlayers(players);
			}
			else {

				updatePlayers(players, turnTracker);
			}

			boolean local_turn = turnTracker.isLocalPlayerTurn();
			boolean can_finish_turn = facade.canFinishTurn();
			boolean enable = can_finish_turn && local_turn;

			getView().updateGameState(makeMessage(turnTracker.getStatus(), local_turn, can_finish_turn), enable);
		}
	};

	private Observer turnTrackerObserver = new Observer() {

		@Override
		public void update(Observable o, Object arg) {

			TurnTracker turnTracker = (TurnTracker)o;

			Players players = turnTracker.getPlayers();

			if(!playersInitialized) {

				initializePlayers(players);
			}
			else {

				updatePlayers(players, turnTracker);
			}

			boolean local_turn = turnTracker.isLocalPlayerTurn();
			boolean can_finish_turn = facade.canFinishTurn();
			boolean enable = can_finish_turn && local_turn;

			getView().updateGameState(makeMessage(turnTracker.getStatus(), local_turn, can_finish_turn), enable);
		}
	};

}

