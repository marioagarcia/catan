package client.join;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.CatanColor;
import client.base.*;
import client.communication.facade.ModelFacade;
import client.misc.*;
import client.model.GameInfo;
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
	
	public void getGamesList(){
		ModelFacade facade = ModelFacade.getInstance(null);
		GameInfo[] games = facade.getGamesList();
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.setPlayerInfo(selectColorView.getSelectedColor(), 
								 facade.getLocalPlayer().getName(),
								 facade.getLocalPlayer().getPlayerId());
		
		
		getJoinGameView().setGames(games, playerInfo);
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
		getGamesList();
		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame() {
		
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		
		getNewGameView().closeModal();
	}

	@Override
	public void createNewGame() {
		ModelFacade facade = ModelFacade.getInstance(null);
		String gameName = newGameView.getTitle();
		Boolean randTiles = newGameView.getRandomlyPlaceHexes();
		Boolean randNumbers = newGameView.getRandomlyPlaceNumbers();
		Boolean randPorts = newGameView.getUseRandomPorts();
		
		if(facade.createNewGame(gameName, randTiles, randNumbers, randPorts)){
			getGamesList();
			getNewGameView().closeModal();
		}else{
			messageView.setTitle("Create Game Error");
			messageView.setMessage("Unable to create game.  Please try again");
			messageView.showModal();
		}
	}

	@Override
	public void startJoinGame(GameInfo game) {
		chosenGame = game;
		disableColors();
		((SelectColorView)getSelectColorView()).disableJoinButton();
		getSelectColorView().showModal();
	}
	
	public void disableColors(){
		List<PlayerInfo> players = chosenGame.getPlayers();
		for(int i = 0; i < players.size(); i++){
			if(players.get(i) != null){
				if(players.get(i).getColor() != null){
					selectColorView.setColorEnabled(players.get(i).getColor(), false);
				}
			}
		}
	}

	@Override
	public void cancelJoinGame() {
	
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {
		
		ModelFacade facade = ModelFacade.getInstance(null);
		if(facade.canJoinGame(color, chosenGame)){
		// If join succeeded
			facade.joinGame(color, chosenGame);
			getSelectColorView().closeModal();
			getJoinGameView().closeModal();
			joinAction.execute();
		}else{
			messageView.setTitle("Join Game Error");
			messageView.setMessage("Unable to join game.  Please try again");
			messageView.showModal();
		}
	}

}

