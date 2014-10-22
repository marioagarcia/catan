package client.communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import client.base.*;
import client.communication.facade.ModelFacade;
import client.model.logging.GameLog;
import client.model.logging.chat.GameChatInterface;
import client.model.logging.chat.Message;
import client.model.logging.chat.MessageDoesNotExistException;
import client.model.logging.chat.MessageInterface;
import client.model.player.Player;
import client.model.player.Players;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController {
	
	ModelFacade facade = ModelFacade.getInstance(null);

	public ChatController(IChatView view) {
		
		super(view);
		
		facade.getManager().getGameLog().addObserver(chatObserver);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		facade.sendChat(message);		
	}
	
	private void updateChat(GameChatInterface gameChat) {
		Players players = facade.getManager().getAllPlayers();
		Map<String, CatanColor> colorByIdMap = new HashMap<String, CatanColor>();
		List<LogEntry> entries = new ArrayList<LogEntry>();
		
		for(Player player : players.getPlayerList()) {
			colorByIdMap.put(player.getName(), player.getColor());
		}
		
		for (int message_index = 0; message_index < gameChat.getSize(); message_index++) {
			MessageInterface message = null;
			
			try {
				
				message = gameChat.getMessage(message_index);
				
			} catch (MessageDoesNotExistException e) {
				e.printStackTrace();
			}
			

			System.out.println(message.toString());
			
			LogEntry log_entry = new LogEntry(colorByIdMap.get(message.getPlayerName()), message.getMessageContent());
			
			entries.add(log_entry);
		}
		
		getView().setEntries(entries);
		
	}

	private Observer chatObserver = new Observer() {
		
		@Override
		public void update(Observable o, Object arg) {

			System.out.println("gamelog updated");
			GameLog gameLog = (GameLog)o;
			ChatController.this.updateChat(gameLog.getGameChat());
		}
	};
}

