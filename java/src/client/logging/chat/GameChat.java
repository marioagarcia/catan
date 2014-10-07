package client.logging.chat;

import java.util.ArrayList;

public class GameChat implements GameChatInterface {

	ArrayList<MessageInterface> messageList;
	
	@Override
	public MessageInterface getMessage(int messageIndex)
			throws MessageDoesNotExistException {
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
