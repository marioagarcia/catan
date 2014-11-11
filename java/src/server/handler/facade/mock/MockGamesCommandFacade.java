package server.handler.facade.mock;

import server.handler.facade.GamesCommandFacadeInterface;
import shared.model.GameInfo;
import shared.model.manager.GameList;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.JoinGameRequestParameters;
import shared.serialization.parameters.LoadGameRequestParameters;
import shared.serialization.parameters.SaveGameRequestParameters;

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
	public Boolean joinGame(JoinGameRequestParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean saveGame(SaveGameRequestParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean loadGame(LoadGameRequestParameters params) {
		// TODO Auto-generated method stub
		return null;
	}


}
