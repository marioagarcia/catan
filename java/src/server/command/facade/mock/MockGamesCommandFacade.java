package server.command.facade.mock;

import server.command.facade.GamesCommandFacadeInterface;
import shared.model.GameInfo;
import shared.model.manager.GameData;
import shared.model.manager.GameList;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.JoinGameParameters;
import shared.serialization.parameters.LoadGameRequestParameters;
import shared.serialization.parameters.SaveGameParameters;

public class MockGamesCommandFacade implements GamesCommandFacadeInterface{

	@Override
	public GameList getGamesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameInfo createGame(CreateGameRequestParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean joinGame(JoinGameParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean saveGame(SaveGameParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData loadGame(LoadGameRequestParameters params) {
		// TODO Auto-generated method stub
		return null;
	}


}
