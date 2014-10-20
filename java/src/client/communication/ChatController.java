package client.communication;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.logging.GameLog;
import client.logging.chat.GameChatInterface;


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
		
	}

	private Observer chatObserver = new Observer() {
		
		@Override
		public void update(Observable o, Object arg) {
			GameLog gameLog = (GameLog)o;
			ChatController.this.updateChat(gameLog.getGameChat());
		}
	};
}

