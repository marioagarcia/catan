package client.communication;

import java.util.*;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.logging.GameLog;
import client.logging.history.LogLineInterface;
import client.manager.GameManager;
import client.model.player.Player;
import shared.definitions.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController {
	
	GameLog gameLog;
	
	private class GameLogObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			gameLog = (GameLog)o;
			GameHistoryController.this.update();
		}
		
	}

	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		
		initFromModel();
	}
	
	@Override
	public IGameHistoryView getView() {
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
		GameManager manager = ModelFacade.getInstance(null).getManager();
		
		if(manager.getGameLog() == null || manager.getGameLog().getGameHistoryLog() == null){
			return;
		}
		
		gameLog = manager.getGameLog();
		gameLog.addObserver(new GameLogObserver());
		
		this.update();
	}
	
	private void update(){
		ArrayList<Player> players = ModelFacade.getInstance(null).getManager().getAllPlayers();
		
		List<LogEntry> entries = new ArrayList<LogEntry>();
		
		for(int i = 0; i < this.gameLog.getGameHistoryLog().getSize(); i++){
			
			LogLineInterface line = this.gameLog.getGameHistoryLog().getLogLine(i);
			
			CatanColor playerColor = CatanColor.WHITE;
			
			for(Player player : players){
				if(player.getName().equals(line.getPlayerName())){
					playerColor = player.getColor();
				}
			}
			
			LogEntry entry = new LogEntry(playerColor, line.getMove());
			entries.add(entry);
		}
		this.getView().setEntries(entries);
	}
	
}

