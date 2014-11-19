package server.command.facade.real;

import server.command.CreateGame;
import server.command.JoinGame;
import server.command.LoadGame;
import server.command.SaveGame;
import server.command.facade.GamesCommandFacadeInterface;
import server.facade.ServerModelFacade;
import shared.model.GameInfo;
import shared.model.manager.GameData;
import shared.model.manager.GameList;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.JoinGameParameters;
import shared.serialization.parameters.LoadGameRequestParameters;
import shared.serialization.parameters.SaveGameParameters;

public class GamesCommandFacade implements GamesCommandFacadeInterface{

	@Override
	public GameList getGamesList() {
//System.out.println("\t\tGames Command Facade: Get Games List");
		return ServerModelFacade.getInstance().getGameList();
	}

	@Override
	public GameInfo createGame(CreateGameRequestParameters params) {
System.out.println("\t\tGames Command Facade: Create Game");
		CreateGame command = new CreateGame(params);
		command.execute();
		if(command.wasSuccessful()){
			return ServerModelFacade.getInstance().getGameInfo(params.getName());
		}else{
			return null;
		}
	}

	@Override
	public Boolean joinGame(JoinGameParameters params, int playerId) {
System.out.println("\t\tGames Command Facade: Join Game");
		JoinGame command = new JoinGame(params, playerId);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public Boolean saveGame(SaveGameParameters params) {
System.out.println("\t\tGames Command Facade: Save Game");
		SaveGame command = new SaveGame(params);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public GameData loadGame(LoadGameRequestParameters params) {
System.out.println("\t\tGames Command Facade: Load Game");
		LoadGame command = new LoadGame(params);
		command.execute();
		return command.getGameData();
	}

}
