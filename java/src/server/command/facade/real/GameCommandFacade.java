package server.command.facade.real;

import java.util.ArrayList;

import server.command.ResetGame;
import server.command.facade.GameCommandFacadeInterface;
import server.facade.ServerModelFacade;
import shared.model.manager.GameData;
import shared.serialization.parameters.MasterParameterInterface;

public class GameCommandFacade implements GameCommandFacadeInterface{
	
	@Override
	public GameData getModel(int gameId) {
System.out.println("\t\tGame Command Facade: Get model");
		return ServerModelFacade.getInstance().getGameModel(gameId);
	}

	@Override
	public GameData reset(int gameId) {
System.out.println("\t\tGame Command Facade: Reset");
		ResetGame command = new ResetGame();
		command.execute();
		if(command.wasSuccessful()){
			return ServerModelFacade.getInstance().getGameModel(gameId);
		}else{
			return null;
		}
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
		return ServerModelFacade.getInstance().getAIList().getAiList();
	}

}
