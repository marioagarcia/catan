package client.model.logging.chat;

import shared.serialization.interfaces.SerializerMessageInterface;

public class Message implements MessageInterface, SerializerMessageInterface {

		String content;
		String playerName;
	
		public Message(String msg, String src){
			content = msg;
			playerName = src;
		}

		@Override
		public void setMessageContent(String content) {
			this.content = content;
		}

		@Override
		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}

		@Override
		public void setMessage(String playerName, String message) {
			this.playerName = playerName;
			this.content = message;
		}

		@Override
		public String getMessageContent() {
			return content;
		}

		@Override
		public String getPlayerName() {
			return playerName;
		}
}
