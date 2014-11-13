package server.command.facade.real;

import server.command.CreateGame;
import server.command.JoinGame;
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
		return ServerModelFacade.getInstance().getGameList();
	}

	@Override
	public GameInfo createGame(CreateGameRequestParameters params) {
		CreateGame command = new CreateGame(params);
		command.execute();
		if(command.wasSuccessful()){
			// TODO Get and return a GameInfo
			return null;
		}else{
			return null;
		}
	}

	@Override
	public Boolean joinGame(JoinGameParameters params) {
		JoinGame command = new JoinGame(params, -1);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public Boolean saveGame(SaveGameParameters params) {
		SaveGame command = new SaveGame(params);
		command.execute();
		return command.wasSuccessful();
	}

	@Override
	public GameData loadGame(LoadGameRequestParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

}
