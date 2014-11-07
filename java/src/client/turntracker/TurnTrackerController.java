package client.turntracker;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.model.GameInfo;
import shared.model.GameModel;
import shared.model.facade.ModelFacade;
import shared.model.player.Player;
import shared.model.player.PlayerInfo;
import shared.model.player.Players;
import shared.model.turntracker.TurnTracker;
import shared.model.turntracker.TurntrackerInterface.Status;
import client.base.*;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {

	private final int TOTAL_PLAYERS = 4;

	boolean playersInitialized;

	public TurnTrackerController(ITurnTrackerView view) {

		super(view);

		ModelFacade.getInstance(null).addGameListObserver(gameListObserver);
		ModelFacade.getInstance(null).addObserver(gameModelObserver);

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

	public void initializePlayers(Players players, int local_player_index) {
		if(players.getPlayerList().size() == TOTAL_PLAYERS) {
			playersInitialized = true;
		}

		for (Player player : players.getPlayerList()) {

			getView().initializePlayer(player.getPlayerIndex(), player.getName(), player.getColor());

		}

		getView().setLocalPlayerColor(players.getPlayer(local_player_index).getColor());
	}

	private void addJoinedPlayers(){

		if(!playersInitialized) {
			
			ModelFacade facade = ModelFacade.getInstance(null);

			GameInfo local_game_info = facade.getCurrentGame();

			//Find the updated current game from the game list and get the player list from that game

			List<PlayerInfo> player_list = getPlayerList(local_game_info); 

			if(player_list != null){

				if(player_list.size() == TOTAL_PLAYERS) {
					playersInitialized = true;
				}

				int player_index = 0;

				for(PlayerInfo player : player_list) {
					getView().initializePlayer(player_index++, player.getName(), player.getColor());
				}
			}
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

		ModelFacade.getInstance(null).finishTurn();
	}

	private Observer gameListObserver = new Observer(){

		@Override
		public void update(Observable o, Object arg) {
			if(!playersInitialized){
				addJoinedPlayers();
			}
		}

	};

	private Observer gameModelObserver = new Observer() {

		@Override
		public void update(Observable o, Object arg) {

			GameModel game_model = (GameModel)o;

			TurnTracker turn_tracker = game_model.getTurnTracker();

			Players players = game_model.getPlayers();

			if(!playersInitialized) {
				initializePlayers(players, game_model.getLocalPlayer().getPlayerIndex());
			}
			else {
				updatePlayers(players, turn_tracker);
			}

			boolean local_turn = turn_tracker.isLocalPlayerTurn();
			boolean can_finish_turn = ModelFacade.getInstance(null).canFinishTurn();
			boolean enable = can_finish_turn && local_turn;

			getView().updateGameState(makeMessage(turn_tracker.getStatus(), local_turn, can_finish_turn), enable);
		}
	};

}

