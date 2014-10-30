package client.join;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.CatanColor;
import client.base.*;
import client.communication.facade.ModelFacade;
import client.misc.*;
import client.model.GameInfo;
import client.model.player.Player;
import client.model.player.PlayerInfo;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	
	private GameInfo chosenGame;
	private PlayerInfo playerInfo;
	
	/**
	 * JoinGameController constructor
	 * 
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView) {
		
		super(view);
		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
	}
	
	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * 
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * 
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}

	@Override
	public void start() {
		populateGamesList(); //Retrieve the games list from the server and populate the view with it
		
		if (!getJoinGameView().isModalShowing()){
			getJoinGameView().showModal();
		}
	}

	@Override
	public void startCreateNewGame() {
		
		if (!getNewGameView().isModalShowing()){
			getNewGameView().showModal();
		}
	}

	@Override
	public void cancelCreateNewGame() {
		
		if (getNewGameView().isModalShowing()){
			getNewGameView().closeModal();
		}
	}

	@Override
	public void createNewGame() {
		ModelFacade facade = ModelFacade.getInstance(null);
		String gameName = newGameView.getTitle();
		Boolean randTiles = newGameView.getRandomlyPlaceHexes();
		Boolean randNumbers = newGameView.getRandomlyPlaceNumbers();
		Boolean randPorts = newGameView.getUseRandomPorts();
		//If creating the new game is successful
		if(facade.createNewGame(gameName, randTiles, randNumbers, randPorts)){
			populateGamesList(); //Get an updated list of games so this new game will be added to the list and update the view with it
			if (getNewGameView().isModalShowing()){
				getNewGameView().closeModal();
			}
		}else{
			messageView.setTitle("Create Game Error");
			messageView.setMessage("Unable to create game.  Please try again");
			messageView.showModal();
		}
		if (getNewGameView().isModalShowing()){
			getNewGameView().closeModal();
		}
	}

	@Override
	public void startJoinGame(GameInfo game) {
		chosenGame = game;
		if (getJoinGameView().isModalShowing()){
			getJoinGameView().closeModal();
		}
		if(chosenGame != null){
			disableTakenColors();
		}
		if(chosenGame.getPlayers().contains(playerInfo)){
			//If the player is in the game, disable all colors
			((SelectColorView)getSelectColorView()).disableAllColors();
			//Now enable the player's color so that is the only color they can choose
			((SelectColorView)getSelectColorView()).setColorEnabled(getPlayerColor(), true);
			//Disable the join button until the user selects their color
			((SelectColorView)getSelectColorView()).disableJoinButton();
			getSelectColorView().showModal();
		}else{
			//If the player is not in the game, disable the join button until an available color is selected
			((SelectColorView)getSelectColorView()).disableJoinButton();
			getSelectColorView().showModal();
		}
	}
	
	public CatanColor getPlayerColor(){
		int playerIndex = chosenGame.getPlayers().indexOf(playerInfo);
		return chosenGame.getPlayers().get(playerIndex).getColor();
	}
	
	public void populateGamesList(){
		ModelFacade facade = ModelFacade.getInstance(null);
		GameInfo[] games = facade.getGamesList(); //Retrieve the list of games from the server		
		PlayerInfo player = new PlayerInfo(); //Get the local player's color, name, and id 
		player.setPlayerInfo(null, facade.getLocalPlayer().getName(), facade.getLocalPlayer().getPlayerId());
		
		playerInfo = player;
		getJoinGameView().setGames(games, player); //Send the list of games and the local player to the view
	}
	//Disables the colors other players have already taken
	public void disableTakenColors(){
		List<PlayerInfo> players = chosenGame.getPlayers();
		//Iterate through the list of players
		for(int i = 0; i < players.size(); i++){
			if(players.get(i) != null){
				if(players.get(i).getColor() != null){
					selectColorView.setColorEnabled(players.get(i).getColor(), false); //If the player and player's color are not null, disable this color
				}
			}
		}
	}

	@Override
	public void cancelJoinGame() {
		if(getSelectColorView().isModalShowing()){
			getSelectColorView().closeModal();
		}
		if (!getJoinGameView().isModalShowing()){
			getJoinGameView().showModal();
		}
	}

	@Override
	public void joinGame(CatanColor color) {
		ModelFacade facade = ModelFacade.getInstance(null);
		if(chosenGame == null){ //This was primarily used in the beginning for testing

			if (getSelectColorView().isModalShowing()){
				getSelectColorView().closeModal();
			}
			
			if(getJoinGameView().isModalShowing()){
				getJoinGameView().closeModal();
			}
			joinAction.execute();
		}else if(facade.canJoinGame(color, chosenGame)){
		// If join succeeded
			if (getSelectColorView().isModalShowing()){
				getSelectColorView().closeModal();
			}
			facade.joinGame(color, chosenGame); //Join the game with the chosen color
			
			if(getJoinGameView().isModalShowing()){
				getJoinGameView().closeModal();
			}
			facade.updateGameModel();
			joinAction.execute();
		}else{
			messageView.setTitle("Join Game Error");
			messageView.setMessage("Unable to join game.  Please try again");
			messageView.showModal();
		}
	}

}

