package server.command;

import server.facade.ServerModelFacade;
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
	}
	
	/**
	 * Calls {@link server.facade.ServerModelFacade#sendChat(int, int, String) sendChat}
	 */
	@Override
	public void execute() {
		
		success = ServerModelFacade.getInstance().sendChat(gameId, playerIndex, chatMessage);
		
		if (success){
			
			ServerModelFacade.getInstance().persistCommand(this, "SendChat", gameId);
		}
	}
}
