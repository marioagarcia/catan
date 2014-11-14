package server.command.facade.mock;

import server.command.facade.GamesCommandFacadeInterface;
import shared.model.GameInfo;
import shared.model.manager.GameData;
import shared.model.manager.GameList;
import shared.model.mock.MockGameInfo;
import shared.model.mock.MockGameList;
import shared.serialization.parameters.CreateGameRequestParameters;
import shared.serialization.parameters.JoinGameParameters;
import shared.serialization.parameters.LoadGameRequestParameters;
import shared.serialization.parameters.SaveGameParameters;

public class MockGamesCommandFacade implements GamesCommandFacadeInterface{

	@Override
	public GameList getGamesList() {
		 return new MockGameList();
	}

	@Override
	public GameInfo createGame(CreateGameRequestParameters params) {
		return new MockGameInfo(params.getName());
	}

	@Override
	public Boolean joinGame(JoinGameParameters params, int playerId) {
		return true;
	}

	@Override
	public Boolean saveGame(SaveGameParameters params) {
		return true;
	}

	@Override
	public GameData loadGame(LoadGameRequestParameters params) {
		// TODO Auto-generated method stub
		return null;
	}


}
