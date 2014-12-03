package server.command;

import server.persistence.CommandDTO;
import shared.serialization.parameters.SendChatParameters;

/**
 * A Command object that adds a new chat message to a given game
 * @author Kevin
 */
public class SendChat extends CatanCommand {

	private String chatMessage = null;
	/**
	 * Initializes the SendChat object with the data necessary to add a new chat message to a given game 
	 * @param parameters An object containing the index of the player sending the message, and the content of their message
	 * @param game_id
	 */
	public SendChat(SendChatParameters parameters, int game_id){
		
		this.gameId = game_id;
		
		this.playerIndex = parameters.getPlayerIndex();
		this.chatMessage = parameters.getContent();
		
		this.dto = new CommandDTO(parameters, "SendChatParameters", game_id);
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#sendChat(int, int, String) sendChat}
	 */
	@Override
	public void execute() {
		
		success = facadeInstance.sendChat(gameId, playerIndex, chatMessage);
	}
}
