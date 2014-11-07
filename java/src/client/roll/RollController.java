package client.roll;

import java.util.Observable;
import java.util.Observer;
import client.base.*;
import client.facade.ClientModelFacade;


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
		
		DiceRoller diceRoller = new DiceRoller();
		int rolledNumber = diceRoller.roll();
		
		if (getRollView().isModalShowing()){
			getRollView().closeModal();
		}
		
		getResultView().setRollValue(rolledNumber);
		if(!getResultView().isModalShowing()){
			getResultView().showModal();
		}

	}
	
	public void setIsRolling(Boolean b){
		isRolling = b;
	}
	
	private Observer gameModelObserver = new Observer(){

		@Override
		public void update(Observable o, Object arg) {
			
			if(ClientModelFacade.getInstance(null).canRoll() && !isRolling){
				startRoll();
			}
		}
	};
}

