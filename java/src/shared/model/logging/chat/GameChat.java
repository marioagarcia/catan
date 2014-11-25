package shared.model.logging.chat;

import java.io.Serializable;
import java.util.ArrayList;

public class GameChat implements GameChatInterface, Serializable {

	private static final long serialVersionUID = -7264301618621282302L;
	
	ArrayList<MessageInterface> messageList;
	
	public GameChat() {
		messageList = new ArrayList<MessageInterface>();
	}
	
	@Override
	public MessageInterface getMessage(int messageIndex){
		return messageList.get(messageIndex);
	}

	@Override
	public int getSize() {
		return messageList.size();
	}

	@Override
	public void addMessage(MessageInterface newMessage) {
		messageList.add(newMessage);
	}

}
