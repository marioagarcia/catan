package client.communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import shared.model.GameModel;
import shared.model.logging.chat.GameChatInterface;
import shared.model.logging.chat.MessageDoesNotExistException;
import shared.model.logging.chat.MessageInterface;
import shared.model.player.Player;
import shared.model.player.Players;
import client.base.*;
import client.facade.ClientModelFacade;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController {

	public ChatController(IChatView view) {
		
		super(view);
		
		ClientModelFacade.getInstance(null).addObserver(chatObserver);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		ClientModelFacade.getInstance(null).sendChat(message);
	}

	private void updateChat(GameModel game_model) {
		GameChatInterface game_chat = game_model.getGameLog().getGameChat();
		Players players = game_model.getPlayers();
		Map<String, CatanColor> colorByIdMap = new HashMap<String, CatanColor>();
		List<LogEntry> entries = new ArrayList<LogEntry>();
		
		for(Player player : players.getPlayerList()) {
			colorByIdMap.put(player.getName(), player.getColor());
		}
		
		for (int message_index = 0; message_index < game_chat.getSize(); message_index++) {
			MessageInterface message = null;
			
			message = game_chat.getMessage(message_index);
			
			LogEntry log_entry = new LogEntry(colorByIdMap.get(message.getPlayerName()), message.getMessageContent());
			
			entries.add(log_entry);
		}
		
		getView().setEntries(entries);
		
	}

	private Observer chatObserver = new Observer() {
		
		@Override
		public void update(Observable o, Object arg) {
			ChatController.this.updateChat((GameModel)o);
		}
	};
}

