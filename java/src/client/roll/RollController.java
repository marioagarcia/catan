package client.roll;

import java.awt.Frame;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import javax.swing.JOptionPane;

import shared.model.GameModel;
import shared.model.turntracker.TurnTracker;
import client.base.*;
import client.manager.ClientModelFacade;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

	private IRollResultView resultView;
	private Boolean isRolling;
	
	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
		
		ClientModelFacade.getInstance(null).addObserver(gameModelObserver);
		setResultView(resultView);
		isRolling = false;
	}
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}
	
	public void startRoll(){
		setIsRolling(true);
		((RollView)getRollView()).startRollTimer();
		getRollView().setMessage("Automatically rolling in 5 seconds...");
		if(!getRollView().isModalShowing()){	
			getRollView().showModal();
		}
	}
	
	@Override
	public void rollDice() {
		ClientModelFacade facade = ClientModelFacade.getInstance(null);
		
		DiceRoller diceRoller = new DiceRoller();
		int rolledNumber = diceRoller.roll();
		
		if (getRollView().isModalShowing()){
			getRollView().closeModal();
		}
		
		getResultView().setRollValue(rolledNumber);
		if(!getResultView().isModalShowing()){
			getResultView().showModal();
		}
		//facade.roll(rolledNumber);
		//setIsRolling(false);

	}
	
	public void setIsRolling(Boolean b){
		isRolling = b;
	}
	
	private Observer gameModelObserver = new Observer(){

		@Override
		public void update(Observable o, Object arg) {
			TurnTracker tt = ((GameModel)o).getTurnTracker();
			
			if(ClientModelFacade.getInstance(null).canRoll() && !isRolling){
				startRoll();
			}
		}
	};
}

