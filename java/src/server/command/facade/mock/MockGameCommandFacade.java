package server.command.facade.mock;

import java.util.ArrayList;

import server.command.facade.GameCommandFacadeInterface;
import shared.model.manager.GameData;
import shared.model.mock.MockAIList;
import shared.model.mock.MockGameModel;
import shared.serialization.parameters.MasterParameterInterface;

public class MockGameCommandFacade implements GameCommandFacadeInterface{

	@Override
	public GameData getModel() {
		System.out.println("Trying to get Model");
		return new MockGameModel().getGameData();
	}

	@Override
	public GameData reset() {
		return new MockGameModel().getGameData();
	}

	@Override
	public GameData postCommands(ArrayList<MasterParameterInterface> commandList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MasterParameterInterface> getCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean AddAI(String aiType) {
		return true;
	}

	@Override
	public String[] getListAI() {
		MockAIList mockAIList = new MockAIList();
		return mockAIList.getListAI();
	}


}
