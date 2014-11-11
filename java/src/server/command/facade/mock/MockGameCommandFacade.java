package server.command.facade.mock;

import java.util.ArrayList;

import server.command.facade.GameCommandFacadeInterface;
import shared.model.manager.GameData;
import shared.serialization.parameters.MasterParameterInterface;

public class MockGameCommandFacade implements GameCommandFacadeInterface{

	@Override
	public GameData getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameData reset() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getListAI() {
		// TODO Auto-generated method stub
		return null;
	}


}
