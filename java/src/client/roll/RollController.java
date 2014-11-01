package client.roll;

import java.awt.Frame;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import javax.swing.JOptionPane;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.GameModel;
import client.model.turntracker.TurnTracker;


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
		
		ModelFacade.getInstance(null).addObserver(gameModelObserver);
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
		ModelFacade facade = ModelFacade.getInstance(null);
		
		DiceRoller diceRoller = new DiceRoller();
		int rolledNumber = diceRoller.roll();
		
		facade.roll(rolledNumber);
		if (getRollView().isModalShowing()){
			getRollView().closeModal();
		}
		
		getResultView().setRollValue(rolledNumber);
		if(!getResultView().isModalShowing()){
			getResultView().showModal();
		}
		setIsRolling(false);

	}
	
	public void setIsRolling(Boolean b){
		isRolling = b;
	}
	
	private Observer gameModelObserver = new Observer(){

		@Override
		public void update(Observable o, Object arg) {
			TurnTracker tt = ((GameModel)o).getTurnTracker();
			
			if(ModelFacade.getInstance(null).canRoll() && !isRolling){
				startRoll();
			}
		}
	};
}

