package client.communication;

import java.util.*;

import client.base.*;
import client.facade.ClientModelFacade;
import shared.definitions.*;
import shared.model.GameModel;
import shared.model.logging.history.LogLineInterface;
import shared.model.player.Player;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController {

	private class GameModelObserver implements Observer{

		@Override
		public void update(Observable o, Object arg){
			GameHistoryController.this.update((GameModel)o);
		}
		
	}

	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		
		ClientModelFacade.getInstance(null).addObserver(new GameModelObserver());
	}
	
	@Override
	public IGameHistoryView getView() {
		return (IGameHistoryView)super.getView();
	}

	private void update(GameModel game_manager){
		List<LogEntry> entries = new ArrayList<LogEntry>();
		
		for(int i = 0; i < game_manager.getGameLog().getGameHistoryLog().getSize(); i++){
			
			LogLineInterface line = game_manager.getGameLog().getGameHistoryLog().getLogLine(i);
			
			CatanColor playerColor = CatanColor.WHITE;
			
			for(Player player : game_manager.getPlayers().getPlayerList()){
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

