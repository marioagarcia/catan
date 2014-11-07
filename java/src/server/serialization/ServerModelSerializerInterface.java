package server.serialization;

import java.util.ArrayList;

import shared.model.GameInfo;
import shared.model.manager.GameCommands;
import shared.serialization.parameters.*;

public interface ServerModelSerializerInterface {
	
	public CredentialsParameters deserializeCredentials(String jsonString);
	
	public String serializeGamesList(ArrayList<GameInfo> gamesList);
	
	public CreateGameRequestParameters deserializeCreateGameRequest(String jsonString);
	
	public String serializeGameInfo(GameInfo gameInfo);
	
	public SaveGameRequestParameters deserializeSaveGameRequest(String jsonString);
	
	public LoadGameRequestParameters deserializeLoadGameRequest(String jsonString);
	
	public ArrayList<MasterParameterInterface> deserializePostGameCommands(String jsonString);
	
	public String serializeGetGameCommands(GameCommands gameCommands);
	
	public AIRequestParameters deserializeAIRequest(String jsonString);
	
	public String serializeGetListAI(String[] aiList);
	
	public SendChatParameters deserializeSendChat(String jsonString);
	
	public RollNumberParameters deserializeRollNumber(String jsonString);
	
	public RobPlayerParameters deserializeRobPlayer(String jsonString);
	
	public FinishTurnParameters deserializeFinishTurn(String jsonString);
	
	public BuyDevCardParameters deserializeBuyDevCard(String jsonString);
	
	public YearOfPlentyParameters deserializeYearOfPlenty(String jsonString);

}
