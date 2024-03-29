package shared.model.logging.chat;


public interface GameChatInterface 
{
	/**
	 * gets the message at a particular index in the game chat
	 * @param messageIndex the location of the message to return
	 * @return MessageInterface instance if index exists
	 */
	public MessageInterface getMessage(int messageIndex);
	
	/**
	 * gets the number of messages in the game chat history
	 * @return int number of messages sent
	 */
	public int getSize();
	
	/**
	 * adds a message to the chat record
	 * @param newMessage the message to be added
	 */
	public void addMessage(MessageInterface newMessage);
}
