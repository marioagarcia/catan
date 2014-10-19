package client.communication;

import java.util.*;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.logging.GameLog;
import client.logging.history.LogLineInterface;
import client.model.player.Player;
import shared.definitions.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController {

	private class GameLogObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			GameLog game_log = (GameLog)o;
			GameHistoryController.this.update(game_log);
		}
		
	}

	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		
		ModelFacade.getInstance(null).getManager().getGameLog().addObserver(new GameLogObserver());
	}
	
	@Override
	public IGameHistoryView getView() {
		return (IGameHistoryView)super.getView();
	}

	private void update(GameLog game_log){
		ArrayList<Player> players = ModelFacade.getInstance(null).getManager().getAllPlayers();
		
		List<LogEntry> entries = new ArrayList<LogEntry>();
		
		for(int i = 0; i < game_log.getGameHistoryLog().getSize(); i++){
			
			LogLineInterface line = game_log.getGameHistoryLog().getLogLine(i);
			
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

