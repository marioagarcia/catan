package client.roll;

import java.awt.Frame;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import javax.swing.JOptionPane;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.turntracker.TurnTracker;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

	private IRollResultView resultView;

	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
		
		ModelFacade.getInstance(null).addTurnTrackerObserver(new TurnTrackerObserver());
		setResultView(resultView);
		
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
	
	@Override
	public void rollDice() {
		ModelFacade facade = ModelFacade.getInstance(null);
		
		if(facade.canRoll()){
			DiceRoller diceRoller = new DiceRoller();
			int rolledNumber = diceRoller.roll();
			
			facade.roll(rolledNumber);
			if (getRollView().isModalShowing()){
				getRollView().closeModal();
			}
			
			getResultView().setRollValue(rolledNumber);
			getResultView().showModal();
		}else{
			JOptionPane.showMessageDialog(new Frame(), "You are not allowed to roll right now.", "Roll Error", JOptionPane.ERROR_MESSAGE);
			getRollView().closeModal();
		}
	}
	
	private void updateRollController(){
		((RollView)getRollView()).startRollTimer();
		getRollView().showModal();
	}
	
	

	private class TurnTrackerObserver implements Observer{

		@Override
		public void update(Observable o, Object arg) {
			TurnTracker tt = ModelFacade.getInstance(null).getManager().getTurnTracker();
			
			if(ModelFacade.getInstance(null).canRoll()){
				updateRollController();
			}
		}
		
	}
}

